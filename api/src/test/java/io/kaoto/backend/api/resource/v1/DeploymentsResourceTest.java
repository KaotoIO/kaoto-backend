package io.kaoto.backend.api.resource.v1;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.KubernetesTestServer;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@QuarkusTest
@WithKubernetesTestServer
@TestHTTPEndpoint(DeploymentsResource.class)
class DeploymentsResourceTest {

    private StepCatalog catalog;
    private static final String NAME = "integration-4";

    @KubernetesTestServer
    private KubernetesServer mockServer;

    @BeforeEach
    public void before() {
        final Pod pod1 = new PodBuilder()
                .withNewMetadata()
                .withName(NAME)
                .withNamespace("test")
                .and().build();

        mockServer.getClient().pods().create(pod1);
    }

    @Test
    void test() throws URISyntaxException, IOException {

        catalog.waitForWarmUp().join();

        var res = given()
                .when()
                .contentType("application/json")
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        assertEquals("[]", res.extract().response().asString());


        String yamlBinding = Files.readString(Path.of(
                DeploymentsResourceTest.class.getResource(
                                "../twitter-search-source-binding.yaml")
                        .toURI()));

        given()
                .when()
                .get("/{name}", NAME)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());

        given()
                .when()
                .contentType("application/json")
                .delete("/{name}", NAME)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());

        given()
                .when().body(yamlBinding)
                .contentType("text/yaml")
                .post("/{name}", "Updated integration")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        res = given()
                .when()
                .contentType("application/json")
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        assertNotEquals("[]", res.extract().response().asString());

        res = given()
                .when()
                .get("/{name}", NAME)
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        var yaml = res.extract().response().asString();
        assertNotNull(yaml);
        assertTrue(yaml.contains("kind: KameletBinding"));
        assertTrue(yaml.contains("name: twitter-search-source"));
        assertTrue(yaml.contains("name: aws-translate-action"));

        res = given()
                .when()
                .contentType("text/plain")
                .get("/{name}/logs?lines=1&namespace=test", NAME)
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        assertFalse(res.extract().response().asString().isEmpty());

        res = given()
                .when()
                .contentType("application/json")
                .delete("/{name}", NAME)
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        assertTrue(Boolean.valueOf(res.extract().response().asString()));

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
