package io.kaoto.backend.api.service.deployment.generator.serverlessworkflow;

import com.fasterxml.jackson.databind.JsonNode;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.actions.Action;
import io.serverlessworkflow.api.end.End;
import io.serverlessworkflow.api.interfaces.State;
import io.serverlessworkflow.api.start.Start;
import io.serverlessworkflow.api.states.ForEachState;
import io.serverlessworkflow.api.states.InjectState;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ServerlessWorkflowGeneratorService
        implements DeploymentGeneratorService {

    private Logger log =
            Logger.getLogger(ServerlessWorkflowGeneratorService.class);

    public static final String SERVERLESS_WORKFLOW = "ServerlessWorkflow";

    @Override
    public List<String> getKinds() {
        return Arrays.asList(new String[]{SERVERLESS_WORKFLOW});
    }

    public String identifier() {
        return SERVERLESS_WORKFLOW;
    }

    public ServerlessWorkflowGeneratorService() {

    }

    @Override
    public String parse(final List<Step> steps,
                        final Map<String, Object> metadata) {

        if (!appliesTo(steps)) {
            return "";
        }

        List<State> states = new ArrayList<>();
        for (Step step : steps) {
            State state = null;

            switch (step.getName()) {
                case "foreach":
                    state = new ForEachState();
                    for (Parameter p : step.getParameters()) {
                        if (p.getValue() != null) {
                            switch (p.getId()) {
                                case "inputCollection" -> (
                                        (ForEachState) state)
                                        .setInputCollection(
                                                p.getValue()
                                                        .toString());
                                case "outputCollection" -> (
                                        (ForEachState) state)
                                        .setOutputCollection(
                                                p.getValue().toString());
                                case "iterationParam" -> (
                                        (ForEachState) state)
                                        .setIterationParam(
                                                p.getValue().toString());
                                case "name" -> (
                                        (ForEachState) state).setName(
                                        p.getValue().toString());
                                case "id" -> ((ForEachState) state)
                                        .setId(p.getValue().toString());
                                case "actions" -> (
                                        (ForEachState) state).setActions(
                                        (List<Action>) p.getValue());
                                default -> log.warn(
                                        "Unrecognized parameter " + p.getId());
                            }
                        }
                    }
                    break;
                case "inject":
                    state = new InjectState();
                    for (Parameter p : step.getParameters()) {
                        if (p.getValue() != null) {
                            switch (p.getId()) {
                                case "data" -> (
                                        (InjectState) state)
                                        .setData((JsonNode) p.getValue());
                                case "name" -> (
                                        (InjectState) state).setName(
                                        p.getValue().toString());
                                case "end" -> (
                                        (InjectState) state).setEnd(
                                                (End) p.getValue());
                                default -> log.warn(
                                        "Unrecognized parameter " + p.getId());
                            }
                        }
                    }
                    break;
                default:
                    log.error("State unknown: " + step);
                    break;
            }

            if (state != null) {
                states.add(state);
            }
        }

        Workflow workflow =
                new Workflow()
                        .withId(
                                metadata.getOrDefault("id", null)
                                        .toString())
                        .withName(
                                metadata.getOrDefault("name", null)
                                        .toString())
                        .withStates(states)
                        .withSpecVersion(
                                metadata.getOrDefault("specVersion", null)
                                        .toString())
                        .withVersion(
                                metadata.getOrDefault("version", "1.0")
                                        .toString())
                        .withSpecVersion(
                                metadata.getOrDefault("specVersion", null)
                                        .toString())
                        .withDescription(
                                metadata.getOrDefault("description", null)
                                        .toString());

        workflow.withStart((Start)
                metadata.getOrDefault("start", null));

        return Workflow.toYaml(workflow);
    }


    @Override
    public String parse(final String name, final List<Step> steps) {
        Map<String, Object> md = new HashMap<>();
        md.put("name", name);
        return parse(steps, md);
    }

    @Override
    public boolean appliesTo(final List<Step> steps) {
        return steps.stream().allMatch(
                s -> SERVERLESS_WORKFLOW.equalsIgnoreCase(s.getKind()));
    }
}
