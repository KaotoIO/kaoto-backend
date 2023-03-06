package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.List;
import java.util.Map;


public class Resequence extends Expression {
    public static final String RESEQUENCER_CONFIG_LABEL= "stream-config";
    public static final String RESEQUENCER_CONFIG_LABEL2= "batch-config";
    public static final String RESEQUENCER_CONFIG_LABEL3= "streamConfig";
    public static final String RESEQUENCER_CONFIG_LABEL4= "batchConfig";

    public static final String DESCRIPTION_LABEL = "description";

    public static final String STEPS_LABEL = "steps";

    private Map<String, String> streamConfig;

    private Map<String, String> batchConfig;

    private Map<String, String> description;

    private List<FlowStep> steps;


    public Resequence() {
         //Needed for serialization
    }

    public Resequence(Step step, final KamelPopulator kameletPopulator) {
        super(step);

        final var branches = step.getBranches();
        if (branches != null && !branches.isEmpty()) {
            setSteps(kameletPopulator.processSteps(branches.get(0)));
        }
    }

    public Resequence(final @JsonProperty(EXPRESSION_LABEL) Expression expression,
                      final @JsonProperty(RESEQUENCER_CONFIG_LABEL)  Map<String, String> resequencerConfig,
                      final @JsonProperty(RESEQUENCER_CONFIG_LABEL2)  Map<String, String> resequencerConfig2,
                      final @JsonProperty(RESEQUENCER_CONFIG_LABEL3)  Map<String, String> resequencerConfig3,
                      final @JsonProperty(RESEQUENCER_CONFIG_LABEL4)  Map<String, String> resequencerConfig4,
                      final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description,
                      final @JsonProperty(SIMPLE_LABEL) String simple,
                      final @JsonProperty(JQ_LABEL) String jq,
                      final @JsonProperty(CONSTANT_LABEL) String constant) {
        super(expression, constant, simple, jq, null, null, null, null);
        if (resequencerConfig != null) {
            setStreamConfig(resequencerConfig);
        } else if (resequencerConfig2 != null) {
            setBatchConfig(resequencerConfig2);
        } else if (resequencerConfig3 != null) {
            setStreamConfig(resequencerConfig3);
        } else if (resequencerConfig4 != null) {
            setBatchConfig(resequencerConfig4);
        }
        setDescription(description);
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        super.assignAttribute(parameter);
        switch (parameter.getId()) {
            case RESEQUENCER_CONFIG_LABEL:
            case RESEQUENCER_CONFIG_LABEL3:
                this.setStreamConfig((Map<String, String>) parameter.getValue());
                break;
            case RESEQUENCER_CONFIG_LABEL2:
            case RESEQUENCER_CONFIG_LABEL4:
                this.setBatchConfig((Map<String, String>) parameter.getValue());
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getRepresenterProperties();
        if (this.getStreamConfig() != null) {
            properties.put(RESEQUENCER_CONFIG_LABEL, this.getStreamConfig());
        }
        if (this.getBatchConfig() != null) {
            properties.put(RESEQUENCER_CONFIG_LABEL2, this.getBatchConfig());
        }
        if (this.getDescription() != null) {
            properties.put(DESCRIPTION_LABEL, this.getDescription());
        }
        if (this.getSteps() != null) {
            properties.put(STEPS_LABEL, this.getSteps());
        }

        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        super.assignProperty(parameter);
        switch (parameter.getId()) {
            case RESEQUENCER_CONFIG_LABEL:
            case RESEQUENCER_CONFIG_LABEL3:
                parameter.setValue(this.getStreamConfig());
                break;
            case RESEQUENCER_CONFIG_LABEL2:
            case RESEQUENCER_CONFIG_LABEL4:
                parameter.setValue(this.getBatchConfig());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.getDescription());
                break;
            default:
                break;
        }
    }

    @Override
    public void processBranches(final Step step, final StepCatalog catalog,
                                final KameletStepParserService kameletStepParserService) {
        step.setBranches(List.of(createBranch(STEPS_LABEL, this.getSteps(), kameletStepParserService)));
    }

    public Map<String, String> getStreamConfig() {
        return streamConfig;
    }

    public void setStreamConfig(final Map<String, String> streamConfig) {
        this.streamConfig = streamConfig;
    }

    public Map<String, String> getBatchConfig() {
        return batchConfig;
    }

    public void setBatchConfig(final Map<String, String> batchConfig) {
        this.batchConfig = batchConfig;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }

    public List<FlowStep> getSteps() {
        return steps;
    }

    public void setSteps(final List<FlowStep> steps) {
        this.steps = steps;
    }
}
