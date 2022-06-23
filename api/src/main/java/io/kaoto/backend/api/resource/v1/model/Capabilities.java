package io.kaoto.backend.api.resource.v1.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 🐱class Capabilities
 */
public class Capabilities {
    private List<Map<String, String>> dsls = new ArrayList<>();

    public List<Map<String, String>> getDSLs() {
        return dsls;
    }

    public void setDSLs(
            final Collection<Map<String, String>> dsls) {
        this.dsls.addAll(dsls);
    }
}
