package io.kaoto.backend.api.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.resource.request.DeploymentResourceYamlRequest;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@QuarkusTest
@WithKubernetesTestServer
@TestHTTPEndpoint(IntegrationResource.class)
class IntegrationResourceTest {

    private static String jsonRequest;
    private StepCatalog catalog;

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        jsonRequest = Files.readString(Path.of(
                IntegrationResourceTest.class.getResource(
                                "request.json")
                        .toURI()));
    }

    @Test
    void customResourcesDefinition() {

        var res = given()
                .when().body(jsonRequest)
                .contentType("application/json")
                .post("/customResource?type=KameletBinding")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        assertNotNull(res.extract().response().asString());

    }

    @Test
    void integrations() throws JsonProcessingException {

        catalog.waitForWarmUp().join();

        var res = given()
                .when()
                .contentType("application/json")
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        assertEquals("[]", res.extract().response().asString());

        DeploymentResourceYamlRequest request =
                new DeploymentResourceYamlRequest();

        request.setName("tralalala");
        request.setSteps(
                new Step[] {
                        catalog.getReadOnlyCatalog().searchStepByID(
                                "kamelet:source"),
                        catalog.getReadOnlyCatalog().searchStepByID(
                                "log")
                });

        ObjectMapper objectMapper = new ObjectMapper();

        final var integration =
                objectMapper.writeValueAsString(request);

        given()
                .when().body(integration)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        given()
                .when()
                .contentType("application/json")
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        given()
                .when()
                .contentType("application/json")
                .delete("/" + request.getName())
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        res = given()
                .when()
                .contentType("application/json")
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        assertEquals("[]", res.extract().response().asString());
    }

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }
}
