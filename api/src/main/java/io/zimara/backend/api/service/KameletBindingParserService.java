package io.zimara.backend.api.service;

import io.zimara.backend.api.Catalog;
import io.zimara.backend.metadata.MetadataCatalog;
import io.zimara.backend.metadata.catalog.ReadOnlyCatalog;
import io.zimara.backend.model.Parameter;
import io.zimara.backend.model.Step;
import io.zimara.backend.model.step.kamelet.KameletStep;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KameletBindingParserService implements ParserService {

    private final Yaml yaml = new Yaml();
    private String identifier = "Kamelet Binding";

    @Override
    public List<Step> parse(String input) {

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
        MetadataCatalog catalog = Catalog.getReadOnlyCatalog();

        if (source.containsKey("uri")) {
            String uri = source.get("uri").toString();
            step = catalog.searchStepByName(uri.substring(0, uri.indexOf(":")));
        } else if (source.containsKey("ref")) {
            Map<String, Object> ref = (Map<String, Object>) source.getOrDefault("ref", Collections.emptyMap());
            Map<String, Object> properties = (Map<String, Object>) source.getOrDefault("properties", Collections.emptyMap());

            step = catalog.searchStepByName(ref.get("name").toString());

            for(Map.Entry<String, Object> c : properties.entrySet()) {
                for(Parameter p : ((KameletStep) step).getParameters()) {
                    if(p.getId().equalsIgnoreCase(c.getKey())) {
                        p.setValue(c.getValue());
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
    public Boolean appliesTo(String yaml) {
        return yaml.contains("kind: KameletBinding");
    }

}
