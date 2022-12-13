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
public class Sort extends Expression {


    public static final String COMPARATOR_LABEL = "comparator";

    public static final String COMPARATOR_LABEL2 = "comparator-ref";

    public static final String COMPARATOR_LABEL3 = "comparatorRef";

    public static final String DESCRIPTION_LABEL = "description";

    private String comparator;

    private Map<String, String> description;

    public Sort() {
        //Empty for serialization purposes
    }

    @JsonCreator
    public Sort(final @JsonProperty(EXPRESSION_LABEL) Expression expression,
                final @JsonProperty(CONSTANT_LABEL) String constant,
                final @JsonProperty(SIMPLE_LABEL) String simple,
                final @JsonProperty(JQ_LABEL) String jq,
                final @JsonProperty(COMPARATOR_LABEL) String comparator,
                final @JsonProperty(COMPARATOR_LABEL2) String comparator2,
                final @JsonProperty(COMPARATOR_LABEL3) String comparator3,
                final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description) {
        super(expression, constant, simple, jq, null);
        final var alternativeComparator = comparator2 != null ? comparator2 : comparator3;
        setComparator(comparator != null ? comparator : alternativeComparator);
        setDescription(description);
    }

    public Sort(Step step) {
        super(step);
    }


    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getRepresenterProperties();

        if (this.getComparator() != null) {
            properties.put(COMPARATOR_LABEL, this.getComparator());
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
            case COMPARATOR_LABEL:
            case COMPARATOR_LABEL2:
            case COMPARATOR_LABEL3:
                this.setComparator(String.valueOf(parameter.getValue()));
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
            case COMPARATOR_LABEL:
            case COMPARATOR_LABEL2:
            case COMPARATOR_LABEL3:
                parameter.setValue(this.getComparator());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.getDescription());
                break;
            default:
                break;
        }
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(final String comparator) {
        this.comparator = comparator;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }
}
