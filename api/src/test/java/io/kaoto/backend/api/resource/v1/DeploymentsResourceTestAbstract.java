package io.kaoto.backend.api.resource.v1;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import io.kaoto.backend.model.deployment.camelroute.Integration;
import io.kaoto.backend.model.deployment.kamelet.Kamelet;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.kubernetes.client.KubernetesTestServer;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@WithKubernetesTestServer
@TestHTTPEndpoint(DeploymentsResource.class)
public abstract class DeploymentsResourceTestAbstract {

    @KubernetesTestServer
    KubernetesServer mockServer;

    private static final String DEFAULT_NAMESPACE = "default";
    private static final String OTHER_NAMESPACE = "other";
    private static final String BINDING_NAME = "integration-4";

    protected abstract void waitForWarmUpCatalog();

    @BeforeEach
    void setupTest() {
        waitForWarmUpCatalog();
    }

    @AfterEach
    void cleanUp() {
        mockServer.getClient().resources(KameletBinding.class).inAnyNamespace().list().getItems()
                .forEach(kameletBinding -> mockServer.getClient().resource(kameletBinding).delete());
        mockServer.getClient().resources(Kamelet.class).inAnyNamespace().list().getItems()
                .forEach(kamelet -> mockServer.getClient().resource(kamelet).delete());
        mockServer.getClient().resources(Integration.class).inAnyNamespace().list().getItems()
                .forEach(integration -> mockServer.getClient().resource(integration).delete());
    }

    @Test
    void getAllTest() {
        List<String> res = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().jsonPath().getList("type", String.class);
        assertThat(res).isEmpty();

        createTestKameletBinding(DEFAULT_NAMESPACE);
        createTestKamelet(DEFAULT_NAMESPACE);
        createTestIntegration(DEFAULT_NAMESPACE);
        res = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().jsonPath().getList("type", String.class);
        assertThat(res)
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder("KameletBinding", "Kamelet", "Integration");
    }

    @Test
    void getAllNamespaceQueryTest() {
        List<String> res = given()
                .queryParam("namespace", OTHER_NAMESPACE)
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().jsonPath().getList("type", String.class);
        assertThat(res).isEmpty();

        createTestKameletBinding(DEFAULT_NAMESPACE);
        res = given()
                .queryParam("namespace", OTHER_NAMESPACE)
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().jsonPath().getList("type", String.class);
        assertThat(res).isEmpty();

        createTestKameletBinding(OTHER_NAMESPACE);
        createTestKamelet(OTHER_NAMESPACE);
        createTestIntegration(OTHER_NAMESPACE);
        res = given()
                .queryParam("namespace", OTHER_NAMESPACE)
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().jsonPath().getList("type", String.class);
        assertThat(res)
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder("KameletBinding", "Kamelet", "Integration");
    }

    @Test
    void getExceptionsTest() {
        String response = given()
                .queryParam("type", "KameletBinding")
                .when()
                .get("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode())
                .extract().body().asString();
        assertThat(response).isEqualTo(
                String.format("Error processing deployment: Resource with name %s not found.", BINDING_NAME));
        response = given()
                .when()
                .get("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .extract().body().asString();
        assertThat(response).isEqualTo("Error processing deployment: resource.type: must not be null");
        createTestKameletBinding(DEFAULT_NAMESPACE);
        response = given()
                .when()
                .get("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .extract().body().asString();
        assertThat(response).isEqualTo("Error processing deployment: resource.type: must not be null");
    }

    @Test
    void getTest() {
        given()
                .queryParam("type", "KameletBinding")
                .when()
                .get("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
        createTestKameletBinding(DEFAULT_NAMESPACE);
        String resource = given()
                .queryParam("type", "KameletBinding")
                .when()
                .get("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().asString();
        assertThat(resource)
                .contains("kind: KameletBinding")
                .contains("name: twitter-search-source");
    }

    @Test
    void getNamespaceQueryTest() {
        createTestKameletBinding(OTHER_NAMESPACE);
        String differentTestKb = createDifferentTestKameletBinding(OTHER_NAMESPACE);
        given()
                .queryParam("namespace", OTHER_NAMESPACE)
                .queryParam("type", "Kamelet")
                .when()
                .get("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
        given()
                .queryParam("namespace", DEFAULT_NAMESPACE)
                .queryParam("type", "KameletBinding")
                .when()
                .get("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
        given()
                .queryParam("namespace", DEFAULT_NAMESPACE)
                .queryParam("type", "KameletBinding")
                .when()
                .get("/{name}", differentTestKb)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
        String resource = given()
                .queryParam("namespace", OTHER_NAMESPACE)
                .queryParam("type", "KameletBinding")
                .when()
                .get("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().response().asString();
        assertThat(resource)
                .contains("kind: KameletBinding")
                .contains("name: twitter-search-source");

        String kameletName = createTestKamelet(DEFAULT_NAMESPACE);
        resource = given()
                .queryParam("type", "Kamelet")
                .when()
                .get("/{name}", kameletName)
                .then()
                .contentType("text/yaml")
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().response().asString();
        assertThat(resource).contains("kind: Kamelet");
    }

    @Test
    void startKameletTest() {
        assertThat(mockServer.getClient().resources(Kamelet.class).inNamespace(DEFAULT_NAMESPACE).list()
                .getItems()).isEmpty();
        given()
                .when().body(getTestKameletBinding("../eip.kamelet.yaml"))
                .contentType("text/yaml")
                .post("/{name}", "Not needed anymore")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        assertThat(mockServer.getClient().resources(Kamelet.class).inNamespace(DEFAULT_NAMESPACE).list()
                .getItems())
                .extracting(Kamelet::getMetadata)
                .extracting(ObjectMeta::getName)
                .as("Check that Kamelet was created")
                .containsExactly("eip-action");
    }

    @Test
    void startKameletBindingsTest() {
        assertThat(mockServer.getClient().resources(KameletBinding.class).inNamespace(DEFAULT_NAMESPACE).list()
                .getItems()).isEmpty();
        given()
                .when().body(getTestKameletBinding("../twitter-search-source-binding.yaml"))
                .contentType("text/yaml")
                .post("/{name}", "Not needed anymore")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        assertThat(mockServer.getClient().resources(KameletBinding.class).inNamespace(DEFAULT_NAMESPACE).list()
                .getItems())
                .extracting(KameletBinding::getMetadata)
                .extracting(ObjectMeta::getName)
                .as("Check that Kamelet Bindings was created")
                .containsExactly(BINDING_NAME);
    }

    @Test
    void startIntegrationTest() {
        assertThat(mockServer.getClient().resources(Integration.class).inNamespace(DEFAULT_NAMESPACE).list()
                .getItems()).isEmpty();
        given()
                .when().body(getTestKameletBinding("../integration.yaml"))
                .contentType("text/yaml")
                .post("/{name}", "Not needed anymore")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        assertThat(mockServer.getClient().resources(Integration.class).inNamespace(DEFAULT_NAMESPACE).list()
                .getItems())
                .extracting(Integration::getMetadata)
                .extracting(ObjectMeta::getName)
                .as("Check that Integration was created")
                .containsExactly("integration");
    }

    @Test
    void startInvalidTest() {
        String response = given()
                .when().body(getTestKameletBinding("../route-with-beans.yaml"))
                .contentType("text/yaml")
                .post("/{name}", "Not needed anymore")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .extract().response().asString();
        assertThat(response).isEqualTo(
                "Error processing deployment: Couldn't understand the yaml sent. Check the syntax and try again.");
    }

    @Test
    void startNamespaceQueryTest() {
        assertThat(mockServer.getClient().resources(KameletBinding.class).inNamespace(DEFAULT_NAMESPACE).list()
                .getItems()).isEmpty();
        assertThat(mockServer.getClient().resources(KameletBinding.class).inNamespace(OTHER_NAMESPACE).list()
                .getItems()).isEmpty();
        given()
                .queryParam("namespace", OTHER_NAMESPACE)
                .when().body(getTestKameletBinding("../twitter-search-source-binding.yaml"))
                .contentType("text/yaml")
                .post("/{name}", "Not needed anymore")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        assertThat(mockServer.getClient().resources(KameletBinding.class).inNamespace(DEFAULT_NAMESPACE).list()
                .getItems()).isEmpty();
        assertThat(mockServer.getClient().resources(KameletBinding.class).inNamespace(OTHER_NAMESPACE).list()
                .getItems())
                .extracting(KameletBinding::getMetadata)
                .extracting(ObjectMeta::getName)
                .as("Check that kamelet binding was created")
                .containsExactly(BINDING_NAME);
    }
    @Test
    void deleteExceptionsTest() {
        String response = given()
                .queryParam("type", "KameletBinding")
                .when()
                .get("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode())
                .extract().body().asString();
        assertThat(response).isEqualTo(
                String.format("Error processing deployment: Resource with name %s not found.", BINDING_NAME));
        response = given()
                .when()
                .delete("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .extract().body().asString();
        assertThat(response).isEqualTo("Error processing deployment: stop.type: must not be null");
    }


    @Test
    void deleteTest() {
        createTestKameletBinding(DEFAULT_NAMESPACE);
        createTestKameletBinding(OTHER_NAMESPACE);
        String differentKbName = createDifferentTestKameletBinding(DEFAULT_NAMESPACE);
        String kameletName = createTestKamelet(DEFAULT_NAMESPACE);

        Boolean result = given()
                .queryParam("type", "KameletBinding")
                .when()
                .delete("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Boolean.class);
        assertThat(result).isTrue();
        assertThat(
                mockServer.getClient().resources(KameletBinding.class).inNamespace(DEFAULT_NAMESPACE).list()
                        .getItems())
                .extracting(KameletBinding::getMetadata)
                .extracting(ObjectMeta::getName)
                .as("Check that kamelet binding was deleted")
                .doesNotContain(BINDING_NAME)
                .as("Check that other kamelet binding was not deleted")
                .containsExactly(differentKbName);
        assertThat(
                mockServer.getClient().resources(Kamelet.class).inNamespace(DEFAULT_NAMESPACE).list()
                        .getItems())
                .extracting(Kamelet::getMetadata)
                .extracting(ObjectMeta::getName)
                .as("Check that kamelet was not deleted")
                .containsExactly(kameletName);
        assertThat(
                mockServer.getClient().resources(KameletBinding.class).inNamespace(OTHER_NAMESPACE).list()
                        .getItems())
                .extracting(KameletBinding::getMetadata)
                .extracting(ObjectMeta::getName)
                .as("Check that kamelet binding with the same name in different namespace was not deleted")
                .containsExactly(BINDING_NAME);
    }

    @Test
    void deleteNamespaceAndTypeQueryTest() {
        createTestKameletBinding(DEFAULT_NAMESPACE);
        createTestKameletBinding(OTHER_NAMESPACE);
        String differentKbName = createDifferentTestKameletBinding(OTHER_NAMESPACE);
        String kameletName = createTestKamelet(OTHER_NAMESPACE);

        Boolean result = given()
                .queryParam("namespace", OTHER_NAMESPACE)
                .queryParam("type", "KameletBinding")
                .when()
                .delete("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Boolean.class);
        assertThat(result).isTrue();

        assertThat(
                mockServer.getClient().resources(KameletBinding.class).inNamespace(OTHER_NAMESPACE).list()
                        .getItems())
                .extracting(KameletBinding::getMetadata)
                .extracting(ObjectMeta::getName)
                .as("Check that kamelet binding was deleted from specified namespace")
                .doesNotContain(BINDING_NAME)
                .as("Check that other kamelet binding was not deleted from specified namespace")
                .containsExactly(differentKbName);
        assertThat(
                mockServer.getClient().resources(Kamelet.class).inNamespace(OTHER_NAMESPACE).list()
                        .getItems())
                .extracting(Kamelet::getMetadata)
                .extracting(ObjectMeta::getName)
                .as("Check that kamelet was not deleted from specified namespace")
                .containsExactly(kameletName);
        assertThat(
                mockServer.getClient().resources(KameletBinding.class).inNamespace(DEFAULT_NAMESPACE).list()
                        .getItems())
                .extracting(KameletBinding::getMetadata)
                .extracting(ObjectMeta::getName)
                .as("Check that kamelet binding with the same name in default namespace was not deleted")
                .containsExactly(BINDING_NAME);
        given()
                .queryParam("namespace", OTHER_NAMESPACE)
                .queryParam("type", "KameletBinding")
                .when()
                .delete("/{name}", BINDING_NAME)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    private void createTestKameletBinding(String namespace) {
        mockServer.getClient().resources(KameletBinding.class).inNamespace(namespace)
                .load(DeploymentsResourceTestAbstract.class.getResourceAsStream(
                        "../twitter-search-source-binding.yaml")).create();
    }

    private String getTestKameletBinding(String pathToFileInResources) {
        try {
            return Files.readString(Path.of(Objects.requireNonNull(
                    DeploymentsResourceTestAbstract.class.getResource(pathToFileInResources)).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException("Problem with processing kamelet binding file: " + e.getMessage());
        }
    }

    private String createDifferentTestKameletBinding(String namespace) {
        mockServer.getClient().resources(KameletBinding.class).inNamespace(namespace)
                .load(DeploymentsResourceTestAbstract.class.getResourceAsStream(
                        "../camel-conector-example.yaml")).create();
        return "camel-conector-example";
    }

    private String createTestKamelet(String namespace) {
        mockServer.getClient().resources(Kamelet.class).inNamespace(namespace)
                .load(DeploymentsResourceTestAbstract.class.getResourceAsStream(
                        "../eip.kamelet.yaml")).create();
        return "eip-action";
    }

    private String createTestIntegration(String namespace) {
        mockServer.getClient().resources(Integration.class).inNamespace(namespace)
                .load(DeploymentsResourceTestAbstract.class.getResourceAsStream(
                        "../integration.yaml")).create();
        return "integration";
    }
}
