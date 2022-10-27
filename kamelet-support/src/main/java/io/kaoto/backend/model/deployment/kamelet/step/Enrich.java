package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.deployment.kamelet.Expression;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.LinkedHashMap;
import java.util.Map;


public class Enrich extends EIPStep {
    public static final String EXPRESSION_LABEL = "expression";
    public static final String AGGREGATION_STRATEGY_LABEL = "aggregation-strategy";
    public static final String AGGREGATION_STRATEGY_LABEL2 = "aggregationStrategy";
    public static final String AGGREGATION_STRATEGY_METHOD_NAME_LABEL2 = "aggregationStrategyMethodName";
    public static final String AGGREGATION_STRATEGY_METHOD_NAME_LABEL = "aggregation-strategy-method-name";
    public static final String AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL = "aggregation-strategy-method-allow-null";
    public static final String AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL2 = "aggregationStrategyMethodAllowNull";
    public static final String AGGREGATION_ON_EXCEPTION_LABEL = "aggregate-on-exception";
    public static final String AGGREGATION_ON_EXCEPTION_LABEL2 = "aggregateOnException";
    public static final String SHARE_UNIT_OF_WORK_LABEL = "share-unit-of-work";
    public static final String SHARE_UNIT_OF_WORK_LABEL2 = "shareUnitOfWork";
    public static final String CACHE_SIZE_LABEL = "cache-size";
    public static final String CACHE_SIZE_LABEL2 = "cacheSize";
    public static final String IGNORE_INVALID_ENDPOINT_LABEL = "ignore-invalid-endpoint";
    public static final String IGNORE_INVALID_ENDPOINT_LABEL2 = "ignoreInvalidEndpoint";
    public static final String ALLOW_OPTIMISED_COMPONENTS = "allow-optimised-components";
    public static final String ALLOW_OPTIMISED_COMPONENTS2 = "allowOptimisedComponents";
    public static final String DESCRIPTION_LABEL = "description";

    @JsonProperty(EXPRESSION_LABEL)
    private Expression expression;

    @JsonProperty(AGGREGATION_STRATEGY_LABEL)
    private Object aggregationStrategy;

    @JsonProperty(AGGREGATION_STRATEGY_METHOD_NAME_LABEL)
    private String aggregationStrategyMethodName;

    @JsonProperty(AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL)
    private Boolean aggregationStrategyMethodAllowNull;

    @JsonProperty(AGGREGATION_ON_EXCEPTION_LABEL)
    private Boolean aggregateOnException;

    @JsonProperty(SHARE_UNIT_OF_WORK_LABEL)
    private Boolean shareUnitOfWork;

    @JsonProperty(CACHE_SIZE_LABEL)
    private Integer cacheSize;

    @JsonProperty(IGNORE_INVALID_ENDPOINT_LABEL)
    private Boolean ignoreInvalidEndpoint;

    @JsonProperty(ALLOW_OPTIMISED_COMPONENTS)
    private Boolean allowOptimisedComponents;

    @JsonProperty(DESCRIPTION_LABEL)
    private Map<String, String> description;


    public Enrich() {
         //Needed for serialization
    }

    public Enrich(Step step) {
        super(step);
    }

    @Override
    Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        if (this.expression != null) {
            properties.put(EXPRESSION_LABEL, this.expression);
        }
        if (this.aggregationStrategy != null) {
            properties.put(AGGREGATION_STRATEGY_LABEL, this.aggregationStrategy);
        }
        if (this.aggregationStrategyMethodName != null) {
            properties.put(AGGREGATION_STRATEGY_METHOD_NAME_LABEL, this.aggregationStrategyMethodName);
        }
        if (this.aggregationStrategyMethodAllowNull != null) {
            properties.put(AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL, this.aggregationStrategyMethodAllowNull);
        }
        if (this.aggregateOnException != null) {
            properties.put(AGGREGATION_ON_EXCEPTION_LABEL, this.aggregateOnException);
        }
        if (this.shareUnitOfWork != null) {
            properties.put(SHARE_UNIT_OF_WORK_LABEL, this.shareUnitOfWork);
        }
        if (this.cacheSize != null) {
            properties.put(CACHE_SIZE_LABEL, this.cacheSize);
        }
        if (this.ignoreInvalidEndpoint != null) {
            properties.put(IGNORE_INVALID_ENDPOINT_LABEL, this.ignoreInvalidEndpoint);
        }
        if (this.allowOptimisedComponents != null) {
            properties.put(ALLOW_OPTIMISED_COMPONENTS, this.allowOptimisedComponents);
        }
        if (this.description != null) {
            properties.put(DESCRIPTION_LABEL, this.description);
        }
        return properties;
    }

    @Override
    void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case EXPRESSION_LABEL:
                parameter.setValue(this.expression);
                break;
            case AGGREGATION_STRATEGY_LABEL:
            case AGGREGATION_STRATEGY_LABEL2:
                parameter.setValue(this.aggregationStrategy);
                break;
            case AGGREGATION_STRATEGY_METHOD_NAME_LABEL:
            case AGGREGATION_STRATEGY_METHOD_NAME_LABEL2:
                parameter.setValue(this.aggregationStrategyMethodName);
                break;
            case AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL:
            case AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL2:
                parameter.setValue(this.aggregationStrategyMethodAllowNull);
                break;
            case AGGREGATION_ON_EXCEPTION_LABEL:
            case AGGREGATION_ON_EXCEPTION_LABEL2:
                parameter.setValue(this.aggregateOnException);
                break;
            case SHARE_UNIT_OF_WORK_LABEL:
            case SHARE_UNIT_OF_WORK_LABEL2:
                parameter.setValue(this.shareUnitOfWork);
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                parameter.setValue(this.cacheSize);
                break;
            case IGNORE_INVALID_ENDPOINT_LABEL:
            case IGNORE_INVALID_ENDPOINT_LABEL2:
                parameter.setValue(this.ignoreInvalidEndpoint);
                break;
            case ALLOW_OPTIMISED_COMPONENTS:
            case ALLOW_OPTIMISED_COMPONENTS2:
                parameter.setValue(this.allowOptimisedComponents);
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.description);
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }


    @Override
    void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case EXPRESSION_LABEL:
                this.setExpression(new Expression(parameter.getValue()));
                break;
            case AGGREGATION_STRATEGY_LABEL:
            case AGGREGATION_STRATEGY_LABEL2:
                this.setAggregationStrategy(parameter.getValue().toString());
                break;
            case AGGREGATION_STRATEGY_METHOD_NAME_LABEL2:
            case AGGREGATION_STRATEGY_METHOD_NAME_LABEL:
                this.setAggregationStrategyMethodName(parameter.getValue().toString());
                break;
            case AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL:
            case AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL2:
                this.setAggregationStrategyMethodAllowNull(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case AGGREGATION_ON_EXCEPTION_LABEL:
            case AGGREGATION_ON_EXCEPTION_LABEL2:
                this.setAggregateOnException(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case SHARE_UNIT_OF_WORK_LABEL:
            case SHARE_UNIT_OF_WORK_LABEL2:
                this.setShareUnitOfWork(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                this.setCacheSize(Integer.valueOf(parameter.getValue().toString()));
                break;
            case IGNORE_INVALID_ENDPOINT_LABEL:
            case IGNORE_INVALID_ENDPOINT_LABEL2:
                this.setIgnoreInvalidEndpoint(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case ALLOW_OPTIMISED_COMPONENTS:
            case ALLOW_OPTIMISED_COMPONENTS2:
                this.setAllowOptimisedComponents(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }
    
    public Expression getExpression() {
        return expression;
    }

    public void setExpression(final Expression expression) {
        this.expression = expression;
    }

    public Object getAggregationStrategy() {
        return aggregationStrategy;
    }

    public void setAggregationStrategy(final Object aggregationStrategy) {
        this.aggregationStrategy = aggregationStrategy;
    }

    public String getAggregationStrategyMethodName() {
        return aggregationStrategyMethodName;
    }

    public void setAggregationStrategyMethodName(final String aggregationStrategyMethodName) {
        this.aggregationStrategyMethodName = aggregationStrategyMethodName;
    }

    public Boolean getAggregationStrategyMethodAllowNull() {
        return aggregationStrategyMethodAllowNull;
    }

    public void setAggregationStrategyMethodAllowNull(final Boolean aggregationStrategyMethodAllowNull) {
        this.aggregationStrategyMethodAllowNull = aggregationStrategyMethodAllowNull;
    }

    public Boolean getAggregateOnException() {
        return aggregateOnException;
    }

    public void setAggregateOnException(final Boolean aggregateOnException) {
        this.aggregateOnException = aggregateOnException;
    }

    public Boolean getShareUnitOfWork() {
        return shareUnitOfWork;
    }

    public void setShareUnitOfWork(final Boolean shareUnitOfWork) {
        this.shareUnitOfWork = shareUnitOfWork;
    }

    public Integer getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(final Integer cacheSize) {
        this.cacheSize = cacheSize;
    }

    public Boolean getIgnoreInvalidEndpoint() {
        return ignoreInvalidEndpoint;
    }

    public void setIgnoreInvalidEndpoint(final Boolean ignoreInvalidEndpoint) {
        this.ignoreInvalidEndpoint = ignoreInvalidEndpoint;
    }

    public Boolean getAllowOptimisedComponents() {
        return allowOptimisedComponents;
    }

    public void setAllowOptimisedComponents(final Boolean allowOptimisedComponents) {
        this.allowOptimisedComponents = allowOptimisedComponents;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }
}
