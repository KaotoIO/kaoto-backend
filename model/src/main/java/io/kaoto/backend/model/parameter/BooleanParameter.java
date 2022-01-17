package io.kaoto.backend.model.parameter;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("boolean")
public class BooleanParameter extends Parameter<Boolean> {

    public BooleanParameter(final String id, final String title,
                            final String description, final Boolean v) {
        super(id, title, description, v);
    }

    public BooleanParameter() {
        super();
    }

    @Override
    public String getType() {
        return "boolean";
    }
}
