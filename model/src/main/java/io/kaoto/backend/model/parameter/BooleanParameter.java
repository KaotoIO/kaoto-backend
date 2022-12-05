package io.kaoto.backend.model.parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("boolean")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooleanParameter extends Parameter<Boolean> {

    public BooleanParameter(final String id, final String title,
                            final String description, final Boolean v) {
        super(id, title, description, v);
    }

    public BooleanParameter() {
        super();
    }

    @Override
    public Boolean convertToType(final Object value) {
        try {
            return Boolean.valueOf(String.valueOf(value));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getType() {
        return "boolean";
    }
}
