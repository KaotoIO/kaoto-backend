package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestHTTPEndpoint(IntegrationsResource.class)
class IntegrationsResourceTest {

    @Inject
    public void setStepCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }
    private StepCatalog catalog;

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void thereAndBackAgain() throws URISyntaxException, IOException {

        String yaml = Files.readString(Path.of(
                DeploymentsResourceTest.class.getResource(
                                "../twitter-search-source-binding.yaml")
                        .toURI()));

       var res = given()
               .when()
               .contentType("text/yaml")
               .body(yaml)
               .post("?dsl=KameletBinding")
               .then()
               .statusCode(Response.Status.OK.getStatusCode());

       String json = res.extract().body().asString();

        res = given()
                .when()
                .contentType("application/json")
                .body(json)
                .post("?dsl=KameletBinding")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());


        String yaml2 = res.extract().body().asString();

        assertEquals(yaml, yaml2);

    }
}
