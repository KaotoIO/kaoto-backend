package io.zimara.backend.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class GetViewsTest {

    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/view")
                .then()
                .statusCode(200);
    }

}