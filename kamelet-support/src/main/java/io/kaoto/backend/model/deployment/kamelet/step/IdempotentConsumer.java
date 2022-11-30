package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.List;
import java.util.Map;


public class IdempotentConsumer extends Expression {
    public static final String EXPRESSION_LABEL = "expression";

    public static final String IDEMPOTENT_REPOSITORY_LABEL = "idempotent-repository";
    public static final String IDEMPOTENT_REPOSITORY_LABEL2 = "idempotentRepository";

    public static final String EAGER_LABEL = "eager";

    public static final String COMPLETION_EAGER_LABEL = "completion-eager";
    public static final String COMPLETION_EAGER_LABEL2 = "completionEager";

    public static final String SKIP_DUPLICATE_LABEL = "skip-duplicate";
    public static final String SKIP_DUPLICATE_LABEL2 = "skipDuplicate";

    public static final String REMOVE_ON_FAILURE_LABEL = "remove-on-failure";
    public static final String REMOVE_ON_FAILURE_LABEL2 = "removeOnFailure";

    public static final String DESCRIPTION_LABEL = "description";

    public static final String STEPS_LABEL = "steps";

    @JsonProperty(EXPRESSION_LABEL)
    private Expression expression;

    @JsonProperty(IDEMPOTENT_REPOSITORY_LABEL)
    private Object idempotentRepository;

    @JsonProperty(EAGER_LABEL)
    private Boolean eager;

    @JsonProperty(COMPLETION_EAGER_LABEL)
    private Boolean completionEager;

    @JsonProperty(SKIP_DUPLICATE_LABEL)
    private Boolean skipDuplicate;

    @JsonProperty(REMOVE_ON_FAILURE_LABEL)
    private Boolean removeOnFailure;

    @JsonProperty(DESCRIPTION_LABEL)
    private Map<String, String> description;

    @JsonProperty(STEPS_LABEL)
    private List<FlowStep> steps;


    public IdempotentConsumer(Step step, final KamelPopulator kameletPopulator) {
        super(step);

        if (step.getBranches() != null && !step.getBranches().isEmpty()) {
            setSteps(kameletPopulator.processSteps(step.getBranches().get(0)));
        }
    }

    public IdempotentConsumer() {
         //Needed for serialization
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        super.assignAttribute(parameter);
        switch (parameter.getId()) {
            case IDEMPOTENT_REPOSITORY_LABEL:
            case IDEMPOTENT_REPOSITORY_LABEL2:
                this.setIdempotentRepository(parameter.getValue());
                break;
            case EXPRESSION_LABEL:
                this.setExpression(new Expression(parameter.getValue()));
                break;
            case EAGER_LABEL:
                this.setEager(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case COMPLETION_EAGER_LABEL:
            case COMPLETION_EAGER_LABEL2:
                this.setCompletionEager(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case SKIP_DUPLICATE_LABEL:
            case SKIP_DUPLICATE_LABEL2:
                this.setSkipDuplicate(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case REMOVE_ON_FAILURE_LABEL:
            case REMOVE_ON_FAILURE_LABEL2:
                this.setRemoveOnFailure(Boolean.valueOf(String.valueOf(parameter.getValue())));
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getRepresenterProperties();
        if (this.getExpression() != null) {
            properties.putAll(this.getExpression().getRepresenterProperties());
        }
        if (this.getIdempotentRepository() != null) {
            properties.put(IDEMPOTENT_REPOSITORY_LABEL, this.getIdempotentRepository());
        }
        if (this.getEager() != null) {
            properties.put(EAGER_LABEL, this.getEager());
        }
        if (this.getCompletionEager() != null) {
            properties.put(COMPLETION_EAGER_LABEL, this.getCompletionEager());
        }
        if (this.getSkipDuplicate() != null) {
            properties.put(SKIP_DUPLICATE_LABEL, this.getSkipDuplicate());
        }
        if (this.getRemoveOnFailure() != null) {
            properties.put(REMOVE_ON_FAILURE_LABEL, this.getRemoveOnFailure());
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
    protected void assignProperty(final Parameter parameter) {
        super.assignProperty(parameter);
        switch (parameter.getId()) {
            case EXPRESSION_LABEL:
                parameter.setValue(this.getExpression());
                break;
            case IDEMPOTENT_REPOSITORY_LABEL:
            case IDEMPOTENT_REPOSITORY_LABEL2:
                parameter.setValue(this.getIdempotentRepository());
                break;
            case EAGER_LABEL:
                parameter.setValue(this.getEager());
                break;
            case COMPLETION_EAGER_LABEL:
            case COMPLETION_EAGER_LABEL2:
                parameter.setValue(this.getCompletionEager());
                break;
            case SKIP_DUPLICATE_LABEL:
            case SKIP_DUPLICATE_LABEL2:
                parameter.setValue(this.getSkipDuplicate());
                break;
            case REMOVE_ON_FAILURE_LABEL:
            case REMOVE_ON_FAILURE_LABEL2:
                parameter.setValue(this.getRemoveOnFailure());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.getDescription());
                break;
            default:
                break;
        }
    }

    @Override
    public void processBranches(final Step step, final StepCatalog catalog,
                                final KameletStepParserService kameletStepParserService) {
        var filter_ids = this.getRepresenterProperties();
        var id = STEPS_LABEL;
        if (filter_ids.size() > 0) {
            id = String.valueOf(filter_ids.entrySet().stream().findFirst().get().getValue());
        }
        step.setBranches(List.of(createBranch(id, this.getSteps(), kameletStepParserService)));
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(final Expression expression) {
        this.expression = expression;
    }

    public Object getIdempotentRepository() {
        return idempotentRepository;
    }

    public void setIdempotentRepository(final Object idempotentRepository) {
        this.idempotentRepository = idempotentRepository;
    }

    public Boolean getEager() {
        return eager;
    }

    public void setEager(final Boolean eager) {
        this.eager = eager;
    }

    public Boolean getCompletionEager() {
        return completionEager;
    }

    public void setCompletionEager(final Boolean completionEager) {
        this.completionEager = completionEager;
    }

    public Boolean getSkipDuplicate() {
        return skipDuplicate;
    }

    public void setSkipDuplicate(final Boolean skipDuplicate) {
        this.skipDuplicate = skipDuplicate;
    }

    public Boolean getRemoveOnFailure() {
        return removeOnFailure;
    }

    public void setRemoveOnFailure(final Boolean removeOnFailure) {
        this.removeOnFailure = removeOnFailure;
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
}
