package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

@QuarkusTest
@TestHTTPEndpoint(StepResource.class)
class StepResourceTest {

    public static final String INFINISPAN_SOURCE = "infinispan-source";
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
    void stepById() {
        Step s = stepResource.stepById(INFINISPAN_SOURCE + "-START");
        Assertions.assertNotNull(s);
        Assertions.assertEquals(s.getName() + "-" + s.getType(), s.getId());
    }

    @Test
    void stepsByName() {
        Collection<Step> steps = stepResource.stepsByName(INFINISPAN_SOURCE);
        for (Step s : steps) {
            Assertions.assertNotNull(s);
            Assertions.assertEquals(INFINISPAN_SOURCE, s.getName());
            Assertions.assertEquals(s.getName() + "-" + s.getType(), s.getId());
        }
    }

    @Test
    @Timeout(100)
    void speedKamelet() {
        stepResource.all("KameletBinding", null, null, null, null, null, null);
    }

    @Test
    @Timeout(100)
    void speedKameletAndBinding() {
        stepResource.all("KameletBinding,Kamelet", null, null, null, null, null, null);
    }
    @Test
    @Timeout(100)
    void speedKameletAndBindingEnd() {
        stepResource.all("KameletBinding,Kamelet", Step.END, null, null, null, null, null);
    }
    @Test
    @Timeout(100)
    void speedKameletBindingMiddle() {
        stepResource.all("KameletBinding", Step.MIDDLE, null, null, null, null, null);
    }

    @ParameterizedTest
    @ValueSource(longs = {10l, 25l, 42l})
    @Timeout(100)
    void limitAndStart(long limitParameter) {
        Assertions.assertTrue(stepResource.all(null, null, null, null, null, null, null).size() > limitParameter);
        List<Step> limit = stepResource.all(null, null, null, limitParameter, null, null, null).stream().toList();
        Assertions.assertEquals(limitParameter, limit.size());

        Long[] startParameters = new Long[]{0l, 3l, 5l};

        for (Long startParameter : startParameters) {
            List<Step> start =
                    stepResource.all(null, null, null, limitParameter, startParameter, null, null).stream().toList();
            Assertions.assertEquals(limitParameter, start.size());

            for (int i = 0; i < startParameter; i++) {
                Assertions.assertEquals(limit.get((int) (startParameter + i)), start.get(i));
            }
        }
    }

    @Test
    void allSteps() {
        Collection<Step> steps = stepResource.all(null, null, null, null, null, null, null);
        Assertions.assertNotNull(steps);
        Assertions.assertEquals(
                catalog.getReadOnlyCatalog().getAll().size(),
                steps.size());

        var integrationType = "KameletBinding";
        Assertions.assertNotNull(steps);
        Assertions.assertTrue(
                stepResource.all(null, integrationType, null, null, null, null, null)
                        .stream().allMatch(s ->
                                integrationType.equalsIgnoreCase(s.getType())));

        var kind = "Kamelet";
        Assertions.assertNotNull(steps);
        Assertions.assertTrue(
                stepResource.all(null, null, kind, null, null, null, null)
                        .stream().allMatch(s ->
                                kind.equalsIgnoreCase(s.getKind())));
        var type = Step.START;
        Assertions.assertNotNull(steps);
        Assertions.assertTrue(
                stepResource.all(type, null, null, null, null, null, null)
                        .stream().allMatch(s ->
                                type.equalsIgnoreCase(s.getType())));

        Assertions.assertNotNull(steps);
        Assertions.assertTrue(
                stepResource.all(type, integrationType, kind, null, null, null, null)
                        .stream().allMatch(s ->
                                type.equalsIgnoreCase(s.getType())
                                && kind.equalsIgnoreCase(s.getKind())));
    }
}
