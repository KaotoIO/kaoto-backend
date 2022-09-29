package io.kaoto.backend.model.parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * 🐱miniclass ArrayParameter (Parameter)
 *
 */
@JsonTypeName("array")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrayParameter extends Parameter<Object[]> {
    private Integer maxItems;
    private Integer minItems;
    private Boolean uniqueItems;

    public ArrayParameter(final String id, final String title,
                          final String description, final Object[] v) {
        super(id, title, description, v);
    }

    public ArrayParameter() {
        super();
    }

    /*
     * 🐱property maxItems: Integer
     *
     * Maximum number of items this array can have.
     */
    public Integer getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(final Integer maxItems) {
        this.maxItems = maxItems;
    }

    /*
     * 🐱property minItems: Integer
     *
     * Minimum number of items this array must have.
     */
    public Integer getMinItems() {
        return minItems;
    }

    public void setMinItems(final Integer minItems) {
        this.minItems = minItems;
    }

    /*
     * 🐱property uniqueItems: boolean
     *
     * Indicates if all items must be unique.
     */
    public Boolean getUniqueItems() {
        return uniqueItems;
    }

    public void setUniqueItems(final Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    @Override
    public String getType() {
        return "array";
    }
}
