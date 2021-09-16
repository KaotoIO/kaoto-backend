package io.zimara.backend.api.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.api.metadata.catalog.StepCatalog;
import io.zimara.backend.api.resource.request.DeploymentResourceYamlRequest;
import io.zimara.backend.api.service.step.parser.KameletBindingStepParserService;
import io.zimara.backend.api.service.step.parser.StepParserService;
import io.zimara.backend.api.service.viewdefinition.ViewDefinitionService;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
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
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(DeploymentResource.class)
class DeploymentResourceTest {

    private DeploymentResource stepResource;
    private StepCatalog stepCatalog;
    private StepParserService<Step> stepParser;
    private static String binding;
    private ViewDefinitionService viewDefinitionService;

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.stepCatalog = catalog;
    }

    @Inject
    public void setStepParser(
            final KameletBindingStepParserService stepParser) {
        this.stepParser = stepParser;
    }

    @Inject
    public void setViewDefinitionService(
            final ViewDefinitionService viewDefinitionService) {
        this.viewDefinitionService = viewDefinitionService;
    }

    @Inject
    public void setDeploymentResource(final DeploymentResource stepResource) {
        this.stepResource = stepResource;
    }

    @BeforeEach
    void ensureCatalog() {
        stepCatalog.waitForWarmUp().join();
    }

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        binding = Files.readString(
                Path.of(
                        DeploymentResourceTest.class.getResource(
                                "twitter-search-source-binding.yaml")
                                .toURI()));
    }

    @Test
    void testYaml() {

        List<Step> steps = stepParser.parse(binding);
        DeploymentResourceYamlRequest request =
                new DeploymentResourceYamlRequest();
        request.setName("twitter-search-source-binding");
        request.setSteps(steps.toArray(new KameletStep[0]));

        given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .post("yaml")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void testExceptions() {
        given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .post("yaml")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
    }
}
