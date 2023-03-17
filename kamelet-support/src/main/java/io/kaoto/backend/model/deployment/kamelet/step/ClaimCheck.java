package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class ClaimCheck extends EIPStep {

    public static final String DESCRIPTION_LABEL = "description";
    public static final String OPERATION_LABEL = "operation";
    public static final String KEY_LABEL = "key";
    public static final String FILTER_LABEL = "filter";
    public static final String AGGREGATION_STRATEGY_LABEL = "aggregationStrategy";
    public static final String AGGREGATION_STRATEGY_LABEL2 = "aggregation-strategy";
    public static final String AGGREGATION_STRATEGY_METHOD_NAME_LABEL = "aggregationStrategyMethodName";
    public static final String AGGREGATION_STRATEGY_METHOD_NAME_LABEL2 = "aggregation-strategy-method-name";

    @JsonProperty(OPERATION_LABEL)
    private String operation;

    @JsonProperty(KEY_LABEL)
    private String key;

    @JsonProperty(FILTER_LABEL)
    private String filter;

    @JsonProperty(AGGREGATION_STRATEGY_LABEL)
    private String aggregationStrategy;

    @JsonProperty(AGGREGATION_STRATEGY_METHOD_NAME_LABEL)
    private String aggregationStrategyMethodName;

    @JsonProperty(DESCRIPTION_LABEL)
    private String description;


    public ClaimCheck() {
        //Needed for serialization
    }

    public ClaimCheck(Step step) {
        super(step);
    }

    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getDefaultRepresenterProperties();
        if (this.operation != null) {
            properties.put(OPERATION_LABEL, this.operation);
        }
        if (this.key != null) {
            properties.put(KEY_LABEL, this.key);
        }
        if (this.filter != null) {
            properties.put(FILTER_LABEL, this.filter);
        }
        if (this.aggregationStrategy != null) {
            properties.put(AGGREGATION_STRATEGY_LABEL2, this.aggregationStrategy);
        }
        if (this.aggregationStrategyMethodName != null) {
            properties.put(AGGREGATION_STRATEGY_METHOD_NAME_LABEL2, this.aggregationStrategyMethodName);
        }
        return properties;
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case OPERATION_LABEL:
                this.setOperation(parameter.getValue().toString());
                break;
            case KEY_LABEL:
                this.setKey(parameter.getValue().toString());
                break;
            case FILTER_LABEL:
                this.setFilter(parameter.getValue().toString());
                break;
            case AGGREGATION_STRATEGY_LABEL:
            case AGGREGATION_STRATEGY_LABEL2:
                this.setAggregationStrategy(parameter.getValue().toString());
                break;
            case AGGREGATION_STRATEGY_METHOD_NAME_LABEL:
            case AGGREGATION_STRATEGY_METHOD_NAME_LABEL2:
                this.setAggregationStrategyMethodName(parameter.getValue().toString());
                break;
            case DESCRIPTION_LABEL:
                this.setDescription(parameter.getValue().toString());
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case OPERATION_LABEL:
                parameter.setValue(this.operation);
                break;
            case KEY_LABEL:
                parameter.setValue(this.key);
                break;
            case FILTER_LABEL:
                parameter.setValue(this.filter);
                break;
            case AGGREGATION_STRATEGY_LABEL:
            case AGGREGATION_STRATEGY_LABEL2:
                parameter.setValue(this.aggregationStrategy);
                break;
            case AGGREGATION_STRATEGY_METHOD_NAME_LABEL:
            case AGGREGATION_STRATEGY_METHOD_NAME_LABEL2:
                parameter.setValue(this.aggregationStrategyMethodName);
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.description);
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(final String operation) {
        this.operation = operation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(final String filter) {
        this.filter = filter;
    }

    public String getAggregationStrategy() {
        return aggregationStrategy;
    }

    public void setAggregationStrategy(final String aggregationStrategy) {
        this.aggregationStrategy = aggregationStrategy;
    }

    public String getAggregationStrategyMethodName() {
        return aggregationStrategyMethodName;
    }

    public void setAggregationStrategyMethodName(final String aggregationStrategyMethodName) {
        this.aggregationStrategyMethodName = aggregationStrategyMethodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
