package io.kaoto.backend.api.service.step.parser.syndesis;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@QuarkusTest
class SyndesisStepParserServiceTest {

    private SyndesisStepParserService service;
    private StepCatalog catalog;

    @Inject
    public void setService(
            final SyndesisStepParserService service) {
        this.service = service;
    }

    @Inject
    public void setStepCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void deepParseModel1() {
        try {
            String json = Files.readString(Path.of(
                    SyndesisStepParserServiceTest.class.getResource(
                            "model-1.json").toURI()));

            StepParserService.ParseResult<Step> result =
                    service.deepParse(json);
            assertNotNull(result);
            for (var s : result.getSteps()) {
                System.out.println("step: " + s);
            }
            for (var s : result.getSteps()) {
                assertNotNull(s);
            }
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    void deepParseModel2() {
        try {
            String json = Files.readString(Path.of(
                    SyndesisStepParserServiceTest.class.getResource(
                            "model-2.json").toURI()));

            StepParserService.ParseResult<Step> result =
                    service.deepParse(json);
            assertNotNull(result);
            for (var s : result.getSteps()) {
                System.out.println("step: " + s);
            }
            for (var s : result.getSteps()) {
                assertNotNull(s);
            }
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    void deepParseModel3() {
        try {
            String json = Files.readString(Path.of(
                    SyndesisStepParserServiceTest.class.getResource(
                            "model-3.json").toURI()));

            StepParserService.ParseResult<Step> result =
                    service.deepParse(json);
            assertNotNull(result);
            for (var s : result.getSteps()) {
                System.out.println("step: " + s);
            }
            for (var s : result.getSteps()) {
                assertNotNull(s);
            }
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    void deepParseModel4() {
        try {
            String json = Files.readString(Path.of(
                    SyndesisStepParserServiceTest.class.getResource(
                            "model-4.json").toURI()));

            StepParserService.ParseResult<Step> result =
                    service.deepParse(json);
            assertNotNull(result);
            for (var s : result.getSteps()) {
                System.out.println("step: " + s);
            }
            for (var s : result.getSteps()) {
                assertNotNull(s);
            }
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    void deepParseModel5() {
        try {
            String json = Files.readString(Path.of(
                    SyndesisStepParserServiceTest.class.getResource(
                            "model-5.json").toURI()));

            StepParserService.ParseResult<Step> result =
                    service.deepParse(json);
            assertNotNull(result);
            for (var s : result.getSteps()) {
                System.out.println("step: " + s);
            }
            for (var s : result.getSteps()) {
                assertNotNull(s);
            }
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    void deepParseModel6() {
        try {
            String json = Files.readString(Path.of(
                    SyndesisStepParserServiceTest.class.getResource(
                            "model-6.json").toURI()));

            StepParserService.ParseResult<Step> result =
                    service.deepParse(json);
            assertNotNull(result);
            for (var s : result.getSteps()) {
                System.out.println("step: " + s);
            }
            for (var s : result.getSteps()) {
                assertNotNull(s);
            }
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
