package io.kaoto.backend.model.parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * 🐱miniclass NumberParameter (Parameter)
 *
 */
@JsonTypeName("number")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NumberParameter extends Parameter<Number> {

    private Number maximum;
    private Number minimum;

    public NumberParameter(final String id,
                           final String title,
                           final String description,
                           final Boolean nullable,
                           final Number[] enumeration,
                           final Number[] examples,
                           final Number defaultValue)  {
            super(id, title, description, nullable, enumeration, examples, defaultValue);
    }

    public NumberParameter() {
        super();
    }

    @Override
    public Number convertToType(final Object value) {
        try {
            return Long.valueOf(String.valueOf(value));
        } catch (Exception e) {
            return null;
        }
    }

    /*
     * 🐱property maximum: Number
     *
     * Maximum value for this property.
     */
    public Number getMaximum() {
        return maximum;
    }

    public void setMaximum(final Number maximum) {
        this.maximum = maximum;
    }

    /*
     * 🐱property minimum: Number
     *
     * Minimum value for this property.
     */
    public Number getMinimum() {
        return minimum;
    }

    public void setMinimum(final Number minimum) {
        this.minimum = minimum;
    }

    @Override
    public String getType() {
        return "number";
    }
}
