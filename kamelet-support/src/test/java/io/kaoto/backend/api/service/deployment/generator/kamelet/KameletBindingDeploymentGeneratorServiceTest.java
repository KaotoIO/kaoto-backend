package io.kaoto.backend.api.service.deployment.generator.kamelet;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class KameletBindingDeploymentGeneratorServiceTest {

    @Inject
    private KameletBindingDeploymentGeneratorService service;

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
                + "group: camel.apache.org\n"
                + "kind: KameletBinding\n"
                + "metadata:\n"
                + "  additionalProperties: {}\n"
                + "  finalizers: []\n"
                + "  managedFields: []\n"
                + "  name: " + name + "\n"
                + "  ownerReferences: []\n"
                + "plural: kameletbindings\n"
                + "scope: Namespaced\n"
                + "served: true\n"
                + "singular: kameletbinding\n"
                + "spec: {}\n"
                + "storage: true\n"
                + "version: v1alpha1\n", service.parse(steps, md));


        steps.add(catalog.getReadOnlyCatalog()
                .searchStepByName("aws-s3-source"));

        assertTrue(service.appliesTo(steps));

        assertEquals("apiVersion: camel.apache.org/v1alpha1\n"
                + "group: camel.apache.org\n"
                + "kind: KameletBinding\n"
                + "metadata:\n"
                + "  additionalProperties: {}\n"
                + "  finalizers: []\n"
                + "  managedFields: []\n"
                + "  name: " + name + "\n"
                + "  ownerReferences: []\n"
                + "plural: kameletbindings\n"
                + "scope: Namespaced\n"
                + "served: true\n"
                + "singular: kameletbinding\n"
                + "spec:\n"
                + "  source:\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      name: aws-s3-source\n"
                + "      kind: Kamelet\n"
                + "storage: true\n"
                + "version: v1alpha1\n",  service.parse(steps, md));

        Step step = catalog.getReadOnlyCatalog()
                .searchStepByName("knative-sink");
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
                + "group: camel.apache.org\n"
                + "kind: KameletBinding\n"
                + "metadata:\n"
                + "  additionalProperties: {}\n"
                + "  finalizers: []\n"
                + "  managedFields: []\n"
                + "  name: " + name + "\n"
                + "  ownerReferences: []\n"
                + "plural: kameletbindings\n"
                + "scope: Namespaced\n"
                + "served: true\n"
                + "singular: kameletbinding\n"
                + "spec:\n"
                + "  source:\n"
                + "    ref:\n"
                + "      apiVersion: camel.apache.org/v1alpha1\n"
                + "      name: aws-s3-source\n"
                + "      kind: Kamelet\n"
                + "  sink:\n"
                + "    ref:\n"
                + "      apiVersion: eventing.knative.dev/v1\n"
                + "      name: broker-name\n"
                + "      kind: Broker\n"
                + "    properties:\n"
                + "      type: example\n"
                + "storage: true\n"
                + "version: v1alpha1\n",  service.parse(steps, md));
    }

    @Test
    void appliesTo() {
        List<Step> steps = new ArrayList<Step>();
        assertTrue(service.appliesTo(steps));

        Step step = new Step();
        step.setKind("Camel-Connector");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        step = new Step();
        step.setKind("Camel-Connector");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        step = new Step();
        step.setKind("Kamelet");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

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
        step.setKind("Camel-Connector");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

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

    public KameletBindingDeploymentGeneratorService getService() {
        return service;
    }

    public void setService(
            final KameletBindingDeploymentGeneratorService service) {
        this.service = service;
    }

    public StepCatalog getCatalog() {
        return catalog;
    }

    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }
}
