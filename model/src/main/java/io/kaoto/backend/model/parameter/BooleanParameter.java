package io.kaoto.backend.model.parameter;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("boolean")
public class BooleanParameter extends Parameter<Boolean> {

    public BooleanParameter(final String id, final String title,
                            final String description, final Boolean v,
                            final String type) {
        super(id, title, description, v, type);
    }

    public BooleanParameter() {
        super();
    }

    @Override
    public String getType() {
        return "boolean";
    }
}
