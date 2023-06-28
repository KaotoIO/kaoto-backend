package io.kaoto.backend.api.resource.v2;

import io.kaoto.backend.api.resource.model.FlowsWrapper;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestHTTPEndpoint(IntegrationsResource.class)
class IntegrationsResourceTest {
    @Test
    void amqAmq() throws Exception {
        String yaml = Files.readString(Path.of(
                IntegrationsResourceTest.class.getResource("../../resource/amq-amq.yaml").toURI()));

        var res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var flows = res.extract().body().as(FlowsWrapper.class).flows();
        assertTrue(!flows.isEmpty());
        var flow = flows.get(0);
        assertEquals(2, flow.getSteps().size());
        Step amq1 = flow.getSteps().get(0);
        Step amq2 = flow.getSteps().get(1);
        assertEquals("START", amq1.getType());
        assertEquals("activemq", amq1.getName());
        assertEquals("activemq", amq2.getName());
        assertEquals("MIDDLE", amq2.getType());
        assertTrue(amq1.getParameters().stream()
                .anyMatch(parameter -> parameter.getId().equalsIgnoreCase("destinationType")
                        && parameter.getValue().toString().equalsIgnoreCase("queue")));
        assertTrue(amq1.getParameters().stream()
                .anyMatch(parameter -> parameter.getId().equalsIgnoreCase("destinationName")
                        && parameter.getValue().toString().equalsIgnoreCase("myQueueName")));
        assertTrue(amq2.getParameters().stream()
                .anyMatch(parameter -> parameter.getId().equalsIgnoreCase("destinationType")
                        && parameter.getValue().toString().equalsIgnoreCase("topic")));
        assertTrue(amq2.getParameters().stream()
                .anyMatch(parameter -> parameter.getId().equalsIgnoreCase("destinationName")
                        && parameter.getValue().toString().equalsIgnoreCase("anotherOne")));


        String json = res.extract().body().asString();

        res = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        assertThat(res.extract().body().asString()).isEqualToNormalizingNewlines(yaml);
    }

    @Test
    void amqAmqFromJson() throws Exception {
        String json = Files.readString(Path.of(
                IntegrationsResourceTest.class.getResource("../../resource/amq-amq-multi.json").toURI()));

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
        assertTrue(!flows.isEmpty());
        var flow = flows.get(0);
        assertEquals(2, flow.getSteps().size());
        Step amq1 = flow.getSteps().get(0);
        Step amq2 = flow.getSteps().get(1);
        assertEquals("START", amq1.getType());
        assertEquals("activemq", amq1.getName());
        assertEquals("activemq", amq2.getName());
        assertEquals("MIDDLE", amq2.getType());

        assertTrue(amq1.getParameters().stream()
                .anyMatch(parameter -> parameter.getId().equalsIgnoreCase("destinationType")
                        && parameter.getValue().toString().equalsIgnoreCase("topic")));
        assertTrue(amq1.getParameters().stream()
                .anyMatch(parameter -> parameter.getId().equalsIgnoreCase("destinationName")
                        && parameter.getValue() == null));
        assertTrue(amq2.getParameters().stream()
                .anyMatch(parameter -> parameter.getId().equalsIgnoreCase("destinationType")
                        && parameter.getValue().toString().equalsIgnoreCase("queue")));
        assertTrue(amq2.getParameters().stream()
                .anyMatch(parameter -> parameter.getId().equalsIgnoreCase("destinationName")
                        && parameter.getValue().toString().equalsIgnoreCase("sdfg")));
    }

    @Test
    void newBranchStep() throws Exception {
        String json =
                Files.readString(Path.of(
                        IntegrationsResourceTest.class.getResource("../../resource/new-branch-step-multi.json")
                                .toURI()));
        var res = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        Yaml yaml = new Yaml(new Constructor(KameletBinding.class, new LoaderOptions()), new KameletRepresenter());
        yaml.parse(new InputStreamReader(res.extract().body().asInputStream()));
    }

    @Test
    void scriptStep() throws Exception {
        String json = Files.readString(Path.of(
                IntegrationsResourceTest.class.getResource("../../resource/script-multi.json").toURI()));
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
        assertTrue(!flows.isEmpty());
        var flow = flows.get(0);
        assertEquals(2, flow.getSteps().size());
        Step script = flow.getSteps().get(1);
        var groovy = script.getParameters().stream().filter(p -> "groovy".equals(p.getId())).findAny();
        assertTrue(groovy.isPresent());
        assertEquals("some groovy script", groovy.get().getValue());
    }

    @Test
    void expressionObject() throws Exception {
        String yaml = Files.readString(Path.of(
                IntegrationsResourceTest.class.getResource(
                                "../expression-object.yaml")
                        .toURI()));
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
        List<Object> steps = (List<Object>) ((Map) ((Map) ((Map) parsed.get(0)).get("route")).get("from")).get("steps");
        assertEquals(20, steps.size());
        var choice = (Map<String, Object>) ((Map<String, Object>) steps.get(0)).get("choice");
        var when0 = (Map<String, Object>) ((List<Object>) choice.get("when")).get(0);
        assertExpression("choice", "simple", when0);
        var delay = (Map<String, Object>) ((Map<String, Object>) steps.get(1)).get("delay");
        assertExpression("delay", "simple", delay);
        var drouter = (Map<String, Object>) ((Map<String, Object>) steps.get(2)).get("dynamic-router");
        assertExpression("dynamic-router", "simple", drouter);
        var enrich = (Map<String, Object>) ((Map<String, Object>) steps.get(3)).get("enrich");
        assertExpression("enrich", "simple", enrich);
        var filter = (Map<String, Object>) ((Map<String, Object>) steps.get(4)).get("filter");
        assertExpression("filter", "simple", filter);
        var penrich = (Map<String, Object>) ((Map<String, Object>) steps.get(5)).get("poll-enrich");
        assertExpression("poll-enrich", "simple", penrich);
        var rlist = (Map<String, Object>) ((Map<String, Object>) steps.get(6)).get("recipient-list");
        assertExpression("recipient-list", "simple", rlist);
        var resequence = (Map<String, Object>) ((Map<String, Object>) steps.get(7)).get("resequence");
        assertExpression("resequence", "simple", resequence);
        var rslip = (Map<String, Object>) ((Map<String, Object>) steps.get(8)).get("routing-slip");
        assertExpression("routing-slip", "simple", rslip);
        var script = (Map<String, Object>) ((Map<String, Object>) steps.get(9)).get("script");
        assertExpression("script", "groovy", script);
        var scall = (Map<String, Object>) ((Map<String, Object>) steps.get(10)).get("service-call");
        assertExpression("service-call", "jsonpath", scall);
        var sbody = (Map<String, Object>) ((Map<String, Object>) steps.get(11)).get("set-body");
        assertExpression("set-body", "constant", sbody);
        var sheader = (Map<String, Object>) ((Map<String, Object>) steps.get(12)).get("set-header");
        assertExpression("set-header", "jq", sheader);
        var sprop = (Map<String, Object>) ((Map<String, Object>) steps.get(13)).get("set-property");
        assertExpression("set-property", "jq", sprop);
        var sort = (Map<String, Object>) ((Map<String, Object>) steps.get(14)).get("sort");
        assertExpression("sort", "simple", sort);
        var split = (Map<String, Object>) ((Map<String, Object>) steps.get(15)).get("split");
        assertExpression("split", "simple", split);
        var throttle = (Map<String, Object>) ((Map<String, Object>) steps.get(16)).get("throttle");
        assertExpression("throttle", "simple", throttle);
        var transform = (Map<String, Object>) ((Map<String, Object>) steps.get(17)).get("transform");
        assertExpression("transform", "jq", transform);
        var validate = (Map<String, Object>) ((Map<String, Object>) steps.get(18)).get("validate");
        assertExpression("validate", "simple", validate);
        var dotry = (Map<String, Object>) ((Map<String, Object>) steps.get(19)).get("do-try");
        var docatch0 = (Map<String, Object>) ((List<Object>) dotry.get("do-catch")).get(0);
        var onwhen = (Map<String, Object>) docatch0.get("on-when");
        assertExpression("on-when", "simple", onwhen);
    }

    private void assertExpression(String name, String syntax, Map<String, Object> step) {
        var nested = (Map<String, Object>) ((Map<String, Object>) step.get("expression")).get(syntax);
        assertEquals(name, nested.get("expression"));
        assertNull(nested.get("result-type"));
    }

    @Test
    void kameletUri() throws Exception {
        String yaml = Files.readString(Path.of(
                IntegrationsResourceTest.class.getResource("../../resource/kamelet-uri.yaml").toURI()));
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
        var uri = (String) ((Map) ((Map) ((Map) parsed.get(0)).get("route")).get("from")).get("uri");
        assertEquals("kamelet:telegram-source:test/", uri);
    }

    @Test
    void uriLogStop() throws Exception {
        String yaml = Files.readString(Path.of(
                IntegrationsResourceTest.class.getResource("../../resource/uri-log-stop.yaml").toURI()));
        var res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var flows = res.extract().body().as(FlowsWrapper.class).flows();
        assertTrue(!flows.isEmpty());
        var flow = flows.get(0);
        var filter = flow.getSteps().get(1);
        var log = filter.getBranches().get(0).getSteps().get(0);
        assertEquals("MIDDLE", log.getType());
        var filter2 = flow.getSteps().get(2);
        var stop = filter2.getBranches().get(0).getSteps().get(0);
        assertEquals("stop", stop.getName());
        // @FIXME a workaround for https://github.com/KaotoIO/kaoto-ui/issues/1587
        // Fix StopFlowStep once above is implemented
        assertEquals("MIDDLE", stop.getType());
        var log2 = flow.getSteps().get(3);
        assertEquals("MIDDLE", log2.getType());
    }

    @Test
    void noFrom() throws Exception {
        String json = Files.readString(Path.of(
                IntegrationsResourceTest.class.getResource("../../resource/no-from-multi.json").toURI()));
        var res = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        var yaml = res.extract().body().asString();
        List<Object> parsed = new Yaml().load(yaml);
        var from = (Map) ((Map) ((Map) parsed.get(0)).get("route")).get("from");
        var fromUri = (String) from.get("uri");
        assertNull(fromUri);
        var steps = (List<Object>) from.get("steps");
        assertEquals(1, steps.size());
        var to = (Map<String, Object>) ((Map<String, Object>) steps.get(0)).get("to");
        assertEquals("log:", to.get("uri"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Camel Route#route-multi.yaml", "KameletBinding#kamelet-binding-multi.yaml",
            "Kamelet#eip.kamelet.yaml", "Kamelet#kamelet-multi.yaml", "Camel Route#rest-dsl-multi.yaml",
            "Camel Route#route-with-beans.yaml", "Integration#integration.yaml"})
    void roundTrip(String file) throws IOException {

        String[] parameters = file.split("#");

        var route = new String(this.getClass().getResourceAsStream("../../resource/" + parameters[1]).readAllBytes(),
                StandardCharsets.UTF_8);

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

        assertEquals(res0.extract().body().asString(), res1.extract().body().asString());

        var flows = res1.extract().body().as(FlowsWrapper.class);

        for (var flow : flows.flows()) {
            assertEquals(parameters[0], flow.getDsl());
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
        assertThat(route).isEqualToNormalizingNewlines(sourceCode);
    }

    @Test
    void uniqueName() throws IOException {
        var route = new String(this.getClass()
                        .getResourceAsStream("../../resource/route-repeated-names.yaml").readAllBytes(),
                        StandardCharsets.UTF_8);

        //Should be the same as with DSL parameter
        var res1 = given()
                .when()
                .contentType("text/yaml")
                .body(route)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var flows = res1.extract().body().as(FlowsWrapper.class);
        var ids = flows.flows().stream().collect(
                Collectors.groupingBy(i -> i.getMetadata().get("name"), Collectors.counting()))
                .entrySet();
        assertEquals(flows.flows().size(), ids.size());
        assertTrue(ids.stream().allMatch(id -> id.getValue() == 1l));
        assertTrue(ids.stream().allMatch(id ->Pattern.matches( "([A-Za-z0-9])+", id.getKey().toString())));

        //Let's assign the same name to all flows
        var sameIdentifier = "sameIdentifier";
        flows.flows().forEach(i -> i.getMetadata().put("name", sameIdentifier));

        //Now let's try to recreate the source code
        var res2 = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flows)
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        var yaml = res2.extract().body().asString();

        Pattern pattern = Pattern.compile("  id: (.+)(?: \n\r|\n|\r)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(yaml);
        assertTrue(matcher.find());
        assertEquals("id: sameIdentifier", matcher.group(0).trim());
        assertTrue(matcher.find());
        assertNotEquals("id: sameIdentifier", matcher.group(0).trim());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Kamelet#eip.kamelet.yaml", "Integration#integration.yaml",
            "Camel Route#route-with-beans.yaml", "Camel Route#rest-dsl-multi.yaml",
            "KameletBinding#kamelet-binding.yaml"})
    void changeNameAndDescription(String file) throws IOException {

        String[] parameters = file.split("#");

        var route = new String(this.getClass().getResourceAsStream("../../resource/" + parameters[1]).readAllBytes(),
                StandardCharsets.UTF_8);

        //First, get the JSON object of the flow
        var res1 = given()
                .when()
                .contentType("text/yaml")
                .body(route)
                .post("?dsl=" + parameters[0])
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var flows = res1.extract().body().as(FlowsWrapper.class);
        assertTrue(!flows.flows().isEmpty());
        var flow = flows.flows().get(0);
        var randomGeneratedName = "flow-" + System.currentTimeMillis();
        if (flow.getMetadata() != null) {
            randomGeneratedName += flow.getMetadata().get("name");
        }
        var randomGeneratedDesc = "Description " + System.currentTimeMillis();
        if (flow.getMetadata() == null) {
            flow.setMetadata(new LinkedHashMap<>());
        }
        flow.getMetadata().put("name", randomGeneratedName);
        flow.getMetadata().put("description", randomGeneratedDesc);

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
        assertTrue(!flows.flows().isEmpty());
        flow = flows.flows().get(0);
        assertNotNull(flow.getMetadata());
        assertEquals(randomGeneratedName, flow.getMetadata().get("name"));
        assertEquals(randomGeneratedDesc, flow.getMetadata().get("description"));
        assertTrue(sourceCode.contains("    description: " + randomGeneratedDesc));

        switch (parameters[0]) {
            case "Kamelet":
            case "Kamelet Binding":
            case "Integration":
                assertTrue(sourceCode.contains("  name: " + randomGeneratedName));
                break;
            case "Camel Route":
                assertTrue(sourceCode.contains("    id: " + randomGeneratedName));
                break;
            default:
                break;
        }
    }

}
