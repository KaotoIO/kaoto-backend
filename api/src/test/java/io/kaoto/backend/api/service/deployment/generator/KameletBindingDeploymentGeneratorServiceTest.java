package io.kaoto.backend.api.service.deployment.generator;

import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletBindingDeploymentGeneratorService;
import io.quarkus.test.junit.QuarkusTest;
import io.kaoto.backend.model.step.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@QuarkusTest
class KameletBindingDeploymentGeneratorServiceTest {

    public static final String EMPTY_INTEGRATION =
            "apiVersion: camel.apache.org/v1alpha1\n"
                    + "group: camel.apache.org\n"
                    + "kind: KameletBinding\n"
                    + "metadata:\n"
                    + "  additionalProperties: {}\n"
                    + "  finalizers: []\n"
                    + "  managedFields: []\n"
                    + "  name: ''\n"
                    + "  ownerReferences: []\n"
                    + "plural: kameletbindings\n"
                    + "scope: Namespaced\n"
                    + "served: true\n"
                    + "singular: kameletbinding\n"
                    + "spec: {}\n"
                    + "storage: true\n"
                    + "version: v1alpha1\n";
    private KameletBindingDeploymentGeneratorService parser;

    @Inject
    public void setParser(
            final KameletBindingDeploymentGeneratorService parser) {
        this.parser = parser;
    }

    @Test
    void parse() {
        List<Step> steps = new ArrayList();
        Assertions.assertEquals(EMPTY_INTEGRATION, parser.parse(
                steps, Collections.emptyMap(), Collections.emptyList()));
        steps.add(new Step());
        steps.add(new Step());
        for (Step step : steps) {
            step.setKind("Kamelet");
        }
        Assertions.assertNotEquals("",
                parser.parse(steps, Collections.emptyMap(),
                        Collections.emptyList()));
        Assertions.assertNotEquals(EMPTY_INTEGRATION,
                parser.parse(steps, Collections.emptyMap(),
                        Collections.emptyList()));
    }
}
