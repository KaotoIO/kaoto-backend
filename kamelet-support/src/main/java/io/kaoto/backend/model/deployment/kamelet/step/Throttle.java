package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Throttle extends Expression {

    public static final String DESCRIPTION_LABEL = "description";

    public static final String CORRELATION_EXPRESSION_LABEL2 = "correlationExpression";
    public static final String CORRELATION_EXPRESSION_LABEL = "correlation-expression";

    public static final String EXECUTOR_SERVICE_LABEL2 = "executorService";
    public static final String EXECUTOR_SERVICE_LABEL = "executor-service";

    public static final String TIME_PERIOD_MILLIS_LABEL2 = "timePeriodMillis";
    public static final String TIME_PERIOD_MILLIS_LABEL = "time-period-millis";

    public static final String ASYNC_DELAYED_LABEL2 = "asyncDelayed";
    public static final String ASYNC_DELAYED_LABEL = "async-delayed";

    public static final String CALLER_RUNS_WHEN_REJECTED_LABEL = "caller-runs-when-rejected";
    public static final String CALLER_RUNS_WHEN_REJECTED_LABEL2 = "callerRunsWhenRejected";

    public static final String REJECT_EXECUTION_LABEL = "reject-execution";
    public static final String REJECT_EXECUTION_LABEL2 = "rejectExecution";

    private Expression correlationExpression;
    private Object executorService;
    private Integer timePeriodMillis;
    private Boolean asyncDelayed;
    private Boolean callerRunsWhenRejected;
    private Boolean rejectExecution;
    private Map<String, String> description;

    public Throttle() {
        //Empty for serialization purposes
    }

    @JsonCreator
    public Throttle(final @JsonProperty(EXPRESSION_LABEL) Expression expression,
                    final @JsonProperty(CONSTANT_LABEL) String constant,
                    final @JsonProperty(SIMPLE_LABEL) String simple,
                    final @JsonProperty(JQ_LABEL) String jq,
                    final @JsonProperty(CORRELATION_EXPRESSION_LABEL) Expression correlationExpression,
                    final @JsonProperty(CORRELATION_EXPRESSION_LABEL2) Expression correlationExpression2,
                    final @JsonProperty(EXECUTOR_SERVICE_LABEL) Object executorService,
                    final @JsonProperty(EXECUTOR_SERVICE_LABEL2) Object executorService2,
                    final @JsonProperty(TIME_PERIOD_MILLIS_LABEL) Integer timePeriodMillis,
                    final @JsonProperty(TIME_PERIOD_MILLIS_LABEL2) Integer timePeriodMillis2,
                    final @JsonProperty(ASYNC_DELAYED_LABEL) Boolean asyncDelayed,
                    final @JsonProperty(ASYNC_DELAYED_LABEL2) Boolean asyncDelayed2,
                    final @JsonProperty(CALLER_RUNS_WHEN_REJECTED_LABEL) Boolean callerRunsWhenRejected,
                    final @JsonProperty(CALLER_RUNS_WHEN_REJECTED_LABEL2) Boolean callerRunsWhenRejected2,
                    final @JsonProperty(REJECT_EXECUTION_LABEL) Boolean rejectExecution,
                    final @JsonProperty(REJECT_EXECUTION_LABEL2) Boolean rejectExecution2,
                    final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description) {
        super(expression, constant, simple, jq, null);
        setCorrelationExpression(correlationExpression != null ? correlationExpression : correlationExpression2);
        setExecutorService(executorService != null ? executorService : executorService2);
        setTimePeriodMillis(timePeriodMillis != null ? timePeriodMillis : timePeriodMillis2);
        setAsyncDelayed(asyncDelayed != null ? asyncDelayed : asyncDelayed2);
        setCallerRunsWhenRejected(callerRunsWhenRejected != null ? callerRunsWhenRejected : callerRunsWhenRejected2);
        setRejectExecution(rejectExecution != null ? rejectExecution : rejectExecution2);
        setDescription(description);
    }

    public Throttle(Step step) {
        super(step);
    }


    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getRepresenterProperties();

        if (this.getCorrelationExpression() != null) {
            properties.put(CORRELATION_EXPRESSION_LABEL, this.getCorrelationExpression());
        }
        if (this.getExecutorService() != null) {
            properties.put(EXECUTOR_SERVICE_LABEL, this.getExecutorService());
        }
        if (this.getTimePeriodMillis() != null) {
            properties.put(TIME_PERIOD_MILLIS_LABEL, this.getTimePeriodMillis());
        }
        if (this.getAsyncDelayed() != null) {
            properties.put(ASYNC_DELAYED_LABEL, this.getAsyncDelayed());
        }
        if (this.getCallerRunsWhenRejected() != null) {
            properties.put(CALLER_RUNS_WHEN_REJECTED_LABEL, this.getCallerRunsWhenRejected());
        }
        if (this.getRejectExecution() != null) {
            properties.put(REJECT_EXECUTION_LABEL, this.getRejectExecution());
        }
        if (this.getDescription() != null) {
            properties.put(DESCRIPTION_LABEL, this.getDescription());
        }

        return properties;
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        super.assignAttribute(parameter);
        switch (parameter.getId()) {
            case CORRELATION_EXPRESSION_LABEL:
            case CORRELATION_EXPRESSION_LABEL2:
                this.setCorrelationExpression(new Expression(parameter.getValue()));
                break;
            case EXECUTOR_SERVICE_LABEL:
            case EXECUTOR_SERVICE_LABEL2:
                this.setExecutorService(parameter.getValue());
                break;
            case TIME_PERIOD_MILLIS_LABEL:
            case TIME_PERIOD_MILLIS_LABEL2:
                this.setTimePeriodMillis(Integer.valueOf(String.valueOf(parameter.getValue())));
                break;
            case ASYNC_DELAYED_LABEL:
            case ASYNC_DELAYED_LABEL2:
                this.setAsyncDelayed(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case CALLER_RUNS_WHEN_REJECTED_LABEL:
            case CALLER_RUNS_WHEN_REJECTED_LABEL2:
                this.setCallerRunsWhenRejected(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case REJECT_EXECUTION_LABEL:
            case REJECT_EXECUTION_LABEL2:
                this.setRejectExecution(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                break;
        }
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        super.assignProperty(parameter);
        switch (parameter.getId()) {
            case CORRELATION_EXPRESSION_LABEL:
            case CORRELATION_EXPRESSION_LABEL2:
                parameter.setValue(this.getCorrelationExpression());
                break;
            case EXECUTOR_SERVICE_LABEL:
            case EXECUTOR_SERVICE_LABEL2:
                parameter.setValue(this.getExecutorService());
                break;
            case TIME_PERIOD_MILLIS_LABEL:
            case TIME_PERIOD_MILLIS_LABEL2:
                parameter.setValue(this.getTimePeriodMillis());
                break;
            case ASYNC_DELAYED_LABEL:
            case ASYNC_DELAYED_LABEL2:
                parameter.setValue(this.getAsyncDelayed());
                break;
            case CALLER_RUNS_WHEN_REJECTED_LABEL:
            case CALLER_RUNS_WHEN_REJECTED_LABEL2:
                parameter.setValue(this.getCallerRunsWhenRejected());
                break;
            case REJECT_EXECUTION_LABEL:
            case REJECT_EXECUTION_LABEL2:
                parameter.setValue(this.getRejectExecution());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.getDescription());
                break;
            default:
                break;
        }
    }

    public Expression getCorrelationExpression() {
        return correlationExpression;
    }

    public void setCorrelationExpression(final Expression correlationExpression) {
        this.correlationExpression = correlationExpression;
    }

    public Object getExecutorService() {
        return executorService;
    }

    public void setExecutorService(final Object executorService) {
        this.executorService = executorService;
    }

    public Integer getTimePeriodMillis() {
        return timePeriodMillis;
    }

    public void setTimePeriodMillis(final Integer timePeriodMillis) {
        this.timePeriodMillis = timePeriodMillis;
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

    public Boolean getRejectExecution() {
        return rejectExecution;
    }

    public void setRejectExecution(final Boolean rejectExecution) {
        this.rejectExecution = rejectExecution;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }
}
