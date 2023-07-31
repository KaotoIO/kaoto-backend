package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.camel.model.deployment.kamelet.expression.Tokenizer;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.List;
import java.util.Map;


public class Split extends Tokenizer {

    public static final String TOKENIZE_LABEL = "tokenize";

    public static final String DESCRIPTION_LABEL = "description";

    public static final String STEPS_LABEL = "steps";

    public static final String DELIMITER_LABEL = "delimiter";

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

    public static final String STOP_ON_EXCEPTION_LABEL = "stopOnException";
    public static final String STOP_ON_EXCEPTION_LABEL2 = "stop-on-exception";

    public static final String TIMEOUT_LABEL = "timeout";

    public static final String EXECUTOR_SERVICE_LABEL = "executorService";
    public static final String EXECUTOR_SERVICE_LABEL2 = "executor-service";

    public static final String ON_PREPARE_LABEL = "onPrepare";
    public static final String ON_PREPARE_LABEL2 = "on-prepare";

    public static final String SHARE_UNIT_OF_WORK_LABEL = "shareUnitOfWork";
    public static final String SHARE_UNIT_OF_WORK_LABEL2 = "share-init-of-work";

    @JsonProperty(TOKENIZE_LABEL)
    private Tokenizer tokenize;

    @JsonProperty(DELIMITER_LABEL)
    private String delimiter;

    @JsonProperty(AGGREGATION_STRATEGY_LABEL)
    @JsonAlias(AGGREGATION_STRATEGY_LABEL2)
    private Map<String, Object> aggregationStrategy;

    @JsonProperty(AGGREGATION_STRATEGY_METHOD_NAME_LABEL)
    @JsonAlias(AGGREGATION_STRATEGY_METHOD_NAME_LABEL2)
    private String aggregationStrategyMethodName;

    @JsonProperty(AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL)
    @JsonAlias(AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL2)
    private Boolean aggregationStrategyMethodAllowNull;

    @JsonProperty(PARALLEL_AGGREGATE_LABEL)
    @JsonAlias(PARALLEL_AGGREGATE_LABEL2)
    private Boolean parallelAggregate;

    @JsonProperty(PARALLEL_PROCESSING_LABEL)
    @JsonAlias(PARALLEL_PROCESSING_LABEL2)
    private Boolean parallelProcessing;

    @JsonProperty(STREAMING_LABEL)
    private Boolean streaming;

    @JsonProperty(STOP_ON_EXCEPTION_LABEL)
    @JsonAlias(STOP_ON_EXCEPTION_LABEL2)
    private Boolean stopOnException;

    @JsonProperty(TIMEOUT_LABEL)
    private String timeout;

    @JsonProperty(EXECUTOR_SERVICE_LABEL)
    @JsonAlias(EXECUTOR_SERVICE_LABEL2)
    private Map<String, Object> executorService;

    @JsonProperty(ON_PREPARE_LABEL)
    @JsonAlias(ON_PREPARE_LABEL2)
    private Map<String, Object> onPrepare;

    @JsonProperty(SHARE_UNIT_OF_WORK_LABEL)
    @JsonAlias(SHARE_UNIT_OF_WORK_LABEL2)
    private Boolean shareUnitOfWork;

    @JsonProperty(DESCRIPTION_LABEL)
    private Map<String, String> description;

    @JsonProperty(STEPS_LABEL)
    private List<FlowStep> steps;


    public Split() {
        //Needed for serialization
    }

    public Split(Step step, final KamelPopulator kameletPopulator) {
        super(step);

        if (step.getBranches() != null && !step.getBranches().isEmpty()) {
            setSteps(kameletPopulator.processSteps(step.getBranches().get(0)));
        }
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        if (parameter.getValue() != null) {
            super.assignAttribute(parameter);
            switch (parameter.getId()) {
                case TOKENIZE_LABEL:
                    var value = parameter.getValue();
                    if (value instanceof Map) {
                        if (((Map<?, ?>) value).containsKey(TOKENIZE_LABEL)) {
                            this.setTokenize(new Tokenizer(((Map<?, ?>) value).get(TOKENIZE_LABEL)));
                        }
                    } else {
                        this.setTokenize(new Tokenizer(value));
                    }
                    break;
                case DESCRIPTION_LABEL:
                    this.setDescription((Map<String, String>) parameter.getValue());
                    break;
                case DELIMITER_LABEL:
                    this.setDelimiter(parameter.getValue().toString());
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
        Map<String, Object> properties = super.getRepresenterProperties();
        if (this.getTokenize() != null) {
            properties.putAll(this.tokenize.getRepresenterProperties());
        }
        if (this.delimiter != null) {
            properties.put(DELIMITER_LABEL, this.delimiter);
        }
        if (this.aggregationStrategy != null) {
            properties.put(AGGREGATION_STRATEGY_LABEL, this.aggregationStrategy);
        }
        if (this.aggregationStrategyMethodName != null) {
            properties.put(AGGREGATION_STRATEGY_METHOD_NAME_LABEL2, this.aggregationStrategyMethodName);
        }
        if (this.aggregationStrategyMethodAllowNull != null) {
            properties.put(AGGREGATION_STRATEGY_METHOD_ALLOW_NULL_LABEL2, this.aggregationStrategyMethodAllowNull);
        }
        if (this.parallelAggregate != null) {
            properties.put(PARALLEL_AGGREGATE_LABEL2, this.parallelAggregate);
        }
        if (this.parallelProcessing != null) {
            properties.put(PARALLEL_PROCESSING_LABEL2, this.parallelProcessing);
        }
        if (this.streaming != null) {
            properties.put(STREAMING_LABEL, this.streaming);
        }
        if (this.stopOnException != null) {
            properties.put(STOP_ON_EXCEPTION_LABEL2, this.stopOnException);
        }
        if (this.timeout != null) {
            properties.put(TIMEOUT_LABEL, this.timeout);
        }
        if (this.executorService != null) {
            properties.put(EXECUTOR_SERVICE_LABEL2, this.executorService);
        }
        if (this.onPrepare != null) {
            properties.put(ON_PREPARE_LABEL2, this.onPrepare);
        }
        if (this.shareUnitOfWork != null) {
            properties.put(SHARE_UNIT_OF_WORK_LABEL2, this.shareUnitOfWork);
        }
        if (this.description != null) {
            properties.put(DESCRIPTION_LABEL, this.description);
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
        super.assignProperty(parameter);
        switch (parameter.getId()) {
            case TOKENIZE_LABEL:
                parameter.setValue(this.getTokenize());
                break;
            case DELIMITER_LABEL:
                parameter.setValue(this.delimiter);
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

    public void setDelimiter(final String delimiter) {
        this.delimiter = delimiter;
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

    public Tokenizer getTokenize() {
        return tokenize;
    }

    public void setTokenize(final Tokenizer tokenize) {
        this.tokenize = tokenize;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public Map<String, Object> getAggregationStrategy() {
        return aggregationStrategy;
    }

    public String getAggregationStrategyMethodName() {
        return aggregationStrategyMethodName;
    }
}
