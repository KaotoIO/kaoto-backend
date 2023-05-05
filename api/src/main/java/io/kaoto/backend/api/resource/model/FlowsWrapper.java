package io.kaoto.backend.api.resource.model;

import io.kaoto.backend.api.resource.v1.model.Integration;
import java.util.List;
import java.util.Map;

/**
 * 🐱class FlowsWrapper
 *
 * Used by the API to pass flows back and forth.
 *
 */
public record FlowsWrapper(List<Integration> flows, Map<String, Object> properties) {

    public FlowsWrapper(List<Integration> flows) {
        this(flows, null);
    }

    public FlowsWrapper(List<Integration> flows, Map<String, Object> properties) {
        this.flows = flows;
        this.properties = properties;
    }
}
