package io.kaoto.backend.model.deployment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.fabric8.kubernetes.client.CustomResource;

import java.util.Arrays;

public class Integration {

    private String name;
    private String date;
    private String[] errors = new String[]{};
    private Object status;
    private String namespace;
    private String type;
    @JsonIgnore
    private CustomResource resource;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }



    @JsonIgnore
    public CustomResource getResource() {
        return resource;
    }

    public void setResource(final CustomResource resource) {
        this.resource = resource;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(final Object status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public String[] getErrors() {
        return Arrays.copyOf(errors, errors.length);
    }

    public void setErrors(final String[] errors) {
        this.errors = errors;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}
