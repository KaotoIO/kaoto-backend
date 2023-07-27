package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class StepResourceTest extends StepResourceTestAbstract {

    private StepCatalog catalog;

    @Inject
    public void setStepCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Override
    protected void waitForWarmUpCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void allStepsFromBackend() {
        Step[] steps = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);

        assertThat(steps).isNotNull().isNotEmpty()
                .as("Check that API returns all steps from backend")
                .hasSize(catalog.getReadOnlyCatalog().getAll().size());
    }
}
