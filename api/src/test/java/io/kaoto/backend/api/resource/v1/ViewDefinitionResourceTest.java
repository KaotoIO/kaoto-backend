package io.kaoto.backend.api.resource.v1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.kaoto.backend.model.view.ViewDefinition;
import io.kaoto.backend.camel.KamelHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.metadata.catalog.ViewDefinitionCatalog;
import io.kaoto.backend.api.resource.v1.model.Integration;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestHTTPEndpoint(ViewDefinitionResource.class)
class ViewDefinitionResourceTest {
    private static String route = "";

    private StepCatalog stepCatalog;

    private ViewDefinitionCatalog viewCatalog;

    @Inject
    public void setStepCatalog(final StepCatalog stepCatalog) {
        this.stepCatalog = stepCatalog;
    }

    @Inject
    public void setViewCatalog(final ViewDefinitionCatalog viewCatalog) {
        this.viewCatalog = viewCatalog;
    }

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        route = Files.readString(Path.of(ViewDefinitionResourceTest.class.getResource("../route.yaml").toURI()));
    }

    @BeforeEach
    void ensureCatalog() {
        stepCatalog.waitForWarmUp().join();
        viewCatalog.waitForWarmUp().join();
    }

    @Test
    void testViews() throws JsonProcessingException {
        List<Step> steps = new ArrayList<>();
        steps.add(stepCatalog.getReadOnlyCatalog().searchByID("kamelet:source-START"));
        steps.add(stepCatalog.getReadOnlyCatalog().searchByID("log-producer"));

        final var json = KamelHelper.JSON_MAPPER.writeValueAsString(steps);
        ViewDefinition[] viewDefinitions = given()
                .when().body(json)
                .contentType(MediaType.APPLICATION_JSON).post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(ViewDefinition[].class);
        assertThat(viewDefinitions)
                .isNotEmpty()
                .hasSize(1)
                .extracting(ViewDefinition::getType)
                .containsExactlyInAnyOrder("generic");
    }

    @Test
    void testInvalidType() {
        given()
                .when().body("Not Important").post()
                .then()
                .statusCode(Response.Status
                        .UNSUPPORTED_MEDIA_TYPE.getStatusCode());
    }


    @Test
    void testNestedSteps() throws JsonProcessingException {

        //Get the JSON steps from the YAML
        Integration integration  = given()
                .when()
                .contentType("text/yaml")
                .body(route)
                .post("/../integrations?dsl=Kamelet")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Integration.class);

        ViewDefinition[] viewDefinitions = given()
                .when().body(KamelHelper.JSON_MAPPER.writeValueAsString(integration.getSteps()))
                .contentType(MediaType.APPLICATION_JSON).post()
                .then()
                .log().body()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(ViewDefinition[].class);
        assertThat(viewDefinitions)
                .isNotEmpty()
                .hasSize(4)
                .extracting(ViewDefinition::getType)
                .containsExactlyInAnyOrder("step", "step", "generic", "step");
    }
}
