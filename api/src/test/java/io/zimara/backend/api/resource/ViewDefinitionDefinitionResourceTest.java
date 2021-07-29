package io.zimara.backend.api.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.api.metadata.catalog.StepCatalog;
import io.zimara.backend.api.metadata.catalog.ViewDefinitionCatalog;
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
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestHTTPEndpoint(ViewDefinitionResource.class)
class ViewDefinitionDefinitionResourceTest {
    private static String binding = "";

    @Inject
    StepCatalog stepCatalog;

    @Inject
    ViewDefinitionCatalog viewCatalog;

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        binding = Files.readString(Path.of(ViewDefinitionDefinitionResourceTest.class.getResource("twitter-search-source-binding.yaml").toURI()));
    }

    @BeforeEach
    void ensureCatalog() {
        stepCatalog.waitForWarmUp().join();
        viewCatalog.waitForWarmUp().join();
    }

    @Test
    void testViews() {
        given()
                .when().queryParam("yaml", binding).contentType("text/yaml").post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("rows.size()", is(1),
                        "[0].name", is("testview"),
                        "[0].type", is("integrationview"),
                        "[0].steps.size()", is(2));
    }

    @Test
    void testInvalidType() {
        given()
                .when().queryParam("yaml", "Not Important").post()
                .then()
                .statusCode(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode());
    }

    @Test
    void testExceptions() {
        given()
                .when()
                .queryParam("yaml", "kind: KameletBinding : wrong invalid content")
                .contentType("text/yaml")
                .post()
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
    }

}