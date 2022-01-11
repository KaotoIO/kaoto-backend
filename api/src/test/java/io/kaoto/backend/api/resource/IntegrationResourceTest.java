package io.kaoto.backend.api.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;


@QuarkusTest
@TestHTTPEndpoint(IntegrationResource.class)
class IntegrationResourceTest {

    private static String jsonRequest;

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        jsonRequest = Files.readString(Path.of(
                ViewDefinitionResourceTest.class.getResource(
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
}
