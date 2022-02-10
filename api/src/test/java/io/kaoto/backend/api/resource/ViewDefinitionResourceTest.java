package io.kaoto.backend.api.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.metadata.catalog.ViewDefinitionCatalog;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestHTTPEndpoint(ViewDefinitionResource.class)
class ViewDefinitionResourceTest {
    private static String binding = "";

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
        binding = Files.readString(Path.of(
                ViewDefinitionResourceTest.class.getResource(
                        "twitter-search-source-binding.yaml")
                        .toURI()));
    }

    @BeforeEach
    void ensureCatalog() {
        stepCatalog.waitForWarmUp().join();
        viewCatalog.waitForWarmUp().join();
    }

    @Test
    void testViews() throws JsonProcessingException {
        var res = given()
                .when().body(binding)
                .contentType("text/yaml").post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        res.body("views.size()", is(4),
                "views[0].type", is("generic"),
                "steps.size()", is(4));

        List<Step> steps = new LinkedList<Step>();
        steps.add(stepCatalog.getReadOnlyCatalog().searchStepByID(
                                "kamelet:source"));
        steps.add(stepCatalog.getReadOnlyCatalog().searchStepByID(
                                "log"));

        final var mapper = new ObjectMapper();
        final var json = mapper.writeValueAsString(steps);
        res = given()
                .when().body(json)
                .contentType(MediaType.APPLICATION_JSON).post()
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        res.body("views.size()", is(1),
                "views[0].type", is("generic"),
                "steps.size()", is(2));

    }

    @Test
    void testInvalidType() {
        given()
                .when().body("Not Important").post()
                .then()
                .statusCode(Response.Status
                        .UNSUPPORTED_MEDIA_TYPE.getStatusCode());
    }
}
