package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// CPD-OFF
public class Multicast extends EIPStep {


    public static final String DESCRIPTION_LABEL = "description";

    public static final String STEPS_LABEL = "steps";

    public static final String AGGREGATION_STRATEGY_LABEL = "aggregationStrategy";
    public static final String AGGREGATION_STRATEGY_LABEL2 = "aggregation-strategy";

    public static final String AGGREGATION_STRATEGY_METHOD_NAME_LABEL = "aggregationStrategyMethodName";
    public static final String AGGREGATION_STRATEGY_METHOD_NAME_LABEL2 = "aggregation-strategy-method-name";

    public static final String AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL = "aggregationStrategyMethodAllowNull";
    public static final String AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL2 = "aggregation-strategy-method-allow-null";

    public static final String PARALLEL_AGGREGATE_LABEL = "parallelAggregate";
    public static final String PARALLEL_AGGREGATE_LABEL2 = "parallel-aggregate";

    public static final String PARALLEL_PROCESSING_LABEL = "parallelProcessing";
    public static final String PARALLEL_PROCESSING_LABEL2 = "parallel-processing";

    public static final String STREAMING_LABEL = "streaming";

    public static final String STOP_ON_EXCEPTION_LABEL2 = "stopOnException";
    public static final String STOP_ON_EXCEPTION_LABEL = "stop-on-exception";

    public static final String TIMEOUT_LABEL = "timeout";

    public static final String EXECUTOR_SERVICE_LABEL = "executorService";
    public static final String EXECUTOR_SERVICE_LABEL2 = "executor-service";

    public static final String ON_PREPARE_LABEL = "onPrepare";
    public static final String ON_PREPARE_LABEL2 = "on-prepare";

    public static final String SHARE_UNIT_OF_WORK_LABEL = "shareUnitOfWork";
    public static final String SHARE_UNIT_OF_WORK_LABEL2 = "share-init-of-work";

    private Map<String, Object> aggregationStrategy;

    private String aggregationStrategyMethodName;

    private Boolean aggregationStrategyMethodAllowNull;

    private Boolean parallelAggregate;

    private Boolean parallelProcessing;

    private Boolean streaming;

    private Boolean stopOnException;

    private String timeout;

    private Map<String, Object> executorService;

    private Map<String, Object> onPrepare;

    private Boolean shareUnitOfWork;

    private Map<String, String> description;

    private List<FlowStep> steps;


    @JsonCreator
    public Multicast(final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description,
                     final @JsonProperty(STEPS_LABEL) List<FlowStep> steps,
                     final @JsonProperty(AGGREGATION_STRATEGY_LABEL) Map<String, Object> aggregationStrategy,
                     final @JsonProperty(AGGREGATION_STRATEGY_LABEL2) Map<String, Object> aggregationStrategy2,
                     final @JsonProperty(AGGREGATION_STRATEGY_METHOD_NAME_LABEL) String aggregationStrategyMethodName,
                     final @JsonProperty(AGGREGATION_STRATEGY_METHOD_NAME_LABEL2) String aggregationStrategyMethodName2,
                     final @JsonProperty(AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL)
                         Boolean aggregationStrategyMethodAllowNull,
                     final @JsonProperty(AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL2)
                         Boolean aggregationStrategyMethodAllowNull2,
                     final @JsonProperty(PARALLEL_AGGREGATE_LABEL) Boolean parallelAggregate,
                     final @JsonProperty(PARALLEL_AGGREGATE_LABEL2) Boolean parallelAggregate2,
                     final @JsonProperty(PARALLEL_PROCESSING_LABEL) Boolean parallelProcessing,
                     final @JsonProperty(PARALLEL_PROCESSING_LABEL2) Boolean parallelProcessing2,
                     final @JsonProperty(STREAMING_LABEL) Boolean streaming,
                     final @JsonProperty(STOP_ON_EXCEPTION_LABEL2) Boolean stopOnException,
                     final @JsonProperty(STOP_ON_EXCEPTION_LABEL) Boolean stopOnException2,
                     final @JsonProperty(TIMEOUT_LABEL) String timeout,
                     final @JsonProperty(EXECUTOR_SERVICE_LABEL) Map<String, Object> executorService,
                     final @JsonProperty(EXECUTOR_SERVICE_LABEL2) Map<String, Object> executorService2,
                     final @JsonProperty(ON_PREPARE_LABEL) Map<String, Object> onPrepare,
                     final @JsonProperty(ON_PREPARE_LABEL2) Map<String, Object> onPrepare2,
                     final @JsonProperty(SHARE_UNIT_OF_WORK_LABEL) Boolean shareUnitOfWork,
                     final @JsonProperty(SHARE_UNIT_OF_WORK_LABEL2) Boolean shareUnitOfWork2) {
        super();
        setDescription(description);
        setSteps(steps);
        setAggregationStrategy(aggregationStrategy != null ? aggregationStrategy : aggregationStrategy2);
        setAggregationStrategyMethodName(aggregationStrategyMethodName != null ? aggregationStrategyMethodName :
                aggregationStrategyMethodName2);
        setAggregationStrategyMethodAllowNull(aggregationStrategyMethodAllowNull != null ?
                aggregationStrategyMethodAllowNull : aggregationStrategyMethodAllowNull2);
        setParallelAggregate(parallelAggregate != null ? parallelAggregate : parallelAggregate2);
        setParallelProcessing(parallelProcessing != null ? parallelProcessing : parallelProcessing2);
        setStreaming(streaming);
        setStopOnException(stopOnException != null ? stopOnException : stopOnException2);
        setTimeout(timeout);
        setExecutorService(executorService != null ? executorService : executorService2);
        setOnPrepare(onPrepare != null ? onPrepare : onPrepare2);
        setShareUnitOfWork(shareUnitOfWork != null ? shareUnitOfWork : shareUnitOfWork2);
    }

    public Multicast() {
        //Needed for serialization
    }

    public Multicast(Step step, final KamelPopulator kameletPopulator) {
        super(step);

        if (step.getBranches() != null && !step.getBranches().isEmpty()) {
            setSteps(kameletPopulator.processSteps(step.getBranches().get(0)));
        }
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        if (parameter.getValue() != null) {
            switch (parameter.getId()) {
                case DESCRIPTION_LABEL:
                    this.setDescription((Map<String, String>) parameter.getValue());
                    break;
                case AGGREGATION_STRATEGY_LABEL:
                case AGGREGATION_STRATEGY_LABEL2:
                    this.setAggregationStrategy((Map<String, Object>) parameter.getValue());
                    break;
                case AGGREGATION_STRATEGY_METHOD_NAME_LABEL:
                case AGGREGATION_STRATEGY_METHOD_NAME_LABEL2:
                    this.setAggregationStrategyMethodName(parameter.getValue().toString());
                    break;
                case AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL:
                case AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL2:
                    this.setAggregationStrategyMethodAllowNull(Boolean.valueOf(parameter.getValue().toString()));
                    break;
                case PARALLEL_AGGREGATE_LABEL:
                case PARALLEL_AGGREGATE_LABEL2:
                    this.setParallelAggregate(Boolean.valueOf(parameter.getValue().toString()));
                    break;
                case PARALLEL_PROCESSING_LABEL:
                case PARALLEL_PROCESSING_LABEL2:
                    this.setParallelProcessing(Boolean.valueOf(parameter.getValue().toString()));
                    break;
                case STREAMING_LABEL:
                    this.setStreaming(Boolean.valueOf(parameter.getValue().toString()));
                    break;
                case STOP_ON_EXCEPTION_LABEL:
                case STOP_ON_EXCEPTION_LABEL2:
                    this.setStopOnException(Boolean.valueOf(parameter.getValue().toString()));
                    break;
                case TIMEOUT_LABEL:
                    this.setTimeout(parameter.getValue().toString());
                    break;
                case EXECUTOR_SERVICE_LABEL:
                case EXECUTOR_SERVICE_LABEL2:
                    this.setExecutorService((Map<String, Object>) parameter.getValue());
                    break;
                case ON_PREPARE_LABEL:
                case ON_PREPARE_LABEL2:
                    this.setOnPrepare((Map<String, Object>) parameter.getValue());
                    break;
                case SHARE_UNIT_OF_WORK_LABEL:
                case SHARE_UNIT_OF_WORK_LABEL2:
                    this.setShareUnitOfWork(Boolean.valueOf(parameter.getValue().toString()));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new HashMap<>();
        if (this.getAggregationStrategy() != null) {
            properties.put(AGGREGATION_STRATEGY_LABEL, this.getAggregationStrategy());
        }
        if (this.getAggregationStrategyMethodName() != null) {
            properties.put(AGGREGATION_STRATEGY_METHOD_NAME_LABEL2, this.getAggregationStrategyMethodName());
        }
        if (this.getAggregationStrategyMethodAllowNull() != null) {
            properties.put(AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL2, this.getAggregationStrategyMethodAllowNull());
        }
        if (this.getParallelAggregate() != null) {
            properties.put(PARALLEL_AGGREGATE_LABEL2, this.getParallelAggregate());
        }
        if (this.getParallelProcessing() != null) {
            properties.put(PARALLEL_PROCESSING_LABEL2, this.getParallelProcessing());
        }
        if (this.getStreaming() != null) {
            properties.put(STREAMING_LABEL, this.getStreaming());
        }
        if (this.getStopOnException() != null) {
            properties.put(STOP_ON_EXCEPTION_LABEL, this.getStopOnException());
        }
        if (this.getTimeout() != null) {
            properties.put(TIMEOUT_LABEL, this.getTimeout());
        }
        if (this.getExecutorService() != null) {
            properties.put(EXECUTOR_SERVICE_LABEL2, this.getExecutorService());
        }
        if (this.getOnPrepare() != null) {
            properties.put(ON_PREPARE_LABEL2, this.getOnPrepare());
        }
        if (this.getShareUnitOfWork() != null) {
            properties.put(SHARE_UNIT_OF_WORK_LABEL2, this.getShareUnitOfWork());
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
    public void processBranches(final Step step, final StepCatalog catalog,
                                final KameletStepParserService kameletStepParserService) {
        step.setBranches(List.of(createBranch(STEPS_LABEL, this.getSteps(), kameletStepParserService)));
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
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
            case PARALLEL_AGGREGATE_LABEL:
            case PARALLEL_AGGREGATE_LABEL2:
                parameter.setValue(this.parallelAggregate);
                break;
            case PARALLEL_PROCESSING_LABEL:
            case PARALLEL_PROCESSING_LABEL2:
                parameter.setValue(this.parallelProcessing);
                break;
            case STREAMING_LABEL:
                parameter.setValue(this.streaming);
                break;
            case STOP_ON_EXCEPTION_LABEL:
            case STOP_ON_EXCEPTION_LABEL2:
                parameter.setValue(this.stopOnException);
                break;
            case TIMEOUT_LABEL:
                parameter.setValue(this.timeout);
                break;
            case EXECUTOR_SERVICE_LABEL:
            case EXECUTOR_SERVICE_LABEL2:
                parameter.setValue(this.executorService);
                break;
            case ON_PREPARE_LABEL:
            case ON_PREPARE_LABEL2:
                parameter.setValue(this.onPrepare);
                break;
            case SHARE_UNIT_OF_WORK_LABEL:
            case SHARE_UNIT_OF_WORK_LABEL2:
                parameter.setValue(this.shareUnitOfWork);
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.description);
                break;
            default:
                break;
        }

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

    public Boolean getParallelAggregate() {
        return parallelAggregate;
    }

    public void setParallelAggregate(final Boolean parallelAggregate) {
        this.parallelAggregate = parallelAggregate;
    }

    public Boolean getParallelProcessing() {
        return parallelProcessing;
    }

    public void setParallelProcessing(final Boolean parallelProcessing) {
        this.parallelProcessing = parallelProcessing;
    }

    public Boolean getStreaming() {
        return streaming;
    }

    public void setStreaming(final Boolean streaming) {
        this.streaming = streaming;
    }

    public Boolean getStopOnException() {
        return stopOnException;
    }

    public void setStopOnException(final Boolean stopOnException) {
        this.stopOnException = stopOnException;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(final String timeout) {
        this.timeout = timeout;
    }

    public void setAggregationStrategy(final Map<String, Object> aggregationStrategy) {
        this.aggregationStrategy = aggregationStrategy;
    }

    public Map<String, Object> getExecutorService() {
        return executorService;
    }

    public void setExecutorService(final Map<String, Object> executorService) {
        this.executorService = executorService;
    }

    public Map<String, Object> getOnPrepare() {
        return onPrepare;
    }

    public void setOnPrepare(final Map<String, Object> onPrepare) {
        this.onPrepare = onPrepare;
    }

    public Boolean getShareUnitOfWork() {
        return shareUnitOfWork;
    }

    public void setShareUnitOfWork(final Boolean shareUnitOfWork) {
        this.shareUnitOfWork = shareUnitOfWork;
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

    public Map<String, Object> getAggregationStrategy() {
        return aggregationStrategy;
    }

    public String getAggregationStrategyMethodName() {
        return aggregationStrategyMethodName;
    }
}
