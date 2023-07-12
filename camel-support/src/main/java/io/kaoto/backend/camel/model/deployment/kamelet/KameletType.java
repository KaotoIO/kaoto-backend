package io.kaoto.backend.camel.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serial;
import java.io.Serializable;

@JsonPropertyOrder({"mediatype"})
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KameletType  implements Serializable {
    @Serial
    private static final long serialVersionUID = 6484049935980579227L;

    @JsonProperty("mediatype")
    private String mediatype;

    public String getMediatype() {
        return mediatype;
    }

    public void setMediatype(final String mediatype) {
        this.mediatype = mediatype;
    }
}
