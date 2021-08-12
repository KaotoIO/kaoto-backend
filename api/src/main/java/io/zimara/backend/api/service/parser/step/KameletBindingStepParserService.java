package io.zimara.backend.api.service.parser.step;

import io.zimara.backend.api.metadata.catalog.StepCatalog;
import io.zimara.backend.api.service.parser.StepParserService;
import io.zimara.backend.model.parameter.Parameter;
import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 🐱miniclass KameletBindingStepParserService (StepParserService)
 *
 */
@ApplicationScoped
public class KameletBindingStepParserService implements StepParserService<Step> {

    private final Yaml yaml = new Yaml(new SafeConstructor());
    private String identifier = "Kamelet Binding";
    private Logger log = Logger.getLogger(KameletBindingStepParserService.class);

    @Inject
    StepCatalog catalog;

    @Override
    public List<Step> parse(String input) {
        if(!appliesTo(input)) {
            throw new IllegalArgumentException("Wrong format provided. This is not parseable by " + getIdentifier());
        }

        List<Step> steps = new ArrayList<>(2);
        Map<String, Object> parsed = yaml.load(input);
        Map<String, Object> empty = Collections.emptyMap();

        processMetadata(parsed, empty);
        processSpec(steps, parsed, empty);

        return steps;
    }

    private void processSpec(List<Step> steps, Map<String, Object> parsed, Map<String, Object> empty) {
        Map<String, Object> spec = (Map<String, Object>) parsed.getOrDefault("spec", empty);
        steps.add(processStep((Map<String, Object>) spec.getOrDefault("source", empty)));
        steps.add(processStep((Map<String, Object>) spec.getOrDefault("sink", empty)));
    }

    private Step processStep(Map<String, Object> source) {
        Step step = null;

        if (source.containsKey("uri")) {
            log.trace("Found uri component.");
            String uri = source.get("uri").toString();
            step = catalog.getReadOnlyCatalog().searchStepByName(uri.substring(0, uri.indexOf(":")));
        } else if (source.containsKey("ref")) {
            log.trace("Found ref component.");
            Map<String, Object> ref = (Map<String, Object>) source.getOrDefault("ref", Collections.emptyMap());
            Map<String, Object> properties = (Map<String, Object>) source.getOrDefault("properties", Collections.emptyMap());

            step = catalog.getReadOnlyCatalog().searchStepByName(ref.get("name").toString());
            log.trace("Found step " + step.getName());

            for (Map.Entry<String, Object> c : properties.entrySet()) {
                for (Parameter p : ((KameletStep) step).getParameters()) {
                    if (p.getId().equalsIgnoreCase(c.getKey())) {
                        p.setValue(c.getValue());
                        break;
                    }
                }
            }
        }

        return step;
    }

    private void processMetadata(Map<String, Object> parsed, Map<String, Object> empty) {
        Map<String, Object> metadata = (Map<String, Object>) parsed.getOrDefault("metadata", empty);
        identifier = metadata.getOrDefault("name", identifier).toString();
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public boolean appliesTo(String yaml) {
        return yaml.contains("kind: KameletBinding");
    }

}
