package io.kaoto.backend.model.deployment.kamelet.step.dataformat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public class DataFormat implements Serializable {

    @Serial
    private static final long serialVersionUID = 239784528129L;

    private String format;
    private Map<String, String> properties;

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(
            final Map<String, String> properties) {
        this.properties = properties;
    }
}
