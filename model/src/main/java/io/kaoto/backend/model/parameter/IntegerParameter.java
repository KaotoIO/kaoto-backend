package io.kaoto.backend.model.parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * 🐱miniclass IntegerParameter (Parameter)
 *
 */
@JsonTypeName("integer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntegerParameter extends Parameter<Integer> {

    private Integer maximum;
    private Integer minimum;

    public IntegerParameter(final String id, final String title,
                            final String description, final Integer v) {
        super(id, title, description, v);
    }

    public IntegerParameter() {
        super();
    }
    /*
     * 🐱property maximum: Integer
     *
     * Maximum value for this property.
     */
    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(final Integer maximum) {
        this.maximum = maximum;
    }

    /*
     * 🐱property minimum: Integer
     *
     * Minimum value for this property.
     */
    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(final Integer minimum) {
        this.minimum = minimum;
    }

    @Override
    public String getType() {
        return "integer";
    }
}
