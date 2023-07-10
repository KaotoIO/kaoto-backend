package io.kaoto.backend.model.parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.json.bind.annotation.JsonbTypeDeserializer;
import java.util.Arrays;

/**
 * 🐱class Parameter
 * 🐱aka List[Parameter]
 * <p>
 * Represents a parameter of a step in an integration.
 * These parameters could be used on the UI to configure the step.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = ObjectParameter.class,
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StringParameter.class, name = "string"),
        @JsonSubTypes.Type(value = ObjectParameter.class, name = "object"),
        @JsonSubTypes.Type(value = NumberParameter.class, name = "number"),
        @JsonSubTypes.Type(value = IntegerParameter.class, name = "integer"),
        @JsonSubTypes.Type(value = BooleanParameter.class, name = "boolean"),
        @JsonSubTypes.Type(value = ArrayParameter.class, name = "array")})
//This is a workaround utility class until Quarkus supports fully polymorphism
@JsonbTypeDeserializer(ParameterDeserializer.class)
public abstract class Parameter<T> implements Cloneable, Comparable<Parameter<T>> {

    // Kaoto
    private String id;
    private boolean path = false;
    @JsonIgnore
    private Integer pathOrder = 0;
    @JsonIgnore
    private String pathSeparator = ":";
    private Object value;

    //JSON schema
    private String title;
    private String description;
    private Boolean nullable;
    private T[] enumeration;
    private T defaultValue;
    private T[] examples;


    protected Parameter(final String id,
                        final String title,
                        final String description,
                        final Boolean nullable,
                        final T[] enumeration,
                        final T[] examples,
                        final T defaultValue) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.nullable = nullable;
        this.enumeration = enumeration;
        this.examples = examples;
        this.defaultValue = defaultValue;
    }

    protected Parameter() {
        super();
    }

    /*
     * 🐱property description: String
     *
     * Helping text describing the parameter
     */
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    /*
     * 🐱property title: String
     *
     * Human name for the view
     */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    /*
     * 🐱property id: String
     *
     * Identifier of the parameter
     */
    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public abstract T convertToType(final Object value);

    /*
     * 🐱property value: Object
     *
     * Actual value of this parameter.
     * Used when describing a configured element.
     *
     */
    public Object getValue() {
        return this.value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    /*
     * 🐱property default: Object
     *
     * Default value, if there is any
     */
    public T getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(final T defaultValue) {
        this.defaultValue = defaultValue;
    }

    /*
     * 🐱property type: String
     *
     * Type of parameter: number, integer, string, boolean, array, object, or
     *  null
     */
    public abstract String getType();

    /*
     * 🐱property path: Boolean
     *
     * Is this a path parameter?
     */
    public boolean isPath() {
        return path;
    }

    public void setPath(final Boolean path) {
        if (path != null) {
            this.path = path;
        }
    }

    /*
     * 🐱property nullable: Boolean
     *
     * Can this property be null?
     */
    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(final Boolean nullable) {
        this.nullable = nullable;
    }

    /*
     * 🐱property enum: Object[]
     *
     * All the possible values for this property.
     */
    public T[] getEnum() {
        return enumeration;
    }

    public void setEnum(final T[] enumeration) {
        this.enumeration = enumeration;
    }


    /*
     * 🐱property examples: Object[]
     *
     * Examples of valid values.
     */
    public T[] getExamples() {
        return examples;
    }

    public void setExamples(final T[] examples) {
        this.examples = examples;
    }

    /*
     * 🐱property pathOrder: Integer
     *
     * If this parameter is a path parameter, order in which it will appear
     */
    public Integer getPathOrder() {
        return pathOrder;
    }

    public void setPathOrder(Integer pathOrder) {
        this.pathOrder = pathOrder;
    }

    /*
     * 🐱property pathSeparator: String
     *
     * If this parameter is a path parameter, what is the string/character to separate it from the rest
     */
    public String getPathSeparator() {
        return pathSeparator;
    }

    public void setPathSeparator(String pathSeparator) {
        this.pathSeparator = pathSeparator;
    }

    @Override
    public Parameter<T> clone() {
        try {
            return (Parameter<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            //silently fail because... we are not really going to have this
        }
        return this;
    }

    @Override
    public int compareTo(Parameter other) {
        return Integer.compare(this.getPathOrder(), other.getPathOrder());
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "id='" + id + '\'' +
                ", value=" + value +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parameter<?> parameter)) return false;

        if (isPath() != parameter.isPath()) return false;
        if (!getId().equals(parameter.getId())) return false;
        if (getPathOrder() != null ? !getPathOrder().equals(parameter.getPathOrder()) :
                parameter.getPathOrder() != null)
            return false;
        if (getPathSeparator() != null ? !getPathSeparator().equals(
                parameter.getPathSeparator()) : parameter.getPathSeparator() != null)
            return false;
        if (getValue() != null ? !getValue().equals(
                parameter.getValue()) : parameter.getValue() != null) return false;
        if (getTitle() != null ? !getTitle().equals(
                parameter.getTitle()) : parameter.getTitle() != null) return false;
        if (getDescription() != null ? !getDescription().equals(
                parameter.getDescription()) : parameter.getDescription() != null)
            return false;
        if (getNullable() != null ? !getNullable().equals(parameter.getNullable()) : parameter.getNullable() != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(enumeration, parameter.enumeration)) return false;
        if (getDefaultValue() != null ? !getDefaultValue().equals(
                parameter.getDefaultValue()) : parameter.getDefaultValue() != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getExamples(), parameter.getExamples());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (isPath() ? 1 : 0);
        result = 31 * result + (getPathOrder() != null ? getPathOrder().hashCode() : 0);
        result = 31 * result + (getPathSeparator() != null ? getPathSeparator().hashCode() : 0);
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getNullable() != null ? getNullable().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(enumeration);
        result = 31 * result + (getDefaultValue() != null ? getDefaultValue().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getExamples());
        return result;
    }
}
