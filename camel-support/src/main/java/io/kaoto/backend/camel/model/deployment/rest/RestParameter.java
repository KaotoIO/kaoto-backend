package io.kaoto.backend.camel.model.deployment.rest;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.camel.KamelHelper;

import java.io.Serializable;
import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestParameter implements Serializable {
    private static final long serialVersionUID = -2303893619555604361L;
    public static final String DATA_TYPE_LABEL = "dataType";
    public static final String DATA_TYPE_LABEL2 = "data-type";
    public static final String DEFAULT_VALUE_LABEL = "defaultValue";
    public static final String DEFAULT_VALUE_LABEL2 = "default-value";
    public static final String ARRAY_TYPE_LABEL = "arrayType";
    public static final String ARRAY_TYPE_LABEL2 = "array-type";
    public static final String COLLECTION_FORMAT_LABEL = "collectionFormat";
    public static final String COLLECTION_FORMAT_LABEL2 = "collection-format";
    public static final String DATA_FORMAT_LABEL = "dataFormat";
    public static final String DATA_FORMAT_LABEL2 = "data-format";
    public static final String DESCRIPTION_LABEL = KamelHelper.DESCRIPTION;
    public static final String NAME_LABEL = KamelHelper.NAME;
    public static final String REQUIRED_LABEL = "required";
    public static final String TYPE_LABEL = "type";
    public static final String EXAMPLES_LABEL = "examples";

    @JsonProperty(DATA_TYPE_LABEL)
    @JsonAlias(DATA_TYPE_LABEL2)
    private String dataType;

    @JsonProperty(DEFAULT_VALUE_LABEL)
    @JsonAlias(DEFAULT_VALUE_LABEL2)
    private Object defaultValue;

    @JsonProperty(DESCRIPTION_LABEL)
    private String description;

    @JsonProperty(NAME_LABEL)
    private String name;

    @JsonProperty(REQUIRED_LABEL)
    private Boolean required;

    @JsonProperty(TYPE_LABEL)

    private String type;

    @JsonProperty(ARRAY_TYPE_LABEL)
    @JsonAlias(ARRAY_TYPE_LABEL2)

    private String arrayType;

    @JsonProperty(COLLECTION_FORMAT_LABEL)
    @JsonAlias(COLLECTION_FORMAT_LABEL2)
    private String collectionFormat;

    @JsonProperty(DATA_FORMAT_LABEL)
    @JsonAlias(DATA_FORMAT_LABEL2)
    private String dataFormat;

    @JsonProperty(EXAMPLES_LABEL)
    private Object examples;


    public RestParameter() {
    //Needed for serialization
    }

    public RestParameter(final RestParameter that) {
        super();
        setDataType(that.getDataType());
        setDefaultValue(that.getDefaultValue());
        setDescription(that.getDescription());
        setName(that.getName());
        setRequired(that.getRequired());
        setType(that.getType());
        setArrayType(that.getArrayType());
        setCollectionFormat(that.getCollectionFormat());
        setDataFormat(that.getDataFormat());
        setExamples(that.getExamples());
    }

    public RestParameter(Map<String, Object> map) {
        this(KamelHelper.GENERIC_MAPPER.convertValue(map, RestParameter.class));
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArrayType() {
        return arrayType;
    }

    public void setArrayType(String arrayType) {
        this.arrayType = arrayType;
    }

    public String getCollectionFormat() {
        return collectionFormat;
    }

    public void setCollectionFormat(String collectionFormat) {
        this.collectionFormat = collectionFormat;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public Object getExamples() {
        return examples;
    }

    public void setExamples(Object examples) {
        this.examples = examples;
    }
}
