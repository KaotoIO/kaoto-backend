package io.kaoto.backend.api.resource.v1;

import static org.junit.Assert.assertFalse;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperties;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import org.awaitility.Awaitility;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ValidatableResponse;

@QuarkusTest
@TestHTTPEndpoint(DeploymentsResource.class)
@EnabledIfSystemProperties({
        @EnabledIfSystemProperty(
                named = "quarkus.kubernetes-client.master-url",
                matches = ".*"),
        @EnabledIfSystemProperty(
                named = "quarkus.kubernetes-client.client-cert-data",
                matches = ".*"),
        @EnabledIfSystemProperty(
                named = "quarkus.kubernetes-client.client-key-data",
                matches = ".*"),
        @EnabledIfSystemProperty(
                named = "quarkus.kubernetes-client.trust-certs",
                matches = "true")
})
class DeploymentsResourceITCase {

    private StepCatalog catalog;
    //  name in timer-to-log-request.json
    private static final String INTEGRATION_NAME = "tralalala";

    public KubernetesClient getKubernetesClient() {
        return kubernetesClient;
    }

    @Inject
    private KubernetesClient kubernetesClient;

    @Test
    void integrations() throws IOException, URISyntaxException {
        kubernetesClient.resources(KameletBinding.class)
                .inNamespace("default")
                .delete();

        catalog.waitForWarmUp().join();

        String integration = Files.readString(Path.of(
                DeploymentsResourceITCase.class.getResource(
                                "../timer-to-log-binding.yaml")
                        .toURI()));

        var res = given()
                .when()
                .contentType("application/json")
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        given()
                .when().body(integration)
                .contentType("text/yaml")
                .post("/{name}", "Updated integration")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        given()
                .when()
                .contentType("application/json")
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        Awaitility.await().until(() -> kubernetesClient.pods()
                .inNamespace("default").list().getItems().size() == 2);

        String fullPodName = kubernetesClient.pods()
                .inNamespace("default").list().getItems().stream()
                .filter(p -> p.getMetadata()
                    .getName()
                        .contains(DeploymentsResourceITCase.INTEGRATION_NAME))
                .map(p -> p.getMetadata().getName())
                .findFirst().orElseThrow();

        kubernetesClient.pods().inNamespace("default").withName(fullPodName)
                .waitUntilReady(10, TimeUnit.SECONDS);

        res = getLogs();
        assertFalse(res.extract().response().asString().isEmpty());
        Awaitility.await().until(() -> getLogs()
                .extract()
                .response()
                .asString()
                .contains("Hello Kaoto"));

        given()
                .when()
                .contentType("application/json")
                .delete("/" + DeploymentsResourceITCase.INTEGRATION_NAME)
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        res = given()
                .when()
                .contentType("application/json")
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        assertFalse(res
                .extract()
                .response()
                .asString()
                .contains(DeploymentsResourceITCase.INTEGRATION_NAME));
    }

    private ValidatableResponse getLogs() {
        return given()
                .when()
                .contentType("text/plain")
                .get("/{name}/logs?lines=20",
                        DeploymentsResourceITCase.INTEGRATION_NAME)
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }
}
