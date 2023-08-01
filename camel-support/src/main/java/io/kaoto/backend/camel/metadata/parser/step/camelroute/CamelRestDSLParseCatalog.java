package io.kaoto.backend.camel.metadata.parser.step.camelroute;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.kaoto.backend.api.metadata.catalog.StepCatalogParser;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.model.deployment.rest.Rest;
import io.kaoto.backend.metadata.ParseCatalog;
import io.kaoto.backend.metadata.parser.EmptyParseCatalog;
import io.kaoto.backend.metadata.parser.ProcessFile;
import io.kaoto.backend.model.parameter.ObjectParameter;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.parameter.StringParameter;
import io.kaoto.backend.model.step.Step;
import io.smallrye.common.constraint.NotNull;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * 🐱class CamelRestDSLParseCatalog
 * 🐱inherits StepCatalogParser
 * <p>
 * 🐱relationship dependsOn ParseCatalog
 * <p>
 * Adds the REST DSL steps to the catalog.
 */
@ApplicationScoped
public class CamelRestDSLParseCatalog implements StepCatalogParser {

    public static final String CAMEL_REST_DSL = Rest.CAMEL_REST_DSL;
    public static final String CAMEL_REST_VERB = "CAMEL-REST-VERB";
    public static final String CAMEL_REST_ENDPOINT = "CAMEL-REST-ENDPOINT";
    public static final String REST_DSL = "REST DSL";
    protected static final String[] KINDS = {CAMEL_REST_DSL, CAMEL_REST_VERB, CAMEL_REST_ENDPOINT};

    private static final String ICON = KamelHelper.loadResourceAsString(
        CamelRestDSLParseCatalog.class,
        "base64icon.txt").orElse("");

    @NotNull
    private static Step getRestParentStep() {
        var step = new Step(Rest.CAMEL_REST_DSL, "rest",
                ICON, new ArrayList<>(), CAMEL_REST_DSL, Step.Type.START);
        step.setMinBranches(1);
        step.setMaxBranches(9);
        step.setDescription("This step represents a REST API.");
        step.setTitle(REST_DSL);
        step.setGroup(REST_DSL);
        var parameters = new ArrayList<Parameter>();
        final var path =
                new StringParameter(Rest.PATH_LABEL, "Path of the endpoint",
                        "Path where this endpoint is listening.", null, null, null,null, null);
        parameters.add(path);
        final var description =
                new StringParameter(Rest.DESCRIPTION_LABEL, "Description of the endpoint",
                        "Human readable description of this endpoint.", null, null, null,null, null);
        parameters.add(description);
        step.setParameters(parameters);
        return step;
    }

    @NotNull
    private static Step getVerbStep(String verb) {
        var step = new Step("camel-rest-verb-" + verb, verb,
                ICON, new ArrayList<>(), CAMEL_REST_VERB, Step.Type.MIDDLE);
        step.setMinBranches(1);
        step.setMaxBranches(-1);
        step.setDescription("This step represents a " + verb.toUpperCase() + " HTTP endpoint in the REST API.");
        step.setTitle("HTTP " + verb.toUpperCase());
        step.setGroup(REST_DSL);
        return step;
    }

    @NotNull
    private static Step getConsumesStep() {
        var step = new Step(Rest.CAMEL_REST_CONSUMES, Rest.CONSUMES_LABEL,
                ICON, new ArrayList<>(), CAMEL_REST_ENDPOINT, Step.Type.MIDDLE);

        var parameters = new ArrayList<Parameter>();

        var parameter = new StringParameter();
        parameter.setId(Rest.CONSUMES_LABEL);
        parameter.setDescription("What kind of Media Type this endpoint consumes.");
        parameter.setTitle("Consumes Media Type");
        parameters.add(parameter);

        parameter = new StringParameter();
        parameter.setId(Rest.PRODUCES_LABEL);
        parameter.setDescription("What kind of Media Type this endpoint produces.");
        parameter.setTitle("Produces Media Type");
        parameters.add(parameter);

        parameter = new StringParameter();
        parameter.setId(Rest.ID_LABEL);
        parameter.setDescription("Identifier of this endpoint.");
        parameter.setTitle("Identifier");
        parameters.add(parameter);

        parameter = new StringParameter();
        parameter.setId(Rest.URI_LABEL);
        parameter.setDescription("Uri path of this endpoint");
        parameter.setTitle("Uri Path");
        parameter.setDefaultValue("/");
        parameters.add(parameter);

        parameter = new StringParameter();
        parameter.setId(Rest.DESCRIPTION_LABEL);
        parameter.setDescription("Description of the endpoint.");
        parameter.setTitle("Description");
        parameters.add(parameter);

        var oparameter = new ObjectParameter();
        oparameter.setId(Rest.PARAM_LABEL);
        oparameter.setDescription("Parameters of the endpoint.");
        oparameter.setTitle(KamelHelper.PARAMETERS);
        parameters.add(oparameter);

        step.setParameters(parameters);

        return step;
    }

    @Override
    public ParseCatalog<Step> getParser(String url) {
        return new CamelRestDSLParser();
    }

    @Override
    public ParseCatalog<Step> getParser(String url, String tag) {
        //We are not expecting to get anything from here
        return new EmptyParseCatalog<>();
    }

    @Override
    public ParseCatalog<Step> getParserFromCluster() {
        //We are not expecting to get anything from here
        return new EmptyParseCatalog<>();
    }

    @Override
    public ParseCatalog<Step> getLocalFolder(Path path) {
        //We are not expecting to get anything from here
        return new EmptyParseCatalog<>();
    }

    @Override
    public boolean generatesKind(String kind) {
        return kind.isBlank() || Arrays.stream(KINDS).anyMatch(k -> k.equalsIgnoreCase(kind));
    }

    class CamelRestDSLParser implements ParseCatalog<Step> {
        @Override
        public CompletableFuture<List<Step>> parse() {
            List<Step> steps = new ArrayList<>();
            steps.add(getRestParentStep());

            String[] verbs = new String[]{"get", "head", "post", "put", "delete", "connect", "options", "trace",
                    "patch"};
            for (String verb : verbs) {
                steps.add(getVerbStep(verb));
            }
            steps.add(getConsumesStep());

            CompletableFuture<List<Step>> metadata = new CompletableFuture<>();
            metadata.complete(steps);
            return metadata;
        }

        @Override
        public void setFileVisitor(ProcessFile<Step> fileVisitor) {
            //We are not going to visit anything, we don't need a fileVisitor
        }

    }
}
