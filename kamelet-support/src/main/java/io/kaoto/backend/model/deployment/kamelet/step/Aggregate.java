package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.Expression;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


@JsonPropertyOrder({"correlation-expression", "completion-predicate", "completion-timeout-expression",
        "completion-size-expression", "optimistic-lock-retry-policy", "parallel-processing", "optimistic-locking",
        "executor-service", "timeout-checker-executor-service", "aggregate-controller", "aggregation-repository",
        "aggregation-strategy", "aggregation-strategy-method-name", "aggregation-strategy-method-allow-null",
        "completion-size", "completion-interval", "completion-timeout", "completion-timeout-checker-interval",
        "completion-from-batch-consumer", "completion-on-new-correlation-group", "eager-check-completion",
        "ignore-invalid-correlation-keys", "close-correlation-key-on-completion", "discard-on-completion-timeout",
        "discard-on-aggregation-failure", "force-completion-on-stop", "complete-all-on-stop", "description"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Aggregate implements Serializable {

    public static final String OPTIMISTIC_LOCK_RETRY_POLICY = "optimisticLockRetryPolicy";
    private static final Logger log = Logger.getLogger(Aggregate.class);
    public static final String CORRELATION_EXPRESSION = "correlation-expression";
    public static final String CORRELATION_EXPRESSION1 = "correlationExpression";
    public static final String COMPLETION_PREDICATE = "completionPredicate";
    public static final String COMPLETION_PREDICATE1 = "completion-predicate";
    public static final String COMPLETION_TIMEOUT_EXPRESSION = "completionTimeoutExpression";
    public static final String COMPLETION_TIMEOUT_EXPRESSION1 = "completion-timeout-expression";
    public static final String COMPLETION_SIZE_EXPRESSION = "completionSizeExpression";
    public static final String COMPLETION_SIZE_EXPRESSION1 = "completion-size-expression";
    public static final String OPTIMISTIC_LOCK_RETRY_POLICY1 = "optimistic-lock-retry-policy";
    public static final String PARALLEL_PROCESSING = "parallel-processing";
    public static final String PARALLEL_PROCESSING1 = "parallelProcessing";
    public static final String OPTIMISTIC_LOCKING = "optimisticLocking";
    public static final String OPTIMISTIC_LOCKING1 = "optimistic-locking";
    public static final String EXECUTOR_SERVICE = "executorService";
    public static final String EXECUTOR_SERVICE1 = "executor-service";
    public static final String TIMEOUT_CHECKER_EXECUTOR_SERVICE = "timeoutCheckerExecutorService";
    public static final String TIMEOUT_CHECKER_EXECUTOR_SERVICE1 = "timeout-checker-executor-service";
    public static final String AGGREGATE_CONTROLLER = "aggregateController";
    public static final String AGGREGATE_CONTROLLER1 = "aggregate-controller";
    public static final String AGGREGATION_REPOSITORY = "aggregationRepository";
    public static final String AGGREGATION_REPOSITORY1 = "aggregation-repository";
    public static final String AGGREGATION_STRATEGY = "aggregationStrategy";
    public static final String AGGREGATION_STRATEGY1 = "aggregation-strategy";
    public static final String AGGREGATION_STRATEGY_METHOD_NAME = "aggregationStrategyMethodName";
    public static final String AGGREGATION_STRATEGY_METHOD_NAME1 = "aggregation-strategy-method-name";
    public static final String AGGREGATION_STRATEGY_METHOD_ALLOW_NULL = "aggregationStrategyMethodAllowNull";
    public static final String AGGREGATION_STRATEGY_METHOD_ALLOW_NULL1 = "aggregation-strategy-method-allow-null";
    public static final String COMPLETION_SIZE = "completionSize";
    public static final String COMPLETION_SIZE1 = "completion-size";
    public static final String COMPLETION_INTERVAL = "completionInterval";
    public static final String COMPLETION_INTERVAL1 = "completion-interval";
    public static final String COMPLETION_TIMEOUT = "completionTimeout";
    public static final String COMPLETION_TIMEOUT1 = "completion-timeout";
    public static final String COMPLETION_TIMEOUT_CHECKER_INTERVAL = "completionTimeoutCheckerInterval";
    public static final String COMPLETION_TIMEOUT_CHECKER_INTERVAL1 = "completion-timeout-checker-interval";
    public static final String COMPLETION_FROM_BATCH_CONSUMER = "completionFromBatchConsumer";
    public static final String COMPLETION_FROM_BATCH_CONSUMER1 = "completion-from-batch-consumer";
    public static final String COMPLETION_ON_NEW_CORRELATION_GROUP = "completionOnNewCorrelationGroup";
    public static final String COMPLETION_ON_NEW_CORRELATION_GROUP1 = "completion-on-new-correlation-group";
    public static final String EAGER_CHECK_COMPLETION = "eagerCheckCompletion";
    public static final String EAGER_CHECK_COMPLETION1 = "eager-check-completion";
    public static final String IGNORE_INVALID_CORRELATION_KEYS = "ignoreInvalidCorrelationKeys";
    public static final String IGNORE_INVALID_CORRELATION_KEYS1 = "ignore-invalid-correlation-keys";
    public static final String CLOSE_CORRELATION_KEY_ON_COMPLETION = "closeCorrelationKeyOnCompletion";
    public static final String CLOSE_CORRELATION_KEY_ON_COMPLETION1 = "close-correlation-key-on-completion";
    public static final String DISCARD_ON_COMPLETION_TIMEOUT = "discardOnCompletionTimeout";
    public static final String DISCARD_ON_COMPLETION_TIMEOUT1 = "discard-on-completion-timeout";
    public static final String DISCARD_ON_AGGREGATION_FAILURE = "discardOnAggregationFailure";
    public static final String DISCARD_ON_AGGREGATION_FAILURE1 = "discard-on-aggregation-failure";
    public static final String FORCE_COMPLETION_ON_STOP = "forceCompletionOnStop";
    public static final String FORCE_COMPLETION_ON_STOP1 = "force-completion-on-stop";
    public static final String COMPLETE_ALL_ON_STOP = "completeAllOnStop";
    public static final String COMPLETE_ALL_ON_STOP1 = "complete-all-on-stop";
    public static final String DESCRIPTION_LABEL = "description";


    @JsonProperty("correlation-expression")
    private Expression correlationExpression;

    @JsonProperty("completion-predicate")
    private String completionPredicate;

    @JsonProperty("completion-timeout-expression")
    private Expression completionTimeoutExpression;

    @JsonProperty("completion-size-expression")
    private String completionSizeExpression;

    @JsonProperty("optimistic-lock-retry-policy")
    private String optimisticLockRetryPolicy;

    @JsonProperty("parallel-processing")
    private Boolean parallelProcessing;

    @JsonProperty("optimistic-locking")
    private String optimisticLocking;

    @JsonProperty("executor-service")
    private String executorService;

    @JsonProperty("timeout-checker-executor-service")
    private String timeoutCheckerExecutorService;

    @JsonProperty("aggregate-controller")
    private String aggregateController;

    @JsonProperty("aggregation-repository")
    private String aggregationRepository;

    @JsonProperty("aggregation-strategy")
    private String aggregationStrategy;

    @JsonProperty("aggregation-strategy-method-name")
    private String aggregationStrategyMethodName;

    @JsonProperty("aggregation-strategy-method-allow-null")
    private Boolean aggregationStrategyMethodAllowNull;

    @JsonProperty("completion-size")
    private Integer completionSize;

    @JsonProperty("completion-interval")
    private String completionInterval;

    @JsonProperty("completion-timeout")
    private String completionTimeout;

    @JsonProperty("completion-timeout-checker-interval")
    private String completionTimeoutCheckerInterval;

    @JsonProperty("completion-from-batch-consumer")
    private Boolean completionFromBatchConsumer;

    @JsonProperty("completion-on-new-correlation-group")
    private Boolean completionOnNewCorrelationGroup;

    @JsonProperty("eager-check-completion")
    private Boolean eagerCheckCompletion;

    @JsonProperty("ignore-invalid-correlation-keys")
    private Boolean ignoreInvalidCorrelationKeys;

    @JsonProperty("close-correlation-key-on-completion")
    private Integer closeCorrelationKeyOnCompletion;

    @JsonProperty("discard-on-completion-timeout")
    private Boolean discardOnCompletionTimeout;

    @JsonProperty("discard-on-aggregation-failure")
    private Boolean discardOnAggregationFailure;

    @JsonProperty("force-completion-on-stop")
    private Boolean forceCompletionOnStop;

    @JsonProperty("complete-all-on-stop")
    private Boolean completeAllOnStop;

    @JsonProperty("description")
    private String description;

    public Aggregate() {
    }

    public Aggregate(Step step) {

        for (var parameter : step.getParameters()) {
            if (parameter.getValue() != null) {
                try {
                    switch (parameter.getId()) {
                        case CORRELATION_EXPRESSION1:
                        case CORRELATION_EXPRESSION:
                            this.setCorrelationExpression(getExpression(parameter.getValue()));
                            break;
                        case COMPLETION_PREDICATE:
                        case COMPLETION_PREDICATE1:
                            this.setCompletionPredicate(parameter.getValue().toString());
                            break;
                        case COMPLETION_TIMEOUT_EXPRESSION:
                        case COMPLETION_TIMEOUT_EXPRESSION1:
                            this.setCompletionTimeoutExpression(getExpression(parameter.getValue()));
                            break;
                        case COMPLETION_SIZE_EXPRESSION:
                        case COMPLETION_SIZE_EXPRESSION1:
                            this.setCompletionSizeExpression(parameter.getValue().toString());
                            break;
                        case OPTIMISTIC_LOCK_RETRY_POLICY:
                        case OPTIMISTIC_LOCK_RETRY_POLICY1:
                            this.setOptimisticLockRetryPolicy(parameter.getValue().toString());
                            break;
                        case PARALLEL_PROCESSING1:
                        case PARALLEL_PROCESSING:
                            this.setParallelProcessing(Boolean.valueOf(parameter.getValue().toString()));
                            break;
                        case OPTIMISTIC_LOCKING:
                        case OPTIMISTIC_LOCKING1:
                            this.setOptimisticLocking(parameter.getValue().toString());
                            break;
                        case EXECUTOR_SERVICE:
                        case EXECUTOR_SERVICE1:
                            this.setExecutorService(parameter.getValue().toString());
                            break;
                        case TIMEOUT_CHECKER_EXECUTOR_SERVICE:
                        case TIMEOUT_CHECKER_EXECUTOR_SERVICE1:
                            this.setTimeoutCheckerExecutorService(parameter.getValue().toString());
                            break;
                        case AGGREGATE_CONTROLLER:
                        case AGGREGATE_CONTROLLER1:
                            this.setAggregateController(parameter.getValue().toString());
                            break;
                        case AGGREGATION_REPOSITORY:
                        case AGGREGATION_REPOSITORY1:
                            this.setAggregationRepository(parameter.getValue().toString());
                            break;
                        case AGGREGATION_STRATEGY:
                        case AGGREGATION_STRATEGY1:
                            this.setAggregationStrategy(parameter.getValue().toString());
                            break;
                        case AGGREGATION_STRATEGY_METHOD_NAME:
                        case AGGREGATION_STRATEGY_METHOD_NAME1:
                            this.setAggregationStrategyMethodName(parameter.getValue().toString());
                            break;
                        case AGGREGATION_STRATEGY_METHOD_ALLOW_NULL:
                        case AGGREGATION_STRATEGY_METHOD_ALLOW_NULL1:
                            this.setAggregationStrategyMethodAllowNull(
                                    Boolean.valueOf(parameter.getValue().toString()));
                            break;
                        case COMPLETION_SIZE:
                        case COMPLETION_SIZE1:
                            this.setCompletionSize(Integer.valueOf(parameter.getValue().toString()));
                            break;
                        case COMPLETION_INTERVAL:
                        case COMPLETION_INTERVAL1:
                            this.setCompletionInterval(parameter.getValue().toString());
                            break;
                        case COMPLETION_TIMEOUT:
                        case COMPLETION_TIMEOUT1:
                            this.setCompletionTimeout(parameter.getValue().toString());
                            break;
                        case COMPLETION_TIMEOUT_CHECKER_INTERVAL:
                        case COMPLETION_TIMEOUT_CHECKER_INTERVAL1:
                            this.setCompletionTimeoutCheckerInterval(parameter.getValue().toString());
                            break;
                        case COMPLETION_FROM_BATCH_CONSUMER:
                        case COMPLETION_FROM_BATCH_CONSUMER1:
                            this.setCompletionFromBatchConsumer(Boolean.valueOf(parameter.getValue().toString()));
                            break;
                        case COMPLETION_ON_NEW_CORRELATION_GROUP:
                        case COMPLETION_ON_NEW_CORRELATION_GROUP1:
                            this.setCompletionOnNewCorrelationGroup(Boolean.valueOf(parameter.getValue().toString()));
                            break;
                        case EAGER_CHECK_COMPLETION:
                        case EAGER_CHECK_COMPLETION1:
                            this.setEagerCheckCompletion(Boolean.valueOf(parameter.getValue().toString()));
                            break;
                        case IGNORE_INVALID_CORRELATION_KEYS:
                        case IGNORE_INVALID_CORRELATION_KEYS1:
                            this.setIgnoreInvalidCorrelationKeys(Boolean.valueOf(parameter.getValue().toString()));
                            break;
                        case CLOSE_CORRELATION_KEY_ON_COMPLETION:
                        case CLOSE_CORRELATION_KEY_ON_COMPLETION1:
                            this.setCloseCorrelationKeyOnCompletion(Integer.valueOf(parameter.getValue().toString()));
                            break;
                        case DISCARD_ON_COMPLETION_TIMEOUT:
                        case DISCARD_ON_COMPLETION_TIMEOUT1:
                            this.setDiscardOnCompletionTimeout(Boolean.valueOf(parameter.getValue().toString()));
                            break;
                        case DISCARD_ON_AGGREGATION_FAILURE:
                        case DISCARD_ON_AGGREGATION_FAILURE1:
                            this.setDiscardOnAggregationFailure(Boolean.valueOf(parameter.getValue().toString()));
                            break;
                        case FORCE_COMPLETION_ON_STOP:
                        case FORCE_COMPLETION_ON_STOP1:
                            this.setForceCompletionOnStop(Boolean.valueOf(parameter.getValue().toString()));
                            break;
                        case COMPLETE_ALL_ON_STOP:
                        case COMPLETE_ALL_ON_STOP1:
                            this.setCompleteAllOnStop(Boolean.valueOf(parameter.getValue().toString()));
                            break;
                        case DESCRIPTION_LABEL:
                            this.setDescription(parameter.getValue().toString());
                            break;
                        default:
                            log.error("Unknown property: " + parameter.getId());
                            break;
                    }
                } catch (Exception e) {
                    log.error("Couldn't assign value to parameter " + parameter.getId(), e);
                }
            }
        }
    }

    private Expression getExpression(final Object value) {
        if (value instanceof Expression e) {
            return e;
        }

        if (value instanceof Map map) {
            Expression e = new Expression();
            if (map.containsKey("constant") && map.get("constant") != null) {
                e.setConstant(map.get("constant").toString());
            }
            if (map.containsKey("simple") && map.get("simple") != null) {
                e.setSimple(map.get("simple").toString());
            }
            return e;
        }

        log.error("Couldn't recognize the expression " + value);
        return null;
    }

    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = new LinkedHashMap<>();
        if (this.correlationExpression != null) {
            properties.put(CORRELATION_EXPRESSION, this.correlationExpression);
        }
        if (this.completionPredicate != null) {
            properties.put(COMPLETION_PREDICATE1, this.completionPredicate);
        }
        if (this.completionTimeoutExpression != null) {
            properties.put(COMPLETION_TIMEOUT_EXPRESSION1, this.completionTimeoutExpression);
        }
        if (this.completionSizeExpression != null) {
            properties.put(COMPLETION_SIZE_EXPRESSION1, this.completionSizeExpression);
        }
        if (this.optimisticLockRetryPolicy != null) {
            properties.put(OPTIMISTIC_LOCK_RETRY_POLICY1, this.optimisticLockRetryPolicy);
        }
        if (this.parallelProcessing != null) {
            properties.put(PARALLEL_PROCESSING1, this.parallelProcessing);
        }
        if (this.optimisticLocking != null) {
            properties.put(OPTIMISTIC_LOCKING1, this.optimisticLocking);
        }
        if (this.executorService != null) {
            properties.put(EXECUTOR_SERVICE1, this.executorService);
        }
        if (this.timeoutCheckerExecutorService != null) {
            properties.put(TIMEOUT_CHECKER_EXECUTOR_SERVICE1, this.timeoutCheckerExecutorService);
        }
        if (this.aggregateController != null) {
            properties.put(AGGREGATE_CONTROLLER1, this.aggregateController);
        }
        if (this.aggregationRepository != null) {
            properties.put(AGGREGATION_REPOSITORY1, this.aggregationRepository);
        }
        if (this.aggregationStrategy != null) {
            properties.put(AGGREGATION_STRATEGY1, this.aggregationStrategy);
        }
        if (this.aggregationStrategyMethodName != null) {
            properties.put(AGGREGATION_STRATEGY_METHOD_NAME1, this.aggregationStrategyMethodName);
        }
        if (this.aggregationStrategyMethodAllowNull != null) {
            properties.put(AGGREGATION_STRATEGY_METHOD_ALLOW_NULL1, this.aggregationStrategyMethodAllowNull);
        }
        if (this.completionSize != null) {
            properties.put(COMPLETION_SIZE1, this.completionSize);
        }
        if (this.completionInterval != null) {
            properties.put(COMPLETION_INTERVAL1, this.completionInterval);
        }
        if (this.completionTimeout != null) {
            properties.put(COMPLETION_TIMEOUT1, this.completionTimeout);
        }
        if (this.completionTimeoutCheckerInterval != null) {
            properties.put(COMPLETION_TIMEOUT_CHECKER_INTERVAL1, this.completionTimeoutCheckerInterval);
        }
        if (this.completionFromBatchConsumer != null) {
            properties.put(COMPLETION_FROM_BATCH_CONSUMER1, this.completionFromBatchConsumer);
        }
        if (this.completionOnNewCorrelationGroup != null) {
            properties.put(COMPLETION_ON_NEW_CORRELATION_GROUP1, this.completionOnNewCorrelationGroup);
        }
        if (this.eagerCheckCompletion != null) {
            properties.put(EAGER_CHECK_COMPLETION1, this.eagerCheckCompletion);
        }
        if (this.ignoreInvalidCorrelationKeys != null) {
            properties.put(IGNORE_INVALID_CORRELATION_KEYS1, this.ignoreInvalidCorrelationKeys);
        }
        if (this.closeCorrelationKeyOnCompletion != null) {
            properties.put(CLOSE_CORRELATION_KEY_ON_COMPLETION1, this.closeCorrelationKeyOnCompletion);
        }
        if (this.discardOnCompletionTimeout != null) {
            properties.put(DISCARD_ON_COMPLETION_TIMEOUT1, this.discardOnCompletionTimeout);
        }
        if (this.discardOnAggregationFailure != null) {
            properties.put(DISCARD_ON_AGGREGATION_FAILURE1, this.discardOnAggregationFailure);
        }
        if (this.forceCompletionOnStop != null) {
            properties.put(FORCE_COMPLETION_ON_STOP1, this.forceCompletionOnStop);
        }
        if (this.completeAllOnStop != null) {
            properties.put(COMPLETE_ALL_ON_STOP1, this.completeAllOnStop);
        }
        if (this.description != null) {
            properties.put(DESCRIPTION_LABEL, this.description);
        }

        return properties;
    }

    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService) {

        Optional<Step> res = catalog.getReadOnlyCatalog()
                .searchByName("aggregate").stream()
                .filter(step -> step.getKind().equalsIgnoreCase("EIP"))
                .findAny();


        if (res.isPresent()) {
            var step = res.get();
            for (var parameter : step.getParameters()) {
                try {
                    switch (parameter.getId()) {
                        case CORRELATION_EXPRESSION:
                        case CORRELATION_EXPRESSION1:
                            parameter.setValue(this.correlationExpression);
                            break;
                        case COMPLETION_PREDICATE:
                        case COMPLETION_PREDICATE1:
                            parameter.setValue(this.completionPredicate);
                            break;
                        case COMPLETION_TIMEOUT_EXPRESSION:
                        case COMPLETION_TIMEOUT_EXPRESSION1:
                            parameter.setValue(this.completionTimeoutExpression);
                            break;
                        case COMPLETION_SIZE_EXPRESSION:
                        case COMPLETION_SIZE_EXPRESSION1:
                            parameter.setValue(this.completionSizeExpression);
                            break;
                        case OPTIMISTIC_LOCK_RETRY_POLICY:
                        case OPTIMISTIC_LOCK_RETRY_POLICY1:
                            parameter.setValue(this.optimisticLockRetryPolicy);
                            break;
                        case PARALLEL_PROCESSING:
                        case PARALLEL_PROCESSING1:
                            parameter.setValue(this.parallelProcessing);
                            break;
                        case OPTIMISTIC_LOCKING:
                        case OPTIMISTIC_LOCKING1:
                            parameter.setValue(this.optimisticLocking);
                            break;
                        case EXECUTOR_SERVICE:
                        case EXECUTOR_SERVICE1:
                            parameter.setValue(this.executorService);
                            break;
                        case TIMEOUT_CHECKER_EXECUTOR_SERVICE:
                        case TIMEOUT_CHECKER_EXECUTOR_SERVICE1:
                            parameter.setValue(this.timeoutCheckerExecutorService);
                            break;
                        case AGGREGATE_CONTROLLER:
                        case AGGREGATE_CONTROLLER1:
                            parameter.setValue(this.aggregateController);
                            break;
                        case AGGREGATION_REPOSITORY:
                        case AGGREGATION_REPOSITORY1:
                            parameter.setValue(this.aggregationRepository);
                            break;
                        case AGGREGATION_STRATEGY:
                        case AGGREGATION_STRATEGY1:
                            parameter.setValue(this.aggregationStrategy);
                            break;
                        case AGGREGATION_STRATEGY_METHOD_NAME:
                        case AGGREGATION_STRATEGY_METHOD_NAME1:
                            parameter.setValue(this.aggregationStrategyMethodName);
                            break;
                        case AGGREGATION_STRATEGY_METHOD_ALLOW_NULL:
                        case AGGREGATION_STRATEGY_METHOD_ALLOW_NULL1:
                            parameter.setValue(this.aggregationStrategyMethodAllowNull);
                            break;
                        case COMPLETION_SIZE:
                        case COMPLETION_SIZE1:
                            parameter.setValue(this.completionSize);
                            break;
                        case COMPLETION_INTERVAL:
                        case COMPLETION_INTERVAL1:
                            parameter.setValue(this.completionInterval);
                            break;
                        case COMPLETION_TIMEOUT:
                        case COMPLETION_TIMEOUT1:
                            parameter.setValue(this.completionTimeout);
                            break;
                        case COMPLETION_TIMEOUT_CHECKER_INTERVAL:
                        case COMPLETION_TIMEOUT_CHECKER_INTERVAL1:
                            parameter.setValue(this.completionTimeoutCheckerInterval);
                            break;
                        case COMPLETION_FROM_BATCH_CONSUMER:
                        case COMPLETION_FROM_BATCH_CONSUMER1:
                            parameter.setValue(this.completionFromBatchConsumer);
                            break;
                        case COMPLETION_ON_NEW_CORRELATION_GROUP:
                        case COMPLETION_ON_NEW_CORRELATION_GROUP1:
                            parameter.setValue(this.completionOnNewCorrelationGroup);
                            break;
                        case EAGER_CHECK_COMPLETION:
                        case EAGER_CHECK_COMPLETION1:
                            parameter.setValue(this.eagerCheckCompletion);
                            break;
                        case IGNORE_INVALID_CORRELATION_KEYS:
                        case IGNORE_INVALID_CORRELATION_KEYS1:
                            parameter.setValue(this.ignoreInvalidCorrelationKeys);
                            break;
                        case CLOSE_CORRELATION_KEY_ON_COMPLETION:
                        case CLOSE_CORRELATION_KEY_ON_COMPLETION1:
                            parameter.setValue(this.closeCorrelationKeyOnCompletion);
                            break;
                        case DISCARD_ON_COMPLETION_TIMEOUT:
                        case DISCARD_ON_COMPLETION_TIMEOUT1:
                            parameter.setValue(this.discardOnCompletionTimeout);
                            break;
                        case DISCARD_ON_AGGREGATION_FAILURE:
                        case DISCARD_ON_AGGREGATION_FAILURE1:
                            parameter.setValue(this.discardOnAggregationFailure);
                            break;
                        case FORCE_COMPLETION_ON_STOP:
                        case FORCE_COMPLETION_ON_STOP1:
                            parameter.setValue(this.forceCompletionOnStop);
                            break;
                        case COMPLETE_ALL_ON_STOP:
                        case COMPLETE_ALL_ON_STOP1:
                            parameter.setValue(this.completeAllOnStop);
                            break;
                        case DESCRIPTION_LABEL:
                            parameter.setValue(this.description);
                            break;
                        default:
                            log.error("Unknown property: " + parameter.getId());
                            break;
                    }
                } catch (Exception e) {
                    log.error("Couldn't assign value to parameter " + parameter.getId(), e);
                }
            }

        }

        return res.orElse(null);
    }

    public Expression getCorrelationExpression() {
        return correlationExpression;
    }

    public void setCorrelationExpression(final Expression correlationExpression) {
        this.correlationExpression = correlationExpression;
    }

    public String getCompletionPredicate() {
        return completionPredicate;
    }

    public void setCompletionPredicate(final String completionPredicate) {
        this.completionPredicate = completionPredicate;
    }

    public Expression getCompletionTimeoutExpression() {
        return completionTimeoutExpression;
    }

    public void setCompletionTimeoutExpression(final Expression completionTimeoutExpression) {
        this.completionTimeoutExpression = completionTimeoutExpression;
    }

    public String getCompletionSizeExpression() {
        return completionSizeExpression;
    }

    public void setCompletionSizeExpression(final String completionSizeExpression) {
        this.completionSizeExpression = completionSizeExpression;
    }

    public String getOptimisticLockRetryPolicy() {
        return optimisticLockRetryPolicy;
    }

    public void setOptimisticLockRetryPolicy(final String optimisticLockRetryPolicy) {
        this.optimisticLockRetryPolicy = optimisticLockRetryPolicy;
    }

    public Boolean getParallelProcessing() {
        return parallelProcessing;
    }

    public void setParallelProcessing(final Boolean parallelProcessing) {
        this.parallelProcessing = parallelProcessing;
    }

    public String getOptimisticLocking() {
        return optimisticLocking;
    }

    public void setOptimisticLocking(final String optimisticLocking) {
        this.optimisticLocking = optimisticLocking;
    }

    public String getExecutorService() {
        return executorService;
    }

    public void setExecutorService(final String executorService) {
        this.executorService = executorService;
    }

    public String getTimeoutCheckerExecutorService() {
        return timeoutCheckerExecutorService;
    }

    public void setTimeoutCheckerExecutorService(final String timeoutCheckerExecutorService) {
        this.timeoutCheckerExecutorService = timeoutCheckerExecutorService;
    }

    public String getAggregateController() {
        return aggregateController;
    }

    public void setAggregateController(final String aggregateController) {
        this.aggregateController = aggregateController;
    }

    public String getAggregationRepository() {
        return aggregationRepository;
    }

    public void setAggregationRepository(final String aggregationRepository) {
        this.aggregationRepository = aggregationRepository;
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

    public Boolean getAggregationStrategyMethodAllowNull() {
        return aggregationStrategyMethodAllowNull;
    }

    public void setAggregationStrategyMethodAllowNull(final Boolean aggregationStrategyMethodAllowNull) {
        this.aggregationStrategyMethodAllowNull = aggregationStrategyMethodAllowNull;
    }

    public Integer getCompletionSize() {
        return completionSize;
    }

    public void setCompletionSize(final Integer completionSize) {
        this.completionSize = completionSize;
    }

    public String getCompletionInterval() {
        return completionInterval;
    }

    public void setCompletionInterval(final String completionInterval) {
        this.completionInterval = completionInterval;
    }

    public String getCompletionTimeout() {
        return completionTimeout;
    }

    public void setCompletionTimeout(final String completionTimeout) {
        this.completionTimeout = completionTimeout;
    }

    public String getCompletionTimeoutCheckerInterval() {
        return completionTimeoutCheckerInterval;
    }

    public void setCompletionTimeoutCheckerInterval(final String completionTimeoutCheckerInterval) {
        this.completionTimeoutCheckerInterval = completionTimeoutCheckerInterval;
    }

    public Boolean getCompletionFromBatchConsumer() {
        return completionFromBatchConsumer;
    }

    public void setCompletionFromBatchConsumer(final Boolean completionFromBatchConsumer) {
        this.completionFromBatchConsumer = completionFromBatchConsumer;
    }

    public Boolean getCompletionOnNewCorrelationGroup() {
        return completionOnNewCorrelationGroup;
    }

    public void setCompletionOnNewCorrelationGroup(final Boolean completionOnNewCorrelationGroup) {
        this.completionOnNewCorrelationGroup = completionOnNewCorrelationGroup;
    }

    public Boolean getEagerCheckCompletion() {
        return eagerCheckCompletion;
    }

    public void setEagerCheckCompletion(final Boolean eagerCheckCompletion) {
        this.eagerCheckCompletion = eagerCheckCompletion;
    }

    public Boolean getIgnoreInvalidCorrelationKeys() {
        return ignoreInvalidCorrelationKeys;
    }

    public void setIgnoreInvalidCorrelationKeys(final Boolean ignoreInvalidCorrelationKeys) {
        this.ignoreInvalidCorrelationKeys = ignoreInvalidCorrelationKeys;
    }

    public Integer getCloseCorrelationKeyOnCompletion() {
        return closeCorrelationKeyOnCompletion;
    }

    public void setCloseCorrelationKeyOnCompletion(final Integer closeCorrelationKeyOnCompletion) {
        this.closeCorrelationKeyOnCompletion = closeCorrelationKeyOnCompletion;
    }

    public Boolean getDiscardOnCompletionTimeout() {
        return discardOnCompletionTimeout;
    }

    public void setDiscardOnCompletionTimeout(final Boolean discardOnCompletionTimeout) {
        this.discardOnCompletionTimeout = discardOnCompletionTimeout;
    }

    public Boolean getDiscardOnAggregationFailure() {
        return discardOnAggregationFailure;
    }

    public void setDiscardOnAggregationFailure(final Boolean discardOnAggregationFailure) {
        this.discardOnAggregationFailure = discardOnAggregationFailure;
    }

    public Boolean getForceCompletionOnStop() {
        return forceCompletionOnStop;
    }

    public void setForceCompletionOnStop(final Boolean forceCompletionOnStop) {
        this.forceCompletionOnStop = forceCompletionOnStop;
    }

    public Boolean getCompleteAllOnStop() {
        return completeAllOnStop;
    }

    public void setCompleteAllOnStop(final Boolean completeAllOnStop) {
        this.completeAllOnStop = completeAllOnStop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
