package io.kaoto.backend.deployment;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WithKubernetesTestServer
@QuarkusTest
class ClusterServiceTest {

    private String binding = "apiVersion: camel.apache.org/v1alpha1\n"
            + "kind: KameletBinding\n"
            + "spec:\n"
            + "  source:\n"
            + "    uri: timer:foo\n"
            + "  sink: \n"
            + "    uri: log:bar";

    @Inject
    private ClusterService clusterService;


    @Test
    void testAll() throws URISyntaxException {
        assertTrue(clusterService.getIntegrations().isEmpty());

        assertFalse(clusterService.start("Wrong text"));
        assertTrue(clusterService.getIntegrations().isEmpty());

        assertTrue(clusterService.start(binding));
        assertFalse(clusterService.getIntegrations().isEmpty());

        final var integrations = clusterService.getIntegrations();
        assertEquals(1, integrations.size());
        final var integration = integrations.get(0);

        assertNotNull(integration.getName());
        assertTrue(integration.isRunning());

        clusterService.stop(integration);
        assertTrue(clusterService.getIntegrations().isEmpty());
    }

    public ClusterService getClusterService() {
        return clusterService;
    }

    public void setClusterService(final ClusterService clusterService) {
        this.clusterService = clusterService;
    }
}
