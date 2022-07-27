package io.kaoto.backend.api.resource.v0;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.resource.v0.request.DeploymentResourceYamlRequest;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.KubernetesTestServer;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


@QuarkusTest
@WithKubernetesTestServer
@TestHTTPEndpoint(IntegrationResource.class)
public class IntegrationResourceTest {

    private static String jsonRequest;
    private StepCatalog catalog;
    @KubernetesTestServer
    private KubernetesServer mockServer;

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        jsonRequest = Files.readString(Path.of(
                IntegrationResourceTest.class.getResource(
                                "../request.json")
                        .toURI()));
    }

    @BeforeEach
    public void before() {
        final Pod pod1 = new PodBuilder()
                .withNewMetadata()
                .withName("tralalala")
                .withNamespace("test")
                .and().build();

        // Set up Kubernetes so that our "pretend" pods are created
        if (mockServer.getClient()
                .pods()
                .inNamespace("test").list().getItems().isEmpty()) {
            mockServer.getClient().pods().create(pod1);
        }
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

        res = given()
                .when()
                .contentType("text/plain")
                .get("/{name}/logs?lines=1&namespace=test", request.getName())
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        assertFalse(res.extract().response().asString().isEmpty());

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
