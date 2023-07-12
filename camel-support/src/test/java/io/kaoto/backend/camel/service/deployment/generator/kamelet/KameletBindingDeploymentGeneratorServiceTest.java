package io.kaoto.backend.camel.service.deployment.generator.kamelet;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.service.dsl.kamelet.KameletBindingDSLSpecification;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class KameletBindingDeploymentGeneratorServiceTest {

    @Inject
    private KameletBindingDSLSpecification service;

    @Inject
    private StepCatalog catalog;

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Test
    void parse() {
        List<Step> steps = new ArrayList<Step>();
        Map<String, Object> md = new HashMap<>();
        String name = "kamelet-binding-test";
        md.put("name", name);
        assertEquals("apiVersion: camel.apache.org/v1alpha1\n"
                + "kind: KameletBinding\n"
                + "metadata:\n"
                + "  name: " + name + "\n"
                + "spec:\n"
                + "  source: null\n"
                + "  sink: null\n",
                service.getDeploymentGeneratorService().parse(steps, md, Collections.emptyList()));

        steps.addAll(catalog.getReadOnlyCatalog()
                .searchByName("aws-s3-source"));

        assertTrue(service.appliesTo(steps));

        assertEquals("apiVersion: camel.apache.org/v1alpha1\n"
                + "kind: KameletBinding\n"
                + "metadata:\n"
                + "  name: " + name + "\n"
                + "spec:\n"
                + "  source:\n"
                + "    properties: {}\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      kind: Kamelet\n"
                + "      name: aws-s3-source\n"
                + "    types: {}\n"
                + "  sink: null\n",
                service.getDeploymentGeneratorService().parse(steps, md, Collections.emptyList()));

        Step step = catalog.getReadOnlyCatalog().searchByName("knative").stream()
                .filter(s -> s.getKind().equalsIgnoreCase("Knative")).findAny().get();
        for (Parameter p : step.getParameters()) {
            if (p.getTitle().equalsIgnoreCase("Type")) {
                p.setValue("example");
            }
            if (p.getTitle().equalsIgnoreCase("name")) {
                p.setValue("broker-name");
            }
        }
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        assertEquals("apiVersion: camel.apache.org/v1alpha1\n"
                + "kind: KameletBinding\n"
                + "metadata:\n"
                + "  name: " + name + "\n"
                + "spec:\n"
                + "  source:\n"
                + "    properties: {}\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      kind: Kamelet\n"
                + "      name: aws-s3-source\n"
                + "    types: {}\n"
                + "  sink:\n"
                + "    properties:\n"
                + "      type: example\n"
                + "    ref:\n"
                + "      apiVersion: eventing.knative.dev/v1\n"
                + "      kind: Broker\n"
                + "      name: broker-name\n"
                + "    types: {}\n",
                service.getDeploymentGeneratorService().parse(steps, md, Collections.emptyList()));
    }

    @Test
    void appliesTo() {
        List<Step> steps = new ArrayList<Step>();
        assertTrue(service.appliesTo(steps));

        Step step = new Step();
        step.setKind("Kamelet");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        step = new Step();
        step.setKind("Eip");
        steps.add(step);
        assertFalse(service.appliesTo(steps));
        steps.clear();

        step = new Step();
        step.setKind("Kamelet");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        step = new Step();
        step.setKind("Knative");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        step = new Step();
        step.setKind("Eip");
        steps.add(step);
        assertFalse(service.appliesTo(steps));

        steps.clear();

        step = new Step();
        step.setKind("Kamelet");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        step = new Step();
        step.setKind("Kamelet");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        step = new Step();
        step.setKind("Kamelet");
        steps.add(step);
        assertTrue(service.appliesTo(steps));
    }

    public KameletBindingDSLSpecification getService() {
        return service;
    }

    public void setService(final KameletBindingDSLSpecification service) {
        this.service = service;
    }

    public StepCatalog getCatalog() {
        return catalog;
    }

    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }
}
