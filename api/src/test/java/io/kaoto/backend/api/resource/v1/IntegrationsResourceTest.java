package io.kaoto.backend.api.resource.v1;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import io.kaoto.backend.model.step.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.resource.v1.model.Integration;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.api.service.language.LanguageService;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(IntegrationsResource.class)
class IntegrationsResourceTest {

    private StepCatalog catalog;
    private LanguageService languageService;
    @Inject
    public void setStepCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Inject
    public void setLanguageService(final LanguageService languageService) {
        this.languageService = languageService;
    }

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void thereAndBackAgain() throws URISyntaxException, IOException {

        final var alldsl = languageService.getAll();
        ObjectMapper mapper = new ObjectMapper();

        String yaml1 = Files.readString(Path.of(
                DeploymentsResourceTest.class.getResource(
                                "../twitter-search-source-binding.yaml")
                        .toURI()));

        var res = given()
                .when()
                .contentType("application/json")
                .body(Collections.emptyList())
                .post("/dsls")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        final var dsllist = mapper.readValue(res.extract().body().asString(), List.class);
        assertEquals(alldsl.size(), dsllist.size());
        assertTrue(alldsl.stream().allMatch(l -> dsllist.contains(l.get("name"))));

        //It will return a valid value even if we are useless users with the DSL
        given()
                .when()
                .contentType("text/yaml")
                .body(yaml1)
                .post("?dsl=SomethingWrong")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        res = given()
                .when()
                .contentType("text/yaml")
                .body(yaml1)
                .post("?dsl=KameletBinding")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        String json = res.extract().body().asString();
        Integration integration = mapper.readValue(json, Integration.class);

        res = given()
                .when()
                .contentType("application/json")
                .body(Collections.emptyList())
                .post("/dsls")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        final var dsllist2 = mapper.readValue(res.extract().body().asString(), List.class);
        assertEquals(alldsl.size(), dsllist2.size());
        assertTrue(alldsl.stream().allMatch(l -> dsllist2.contains(l.get("name"))));

        res = given()
                .when()
                .contentType("application/json")
                .body(integration.getSteps())
                .post("/dsls")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        var dsls = mapper.readValue(res.extract().body().asString(), List.class);
        assertEquals(1, dsls.size());
        assertTrue(dsls.contains("KameletBinding"));

        res = given()
                .when()
                .contentType("application/json")
                .body(json)
                .post("?dsl=KameletBinding")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        String yaml2 = res.extract().body().asString();

        Yaml yaml = new Yaml(
                new Constructor(KameletBinding.class),
                new KameletRepresenter());
        KameletBinding res1 = yaml.load(yaml1);
        KameletBinding res2 = yaml.load(yaml2);

        assertEquals(res1, res2);

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
                IntegrationsResourceTest.class.getResource(
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
                IntegrationsResourceTest.class.getResource(
                                "../script.json")
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
}
