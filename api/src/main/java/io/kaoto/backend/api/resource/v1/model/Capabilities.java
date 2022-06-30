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

    public List<Map<String, String>> getDsls() {
        return dsls;
    }

    public void setDsls(
            final Collection<Map<String, String>> dsls) {
        this.dsls.clear();
        this.dsls.addAll(dsls);
    }
}
