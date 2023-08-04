package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class Threads extends EIPStep {

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


    @JsonProperty(KamelHelper.DESCRIPTION)
    private Map<String, String> description;

    @JsonProperty(EXECUTOR_SERVICE_LABEL)
    @JsonAlias(EXECUTOR_SERVICE_LABEL2)
    private Map<String, Object> executorService;

    @JsonProperty(POOL_SIZE_LABEL)
    @JsonAlias(POOL_SIZE_LABEL2)
    private Integer poolSize;

    @JsonProperty(MAX_POOL_SIZE_LABEL)
    @JsonAlias(MAX_POOL_SIZE_LABEL2)
    private Integer maxPoolSize;

    @JsonProperty(KEEP_ALIVE_TIME_LABEL)
    @JsonAlias(KEEP_ALIVE_TIME_LABEL2)
    private Long keepAliveTime;

    @JsonProperty(TIME_UNIT_LABEL)
    @JsonAlias(TIME_UNIT_LABEL2)
    private String timeUnit;

    @JsonProperty(MAX_QUEUE_SIZE_LABEL)
    @JsonAlias(MAX_QUEUE_SIZE_LABEL2)
    private Integer maxQueueSize;

    @JsonProperty(ALLOW_CORE_THREAD_TIME_OUT_LABEL)
    @JsonAlias(ALLOW_CORE_THREAD_TIME_OUT_LABEL2)
    private Boolean allowCoreThreadTimeOut;

    @JsonProperty(THREAD_NAME_LABEL)
    @JsonAlias(THREAD_NAME_LABEL2)
    private String threadName;

    @JsonProperty(REJECTED_POLICY_LABEL)
    @JsonAlias(REJECTED_POLICY_LABEL2)
    private String rejectedPolicy;

    @JsonProperty(CALLER_RUNS_WHEN_REJECTED_LABEL)
    @JsonAlias(CALLER_RUNS_WHEN_REJECTED_LABEL2)
    private String callerRunsWhenRejected;


    public Threads() {
        //Needed for serialization
    }


    public Threads(Step step) {
        super(step);
    }


    @Override
    protected void assignAttribute(final Parameter parameter) {
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
            case KamelHelper.DESCRIPTION:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                break;
        }
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
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
            case KamelHelper.DESCRIPTION:
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
            properties.put(KamelHelper.DESCRIPTION, this.getDescription());
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
