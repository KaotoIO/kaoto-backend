package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.resource.v1.model.Integration;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestHTTPEndpoint(IntegrationsResource.class)
abstract class IntegrationsResourceTestAbstract {

    protected abstract void waitForWarmUpCatalog();

    @BeforeEach
    void setupTest() {
        waitForWarmUpCatalog();
    }

    @Test
    void complexEIP() throws URISyntaxException, IOException {

        String yaml = Files.readString(Path.of(
                DeploymentsResourceTest.class.getResource(
                                "../eip.kamelet.yaml")
                        .toURI()));

        var res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml)
                .post("?dsl=Kamelet")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        String json = res.extract().body().asString();

        res = given()
                .when()
                .contentType("application/json")
                .body(json)
                .post("?dsl=Kamelet")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        assertThat(res.extract().body().asString()).isEqualToNormalizingNewlines(yaml);
    }

    @Test
    void amqAmq() throws Exception {
        String yaml = Files.readString(Path.of(
                DeploymentsResourceTest.class.getResource("../amq-amq.yaml").toURI()));

        var res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var flow = res.extract().body().as(Integration.class);
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
                .contentType("application/json")
                .body(json)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        assertThat(res.extract().body().asString()).isEqualToNormalizingNewlines(yaml);
    }

    @Test
    void amqAmqFromJson() throws Exception {
        String json = Files.readString(Path.of(
                DeploymentsResourceTest.class.getResource("../amq-amq.json").toURI()));

        var res = given()
                .when()
                .contentType("application/json")
                .body(json)
                .post("?dsl=Camel Route")
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

        var flow = res.extract().body().as(Integration.class);
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
        String json = Files.readString(Path.of(
                IntegrationsResourceTestAbstract.class.getResource(
                                "../new-branch-step.json")
                        .toURI()));
        var res = given()
                .when()
                .contentType("application/json")
                .body(json)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        Yaml yaml = new Yaml(
                new Constructor(KameletBinding.class),
                new KameletRepresenter());
        yaml.parse(new InputStreamReader(res.extract().body().asInputStream()));
    }

    @Test
    void scriptStep() throws Exception {
        String json = Files.readString(Path.of(
                IntegrationsResourceTestAbstract.class.getResource(
                                "../script.json")
                        .toURI()));
        var res = given()
                .when()
                .contentType("application/json")
                .body(json)
                .post("?dsl=Camel Route")
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

        var flow = res.extract().body().as(Integration.class);
        assertEquals(2, flow.getSteps().size());
        Step script = flow.getSteps().get(1);
        var groovy = script.getParameters().stream().filter(p -> "groovy".equals(p.getId())).findAny();
        assertTrue(groovy.isPresent());
        assertEquals("some groovy script", groovy.get().getValue());
    }

    @Test
    void expressionObject() throws Exception {
        String yaml = Files.readString(Path.of(
                IntegrationsResourceTestAbstract.class.getResource(
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
                .contentType("application/json")
                .body(json)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var yaml2 = res.extract().body().asString();
        List<Object> parsed = new Yaml().load(yaml2);
        List<Object> steps = (List<Object>) ((Map) ((Map) parsed.get(0)).get("from")).get("steps");
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
                IntegrationsResourceTestAbstract.class.getResource(
                                "../kamelet-uri.yaml")
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
                .contentType("application/json")
                .body(json)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var yaml2 = res.extract().body().asString();
        List<Object> parsed = new Yaml().load(yaml2);
        var uri = (String) ((Map) ((Map) parsed.get(0)).get("from")).get("uri");
        assertEquals("kamelet:telegram-source:test/", uri);
    }

    @Test
    void uriLogStop() throws Exception {
        String yaml = Files.readString(Path.of(
                IntegrationsResourceTestAbstract.class.getResource(
                                "../uri-log-stop.yaml")
                        .toURI()));
        var res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        var flow = res.extract().body().as(Integration.class);
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
                IntegrationsResourceTestAbstract.class.getResource(
                                "../no-from.json")
                        .toURI()));
        var res = given()
                .when()
                .contentType("application/json")
                .body(json)
                .post("?dsl=Camel Route")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        var yaml = res.extract().body().asString();
        List<Object> parsed = new Yaml().load(yaml);
        var from = (Map) ((Map) parsed.get(0)).get("from");
        var fromUri = (String) from.get("uri");
        assertNull(fromUri);
        var steps = (List<Object>) from.get("steps");
        assertEquals(1, steps.size());
        var to = (Map<String, Object>) ((Map<String, Object>) steps.get(0)).get("to");
        assertEquals("log:", to.get("uri"));
    }
}
