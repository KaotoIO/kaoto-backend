package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.resource.v1.model.Capabilities;
import io.kaoto.backend.camel.service.deployment.generator.camelroute.CamelRouteDeploymentGeneratorService;
import io.kaoto.backend.camel.service.dsl.kamelet.KameletBindingDSLSpecification;
import io.kaoto.backend.camel.service.dsl.kamelet.KameletDSLSpecification;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

import static io.kaoto.backend.api.service.language.LanguagesSpecificationChecker.checkAllLanguagesSpecifications;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;


@QuarkusTest
@TestHTTPEndpoint(CapabilitiesResource.class)
class CapabilitiesResourceTest {

    @Test
    void get() {
        var capabilities = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Capabilities.class);

        assertThat(capabilities).isNotNull();
        checkAllLanguagesSpecifications(capabilities.getDsls());
    }

    @Test
    void getVersion() {
        String response = given()
                .when()
                .get("/version")
                .then()
                .contentType(MediaType.TEXT_PLAIN)
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().asString();
        assertThat(response).matches("^\\d*\\.\\d*\\.\\d*(-.*)?$");
    }

    @Test
    void getNamespace() {
        given()
                .when()
                .get("/namespace")
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .statusCode(Response.Status.OK.getStatusCode())
                .body("namespace", is("default"));
    }

    @ParameterizedTest
    @MethodSource("provideDslSchemaForTest")
    void getValidationSchema(String dsl, Class<?> reourceClass, String file) throws IOException {
        String schemaToValidate =
                new String(Objects.requireNonNull(reourceClass.getResourceAsStream(file)).readAllBytes());

        String response = given()
                .when()
                .get(String.format("/%s/schema/", dsl))
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().asString();
        assertThat(response).isEqualToNormalizingNewlines(schemaToValidate);
    }

    private static Stream<Arguments> provideDslSchemaForTest() {
        return Stream.of(
                Arguments.of("Integration", CamelRouteDeploymentGeneratorService.class, "integration.json"),
                Arguments.of("Camel Route", CamelRouteDeploymentGeneratorService.class, "camel-yaml-dsl.json"),
                Arguments.of("Kamelet", KameletDSLSpecification.class, "kamelet.json"),
                Arguments.of("KameletBinding", KameletBindingDSLSpecification.class, "kameletbinding.json")
        );
    }
}
