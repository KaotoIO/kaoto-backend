package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.camel.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class Delay extends EIPStep {
    public static final String EXPRESSION_LABEL = "expression";
    public static final String ASYNC_DELAYED_LABEL = "asyncDelayed";
    public static final String ASYNC_DELAYED_LABEL2 = "async-delayed";
    public static final String CALLER_RUNS_WHEN_REJECTED_LABEL = "callerRunsWhenRejected";
    public static final String CALLER_RUNS_WHEN_REJECTED_LABEL2 = "caller-runs-when-rejected";
    public static final String EXECUTOR_SERVICE_LABEL = "executorService";
    public static final String EXECUTOR_SERVICE_LABEL2 = "executor-service";
    public static final String DESCRIPTION_LABEL = "description";

    @JsonProperty(EXPRESSION_LABEL)
    private Expression expression;

    @JsonProperty(ASYNC_DELAYED_LABEL2)
    private Boolean asyncDelayed;

    @JsonProperty(CALLER_RUNS_WHEN_REJECTED_LABEL2)
    private Boolean callerRunsWhenRejected;

    @JsonProperty(EXECUTOR_SERVICE_LABEL2)
    private Map<String, String> executorService;

    @JsonProperty(DESCRIPTION_LABEL)
    private Map<String, String> description;


    public Delay() {
         //Needed for serialization
    }

    public Delay(Step step) {
        super(step);
    }

    @Override
    protected void assignAttribute(final Parameter<?> parameter) {
        switch (parameter.getId()) {
            case EXPRESSION_LABEL:
                this.setExpression(new Expression(parameter.getValue()));
                break;
            case ASYNC_DELAYED_LABEL:
            case ASYNC_DELAYED_LABEL2:
                this.setAsyncDelayed(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case CALLER_RUNS_WHEN_REJECTED_LABEL:
            case CALLER_RUNS_WHEN_REJECTED_LABEL2:
                this.setCallerRunsWhenRejected(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case EXECUTOR_SERVICE_LABEL:
            case EXECUTOR_SERVICE_LABEL2:
                this.setExecutorService((Map<String, String>) parameter.getValue());
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                log.error("Unknown property: " + parameter.getId());
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getDefaultRepresenterProperties();
        if (this.expression != null) {
            properties.put(EXPRESSION_LABEL, this.expression);
        }
        if (this.asyncDelayed != null) {
            properties.put(ASYNC_DELAYED_LABEL2, this.asyncDelayed);
        }
        if (this.callerRunsWhenRejected != null) {
            properties.put(CALLER_RUNS_WHEN_REJECTED_LABEL2, this.callerRunsWhenRejected);
        }
        if (this.executorService != null) {
            properties.put(EXECUTOR_SERVICE_LABEL2, this.executorService);
        }
        if (this.description != null) {
            properties.put(DESCRIPTION_LABEL, this.description);
        }
        return properties;
    }

    @Override
    protected void assignProperty(final Parameter<?> parameter) {
        switch (parameter.getId()) {
            case EXPRESSION_LABEL:
                parameter.setValue(this.expression);
                break;
            case ASYNC_DELAYED_LABEL:
            case ASYNC_DELAYED_LABEL2:
                parameter.setValue(this.asyncDelayed);
                break;
            case CALLER_RUNS_WHEN_REJECTED_LABEL:
            case CALLER_RUNS_WHEN_REJECTED_LABEL2:
                parameter.setValue(this.callerRunsWhenRejected);
                break;
            case EXECUTOR_SERVICE_LABEL:
            case EXECUTOR_SERVICE_LABEL2:
                parameter.setValue(this.executorService);
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.description);
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

    public Boolean getAsyncDelayed() {
        return asyncDelayed;
    }

    public void setAsyncDelayed(final Boolean asyncDelayed) {
        this.asyncDelayed = asyncDelayed;
    }

    public Boolean getCallerRunsWhenRejected() {
        return callerRunsWhenRejected;
    }

    public void setCallerRunsWhenRejected(final Boolean callerRunsWhenRejected) {
        this.callerRunsWhenRejected = callerRunsWhenRejected;
    }

    public Map<String, String> getExecutorService() {
        return executorService;
    }

    public void setExecutorService(final Map<String, String> executorService) {
        this.executorService = executorService;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }
}
