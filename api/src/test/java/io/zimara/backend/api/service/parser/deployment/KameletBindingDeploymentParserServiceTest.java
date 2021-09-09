package io.zimara.backend.api.service.parser.deployment;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class KameletBindingDeploymentParserServiceTest {

   @Inject
    KameletBindingDeploymentParserService parser;

    @Test
    void parse() {
        List<Step> steps = new ArrayList();
        Assertions.assertEquals("", parser.parse("", steps));
        steps.add(new Step());
        Assertions.assertEquals("", parser.parse("", steps));
        Step s = new Step();
        steps.add(s);
        Assertions.assertEquals("", parser.parse("", steps));
        s.setSubType("KAMELET");
        Assertions.assertNotEquals("", parser.parse("", steps));
    }

}