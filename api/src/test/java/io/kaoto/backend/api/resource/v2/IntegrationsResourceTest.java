package io.kaoto.backend.api.resource.v2;

import io.kaoto.backend.api.resource.model.FlowsWrapper;
import io.kaoto.backend.api.resource.v1.model.Integration;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.MapAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestHTTPEndpoint(IntegrationsResource.class)
class IntegrationsResourceTest {


    @ParameterizedTest
    @ValueSource(strings = {"amq-amq.yaml", "amq-amq-multi.json"})
    void amqAmq(String file) throws Exception {
        String yaml = loadFileFromResources("../../resource/" + file);

        String mediaType = file.contains("json") ? MediaType.APPLICATION_JSON : "text/yaml";

        var res = given()
                .when()
                .contentType(mediaType)
                .body(yaml)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        if (file.contains("json")) {
            res = given()
                    .when()
                    .contentType("text/yaml")
                    .body(res.extract().body().asString())
                    .post("?dsl=Camel Route")
                    .then()
                    .statusCode(Response.Status.OK.getStatusCode());
        }

        var flows = res.extract().body().as(FlowsWrapper.class).flows();
        assertThat(flows).isNotEmpty();
        var flow = flows.get(0);
        assertThat(flow.getSteps()).hasSize(2);
        Step amq1 = flow.getSteps().get(0);
        Step amq2 = flow.getSteps().get(1);
        assertThat(amq1.getType()).isEqualTo("START");
        assertThat(amq1.getName()).isEqualTo("activemq");
        assertThat(amq2.getType()).isEqualTo("MIDDLE");
        assertThat(amq1.getName()).isEqualTo("activemq");

        assertThat(amq1.getParameters()).anyMatch(parameter ->
                parameter.getId().equalsIgnoreCase("destinationType")
                        && parameter.getValue().toString().equalsIgnoreCase("queue"));
        assertThat(amq1.getParameters()).anyMatch(parameter ->
                parameter.getId().equalsIgnoreCase("destinationName")
                        && parameter.getValue().toString().equalsIgnoreCase("myQueueName"));
        assertThat(amq2.getParameters()).anyMatch(parameter ->
                parameter.getId().equalsIgnoreCase("destinationType")
                        && parameter.getValue().toString().equalsIgnoreCase("topic"));
        assertThat(amq2.getParameters()).anyMatch(parameter ->
                parameter.getId().equalsIgnoreCase("destinationName")
                        && parameter.getValue().toString().equalsIgnoreCase("anotherOne"));
    }

    @Test
    void newBranchStep() throws Exception {
        String json = loadFileFromResources("../../resource/new-branch-step-multi.json");

        var res = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void scriptStep() throws Exception {
        String json = loadFileFromResources("../../resource/script-multi.json");

        var res = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        String yaml = res.extract().body().asString();

        res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var flows = res.extract().body().as(FlowsWrapper.class).flows();
        assertThat(flows).isNotEmpty();
        var flow = flows.get(0);
        assertThat(flow.getSteps()).hasSize(2);
        Step script = flow.getSteps().get(1);
        var groovy = script.getParameters().stream().filter(p -> "groovy".equals(p.getId())).findAny();
        assertThat(groovy).isPresent();
        assertThat(groovy.get().getValue()).isEqualTo("some groovy script");
        assertThat(script.getParameters()).extracting(Parameter::getValue)
                .as("Check that other scripts are null")
                .containsExactlyInAnyOrder(null, null, null, "some groovy script");
    }

    @Test
    void expressionObject() throws Exception {
        String yaml = loadFileFromResources("../expression-object.yaml");

        var res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        String json = res.extract().body().asString();

        res = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var yaml2 = res.extract().body().asString();
        List<Object> parsed = new Yaml().load(yaml2);

        ListAssert<Map> steps = assertThat(parsed.get(0)).asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("route").asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("from").asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("steps").asInstanceOf(InstanceOfAssertFactories.list(Map.class));
        steps.hasSize(20);

        var choiceExpression = getStepForAssertion(steps, "choice")
                .extractingByKey("when").asInstanceOf(InstanceOfAssertFactories.LIST)
                .first().asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("expression").asInstanceOf(InstanceOfAssertFactories.MAP);
        assertExpressionInStep(choiceExpression, "choice", "simple", "choice");

        // same structure for all of these
        // step#syntax#expresion
        for (String stepForTest : List.of(
                "delay#simple#delay",
                "dynamic-router#simple#dynamic-router-exp",
                "enrich#simple#enrich-exp",
                "filter#simple#filter-exp",
                "poll-enrich#simple#poll-enrich-exp",
                "recipient-list#simple#recipient-list-exp",
                "resequence#simple#resequence-exp",
                "routing-slip#simple#routing-slip-exp",
                "script#groovy#script-exp",
                "service-call#jsonpath#service-call-exp",
                "set-body#constant#set-body-exp",
                "set-header#jq#set-header-exp",
                "set-property#jq#set-property-exp",
                "sort#simple#sort-exp",
                "split#simple#split-exp",
                "throttle#simple#throttle-exp",
                "transform#jq#transform-exp",
                "validate#simple#validate-exp"
        )) {
            String[] parameters = stepForTest.split("#");
            var stepExpression = getStepForAssertion(steps, parameters[0])
                    .extractingByKey("expression").asInstanceOf(InstanceOfAssertFactories.MAP);
            assertExpressionInStep(stepExpression, parameters[0], parameters[1], parameters[2]);
        }

        var onWhenExpression = getStepForAssertion(steps, "do-try")
                .extractingByKey("do-catch").asInstanceOf(InstanceOfAssertFactories.list(Map.class))
                .filteredOn(step -> step.containsKey("on-when"))
                .hasSize(1).first().asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("on-when").asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("expression").asInstanceOf(InstanceOfAssertFactories.MAP);
        assertExpressionInStep(onWhenExpression, "on-when", "simple", "on-when");
    }

    /**
     * Return step element from steps collection according to step name
     */
    private MapAssert<Object, Object> getStepForAssertion(ListAssert<Map> steps, String stepName) {
        return steps.as("Steps list should contains one step with name " + stepName)
                .filteredOn(step -> step.containsKey(stepName)).hasSize(1)
                .first().asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey(stepName).asInstanceOf(InstanceOfAssertFactories.MAP);
    }

    // @formatter:off
    /**
     * @param stepExpression - expects expression element only, e.g.
     *  expression:
     *    simple:
     *      expression: choice
     *
     *  expects Map ["simple" -> Object]
     */
    // @formatter:on
    private void assertExpressionInStep(MapAssert<Object, Object> stepExpression, String stepName,
                                        String expectedSyntax, String expectedExpression) {
        stepExpression
                .as(String.format("Step %s should contain syntax %s", stepName, expectedSyntax))
                .containsKey(expectedSyntax)
                .extractingByKey(expectedSyntax).asInstanceOf(InstanceOfAssertFactories.MAP)
                .as(String.format("Step %s should not contains result-type", stepName))
                .doesNotContainKey("result-type")
                .as(String.format("Step %s should contain expression %s", stepName, expectedExpression))
                .containsKey("expression").extractingByKey("expression")
                .isEqualTo(expectedExpression);
    }

    @Test
    void kameletUri() throws Exception {
        String yaml = loadFileFromResources("../../resource/kamelet-uri.yaml");

        var res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        String json = res.extract().body().asString();

        res = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var yaml2 = res.extract().body().asString();
        List<Object> parsed = new Yaml().load(yaml2);

        assertThat(parsed.get(0)).asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("route").asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("from").asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("uri").asString()
                .isEqualTo("kamelet:telegram-source:test/");
    }

    @Test
    void uriLogStop() throws Exception {
        String yaml = loadFileFromResources("../../resource/uri-log-stop.yaml");

        var res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var flows = res.extract().body().as(FlowsWrapper.class).flows();
        assertThat(flows).isNotEmpty();
        var flow = flows.get(0);
        assertThat(flow.getSteps()).hasSize(4);

        var direct = flow.getSteps().get(0);
        assertThat(direct.getName()).isEqualTo("direct");
        assertThat(direct.getType()).isEqualTo("START");

        var filter = flow.getSteps().get(1);
        assertThat(filter.getName()).isEqualTo("filter");
        assertThat(filter.getBranches().get(0).getSteps()).hasSize(1);
        assertThat(filter.getBranches().get(0).getSteps().get(0).getType()).isEqualTo("MIDDLE");
        assertThat(filter.getBranches().get(0).getSteps().get(0).getName()).isEqualTo("log");

        var filter2 = flow.getSteps().get(2);
        assertThat(filter2.getName()).isEqualTo("filter");
        assertThat(filter2.getBranches().get(0).getSteps()).hasSize(1);
        // @FIXME a workaround for https://github.com/KaotoIO/kaoto-ui/issues/1587
        // Fix StopFlowStep once above is implemented
        assertThat(filter2.getBranches().get(0).getSteps().get(0).getType()).isEqualTo("MIDDLE");
        assertThat(filter2.getBranches().get(0).getSteps().get(0).getName()).isEqualTo("stop");

        var log2 = flow.getSteps().get(3);
        assertThat(log2.getName()).isEqualTo("log");
        assertThat(log2.getType()).isEqualTo("MIDDLE");
    }

    @Test
    void noFrom() throws Exception {
        String json = loadFileFromResources("../../resource/no-from-multi.json");

        var res = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        var yaml = res.extract().body().asString();
        List<Object> parsed = new Yaml().load(yaml);

        MapAssert<Object, Object> from = assertThat(parsed.get(0)).asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("route").asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("from").asInstanceOf(InstanceOfAssertFactories.MAP);

        from.extractingByKey("uri").isNull();
        from.extractingByKey("steps").asInstanceOf(InstanceOfAssertFactories.LIST)
                .hasSize(1)
                .first().asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("to").asInstanceOf(InstanceOfAssertFactories.MAP)
                .extractingByKey("uri")
                .isNotNull()
                .isEqualTo("log:");
    }

    @Test
    void beans() throws Exception {
        String yaml = loadFileFromResources("../../resource/kamelet2.yaml");

        var res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml)
                .post("?dsl=Kamelet")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        var flows = res.extract().body().as(FlowsWrapper.class);

        assertThat(flows).isNotNull();
        assertThat(flows.flows()).isNotEmpty();
        var flow = flows.flows().get(0);
        assertThat(flow).isNotNull();
        assertThat(flow.getSteps()).hasSize(2);

        assertThat(flows.metadata().get("beans"))
                .asInstanceOf(InstanceOfAssertFactories.LIST)
                .hasSize(1)
                .first()
                .asInstanceOf(InstanceOfAssertFactories.MAP)
                .hasSize(3)
                .containsEntry(KamelHelper.NAME, "connectionFactoryBean")
                .containsEntry("type", "#class:org.apache.qpid.jms.JmsConnectionFactory")
                .containsEntry("properties", Map.of(
                        "remoteURI", "{{remoteURI}}",
                        "secondProperty", "another"));
    }

    @Test
    void kameletMetadata() throws Exception {
        String yaml = loadFileFromResources("../../resource/kamelet2.yaml");

        var res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml)
                .post("?dsl=Kamelet")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        var flows = res.extract().body().as(FlowsWrapper.class);

        assertThat(flows.flows()).isNotEmpty();
        var flow = flows.flows().get(0);
        assertThat(flow).isNotNull();
        assertThat(flow.getSteps()).hasSize(2);
        assertThat(flow.getMetadata()).hasSize(6);
        assertThat(flow.getMetadata().get(KamelHelper.NAME)).asString().isEqualTo("jms-amqp-10-source");
        assertThat(flow.getMetadata().get("dependencies"))
                .asInstanceOf(InstanceOfAssertFactories.LIST)
                .hasSize(3)
                .containsExactlyInAnyOrder("camel:jms", "camel:amqp", "camel:kamelet");
        assertThat(flow.getMetadata().get("labels"))
                .asInstanceOf(InstanceOfAssertFactories.MAP)
                .hasSize(2)
                .containsEntry("camel.apache.org/kamelet.type", "source")
                .containsEntry("camel.apache.org/requires.runtime", "camel-k");
        assertThat(flow.getMetadata().get("annotations"))
                .asInstanceOf(InstanceOfAssertFactories.MAP)
                .hasSize(5)
                .containsEntry("camel.apache.org/kamelet.support.level", "Stable")
                .containsEntry("camel.apache.org/provider", "Apache Software Foundation")
                .containsEntry("camel.apache.org/catalog.version", "4.0.0-SNAPSHOT")
                .containsEntry("camel.apache.org/kamelet.group", "JMS")
                .containsEntry("camel.apache.org/kamelet.namespace", "Messaging");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Camel Route#route-multi.yaml", "KameletBinding#kamelet-binding.yaml",
            "Kamelet#eip.kamelet.yaml", "Camel Route#rest-dsl-multi.yaml", "Camel Route#empty-route.yaml",
            "Camel Route#route-with-beans.yaml", "Integration#integration.yaml",
            "Integration#integration-multiroute.yaml", "Kamelet#jms-amqp-10-source.kamelet.yaml",
            "Integration#integration-no-step.yaml", "Integration#integration-with-beans.yaml",
            "Kamelet#beans.kamelet.yaml", "Kamelet#aws-cloudtrail.yaml",
            "Kamelet#avro-serialize-action.kamelet.yaml", "Kamelet#google-bigquery-sink.kamelet.yaml",
            "Camel Route#amq-amq.yaml"})
    void roundTrip(String file) throws IOException {

        String[] parameters = file.split("#");

        var route = loadFileFromResources("../../resource/" + parameters[1]);

        //With no DSL parameter
        var res0 = given()
                .when()
                .contentType("text/yaml")
                .body(route)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        //Should be the same as with DSL parameter
        var res1 = given()
                .when()
                .contentType("text/yaml")
                .body(route)
                .post("?dsl=" + parameters[0])
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        assertThat(res0.extract().body().asString()).isEqualToNormalizingNewlines(res1.extract().body().asString());

        var flows = res1.extract().body().as(FlowsWrapper.class);

        for (var flow : flows.flows()) {
            assertThat(flow.getDsl()).isEqualTo(parameters[0]);
        }

        //Now let's try to recreate the source code
        var res2 = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flows)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        var sourceCode = res2.extract().body().asString();

        //This should be the same as the original source code
        Object originalYaml = new Yaml().load(route);
        Object sourceCodeYaml = new Yaml().load(sourceCode);
        assertThat(originalYaml).isEqualTo(sourceCodeYaml);
    }

    @Test
    void uniqueName() throws IOException {
        var route = loadFileFromResources("../../resource/route-repeated-names.yaml");

        //Should be the same as with DSL parameter
        var res1 = given()
                .when()
                .contentType("text/yaml")
                .body(route)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var flows = res1.extract().body().as(FlowsWrapper.class);

        assertThat(flows.flows())
                .extracting(Integration::getMetadata)
                .extracting(map -> map.get(KamelHelper.NAME))
                .doesNotHaveDuplicates()
                .allMatch(name -> Pattern.matches("([A-Za-z0-9])+", name.toString()));

        //Let's assign the same name to all flows
        var sameIdentifier = "sameIdentifier";
        flows.flows().forEach(i -> i.getMetadata().put(KamelHelper.NAME, sameIdentifier));

        //Now let's try to recreate the source code
        var res2 = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flows)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        var yaml = res2.extract().body().asString();

        Pattern pattern = Pattern.compile(" {2}id: (.+)(?: \n\r|\n|\r)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(yaml);
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group(0).trim()).isEqualTo("id: sameIdentifier");
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group(0).trim()).isNotEqualTo("id: sameIdentifier");
        assertThat(matcher.find()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Kamelet#eip.kamelet.yaml", "Integration#integration.yaml",
            "Camel Route#route-with-beans.yaml", "Camel Route#rest-dsl-multi.yaml",
            "KameletBinding#kamelet-binding.yaml", "Kamelet#kamelet2.yaml",
            "Integration#integration-multiroute.yaml", "Kamelet#jms-amqp-10-source.kamelet.yaml"})
    void changeNameAndDescription(String file) throws IOException {

        String[] parameters = file.split("#");

        var route = loadFileFromResources("../../resource/" + parameters[1]);

        //First, get the JSON object of the flow
        var res1 = given()
                .when()
                .contentType("text/yaml")
                .body(route)
                .post("?dsl=" + parameters[0])
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var flows = res1.extract().body().as(FlowsWrapper.class);
        assertThat(flows.flows()).isNotEmpty();
        var flow = flows.flows().get(0);
        var randomGeneratedName = "flow-" + System.currentTimeMillis();
        var randomGeneratedDesc = "Description " + System.currentTimeMillis();
        if ("Kamelet".equals(parameters[0]) || "KameletBinding".equals(parameters[0])) {
            randomGeneratedName += flows.metadata().get(KamelHelper.NAME);
            flows.metadata().put(KamelHelper.NAME, randomGeneratedName);
            flows.metadata().put(KamelHelper.DESCRIPTION, randomGeneratedDesc);
        } else {
            if (flow.getMetadata() != null) {
                randomGeneratedName += flow.getMetadata().get(KamelHelper.NAME);
            }
            if (flow.getMetadata() == null) {
                flow.setMetadata(new LinkedHashMap<>());
            }
            flow.getMetadata().put(KamelHelper.NAME, randomGeneratedName);
            flow.getMetadata().put(KamelHelper.DESCRIPTION, randomGeneratedDesc);
        }
        //Now let's try to recreate the source code
        var res2 = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flows)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        var sourceCode = res2.extract().body().asString();

        //Make sure it gets properly filled in the metadata from the source code
        var res3 = given()
                .when()
                .contentType("text/yaml")
                .body(sourceCode)
                .post("?dsl=" + parameters[0])
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        flows = res3.extract().body().as(FlowsWrapper.class);
        assertThat(flows.flows()).isNotEmpty();
        flow = flows.flows().get(0);
        assertThat(flow.getMetadata()).isNotNull();
        assertThat(sourceCode).contains("description: " + randomGeneratedDesc);

        switch (parameters[0]) {
            case "Kamelet", "Kamelet Binding" -> {
                assertThat(flows.metadata())
                        .containsEntry(KamelHelper.NAME, randomGeneratedName)
                        .containsEntry(KamelHelper.DESCRIPTION, randomGeneratedDesc);
                assertThat(sourceCode).contains("  name: " + randomGeneratedName);
            }
            case "Integration", "Camel Route" -> {
                assertThat(flow.getMetadata())
                        .containsEntry(KamelHelper.NAME, randomGeneratedName)
                        .containsEntry(KamelHelper.DESCRIPTION, randomGeneratedDesc);
                assertThat(sourceCode).contains("    id: " + randomGeneratedName);
            }
            default -> {
            }
        }
    }

    private String loadFileFromResources(String path) throws IOException {
        return new String(Objects.requireNonNull(this.getClass().getResourceAsStream(path), "File must exist")
                .readAllBytes(), StandardCharsets.UTF_8);
    }
}
