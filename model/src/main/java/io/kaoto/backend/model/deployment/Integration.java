package io.kaoto.backend.model.deployment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.fabric8.kubernetes.client.CustomResource;

public class Integration {

    private boolean running;
    private String name;

    @JsonIgnore
    private CustomResource resource;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(final boolean running) {
        this.running = running;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public CustomResource getResource() {
        return resource;
    }

    public void setResource(final CustomResource resource) {
        this.resource = resource;
    }
}
