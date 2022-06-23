package io.kaoto.backend.api.resource.v1;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(CapabilitiesResource.class)
class CapabilitiesResourceTest {


    @Test
    void get() {

        given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }
}
