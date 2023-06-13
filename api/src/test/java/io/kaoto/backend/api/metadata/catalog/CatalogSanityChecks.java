package io.kaoto.backend.api.metadata.catalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.SoftAssertions;

import jakarta.inject.Inject;

import java.util.Collection;
import java.util.Set;

import io.kaoto.backend.api.resource.v1.StepResource;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

/**
 * Execute manually with `-Dtest=CatalogSanityChecks` to check catalog metadata
 */
@QuarkusTest
@TestHTTPEndpoint(StepResource.class)
public class CatalogSanityChecks {

    public static final String TITLE_REGEX = "[A-Z]\\w+.*";
    private StepResource stepResource;
    private StepCatalog catalog;

    @Inject
    public void setStepCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Inject
    public void setStepResource(final StepResource stepResource) {
        this.stepResource = stepResource;
    }

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }


    @Test
    void parameterTitleTest() {
        final Collection<Step> allSteps = stepResource.all(null, null, null, null, null, null, null);
        SoftAssertions sa = new SoftAssertions();
        for (Step step : allSteps) {
            for (Parameter<?> parameter : step.getParameters()) {
                sa.assertThat(parameter.getTitle())
                    .as("Title for %s.%s parameter should not be null", step.getId(), parameter.getId())
                    .isNotNull();
                if (parameter.getTitle() != null) {
                    sa.assertThat(parameter.getTitle())
                        .describedAs("Title for %s.%s parameter should have a human format",
                            step.getId(),
                            parameter.getId())
                        .matches(TITLE_REGEX);
                }
            }
        }
        sa.assertAll();
    }

    @Test
    void stepTitleTest() {
        final Set<String> ignoredNames = Set.of("gRPC");
        final Collection<Step> allSteps = stepResource.all(null, null, null, null, null, null, null);
        SoftAssertions sa = new SoftAssertions();
        for (Step step : allSteps) {
            if (ignoredNames.contains(step.getTitle())) {
                //ignore specified titles
                continue;
            }
            sa.assertThat(step.getTitle())
                .describedAs("Step with id %s should have human readable name", step.getId())
                .matches(TITLE_REGEX);
        }
        sa.assertAll();
    }

}
