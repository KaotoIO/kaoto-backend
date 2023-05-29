package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.resource.v1.model.Capabilities;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestHTTPEndpoint(CapabilitiesResource.class)
class CapabilitiesResourceTest {


    @Test
    void get() {

        var res = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        Capabilities capabilities = res.extract().body().as(Capabilities.class);

        assertNotNull(capabilities);
        assertNotNull(capabilities.getDsls());

        assertEquals(4, capabilities.getDsls().size(), "There should be four DSLs.");

        assertTrue(capabilities.getDsls().stream().anyMatch(dsl ->
                String.valueOf(dsl.get("name")).equalsIgnoreCase("Camel Route")
                        &&  String.valueOf(dsl.get("supportsMultipleFlows")).equalsIgnoreCase("true")));

        assertTrue(capabilities.getDsls().stream().anyMatch(dsl ->
                String.valueOf(dsl.get("name")).equalsIgnoreCase("Integration")
                        &&  String.valueOf(dsl.get("supportsMultipleFlows")).equalsIgnoreCase("true")));

        assertTrue(capabilities.getDsls().stream().anyMatch(dsl ->
                String.valueOf(dsl.get("name")).equalsIgnoreCase("Kamelet")
                        &&  String.valueOf(dsl.get("supportsMultipleFlows")).equalsIgnoreCase("false")));

        assertTrue(capabilities.getDsls().stream().anyMatch(dsl ->
                String.valueOf(dsl.get("name")).equalsIgnoreCase("KameletBinding")
                        &&  String.valueOf(dsl.get("supportsMultipleFlows")).equalsIgnoreCase("false")));

        for(var dsl : capabilities.getDsls()) {
            assertTrue(dsl.containsKey("stepKinds") && dsl.get("stepKinds") != null);
            assertTrue(dsl.containsKey("output") && dsl.get("output") != null);
            assertTrue(dsl.containsKey("input") && dsl.get("input") != null);
            assertTrue(dsl.containsKey("deployable") && dsl.get("deployable") != null);
            assertTrue(dsl.containsKey("validationSchema") && dsl.get("validationSchema") != null);
            assertTrue(dsl.containsKey("description") && dsl.get("description") != null);
            assertTrue(dsl.containsKey("vocabulary") && dsl.get("vocabulary") != null);
            assertTrue(dsl.get("vocabulary") instanceof Map map && map.size() == 1
                    && String.valueOf(map.get("stepsName")).equals("Steps"));
        }
    }
}
