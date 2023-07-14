package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class Threads extends EIPStep {


    public static final String DESCRIPTION_LABEL = "description";

    public static final String EXECUTOR_SERVICE_LABEL = "executorService";
    public static final String EXECUTOR_SERVICE_LABEL2 = "executor-service";

    public static final String POOL_SIZE_LABEL = "poolSize";
    public static final String POOL_SIZE_LABEL2 = "pool-size";

    public static final String MAX_POOL_SIZE_LABEL = "maxPoolSize";
    public static final String MAX_POOL_SIZE_LABEL2 = "max-pool-size";

    public static final String KEEP_ALIVE_TIME_LABEL = "keepAliveTime";
    public static final String KEEP_ALIVE_TIME_LABEL2 = "keep-alive-time";

    public static final String TIME_UNIT_LABEL = "timeUnit";
    public static final String TIME_UNIT_LABEL2 = "time-unit";

    public static final String MAX_QUEUE_SIZE_LABEL = "maxQueueSize";
    public static final String MAX_QUEUE_SIZE_LABEL2 = "max-queue-size";

    public static final String ALLOW_CORE_THREAD_TIME_OUT_LABEL = "allowCoreThreadTimeOut";
    public static final String ALLOW_CORE_THREAD_TIME_OUT_LABEL2 = "allow-core-thread-time-out";

    public static final String THREAD_NAME_LABEL = "threadName";
    public static final String THREAD_NAME_LABEL2 = "thread-name";

    public static final String REJECTED_POLICY_LABEL = "rejectedPolicy";
    public static final String REJECTED_POLICY_LABEL2 = "rejected-policy";

    public static final String CALLER_RUNS_WHEN_REJECTED_LABEL = "callerRunsWhenRejected";
    public static final String CALLER_RUNS_WHEN_REJECTED_LABEL2 = "caller-runs-when-rejected";


    private Map<String, String> description;

    private Map<String, Object> executorService;

    private Integer poolSize;

    private Integer maxPoolSize;

    private Long keepAliveTime;

    private String timeUnit;

    private Integer maxQueueSize;

    private Boolean allowCoreThreadTimeOut;

    private String threadName;

    private String rejectedPolicy;

    private String callerRunsWhenRejected;


    public Threads() {
        //Needed for serialization
    }

    @JsonCreator
    public Threads(final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description,
                   final @JsonProperty(EXECUTOR_SERVICE_LABEL) Map<String, Object> executorService,
                   final @JsonProperty(EXECUTOR_SERVICE_LABEL2) Map<String, Object> executorService2,
                   final @JsonProperty(POOL_SIZE_LABEL) Integer poolSize,
                   final @JsonProperty(POOL_SIZE_LABEL2) Integer poolSize2,
                   final @JsonProperty(MAX_POOL_SIZE_LABEL) Integer maxPoolSize,
                   final @JsonProperty(MAX_POOL_SIZE_LABEL2) Integer maxPoolSize2,
                   final @JsonProperty(KEEP_ALIVE_TIME_LABEL) Long keepAliveTime,
                   final @JsonProperty(KEEP_ALIVE_TIME_LABEL2) Long keepAliveTime2,
                   final @JsonProperty(TIME_UNIT_LABEL) String timeUnit,
                   final @JsonProperty(TIME_UNIT_LABEL2) String timeUnit2,
                   final @JsonProperty(MAX_QUEUE_SIZE_LABEL) Integer maxQueueSize,
                   final @JsonProperty(MAX_QUEUE_SIZE_LABEL2) Integer maxQueueSize2,
                   final @JsonProperty(ALLOW_CORE_THREAD_TIME_OUT_LABEL) Boolean allowCoreThreadTimeOut,
                   final @JsonProperty(ALLOW_CORE_THREAD_TIME_OUT_LABEL2) Boolean allowCoreThreadTimeOut2,
                   final @JsonProperty(THREAD_NAME_LABEL) String threadName,
                   final @JsonProperty(THREAD_NAME_LABEL2) String threadName2,
                   final @JsonProperty(REJECTED_POLICY_LABEL) String rejectedPolicy,
                   final @JsonProperty(REJECTED_POLICY_LABEL2) String rejectedPolicy2,
                   final @JsonProperty(CALLER_RUNS_WHEN_REJECTED_LABEL) String callerRunsWhenRejected,
                   final @JsonProperty(CALLER_RUNS_WHEN_REJECTED_LABEL2) String callerRunsWhenRejected2,
                   final @JsonProperty("id") String id) {
        setDescription(description);
        setExecutorService(executorService != null ? executorService : executorService2);
        setPoolSize(poolSize != null ? poolSize : poolSize2);
        setMaxPoolSize(maxPoolSize != null ? maxPoolSize : maxPoolSize2);
        setKeepAliveTime(keepAliveTime != null ? keepAliveTime : keepAliveTime2);
        setTimeUnit(timeUnit != null ? timeUnit : timeUnit2);
        setMaxQueueSize(maxQueueSize != null ? maxQueueSize : maxQueueSize2);
        setAllowCoreThreadTimeOut(allowCoreThreadTimeOut != null ? allowCoreThreadTimeOut : allowCoreThreadTimeOut2);
        setThreadName(threadName != null ? threadName : threadName2);
        setRejectedPolicy(rejectedPolicy != null ? rejectedPolicy : rejectedPolicy2);
        setCallerRunsWhenRejected(callerRunsWhenRejected != null ? callerRunsWhenRejected : callerRunsWhenRejected2);
        setId(id);
    }


    public Threads(Step step) {
        super(step);
    }


    @Override
    protected void assignAttribute(final Parameter<?> parameter) {
        switch (parameter.getId()) {
            case EXECUTOR_SERVICE_LABEL:
            case EXECUTOR_SERVICE_LABEL2:
                this.setExecutorService((Map<String, Object>) parameter.getValue());
                break;
            case POOL_SIZE_LABEL:
            case POOL_SIZE_LABEL2:
                this.setPoolSize(Integer.valueOf(String.valueOf(parameter.getValue())));
                break;
            case MAX_POOL_SIZE_LABEL:
            case MAX_POOL_SIZE_LABEL2:
                this.setMaxPoolSize(Integer.valueOf(String.valueOf(parameter.getValue())));
                break;
            case KEEP_ALIVE_TIME_LABEL:
            case KEEP_ALIVE_TIME_LABEL2:
                this.setKeepAliveTime(Long.valueOf(String.valueOf(parameter.getValue())));
                break;
            case TIME_UNIT_LABEL:
            case TIME_UNIT_LABEL2:
                this.setTimeUnit(String.valueOf(parameter.getValue()));
                break;
            case MAX_QUEUE_SIZE_LABEL:
            case MAX_QUEUE_SIZE_LABEL2:
                this.setMaxQueueSize(Integer.valueOf(String.valueOf(parameter.getValue())));
                break;
            case ALLOW_CORE_THREAD_TIME_OUT_LABEL:
            case ALLOW_CORE_THREAD_TIME_OUT_LABEL2:
                this.setAllowCoreThreadTimeOut(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case THREAD_NAME_LABEL:
            case THREAD_NAME_LABEL2:
                this.setThreadName(String.valueOf(parameter.getValue()));
                break;
            case REJECTED_POLICY_LABEL:
            case REJECTED_POLICY_LABEL2:
                this.setRejectedPolicy(String.valueOf(parameter.getValue()));
                break;
            case CALLER_RUNS_WHEN_REJECTED_LABEL:
            case CALLER_RUNS_WHEN_REJECTED_LABEL2:
                this.setCallerRunsWhenRejected(String.valueOf(parameter.getValue()));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                break;
        }
    }

    @Override
    protected void assignProperty(final Parameter<?> parameter) {
        switch (parameter.getId()) {
            case EXECUTOR_SERVICE_LABEL:
            case EXECUTOR_SERVICE_LABEL2:
                parameter.setValue(this.getExecutorService());
                break;
            case POOL_SIZE_LABEL:
            case POOL_SIZE_LABEL2:
                parameter.setValue(this.getPoolSize());
                break;
            case MAX_POOL_SIZE_LABEL:
            case MAX_POOL_SIZE_LABEL2:
                parameter.setValue(this.getMaxPoolSize());
                break;
            case KEEP_ALIVE_TIME_LABEL:
            case KEEP_ALIVE_TIME_LABEL2:
                parameter.setValue(this.getKeepAliveTime());
                break;
            case TIME_UNIT_LABEL:
            case TIME_UNIT_LABEL2:
                parameter.setValue(this.getTimeUnit());
                break;
            case MAX_QUEUE_SIZE_LABEL:
            case MAX_QUEUE_SIZE_LABEL2:
                parameter.setValue(this.getMaxQueueSize());
                break;
            case ALLOW_CORE_THREAD_TIME_OUT_LABEL:
            case ALLOW_CORE_THREAD_TIME_OUT_LABEL2:
                parameter.setValue(this.getAllowCoreThreadTimeOut());
                break;
            case THREAD_NAME_LABEL:
            case THREAD_NAME_LABEL2:
                parameter.setValue(this.getThreadName());
                break;
            case REJECTED_POLICY_LABEL:
            case REJECTED_POLICY_LABEL2:
                parameter.setValue(this.getRejectedPolicy());
                break;
            case CALLER_RUNS_WHEN_REJECTED_LABEL:
            case CALLER_RUNS_WHEN_REJECTED_LABEL2:
                parameter.setValue(this.getCallerRunsWhenRejected());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.getDescription());
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getDefaultRepresenterProperties();
        if (this.getDescription() != null) {
            properties.put(DESCRIPTION_LABEL, this.getDescription());
        }
        if (this.getExecutorService() != null) {
            properties.put(EXECUTOR_SERVICE_LABEL2, this.getExecutorService());
        }
        if (this.getPoolSize() != null) {
            properties.put(POOL_SIZE_LABEL2, this.getPoolSize());
        }
        if (this.getMaxPoolSize() != null) {
            properties.put(MAX_POOL_SIZE_LABEL2, this.getMaxPoolSize());
        }
        if (this.getKeepAliveTime() != null) {
            properties.put(KEEP_ALIVE_TIME_LABEL2, this.getKeepAliveTime());
        }
        if (this.getTimeUnit() != null) {
            properties.put(TIME_UNIT_LABEL2, this.getTimeUnit());
        }
        if (this.getMaxQueueSize() != null) {
            properties.put(MAX_QUEUE_SIZE_LABEL2, this.getMaxQueueSize());
        }
        if (this.getAllowCoreThreadTimeOut() != null) {
            properties.put(ALLOW_CORE_THREAD_TIME_OUT_LABEL2, this.getAllowCoreThreadTimeOut());
        }
        if (this.getThreadName() != null) {
            properties.put(THREAD_NAME_LABEL2, this.getThreadName());
        }
        if (this.getRejectedPolicy() != null) {
            properties.put(REJECTED_POLICY_LABEL2, this.getRejectedPolicy());
        }
        if (this.getCallerRunsWhenRejected() != null) {
            properties.put(CALLER_RUNS_WHEN_REJECTED_LABEL2, this.getCallerRunsWhenRejected());
        }

        return properties;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }

    public Map<String, Object> getExecutorService() {
        return executorService;
    }

    public void setExecutorService(final Map<String, Object> executorService) {
        this.executorService = executorService;
    }

    public Integer getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(final Integer poolSize) {
        this.poolSize = poolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(final Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(final Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(final String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Integer getMaxQueueSize() {
        return maxQueueSize;
    }

    public void setMaxQueueSize(final Integer maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public Boolean getAllowCoreThreadTimeOut() {
        return allowCoreThreadTimeOut;
    }

    public void setAllowCoreThreadTimeOut(final Boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(final String threadName) {
        this.threadName = threadName;
    }

    public String getRejectedPolicy() {
        return rejectedPolicy;
    }

    public void setRejectedPolicy(final String rejectedPolicy) {
        this.rejectedPolicy = rejectedPolicy;
    }

    public String getCallerRunsWhenRejected() {
        return callerRunsWhenRejected;
    }

    public void setCallerRunsWhenRejected(final String callerRunsWhenRejected) {
        this.callerRunsWhenRejected = callerRunsWhenRejected;
    }
}
