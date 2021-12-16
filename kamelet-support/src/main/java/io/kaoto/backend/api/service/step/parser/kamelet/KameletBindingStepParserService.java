package io.kaoto.backend.api.service.step.parser.kamelet;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingSpec;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 🐱miniclass KameletBindingStepParserService (StepParserService)
 */
@ApplicationScoped
public class KameletBindingStepParserService
        implements StepParserService<Step> {

    private Logger log =
            Logger.getLogger(KameletBindingStepParserService.class);

    private StepCatalog catalog;

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public List<Step> parse(final String input) {
        if (!appliesTo(input)) {
            throw new IllegalArgumentException(
                    "Wrong format provided. This is not parseable by us");
        }

        List<Step> steps = new ArrayList<>();

        Yaml yaml = new Yaml(new Constructor(KameletBinding.class));
        KameletBinding binding = yaml.load(input);

        processMetadata(binding.getMetadata());
        processSpec(steps, binding.getSpec());

        return steps.stream()
                .filter(Objects::nonNull)
                .toList();
    }

    private void processSpec(final List<Step> steps,
                             final KameletBindingSpec spec) {
        steps.add(processStep(spec.getSource()));

        for (KameletBindingStep intermediateStep : spec.getSteps()) {
            steps.add(processStep(intermediateStep));
        }

        steps.add(processStep(spec.getSink()));
    }

    private Step processStep(final KameletBindingStep source) {
        Step step = null;

        if (source.getUri() != null) {
            log.trace("Found uri component.");
            String uri = source.getUri();
            step = catalog.getReadOnlyCatalog()
                    .searchStepByName(uri.substring(0, uri.indexOf(":")));
            if (step != null) {
                log.trace("Found step " + step.getName());
                setValuesOnParameters(step, uri);
            }
        } else if (source.getRef() != null) {
            log.trace("Found ref component.");
            step = catalog.getReadOnlyCatalog()
                    .searchStepByName(source.getRef().getName());

            if (step != null) {
                log.trace("Found step " + step.getName());
                setValuesOnParameters(step, source.getProperties());
            }
        }

        return step;
    }

    private void setValuesOnParameters(final Step step,
                                       final Map<String, String> properties) {

        for (Map.Entry<String, String> c : properties.entrySet()) {
            for (Parameter p : step.getParameters()) {
                if (p.getId().equalsIgnoreCase(c.getKey())) {
                    p.setValue(c.getValue());
                    break;
                }
            }
        }

    }

    private void setValuesOnParameters(final Step step,
                                       final String uri) {

        String path = uri.substring(uri.indexOf(":") + 1);
        if (path.indexOf("?") > -1) {
            path = path.substring(0, path.indexOf("?"));
        }

        for (Parameter p : step.getParameters()) {
            if (p.isPath()) {
                p.setValue(path);
            }
        }

        Pattern pattern = Pattern.compile(
                "(?:\\?|\\&)([^=]+)\\=([^&\\n]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(uri);

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);

            for (Parameter p : step.getParameters()) {
                if (p.getId().equalsIgnoreCase(key)) {
                    p.setValue(value);
                    break;
                }
            }
        }


    }

    private String processMetadata(
            final ObjectMeta metadata) {
        return metadata.getName();
    }

    @Override
    public boolean appliesTo(final String yaml) {
        return yaml.contains("kind: KameletBinding");
    }

}
