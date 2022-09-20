package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serial;
import java.io.Serializable;

@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class KameletBindingStepRef implements Serializable {
    @Serial
    private static final long serialVersionUID = 13684575275243089L;

    private String kind = "Kamelet";
    private String apiVersion = "camel.apache.org/v1alpha1";
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setKind(final String kind) {
        this.kind = kind;
    }

    public void setApiVersion(final String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KameletBindingStepRef)) {
            return false;
        }

        final KameletBindingStepRef that = (KameletBindingStepRef) o;

        if (getKind() != null ? !getKind().equals(that.getKind()) : that.getKind() != null) {
            return false;
        }
        if (getApiVersion() != null ? !getApiVersion().equals(that.getApiVersion()) : that.getApiVersion() != null) {
            return false;
        }
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getKind() != null ? getKind().hashCode() : 0;
        result = 31 * result + (getApiVersion() != null ? getApiVersion().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "KameletBindingStepRef{" +
                "kind='" + kind + '\'' +
                ", apiVersion='" + apiVersion + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
