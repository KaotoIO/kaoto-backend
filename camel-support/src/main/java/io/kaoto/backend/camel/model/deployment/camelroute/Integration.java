package io.kaoto.backend.camel.model.deployment.camelroute;

import static io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService.DESCRIPTION_ANNO;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.Singular;
import io.fabric8.kubernetes.model.annotation.Version;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.Bean;
import io.kaoto.backend.camel.model.deployment.kamelet.Flow;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.v1.IntegrationStatus;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 🐱class Integration
 *
 * Represents an Integration definition that can be deployed.
 *
 * This generates a flow based on how Camel-K defines YAML routes as
 * described in https://camel.apache.org/components/latest/others/yaml-dsl.html
 * This implementation models the steps as "Loading Camel K Integrations".
 *
 */

@Version(value = "v1" , storage = true , served = true)
@Group("camel.apache.org")
@Singular("integration")
@Plural("integrations")
@RegisterForReflection
public final class Integration extends CustomResource<IntegrationSpec, IntegrationStatus> implements Namespaced {

    @Serial
    private static final long serialVersionUID = -6210923845780906L;

    public Integration() {

    }

    public Integration(
            final List<IntegrationFlow> flowList, final Map<String, Object> metadata, final StepCatalog catalog) {
        this.setMetadata(new ObjectMeta());
        this.getMetadata().setName(metadata.getOrDefault("name", "").toString());
        this.getMetadata().setAdditionalProperties(
                (Map<String, Object>) metadata.getOrDefault("additionalProperties", Collections.emptyMap()));
        this.getMetadata().setAnnotations(
                (Map<String, String>) metadata.getOrDefault("annotations", new LinkedHashMap<>()));
        this.getMetadata().setLabels((Map<String, String>) metadata.getOrDefault("labels", Collections.emptyMap()));
        if (metadata.containsKey("description")) {
            this.getMetadata().getAnnotations().put(DESCRIPTION_ANNO, String.valueOf(metadata.get("description")));
        }
        var original_spec = getMetadata().getAdditionalProperties().remove("spec");
        if (original_spec != null && original_spec instanceof IntegrationSpec ospec) {
            this.setSpec(ospec);
        } else if (original_spec != null && original_spec instanceof Map ospec) {
            ObjectMapper mapper = new ObjectMapper();
            this.setSpec(mapper.convertValue(original_spec, IntegrationSpec.class));
        } else {
            this.setSpec(new IntegrationSpec());
        }

        this.getSpec().set_flows(new ArrayList<>());
        flowList.forEach(iflow -> processFlow(iflow, this.getSpec().get_flows(), catalog));
        if (metadata.containsKey("beans")) {
            processBeans(this.getSpec().get_flows(), (List<Bean>) metadata.get("beans"));
        }
    }

    private void processFlow(IntegrationFlow iflow, List<Flow> flowList, StepCatalog catalog) {
        var flow = new Flow();
        flow.setFrom(new KamelPopulator(catalog).getFlow(iflow.getSteps()));
        if (iflow.getMetadata() != null) {
            if (iflow.getMetadata().get("name") != null) {
                flow.setId(iflow.getMetadata().get("name").toString());
            }
            if (iflow.getMetadata().get("route-configuration-id") != null) {
                flow.setRouteConfigurationId(iflow.getMetadata().get("route-configuration-id").toString());
            }
            if (iflow.getMetadata().get("description") != null) {
                flow.setDescription(iflow.getMetadata().get("description").toString());
            }
        }
        flowList.add(flow);
    }

    private void processBeans(List<Flow> flowList, List<Bean> beans) {
        Flow beansFlow  = new Flow();
        beansFlow.setBeans(new LinkedList<>());
        beansFlow.getBeans().addAll(beans);
        flowList.add(beansFlow);
    }
}
