package io.kaoto.backend.camel.model.deployment.kamelet.step.dataformat;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataFormat implements Serializable {

    @Serial
    private static final long serialVersionUID = 239784528129L;

    private String format;
    private Map<String, Object> properties;

    public DataFormat() {
        //Needed for serialization
    }

    public DataFormat(Object dataformat) {
        this.setProperties(new HashMap<>());
        if (dataformat instanceof DataFormat df) {
            this.setFormat(df.getFormat());
            this.setProperties(df.getProperties());
        } else if (dataformat instanceof Map map) {
            this.setFormat(String.valueOf(map.getOrDefault("format", "")));

            final var potentialProperties = map.getOrDefault("properties", null);
            if (potentialProperties instanceof Map) {
                this.setProperties((Map<String, Object>) potentialProperties);
            }
        }
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(final Map<String, Object> properties) {
        this.properties = properties;
    }
}
