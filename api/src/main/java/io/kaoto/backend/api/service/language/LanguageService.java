package io.kaoto.backend.api.service.language;

import io.kaoto.backend.api.service.dsl.DSLSpecification;
import io.kaoto.backend.camel.KamelHelper;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 🐱miniclass LanguageService (CapabilitiesResource)
 * 🐱relationship compositionOf DSLSpecification, 0..1
 *
 * 🐱section
 * Service to extract languages from all supported DSL. This is the utility
 * class the resource relies on to perform the operations.
 */
@ApplicationScoped
public class LanguageService {

    @ConfigProperty(name = "crd.default")
    private String crdDefault;

    private Instance<DSLSpecification> dslSpecifications;

    /*
     * 🐱method getAll: Map
     *
     * Returns the supported languages.
     */
    @WithSpan
    public Collection<Map<String, Object>> getAll() {

        Map<String, Map<String, Object>> res = new HashMap<>();

        for (DSLSpecification dsl : getDSLs()) {
            var specs = addNewLanguage(res, dsl.identifier(), dsl.description());

            specs.put("stepKinds", dsl.getKinds().toString());
            specs.put("output", Boolean.toString(dsl.getDeploymentGeneratorService() != null));
            specs.put("input", Boolean.toString(dsl.getStepParserService() != null));
            specs.put("deployable", Boolean.toString(dsl.isDeployable()));
            String validationSchemaURI =
                    dsl.validationSchema().equals("")?"":String.format("/v1/capabilities/%s/schema",dsl.identifier());
            specs.put("validationSchema",validationSchemaURI);
            specs.put("supportsMultipleFlows", Boolean.toString(dsl.doesSupportMultipleFlows()));
            specs.put("supportsResourceDescription", Boolean.toString(dsl.doesSupportResourceDescription()));
            //Eventually, i18n
            specs.put("vocabulary", dsl.getVocabulary());
        }

        if (null != crdDefault && !crdDefault.isEmpty() && res.containsKey(crdDefault)) {
            res.get(crdDefault).put("default", "true");
        }

        return res.values();
    }

    public String getValidationSchema(String dsl) {
        String schema = "";

        Optional<DSLSpecification> found = getDSLs().stream()
                .filter( p -> p.identifier().equalsIgnoreCase(dsl))
                .findFirst();
        if (found.isPresent()) {
            schema = found.get().validationSchema();
        }
        return  schema;
    }

    private Map<String, Object> addNewLanguage(final Map<String, Map<String, Object>> res,
                                               final String key,
                                               final String description) {
        res.computeIfAbsent(key, k -> {
            final var language = new HashMap<String, Object>();
            language.put(KamelHelper.DESCRIPTION, description);
            language.put(KamelHelper.NAME, k);
            return language;
        });
        return res.get(key);
    }

    public Instance<DSLSpecification> getDSLs() {
        return dslSpecifications;
    }

    @Inject
    public void setDSLs(final Instance<DSLSpecification> dslSpecifications) {
        this.dslSpecifications = dslSpecifications;
    }

}
