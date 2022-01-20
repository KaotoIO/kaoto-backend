package io.kaoto.backend.api.service.step.parser.serverlessworkflow;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.interfaces.State;
import io.serverlessworkflow.api.mapper.YamlObjectMapper;
import io.serverlessworkflow.api.states.ForEachState;
import io.serverlessworkflow.api.states.InjectState;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ServerlessWorkflowStepParserService
        implements StepParserService<Step> {

    private Logger log =
            Logger.getLogger(ServerlessWorkflowStepParserService.class);

    private StepCatalog catalog;

    @Inject
    public void setCatalog(final StepCatalog catalog) {
        this.catalog = catalog;
    }

    private static YamlObjectMapper yamlObjectMapper
            = new YamlObjectMapper();


    @Override
    public ParseResult<Step> deepParse(final String input) {
        ParseResult<Step> res = new ParseResult<>();
        res.setMetadata(new HashMap<>());
        List<Step> steps = new ArrayList<>();


        Workflow w = Workflow.fromSource(input);

        res.getMetadata().put("name", w.getName());
        res.getMetadata().put("description", w.getDescription());
        res.getMetadata().put("id", w.getId());
        res.getMetadata().put("version", w.getVersion());
        res.getMetadata().put("specVersion", w.getSpecVersion());
        res.getMetadata().put("start", w.getStart());

        for (State state : w.getStates()) {

            catalog.getReadOnlyCatalog().getAll();

            Step step = catalog.getReadOnlyCatalog()
                    .searchStepByName(state.getType().value());

            if (step != null) {
                if (state instanceof ForEachState) {
                    for (Parameter p : step.getParameters()) {
                        switch (p.getId()) {
                            case "inputCollection" ->
                                    p.setValue(((ForEachState) state)
                                            .getInputCollection());
                            case "outputCollection" ->
                                    p.setValue(((ForEachState) state)
                                            .getOutputCollection());
                            case "iterationParam" ->
                                    p.setValue(((ForEachState) state)
                                            .getIterationParam());
                            case "name" ->
                                    p.setValue(state.getName());
                            case "id" -> p.setValue(state.getId());
                            case "actions" ->
                                    p.setValue(((ForEachState) state)
                                            .getActions());
                            default ->
                                    log.warn(
                                            "Unrecognized parameter "
                                                    + p.getId());
                        }

                    }
                } else if (state instanceof InjectState) {
                    for (Parameter p : step.getParameters()) {
                        switch (p.getId()) {
                            case "data" ->
                                    p.setValue(((InjectState) state)
                                            .getData());
                            case "name" ->
                                    p.setValue(state.getName());
                            case "end" ->
                                    p.setValue(state.getEnd());
                            default ->
                                    log.warn(
                                            "Unrecognized parameter "
                                                    + p.getId());
                        }

                    }
                }

                steps.add(step);
            }
        }
        res.setSteps(steps.stream()
                .filter(Objects::nonNull)
                .toList());

        return res;
    }

    @Override
    public List<Step> parse(final String input) {
       return deepParse(input).getSteps();
    }

    @Override
    public boolean appliesTo(final String yaml) {
        return yaml.indexOf("start:") > 0 && yaml.indexOf("status:") > 0;
    }

}
