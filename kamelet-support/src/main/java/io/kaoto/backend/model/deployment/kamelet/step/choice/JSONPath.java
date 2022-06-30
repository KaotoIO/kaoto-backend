package io.kaoto.backend.model.deployment.kamelet.step.choice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.kaoto.backend.model.deployment.kamelet.Expression;


@JsonPropertyOrder({"expression", "suppress-exceptions"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONPath {

    @JsonProperty("expression")
    private Expression expression;

    @JsonProperty("suppress-exceptions")
    private Boolean suppressExceptions;

    public void setExpression(
            final Expression expression) {
        this.expression = expression;
    }

    public void setSuppressExceptions(final Boolean suppressExceptions) {
        this.suppressExceptions = suppressExceptions;
    }

    public Boolean getSuppressExceptions() {
        return suppressExceptions;
    }

    public Expression getExpression() {
        return expression;
    }
}
