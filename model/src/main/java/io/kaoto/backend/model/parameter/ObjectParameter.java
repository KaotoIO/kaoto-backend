package io.kaoto.backend.model.parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * 🐱miniclass ObjectParameter (Parameter)
 *
 */
@JsonTypeName("object")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectParameter extends Parameter<Object> {
    private Integer maxProperties;
    private Integer minProperties;
    private String[] required;

    public ObjectParameter(final String id, final String title,
                           final String description, final Object v) {
        super(id, title, description, v);
    }

    public ObjectParameter() {
        super();
    }

    /*
     * 🐱property maxProperties: Integer
     *
     * Maximum number of properties this object can have.
     */
    public Integer getMaxProperties() {
        return maxProperties;
    }

    public void setMaxProperties(final Integer maxProperties) {
        this.maxProperties = maxProperties;
    }

    /*
     * 🐱property minProperties: Integer
     *
     * Minimum number of properties this object can have.
     */
    public Integer getMinProperties() {
        return minProperties;
    }

    public void setMinProperties(final Integer minProperties) {
        this.minProperties = minProperties;
    }

    /*
     * 🐱property required: String[]
     *
     * List of properties that the object must have.
     */
    public String[] getRequired() {
        return required;
    }

    public void setRequired(final String[] required) {
        this.required = required;
    }

    @Override
    public String getType() {
        return "object";
    }
}
