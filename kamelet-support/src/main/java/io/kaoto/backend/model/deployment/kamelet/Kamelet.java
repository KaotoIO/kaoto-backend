package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;
import io.kaoto.backend.model.deployment.kamelet.step.ToFlowStep;
import io.kaoto.backend.model.deployment.kamelet.step.UriFlowStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 🐱class Kamelet
 * Represents a Kamelet definition that can be deployed.
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@Group("camel.apache.org")
@Version("v1alpha1")
@Kind("Kamelet")
public final class Kamelet
        extends CustomResource<KameletSpec, KameletBindingStatus>
        implements io.fabric8.kubernetes.api.model.Namespaced, Serializable {
    @Serial
    private static final long serialVersionUID = -6210736155226440906L;

    private final String group = "camel.apache.org";

    public Kamelet() {
    }

    public Kamelet(final String name, final List<Step> steps,
                   final String icon) {
        this();

        //Define the steps
        setSpec(new KameletSpec());
        getSpec().setFlow(new Flow());
        getSpec().getFlow().setFrom(processStep(steps.get(0), false));
        getSpec().getFlow().setSteps(new ArrayList<>());

        for (int i = 1; i < steps.size(); i++) {
            getSpec().getFlow().getSteps().add(processStep(steps.get(i), true));
        }

        setMetadata(new ObjectMeta());
        getMetadata().setLabels(new HashMap<>());

        // The code of a "source" Kamelet must send data to the kamelet:sink
        // special endpoint. The code of a "sink" Kamelet must consume data
        // from the special endpoint kamelet:source.
        // In any other case, it is an action.
        Type type = Type.source;

        getMetadata().getLabels().put(group + "/kamelet.type",
                type.name());

        //default icon by now
        getMetadata().getLabels().put(group + "/kamelet.icon",
                icon);


        getMetadata().setName(name + "-" + type.name());
    }

    private FlowStep processStep(final Step step, final Boolean to) {
        FlowStep flowStep = null;

        if ("Camel-Connector".equalsIgnoreCase(step.getKind())) {
            StringBuilder uri = new StringBuilder(step.getName());
            Map<String, String> params = new HashMap<>();
            if (step.getParameters() != null) {
                for (Parameter p : step.getParameters()) {
                    if (p.isPath()) {
                        uri.append(":");
                        uri.append((p.getValue() != null ? p.getValue()
                                : p.getDefault()));
                    } else if (p.getValue() != null) {
                        params.put(p.getId(), p.getValue().toString());
                    }
                }
            }
            flowStep = new UriFlowStep(uri.toString(), params);
            if (to) {
               flowStep = new ToFlowStep(flowStep);
            }
        }

        return flowStep;
    }

    enum Type { source, sink, action }
}
