package io.zimara.backend.api.resource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.api.Catalog;
import io.zimara.backend.model.View;
import io.zimara.backend.model.view.IntegrationView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestHTTPEndpoint(ViewResource.class)
class ViewResourceTest {
    private static String binding = "";

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        binding = Files.readString(Path.of(ViewResourceTest.class.getResource("twitter-search-source-binding.yaml").toURI()));

        Catalog.waitForWarmUp().join();
    }

    @Test
    void testViews() {
        given()
                .when().queryParam("yaml", binding).get()
                .then()
                .statusCode(200)
                .body("rows.size()", is(1),
                        "[0].name", is("twitter-search-source-binding"),
                        "[0].type", is("INTEGRATION"),
                        "[0].steps.size()", is(2));
    }

}