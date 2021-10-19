package io.zimara.backend.model.deployment.kamelet;

import java.util.ArrayList;
import java.util.List;

public final class KameletBindingSpec {
    private KameletBindingStep source;
    private List<KameletBindingStep> steps = new ArrayList<>();
    private KameletBindingStep sink;

    public KameletBindingStep getSource() {
        return source;
    }

    public void setSource(final KameletBindingStep source) {
        this.source = source;
    }

    public List<KameletBindingStep> getSteps() {
        return steps;
    }

    public KameletBindingStep getSink() {
        return sink;
    }

    public void setSink(final KameletBindingStep sink) {
        this.sink = sink;
    }

    public void setSteps(final List<KameletBindingStep> steps) {
        this.steps = steps;
    }
}
