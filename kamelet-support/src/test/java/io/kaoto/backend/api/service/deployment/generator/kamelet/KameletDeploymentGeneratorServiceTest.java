package io.kaoto.backend.api.service.deployment.generator.kamelet;

import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class KameletDeploymentGeneratorServiceTest {

    @Inject
    private KameletDeploymentGeneratorService service;

    @Test
    void parse() {
        List<Step> steps = new ArrayList<Step>();
        Map<String, Object> md = new HashMap<>();
        md.put("name", "kamelet-test");
        assertEquals("apiVersion: camel.apache.org/v1alpha1\n"
                + "group: camel.apache.org\n"
                + "kind: Kamelet\n"
                + "metadata:\n"
                + "  additionalProperties: {}\n"
                + "  annotations:\n"
                + "    camel.apache.org/kamelet.icon: ''\n"
                + "  finalizers: []\n"
                + "  labels:\n"
                + "    camel.apache.org/kamelet.type: action\n"
                + "  managedFields: []\n"
                + "  name: kamelet-test-action\n"
                + "  ownerReferences: []\n"
                + "plural: kamelets\n"
                + "scope: Namespaced\n"
                + "served: true\n"
                + "singular: kamelet\n"
                + "spec:\n"
                + "  definition:\n"
                + "    properties: {}\n"
                + "  dependencies:\n"
                + "  - camel:core\n"
                + "  template:\n"
                + "    from:\n"
                + "      uri: null\n"
                + "      steps: []\n"
                + "storage: true\n"
                + "version: v1alpha1\n",
                service.parse(steps, md, Collections.emptyList()));

        Step step = new Step();
        step.setKind("Camel-Connector");
        step.setName("log");
        step.setParameters(new ArrayList<>());
        Parameter<String> p = new StringParameter();
        p.setPath(true);
        p.setValue("loggerName");
        step.getParameters().add(p);
        p = new StringParameter("level", "level", "", "default", null);
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
                + "  annotations:\n"
                + "    camel.apache.org/kamelet.icon: ''\n"
                + "  finalizers: []\n"
                + "  labels:\n"
                + "    camel.apache.org/kamelet.type: source\n"
                + "  managedFields: []\n"
                + "  name: kamelet-test-source\n"
                + "  ownerReferences: []\n"
                + "plural: kamelets\n"
                + "scope: Namespaced\n"
                + "served: true\n"
                + "singular: kamelet\n"
                + "spec:\n"
                + "  definition:\n"
                + "    properties: {}\n"
                + "  dependencies:\n"
                + "  - camel:core\n"
                + "  template:\n"
                + "    from:\n"
                + "      uri: log:loggerName\n"
                + "      parameters:\n"
                + "        level: info\n"
                + "      steps:\n"
                + "      - to:\n"
                + "          uri: kamelet:sink\n"
                + "storage: true\n"
                + "version: v1alpha1\n",
                service.parse(steps, md, Collections.emptyList()));

        step = new Step();
        step.setKind("EIP");
        step.setName("set-body");
        step.setParameters(new ArrayList<>());
        p = new StringParameter("constant", "constant", "", "default", null);
        p.setValue("Hello Llama");
        step.getParameters().add(p);
        steps.add(1, step);
        assertTrue(service.appliesTo(steps));

        assertEquals("apiVersion: camel.apache.org/v1alpha1\n"
                + "group: camel.apache.org\n"
                + "kind: Kamelet\n"
                + "metadata:\n"
                + "  additionalProperties: {}\n"
                + "  annotations:\n"
                + "    camel.apache.org/kamelet.icon: ''\n"
                + "  finalizers: []\n"
                + "  labels:\n"
                + "    camel.apache.org/kamelet.type: source\n"
                + "  managedFields: []\n"
                + "  name: kamelet-test-source\n"
                + "  ownerReferences: []\n"
                + "plural: kamelets\n"
                + "scope: Namespaced\n"
                + "served: true\n"
                + "singular: kamelet\n"
                + "spec:\n"
                + "  definition:\n"
                + "    properties: {}\n"
                + "  dependencies:\n"
                + "  - camel:core\n"
                + "  template:\n"
                + "    from:\n"
                + "      uri: log:loggerName\n"
                + "      parameters:\n"
                + "        level: info\n"
                + "      steps:\n"
                + "      - set-body:\n"
                + "          constant: Hello Llama\n"
                + "      - to:\n"
                + "          uri: kamelet:sink\n"
                + "storage: true\n"
                + "version: v1alpha1\n",
                service.parse(steps, md, Collections.emptyList()));
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
        step.setKind("EIP-BRANCH");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        step = new Step();
        step.setKind("Camel-Connector");
        steps.add(step);
        assertTrue(service.appliesTo(steps));

        step = new Step();
        step.setKind("Kamelet");
        steps.add(step);
        assertTrue(!service.appliesTo(steps));

        steps.clear();

        step = new Step();
        step.setKind("Kamelet");
        steps.add(step);
        assertTrue(!service.appliesTo(steps));
    }

    public KameletDeploymentGeneratorService getService() {
        return service;
    }

    public void setService(final KameletDeploymentGeneratorService service) {
        this.service = service;
    }

}
