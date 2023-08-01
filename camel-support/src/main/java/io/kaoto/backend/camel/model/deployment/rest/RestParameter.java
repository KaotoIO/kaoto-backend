package io.kaoto.backend.camel.model.deployment.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.camel.KamelHelper;

import java.io.Serializable;
import java.util.Map;

@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
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

    private String dataType;

    private Object defaultValue;

    private String description;

    private String name;

    private Boolean required;

    private String type;

    private String arrayType;

    private String collectionFormat;

    private String dataFormat;

    private Object examples;


    @JsonCreator
    public RestParameter(final @JsonProperty(DATA_TYPE_LABEL2) String dataType,
                         final @JsonProperty(DATA_TYPE_LABEL) String dataType2,
                         final @JsonProperty(DEFAULT_VALUE_LABEL2) Object defaultValue,
                         final @JsonProperty(DEFAULT_VALUE_LABEL) Object defaultValue2,
                         final @JsonProperty(DESCRIPTION_LABEL) String description,
                         final @JsonProperty(NAME_LABEL) String name,
                         final @JsonProperty(REQUIRED_LABEL) Boolean required,
                         final @JsonProperty(TYPE_LABEL) String type,
                         final @JsonProperty(ARRAY_TYPE_LABEL2) String arrayType,
                         final @JsonProperty(ARRAY_TYPE_LABEL) String arrayType2,
                         final @JsonProperty(COLLECTION_FORMAT_LABEL2) String collectionFormat,
                         final @JsonProperty(COLLECTION_FORMAT_LABEL) String collectionFormat2,
                         final @JsonProperty(DATA_FORMAT_LABEL2) String dataFormat,
                         final @JsonProperty(DATA_FORMAT_LABEL) String dataFormat2,
                         final @JsonProperty(EXAMPLES_LABEL) Object examples) {
        super();
        setDataType(dataType != null ? dataType : dataType2);
        setDefaultValue(defaultValue != null ? defaultValue : defaultValue2);
        setDescription(description);
        setName(name);
        setRequired(required);
        setType(type);
        setArrayType(arrayType != null ? arrayType : arrayType2);
        setCollectionFormat(collectionFormat != null ? collectionFormat : collectionFormat2);
        setDataFormat(dataFormat != null ? dataFormat : dataFormat2);
        setExamples(examples);
    }

    public RestParameter(Map<String, Object> map) {
        if (map.containsKey(DATA_TYPE_LABEL) && map.get(DATA_TYPE_LABEL) != null) {
            setDataType(String.valueOf(map.get(DATA_TYPE_LABEL)));
        } else if (map.containsKey(DATA_TYPE_LABEL2) && map.get(DATA_TYPE_LABEL2) != null) {
            setDataType(String.valueOf(map.get(DATA_TYPE_LABEL2)));
        }
        if (map.containsKey(DEFAULT_VALUE_LABEL) && map.get(DEFAULT_VALUE_LABEL) != null) {
            setDefaultValue(map.get(DEFAULT_VALUE_LABEL));
        } else if (map.containsKey(DEFAULT_VALUE_LABEL2) && map.get(DEFAULT_VALUE_LABEL2) != null) {
            setDefaultValue(map.get(DEFAULT_VALUE_LABEL2));
        }
        if (map.containsKey(ARRAY_TYPE_LABEL) && map.get(ARRAY_TYPE_LABEL) != null) {
            setArrayType(String.valueOf(map.get(ARRAY_TYPE_LABEL)));
        } else if (map.containsKey(ARRAY_TYPE_LABEL2) && map.get(ARRAY_TYPE_LABEL2) != null) {
            setArrayType(String.valueOf(map.get(ARRAY_TYPE_LABEL2)));
        }
        if (map.containsKey(COLLECTION_FORMAT_LABEL) && map.get(COLLECTION_FORMAT_LABEL) != null) {
            setCollectionFormat(String.valueOf(map.get(COLLECTION_FORMAT_LABEL)));
        } else if (map.containsKey(COLLECTION_FORMAT_LABEL2) && map.get(COLLECTION_FORMAT_LABEL2) != null) {
            setCollectionFormat(String.valueOf(map.get(COLLECTION_FORMAT_LABEL2)));
        }
        if (map.containsKey(DATA_FORMAT_LABEL) && map.get(DATA_FORMAT_LABEL) != null) {
            setDataFormat(String.valueOf(map.get(DATA_FORMAT_LABEL)));
        } else if (map.containsKey(DATA_FORMAT_LABEL2) && map.get(DATA_FORMAT_LABEL2) != null) {
            setDataFormat(String.valueOf(map.get(DATA_FORMAT_LABEL2)));
        }
        if (map.containsKey(DESCRIPTION_LABEL) && map.get(DESCRIPTION_LABEL) != null) {
            setDescription(String.valueOf(map.get(DESCRIPTION_LABEL)));
        }
        if (map.containsKey(NAME_LABEL) && map.get(NAME_LABEL) != null) {
            setName(String.valueOf(map.get(NAME_LABEL)));
        }
        if (map.containsKey(REQUIRED_LABEL) && map.get(REQUIRED_LABEL) != null) {
            setRequired(Boolean.valueOf(String.valueOf(map.get(REQUIRED_LABEL))));
        }
        if (map.containsKey(TYPE_LABEL) && map.get(TYPE_LABEL) != null) {
            setType(String.valueOf(map.get(TYPE_LABEL)));
        }
        if (map.containsKey(EXAMPLES_LABEL) && map.get(EXAMPLES_LABEL) != null) {
            setExamples(map.get(EXAMPLES_LABEL));
        }
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
