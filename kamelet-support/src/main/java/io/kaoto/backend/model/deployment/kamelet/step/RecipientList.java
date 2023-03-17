package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class RecipientList extends Expression {

    public static final String DELIMITER_LABEL = "delimiter";

    public static final String PARALLEL_PROCESSING_LABEL = "parallel-processing";
    public static final String PARALLEL_PROCESSING_LABEL2 = "parallelProcessing";

    public static final String STRATEGY_REF_LABEL = "strategy-ref";
    public static final String STRATEGY_REF_LABEL2 = "strategyRef";

    public static final String STRATEGY_METHOD_NAME_LABEL2 = "strategyMethodName";
    public static final String STRATEGY_METHOD_NAME_LABEL = "strategy-method-name";

    public static final String STRATEGY_METHOD_ALLOW_NULL_LABEL = "strategy-method-allow-null";
    public static final String STRATEGY_METHOD_ALLOW_NULL_LABEL2 = "strategyMethodAllowNull";

    public static final String EXECUTOR_SERVICE_REF_LABEL = "executor-service-ref";
    public static final String EXECUTOR_SERVICE_REF_LABEL2 = "executorServiceRef";

    public static final String STOP_ON_EXCEPTION_LABEL = "stop-on-exception";
    public static final String STOP_ON_EXCEPTION_LABEL2 = "stopOnException";

    public static final String IGNORE_INVALID_ENDPOINTS_LABEL = "ignore-invalid-endpoints";
    public static final String IGNORE_INVALID_ENDPOINTS_LABEL2 = "ignoreInvalidEndpoints";

    public static final String STREAMING_LABEL = "streaming";

    public static final String TIMEOUT_LABEL = "timeout";

    public static final String ON_PREPARE_REF_LABEL = "on-prepare-ref";
    public static final String ON_PREPARE_REF_LABEL2 = "onPrepareRef";

    public static final String SHARE_UNIT_OF_WORK_LABEL = "share-unit-of-work";
    public static final String SHARE_UNIT_OF_WORK_LABEL2 = "shareUnitOfWork";

    public static final String CACHE_SIZE_LABEL = "cache-size";
    public static final String CACHE_SIZE_LABEL2 = "cacheSize";

    public static final String PARALLEL_AGGREGATE_LABEL = "parallel-aggregate-components";
    public static final String PARALLEL_AGGREGATE_LABEL2 = "parallelAggregateComponents";

    public static final String DESCRIPTION_LABEL = "description";

    private String delimiter;

    private Boolean parallelProcessing;

    private String strategyRef;

    private String strategyMethodName;

    private Boolean strategyMethodAllowNull;

    private String executorServiceRef;

    private Boolean stopOnException;

    private Boolean ignoreInvalidEndpoints;

    private Boolean streaming;

    private String timeout;

    private String onPrepareRef;

    private Boolean shareUnitOfWork;

    private Integer cacheSize;

    private Boolean parallelAggregate;

    private Map<String, String> description;


    public RecipientList() {
         //Needed for serialization
    }

    @JsonCreator
    public RecipientList(final @JsonProperty(EXPRESSION_LABEL) Expression expression,
                         final @JsonProperty(DELIMITER_LABEL)  String delimiter,
                         final @JsonProperty(PARALLEL_PROCESSING_LABEL) Boolean parallelProcessing,
                         final @JsonProperty(PARALLEL_PROCESSING_LABEL2) Boolean parallelProcessing2,
                         final @JsonProperty(STRATEGY_REF_LABEL) String strategyRef,
                         final @JsonProperty(STRATEGY_REF_LABEL2) String strategyRef2,
                         final @JsonProperty(STRATEGY_METHOD_NAME_LABEL) String strategyMethodName,
                         final @JsonProperty(STRATEGY_METHOD_NAME_LABEL2) String strategyMethodName2,
                         final @JsonProperty(STRATEGY_METHOD_ALLOW_NULL_LABEL) Boolean strategyMethodAllowNull,
                         final @JsonProperty(STRATEGY_METHOD_ALLOW_NULL_LABEL2) Boolean strategyMethodAllowNull2,
                         final @JsonProperty(EXECUTOR_SERVICE_REF_LABEL) String executorServiceRef,
                         final @JsonProperty(EXECUTOR_SERVICE_REF_LABEL2) String executorServiceRef2,
                         final @JsonProperty(STOP_ON_EXCEPTION_LABEL) Boolean stopOnException,
                         final @JsonProperty(STOP_ON_EXCEPTION_LABEL2) Boolean stopOnException2,
                         final @JsonProperty(IGNORE_INVALID_ENDPOINTS_LABEL) Boolean ignoreInvalidEndpoints,
                         final @JsonProperty(IGNORE_INVALID_ENDPOINTS_LABEL2) Boolean ignoreInvalidEndpoints2,
                         final @JsonProperty(STREAMING_LABEL) Boolean streaming,
                         final @JsonProperty(TIMEOUT_LABEL) String timeout,
                         final @JsonProperty(ON_PREPARE_REF_LABEL) String onPrepareRef,
                         final @JsonProperty(ON_PREPARE_REF_LABEL2) String onPrepareRef2,
                         final @JsonProperty(SHARE_UNIT_OF_WORK_LABEL) Boolean shareUnitOfWork,
                         final @JsonProperty(SHARE_UNIT_OF_WORK_LABEL2) Boolean shareUnitOfWork2,
                         final @JsonProperty(CACHE_SIZE_LABEL) Integer cacheSize,
                         final @JsonProperty(CACHE_SIZE_LABEL2) Integer cacheSize2,
                         final @JsonProperty(PARALLEL_AGGREGATE_LABEL) Boolean parallelAggregate,
                         final @JsonProperty(PARALLEL_AGGREGATE_LABEL2) Boolean parallelAggregate2,
                         final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description,
                         final @JsonProperty(SIMPLE_LABEL) String simple,
                         final @JsonProperty(JQ_LABEL) String jq,
                         final @JsonProperty(CONSTANT_LABEL) String constant,
                         final @JsonProperty("id") String id) {
        super(expression, constant, simple, jq, null, null, null, null, id);
        setDelimiter(delimiter);
        setParallelProcessing(parallelProcessing != null ? parallelProcessing : parallelProcessing2);
        setStrategyRef(strategyRef != null ? strategyRef : strategyRef2);
        setStrategyMethodName(strategyMethodName != null ? strategyMethodName : strategyMethodName2);
        setStrategyMethodAllowNull(strategyMethodAllowNull != null ?
                strategyMethodAllowNull : strategyMethodAllowNull2);
        setExecutorServiceRef(executorServiceRef != null ? executorServiceRef : executorServiceRef2);
        setStopOnException(stopOnException != null ? stopOnException : stopOnException2);
        setIgnoreInvalidEndpoints(ignoreInvalidEndpoints != null ? ignoreInvalidEndpoints : ignoreInvalidEndpoints2);
        setStreaming(streaming);
        setTimeout(timeout);
        setOnPrepareRef(onPrepareRef != null ? onPrepareRef : onPrepareRef2);
        setShareUnitOfWork(shareUnitOfWork != null ? shareUnitOfWork : shareUnitOfWork2);
        setCacheSize(cacheSize != null ? cacheSize : cacheSize2);
        setParallelAggregate(parallelAggregate != null ? parallelAggregate : parallelAggregate2);
        setDescription(description);
    }

    public RecipientList(Step step) {
        super(step);
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getRepresenterProperties();

        if (this.getDelimiter() != null) {
            properties.put(DELIMITER_LABEL, this.getDelimiter());
        }

        if (this.getParallelProcessing() != null) {
            properties.put(PARALLEL_PROCESSING_LABEL, this.getParallelProcessing());
        }

        if (this.getStrategyRef() != null) {
            properties.put(STRATEGY_REF_LABEL, this.getStrategyRef());
        }

        if (this.getStrategyMethodName() != null) {
            properties.put(STRATEGY_METHOD_NAME_LABEL, this.getStrategyMethodName());
        }
        if (this.getStrategyMethodAllowNull() != null) {
            properties.put(STRATEGY_METHOD_ALLOW_NULL_LABEL, this.getStrategyMethodAllowNull());
        }

        if (this.getExecutorServiceRef() != null) {
            properties.put(EXECUTOR_SERVICE_REF_LABEL, this.getExecutorServiceRef());
        }

        if (this.getStopOnException() != null) {
            properties.put(STOP_ON_EXCEPTION_LABEL, this.getStopOnException());
        }

        if (this.getIgnoreInvalidEndpoints() != null) {
            properties.put(IGNORE_INVALID_ENDPOINTS_LABEL, this.getIgnoreInvalidEndpoints());
        }

        if (this.getStreaming() != null) {
            properties.put(STREAMING_LABEL, this.getStreaming());
        }

        if (this.getTimeout() != null) {
            properties.put(TIMEOUT_LABEL, this.getTimeout());
        }

        if (this.getOnPrepareRef() != null) {
            properties.put(ON_PREPARE_REF_LABEL, this.getOnPrepareRef());
        }

        if (this.getShareUnitOfWork() != null) {
            properties.put(SHARE_UNIT_OF_WORK_LABEL, this.getShareUnitOfWork());
        }

        if (this.getCacheSize() != null) {
            properties.put(CACHE_SIZE_LABEL, this.getCacheSize());
        }

        if (this.getParallelAggregate() != null) {
            properties.put(PARALLEL_AGGREGATE_LABEL, this.getParallelAggregate());
        }

        if (this.description != null) {
            properties.put(DESCRIPTION_LABEL, this.description);
        }
        return properties;
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        super.assignProperty(parameter);
        switch (parameter.getId()) {
            case DELIMITER_LABEL:
                parameter.setValue(this.getDelimiter());
                break;
            case PARALLEL_PROCESSING_LABEL:
            case PARALLEL_PROCESSING_LABEL2:
                parameter.setValue(this.getParallelProcessing());
                break;
            case STRATEGY_REF_LABEL:
            case STRATEGY_REF_LABEL2:
                parameter.setValue(this.getStrategyRef());
                break;
            case STRATEGY_METHOD_NAME_LABEL2:
            case STRATEGY_METHOD_NAME_LABEL:
                parameter.setValue(this.getStrategyMethodName());
                break;
            case STRATEGY_METHOD_ALLOW_NULL_LABEL:
            case STRATEGY_METHOD_ALLOW_NULL_LABEL2:
                parameter.setValue(this.getStrategyMethodAllowNull());
                break;
            case EXECUTOR_SERVICE_REF_LABEL:
            case EXECUTOR_SERVICE_REF_LABEL2:
                parameter.setValue(this.getExecutorServiceRef());
                break;
            case STOP_ON_EXCEPTION_LABEL:
            case STOP_ON_EXCEPTION_LABEL2:
                parameter.setValue(this.getStopOnException());
                break;
            case IGNORE_INVALID_ENDPOINTS_LABEL:
            case IGNORE_INVALID_ENDPOINTS_LABEL2:
                parameter.setValue(this.getIgnoreInvalidEndpoints());
                break;
            case STREAMING_LABEL:
                parameter.setValue(this.getStreaming());
                break;
            case TIMEOUT_LABEL:
                parameter.setValue(this.getTimeout());
                break;
            case ON_PREPARE_REF_LABEL:
            case ON_PREPARE_REF_LABEL2:
                parameter.setValue(this.getOnPrepareRef());
                break;
            case SHARE_UNIT_OF_WORK_LABEL:
            case SHARE_UNIT_OF_WORK_LABEL2:
                parameter.setValue(this.getShareUnitOfWork());
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                parameter.setValue(this.getCacheSize());
                break;
            case PARALLEL_AGGREGATE_LABEL:
            case PARALLEL_AGGREGATE_LABEL2:
                parameter.setValue(this.getParallelAggregate());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.getDescription());
                break;
            default:
                break;
        }
    }


    @Override
    protected void assignAttribute(final Parameter parameter) {
        super.assignAttribute(parameter);
        switch (parameter.getId()) {
            case DELIMITER_LABEL:
                this.setDelimiter(String.valueOf(parameter.getValue()));
                break;
            case PARALLEL_PROCESSING_LABEL:
            case PARALLEL_PROCESSING_LABEL2:
                this.setParallelProcessing(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case STRATEGY_REF_LABEL:
            case STRATEGY_REF_LABEL2:
                this.setStrategyRef(String.valueOf(parameter.getValue()));
                break;
            case STRATEGY_METHOD_NAME_LABEL2:
            case STRATEGY_METHOD_NAME_LABEL:
                this.setStrategyMethodName(String.valueOf(parameter.getValue()));
                break;
            case STRATEGY_METHOD_ALLOW_NULL_LABEL:
            case STRATEGY_METHOD_ALLOW_NULL_LABEL2:
                this.setStrategyMethodAllowNull(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case EXECUTOR_SERVICE_REF_LABEL:
            case EXECUTOR_SERVICE_REF_LABEL2:
                this.setExecutorServiceRef(String.valueOf(parameter.getValue()));
                break;
            case STOP_ON_EXCEPTION_LABEL:
            case STOP_ON_EXCEPTION_LABEL2:
                this.setStopOnException(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case IGNORE_INVALID_ENDPOINTS_LABEL:
            case IGNORE_INVALID_ENDPOINTS_LABEL2:
                this.setIgnoreInvalidEndpoints(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case STREAMING_LABEL:
                this.setStreaming(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case TIMEOUT_LABEL:
                this.setTimeout(String.valueOf(parameter.getValue()));
                break;
            case ON_PREPARE_REF_LABEL:
            case ON_PREPARE_REF_LABEL2:
                this.setOnPrepareRef(String.valueOf(parameter.getValue()));
                break;
            case SHARE_UNIT_OF_WORK_LABEL:
            case SHARE_UNIT_OF_WORK_LABEL2:
                this.setShareUnitOfWork(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case CACHE_SIZE_LABEL:
            case CACHE_SIZE_LABEL2:
                this.setCacheSize(Integer.valueOf(String.valueOf(parameter.getValue())));
                break;
            case PARALLEL_AGGREGATE_LABEL:
            case PARALLEL_AGGREGATE_LABEL2:
                this.setParallelAggregate(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map) parameter.getValue());
                break;
            default:
                break;
        }
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(final String delimiter) {
        this.delimiter = delimiter;
    }

    public Boolean getParallelProcessing() {
        return parallelProcessing;
    }

    public void setParallelProcessing(final Boolean parallelProcessing) {
        this.parallelProcessing = parallelProcessing;
    }

    public String getStrategyRef() {
        return strategyRef;
    }

    public void setStrategyRef(final String strategyRef) {
        this.strategyRef = strategyRef;
    }

    public String getStrategyMethodName() {
        return strategyMethodName;
    }

    public void setStrategyMethodName(final String strategyMethodName) {
        this.strategyMethodName = strategyMethodName;
    }

    public Boolean getStrategyMethodAllowNull() {
        return strategyMethodAllowNull;
    }

    public void setStrategyMethodAllowNull(final Boolean strategyMethodAllowNull) {
        this.strategyMethodAllowNull = strategyMethodAllowNull;
    }

    public String getExecutorServiceRef() {
        return executorServiceRef;
    }

    public void setExecutorServiceRef(final String executorServiceRef) {
        this.executorServiceRef = executorServiceRef;
    }

    public Boolean getStopOnException() {
        return stopOnException;
    }

    public void setStopOnException(final Boolean stopOnException) {
        this.stopOnException = stopOnException;
    }

    public Boolean getIgnoreInvalidEndpoints() {
        return ignoreInvalidEndpoints;
    }

    public void setIgnoreInvalidEndpoints(final Boolean ignoreInvalidEndpoints) {
        this.ignoreInvalidEndpoints = ignoreInvalidEndpoints;
    }

    public Boolean getStreaming() {
        return streaming;
    }

    public void setStreaming(final Boolean streaming) {
        this.streaming = streaming;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(final String timeout) {
        this.timeout = timeout;
    }

    public String getOnPrepareRef() {
        return onPrepareRef;
    }

    public void setOnPrepareRef(final String onPrepareRef) {
        this.onPrepareRef = onPrepareRef;
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

    public Boolean getParallelAggregate() {
        return parallelAggregate;
    }

    public void setParallelAggregate(final Boolean parallelAggregate) {
        this.parallelAggregate = parallelAggregate;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }
}
