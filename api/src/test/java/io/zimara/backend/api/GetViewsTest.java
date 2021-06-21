package io.zimara.backend.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GetViewsTest {

    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/view")
          .then()
             .statusCode(200)
             .body(is("[{\"name\":\"test view\",\"steps\":[{\"icon\":\"icon.png\",\"name\":\"kamelet\",\"subType\":\"KAMELET\",\"type\":\"CONNECTOR\"}],\"type\":\"INTEGRATION\"}]"));
    }

}