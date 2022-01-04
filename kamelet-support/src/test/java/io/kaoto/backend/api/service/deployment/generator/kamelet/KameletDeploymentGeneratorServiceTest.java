package io.kaoto.backend.api.service.deployment.generator.kamelet;

import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class KameletDeploymentGeneratorServiceTest {

    @Inject
    private KameletDeploymentGeneratorService service;

    @Test
    void parse() {
        List<Step> steps = new ArrayList<Step>();
        assertEquals("", service.parse("kamelet-test", steps));

        Step step = new Step();
        step.setKind("Camel-Connector");
        step.setName("log");
        step.setParameters(new ArrayList<>());
        Parameter<String> p = new Parameter<>();
        p.setPath(true);
        p.setValue("loggerName");
        step.getParameters().add(p);
        p = new Parameter<String>("level", "level", "", "default",
                "string");
        p.setValue("info");
        step.getParameters().add(p);
        steps.add(step);

        step = new Step();
        step.setKind("Camel-Connector");
        step.setName("kamelet:sink");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        assertEquals("apiVersion: camel.apache.org/v1alpha1\n"
                + "group: camel.apache.org\n"
                + "kind: Kamelet\n"
                + "metadata:\n"
                + "  additionalProperties: {}\n"
                + "  finalizers: []\n"
                + "  labels:\n"
                + "    camel.apache.org/kamelet.type: source\n"
                + "    camel.apache.org/kamelet.icon: ''\n"
                + "  managedFields: []\n"
                + "  name: kamelet-test-source\n"
                + "  ownerReferences: []\n"
                + "plural: kamelets\n"
                + "scope: Namespaced\n"
                + "served: true\n"
                + "singular: kamelet\n"
                + "spec:\n"
                + "  flow:\n"
                + "    from:\n"
                + "      uri: log:loggerName\n"
                + "      parameters:\n"
                + "        level: info\n"
                + "    steps:\n"
                + "    - to:\n"
                + "        uri: kamelet:sink\n"
                + "        parameters: {}\n"
                + "storage: true\n"
                + "version: v1alpha1\n", service.parse("kamelet-test", steps));


        step = new Step();
        step.setKind("EIP");
        step.setName("set-body");
        step.setParameters(new ArrayList<>());
        p = new Parameter<String>("constant", "constant", "", "default",
                "string");
        p.setValue("Hello Llama");
        step.getParameters().add(p);
        steps.add(1, step);
        assertTrue(service.appliesTo(steps));

        assertEquals("apiVersion: camel.apache.org/v1alpha1\n"
                + "group: camel.apache.org\n"
                + "kind: Kamelet\n"
                + "metadata:\n"
                + "  additionalProperties: {}\n"
                + "  finalizers: []\n"
                + "  labels:\n"
                + "    camel.apache.org/kamelet.type: source\n"
                + "    camel.apache.org/kamelet.icon: ''\n"
                + "  managedFields: []\n"
                + "  name: kamelet-test-source\n"
                + "  ownerReferences: []\n"
                + "plural: kamelets\n"
                + "scope: Namespaced\n"
                + "served: true\n"
                + "singular: kamelet\n"
                + "spec:\n"
                + "  flow:\n"
                + "    from:\n"
                + "      uri: log:loggerName\n"
                + "      parameters:\n"
                + "        level: info\n"
                + "    steps:\n"
                + "    - set-body:\n"
                + "        constant: Hello Llama\n"
                + "    - to:\n"
                + "        uri: kamelet:sink\n"
                + "        parameters: {}\n"
                + "storage: true\n"
                + "version: v1alpha1\n", service.parse("kamelet-test", steps));
    }

    @Test
    void appliesTo() {
        List<Step> steps = new ArrayList<Step>();
        assertFalse(service.appliesTo(steps));

        Step step = new Step();
        step.setKind("Camel-Connector");
        steps.add(step);
        assertFalse(service.appliesTo(steps));

        step = new Step();
        step.setKind("Camel-Connector");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        step = new Step();
        step.setKind("Kamelet");
        steps.add(step);
        assertFalse(service.appliesTo(steps));

        steps.clear();

        step = new Step();
        step.setKind("Kamelet");
        steps.add(step);
        assertFalse(service.appliesTo(steps));

        step = new Step();
        step.setKind("Kamelet");
        steps.add(step);
        assertFalse(service.appliesTo(steps));

        step = new Step();
        step.setKind("Camel-Connector");
        steps.add(step);
        assertFalse(service.appliesTo(steps));
    }

    public KameletDeploymentGeneratorService getService() {
        return service;
    }

    public void setService(final KameletDeploymentGeneratorService service) {
        this.service = service;
    }

}
