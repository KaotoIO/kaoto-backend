package io.kaoto.backend.deployment;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WithKubernetesTestServer
@QuarkusTest
class ClusterServiceTest {

    private String kameletBinding = "apiVersion: camel.apache.org/v1alpha1\n"
            + "kind: KameletBinding\n"
            + "metadata:\n"
            + "  name: abinding\n"
            + "spec:\n"
            + "  source:\n"
            + "    uri: timer:foo\n"
            + "  sink: \n"
            + "    uri: log:bar";

    private String kameletBinding2 = "apiVersion: camel.apache.org/v1alpha1\n"
            + "kind: KameletBinding\n"
            + "metadata:\n"
            + "  name: anotherbinding\n"
            + "spec:\n"
            + "  source:\n"
            + "    uri: timer:foo\n"
            + "  sink: \n"
            + "    uri: log:bar";

    private String kamelet = "apiVersion: camel.apache.org/v1alpha1\n"
            + "kind: Kamelet\n"
            + "metadata:\n"
            + "  name: kamelet-test-sink\n"
            + "spec:\n"
            + "  definition:\n"
            + "    title: \"Test Sink\"\n"
            + "    description: Test Sink\n"
            + "    required:\n"
            + "    type: object\n"
            + "    properties:\n"
            + "      property:\n"
            + "        title: Token\n"
            + "        description: Blablax\n"
            + "        type: string\n"
            + "  template:\n"
            + "    from:\n"
            + "      uri: kamelet:source\n"
            + "      steps:\n"
            + "      - to:\n"
            + "          uri: kamelet:sink\n";

    private String integration = "apiVersion: camel.apache.org/v1\n"
            + "kind: Integration\n"
            + "metadata:\n"
            + "  labels: \n"
            + "    camel.apache.org/created.by.kind: KameletBinding\n"
            + "    camel.apache.org/created.by.name: abinding\n\n"
            + "  name: hello.yaml\n"
            + "spec:\n"
            + "  flows:\n"
            + "  - from:\n"
            + "      uri: timer:tick\n"
            + "      parameters:\n"
            + "        period: '5000'\n"
            + "      steps:\n"
            + "      - to:\n"
            + "          uri: log:tick\n";
    private String integration2 = "apiVersion: camel.apache.org/v1\n"
            + "kind: Integration\n"
            + "metadata:\n"
            + "  name: asdf.yaml\n"
            + "spec:\n"
            + "  flows:\n"
            + "  - from:\n"
            + "      uri: timer:tick\n"
            + "      parameters:\n"
            + "        period: '5000'\n"
            + "      steps:\n"
            + "      - to:\n"
            + "          uri: log:tick\n";

    @Inject
    private ClusterService clusterService;

    @Inject
    private KubernetesClient kubernetesClient;


    @BeforeEach
    void cleanResources() {
        var resources = clusterService.getResources("default");
        for (var d : resources) {
            clusterService.stop(d.getName(), "default", null);
        }
    }

    @Test
    void testAll() {
        String ns = "default";
        assertTrue(clusterService.getResources(ns).isEmpty());

        assertThrows(IllegalArgumentException.class,
                () -> clusterService.start("Wrong text", ns));

        assertTrue(clusterService.getResources(ns).isEmpty());

        clusterService.start(kameletBinding, ns);
        assertFalse(clusterService.getResources(ns).isEmpty());

        final var integrations = clusterService.getResources(ns);
        assertEquals(1, integrations.size());
        final var integration = integrations.get(0);

        assertNotNull(integration.getName());
        assertNotNull(integration.getDate());
        assertNotNull(integration.getErrors());
        assertNotNull(integration.getNamespace());

        clusterService.stop(integration.getName(), ns, integration.getType());
        assertTrue(clusterService.getResources(ns).isEmpty());
    }

    @Test
    void testDuplicatedName() {
        String ns = "default";
        clusterService.start(kameletBinding, ns);
        assertEquals(1, clusterService.getResources(ns).size());
        clusterService.start(kameletBinding, ns);
        assertEquals(2, clusterService.getResources(ns).size());
        clusterService.start(kameletBinding, ns);
        assertEquals(3, clusterService.getResources(ns).size());

        final var integrations = clusterService.getResources(ns);
        assertTrue(integrations.stream().allMatch(i -> i.getName().startsWith("abinding")));
        integrations.stream().forEach(i -> clusterService.stop(i.getName(), ns, null));
        assertTrue(clusterService.getResources(ns).isEmpty());
    }

    @Test
    void testGetDeployments() {
        String ns = "default";

        assertTrue(clusterService.getResources(ns).isEmpty());

        kubernetesClient.genericKubernetesResources(new ResourceDefinitionContext.Builder()
                        .withNamespaced(true)
                        .withGroup("camel.apache.org")
                        .withKind("Kamelet")
                        .withPlural("Kamelets")
                        .withVersion("v1alpha1")
                        .build())
                .inNamespace(ns)
                .load(new ByteArrayInputStream(kamelet.getBytes(StandardCharsets.UTF_8)))
                .create();

        assertEquals(1, clusterService.getResources(ns).size());

        kubernetesClient.genericKubernetesResources(new ResourceDefinitionContext.Builder()
                        .withNamespaced(true)
                        .withGroup("camel.apache.org")
                        .withKind("Integration")
                        .withPlural("Integrations")
                        .withVersion("v1")
                        .build())
                .inNamespace(ns)
                .load(new ByteArrayInputStream(integration.getBytes(StandardCharsets.UTF_8)))
                .create();

        assertEquals(1, clusterService.getResources(ns).size());

        kubernetesClient.genericKubernetesResources(new ResourceDefinitionContext.Builder()
                        .withNamespaced(true)
                        .withGroup("camel.apache.org")
                        .withKind("Integration")
                        .withPlural("Integrations")
                        .withVersion("v1")
                        .build())
                .inNamespace(ns)
                .load(new ByteArrayInputStream(integration2.getBytes(StandardCharsets.UTF_8)))
                .create();

        assertEquals(2, clusterService.getResources(ns).size());

        kubernetesClient.genericKubernetesResources(new ResourceDefinitionContext.Builder()
                        .withNamespaced(true)
                        .withGroup("camel.apache.org")
                        .withKind("KameletBinding")
                        .withPlural("KameletBindings")
                        .withVersion("v1alpha1")
                        .build())
                .inNamespace(ns)
                .load(new ByteArrayInputStream(kameletBinding.getBytes(StandardCharsets.UTF_8)))
                .create();

        assertEquals(3, clusterService.getResources(ns).size());

        kubernetesClient.genericKubernetesResources(new ResourceDefinitionContext.Builder()
                        .withNamespaced(true)
                        .withGroup("camel.apache.org")
                        .withKind("KameletBinding")
                        .withPlural("KameletBindings")
                        .withVersion("v1alpha1")
                        .build())
                .inNamespace(ns)
                .load(new ByteArrayInputStream(kameletBinding2.getBytes(StandardCharsets.UTF_8)))
                .create();

        assertEquals(4, clusterService.getResources(ns).size());
    }

    @Test
    void logs() {
        var pod = "apiVersion: v1\n"
                + "kind: Pod\n"
                + "metadata:\n"
                + "  generateName: abinding-759497b44d-\n"
                + "  labels:\n"
                + "    camel.apache.org/integration: abinding\n"
                + "spec:\n"
                + "  containers:\n"
                + "status:\n"
                + "  phase: Running";


        kubernetesClient.genericKubernetesResources(new ResourceDefinitionContext.Builder()
                        .withNamespaced(true)
                        .withKind("Pod")
                        .withPlural("Pods")
                        .withVersion("v1")
                        .build())
                .inNamespace("default")
                .load(new ByteArrayInputStream(pod.getBytes(StandardCharsets.UTF_8)))
                .create();


        kubernetesClient.genericKubernetesResources(new ResourceDefinitionContext.Builder()
                        .withNamespaced(true)
                        .withGroup("camel.apache.org")
                        .withKind("KameletBinding")
                        .withPlural("KameletBindings")
                        .withVersion("v1alpha1")
                        .build())
                .inNamespace("default")
                .load(new ByteArrayInputStream(kameletBinding.getBytes(StandardCharsets.UTF_8)))
                .create();

        var logs = clusterService.streamlogs("default", "abinding", "KameletBinding", 50);
        assertNotNull(logs);
        assertTrue(logs.subscribe().asStream().allMatch(s -> s != null));

        assertThrows(IllegalArgumentException.class,
                () -> {clusterService.streamlogs("default", "abinding", "Integration", 50);});
        assertThrows(IllegalArgumentException.class,
                () -> {clusterService.streamlogs("default", "abinding", "Kamelet", 50);});

        String intyaml = "apiVersion: camel.apache.org/v1\n"
                + "kind: Integration\n"
                + "metadata:\n"
                + "  name: abinding\n"
                + "spec:\n"
                + "  flows:\n"
                + "  - from:\n"
                + "      uri: timer:tick\n"
                + "      parameters:\n"
                + "        period: '5000'\n"
                + "      steps:\n"
                + "      - to:\n"
                + "          uri: log:tick\n";

        kubernetesClient.genericKubernetesResources(new ResourceDefinitionContext.Builder()
                        .withNamespaced(true)
                        .withGroup("camel.apache.org")
                        .withKind("Integration")
                        .withPlural("Integrations")
                        .withVersion("v1")
                        .build())
                .inNamespace("default")
                .load(new ByteArrayInputStream(intyaml.getBytes(StandardCharsets.UTF_8)))
                .create();

        logs = clusterService.streamlogs("default", "abinding", "Integration", 50);
        assertNotNull(logs);
        assertTrue(logs.subscribe().asStream().allMatch(s -> s != null));
    }

    public ClusterService getClusterService() {
        return clusterService;
    }

    public void setClusterService(final ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    public KubernetesClient getKubernetesClient() {
        return kubernetesClient;
    }

    public void setKubernetesClient(final KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }
}
