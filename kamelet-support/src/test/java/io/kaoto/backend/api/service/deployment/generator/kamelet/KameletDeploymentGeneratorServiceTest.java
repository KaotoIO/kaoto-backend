package io.kaoto.backend.api.service.deployment.generator.kamelet;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class KameletDeploymentGeneratorServiceTest {

    private KameletDeploymentGeneratorService service;

    private StepCatalog catalog;

    @Test
    void parse() {
        List<Step> steps = new ArrayList<Step>();
        Map<String, Object> md = new HashMap<>();
        md.put("name", "kamelet-test");
        assertEquals("apiVersion: camel.apache.org/v1alpha1\n"
                        + "kind: Kamelet\n"
                        + "metadata:\n"
                        + "  annotations:\n"
                        + "    camel.apache.org/kamelet.icon: ''\n"
                        + "  labels:\n"
                        + "    camel.apache.org/kamelet.type: action\n"
                        + "  name: kamelet-test-action\n"
                        + "spec:\n"
                        + "  definition:\n"
                        + "    title: null\n"
                        + "    description: ''\n"
                        + "    properties: {}\n"
                        + "  dependencies:\n"
                        + "  - camel:core\n"
                        + "  template:\n"
                        + "    from:\n"
                        + "      uri: null\n"
                        + "      steps: []\n",
                service.parse(steps, md, Collections.emptyList()));

        Step step = new Step();
        step.setType("START");
        step.setKind("Camel-Connector");
        step.setName("log");
        step.setId("log-action");
        step.setParameters(new LinkedList<>());
        Parameter<String> p = new StringParameter();
        p.setPath(true);
        p.setValue("loggerName");
        p.setId("loggerName");
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
                        + "kind: Kamelet\n"
                        + "metadata:\n"
                        + "  annotations:\n"
                        + "    camel.apache.org/kamelet.icon: ''\n"
                        + "  labels:\n"
                        + "    camel.apache.org/kamelet.type: source\n"
                        + "  name: kamelet-test-source\n"
                        + "spec:\n"
                        + "  definition:\n"
                        + "    title: null\n"
                        + "    description: ''\n"
                        + "    properties: {}\n"
                        + "  dependencies:\n"
                        + "  - camel:core\n"
                        + "  - camel:log\n"
                        + "  template:\n"
                        + "    from:\n"
                        + "      uri: log:loggerName\n"
                        + "      parameters:\n"
                        + "        level: info\n"
                        + "      steps:\n"
                        + "      - to:\n"
                        + "          uri: kamelet:sink\n",
                service.parse(steps, md, Collections.emptyList()));

        step = new Step();
        step.setKind("EIP");
        step.setName("set-body");
        step.setParameters(new LinkedList<>());
        p = new StringParameter("constant", "constant", "", "default", null);
        p.setValue("Hello Llama");
        step.getParameters().add(p);
        steps.add(1, step);
        assertTrue(service.appliesTo(steps));

        assertEquals("apiVersion: camel.apache.org/v1alpha1\n"
                        + "kind: Kamelet\n"
                        + "metadata:\n"
                        + "  annotations:\n"
                        + "    camel.apache.org/kamelet.icon: ''\n"
                        + "  labels:\n"
                        + "    camel.apache.org/kamelet.type: source\n"
                        + "  name: kamelet-test-source\n"
                        + "spec:\n"
                        + "  definition:\n"
                        + "    title: null\n"
                        + "    description: ''\n"
                        + "    properties: {}\n"
                        + "  dependencies:\n"
                        + "  - camel:core\n"
                        + "  - camel:log\n"
                        + "  template:\n"
                        + "    from:\n"
                        + "      uri: log:loggerName\n"
                        + "      parameters:\n"
                        + "        level: info\n"
                        + "      steps:\n"
                        + "      - set-body:\n"
                        + "          constant: Hello Llama\n"
                        + "      - to:\n"
                        + "          uri: kamelet:sink\n",
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

    @BeforeEach
    void ensureCatalog() {
        catalog.waitForWarmUp().join();
    }

    @Inject
    public void setService(final KameletDeploymentGeneratorService service) {
        this.service = service;
    }

    @Inject
    public void setCatalog(StepCatalog catalog) {
        this.catalog = catalog;
    }
}
