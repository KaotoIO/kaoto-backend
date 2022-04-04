package io.kaoto.backend.api.service.language;

import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.step.Step;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * 🐱class LanguageService
 *
 * This endpoint compiles data about the languages supported.
 */
@ApplicationScoped
public class LanguageService {

    private Logger log = Logger.getLogger(LanguageService.class);


    @ConfigProperty(name = "crd.default")
    private String crdDefault;

    @Inject
    private Instance<DeploymentGeneratorService> generatorServices;

    @Inject
    private Instance<StepParserService<Step>> stepParserServices;

    /*
     * 🐱method getAll: Map
     *
     * Returns the supported languages.
     */
    public Map<String, Map<String, String>> getAll() {

        Map<String, Map<String, String>> res = new HashMap<>();

        for (DeploymentGeneratorService parser : getGeneratorServices()) {
            String key = parser.identifier();
            if (!res.containsKey(key)) {
                final var language = new HashMap<String, String>();
                res.put(key, language);
                res.get(key).put("description", parser.description());
            }
            res.get(key).put("output", "true");
        }

        for (StepParserService parser : getStepParserServices()) {
            String key = parser.identifier();
            if (!res.containsKey(key)) {
                final var language = new HashMap<String, String>();
                res.put(key, language);
                res.get(key).put("description", parser.description());
            }
            res.get(key).put("input", "true");
        }

        if (null != crdDefault && !crdDefault.isEmpty()) {
            if (res.containsKey(crdDefault)) {
                res.get(crdDefault).put("default", "true");
            }
        }

        return res;
    }

    public Instance<DeploymentGeneratorService> getGeneratorServices() {
        return generatorServices;
    }

    public void setGeneratorServices(
            final Instance<DeploymentGeneratorService> parsers) {
        this.generatorServices = parsers;
    }

    public Instance<StepParserService<Step>> getStepParserServices() {
        return stepParserServices;
    }

    public void setStepParserServices(
            final Instance<StepParserService<Step>> stepParserServices) {
        this.stepParserServices = stepParserServices;
    }
}
