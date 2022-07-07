package io.kaoto.backend.api.resource.v0;

import io.kaoto.backend.api.resource.v0.request.DeploymentResourceYamlRequest;
import io.kaoto.backend.api.service.deployment.DeploymentService;
import io.kaoto.backend.deployment.ClusterService;
import io.kaoto.backend.model.deployment.Integration;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/integrations")
@ApplicationScoped
@Deprecated(since = "0.3.0", forRemoval = true)
public class IntegrationResource {

    private Logger log = Logger.getLogger(IntegrationResource.class);


    @Inject
    public void setDeploymentService(
            final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @Inject
    public void setClusterService(
            final ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    @ConfigProperty(name = "crd.default")
    private String crdDefault;
    private DeploymentService deploymentService;
    private ClusterService clusterService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customResources")
    @Operation(summary = "Deprecated Get CRDs",
            description = "Returns a list of all potential associated custom "
                    + "resource definitions."
                    + " This is an idempotent operation.",
            deprecated = true)
    public List<Map<String, String>> customResourcesDefinition(
            final @RequestBody DeploymentResourceYamlRequest request) {
        return deploymentService.crd(request.getName(), request.getSteps());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/yaml")
    @Path("/customResource")
    @Operation(summary = "Deprecated Get CRD",
            description = "Returns the associated custom "
                    + "resource definition. This is an idempotent operation.",
            deprecated = true)
    public String customResourceDefinition(
            final @RequestBody DeploymentResourceYamlRequest request,
            final @Parameter(description = "Type of integration. "
                    + "Deprecated. For example: "
                    + "'Kamelet Binding'.", deprecated = true)
            @QueryParam("type") String type,
            final @Parameter(description = "DSL to use. For example: "
                    + "'Kamelet Binding'.")
            @QueryParam("dsl") String dsl) {
        String wanteddsl = type;
        if (dsl != null && !dsl.isBlank()) {
            wanteddsl = dsl;
        }

        List<Map<String, String>> crds = customResourcesDefinition(request);

        log.trace("Found " + crds.size() + " potential CRDs.");

        for (var crd : crds) {
            if (crd.get("dsl").equalsIgnoreCase(wanteddsl)) {
                log.trace("Returning dsl " + wanteddsl);
                return crd.get("crd");
            }
        }

        log.trace("Trying to return default dsl " + crdDefault);
        for (var crd : crds) {
            if (crd.get("dsl").equalsIgnoreCase(crdDefault)) {
                log.trace("Returning dsl " + crdDefault);
                return crd.get("crd");
            }
        }


        log.trace("Returning arbitrary one.");
        return crds.iterator().next().get("crd");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/yaml")
    @Path("/")
    @Operation(summary = "Deprecated Start integration",
            description = "Deploy and start the given integration"
                    + " on the cluster. Deployment will be done "
                    + "as a custom resource.",
            deprecated = true)
    public String start(
            final @RequestBody DeploymentResourceYamlRequest request,
            final @Parameter(description = "Type of integration. For example: "
                    + "'Kamelet Binding'.", deprecated = true)
            @QueryParam("type") String type,
            final @Parameter(description = "DSL of integration. For example: "
                    + "'Kamelet Binding'.")
            @QueryParam("dsl") String dsl,
            final @Parameter(description = "Namespace of the cluster where "
                    + "we want the integration to run.")
            @QueryParam("namespace") String namespace) {
        final var crd = customResourceDefinition(request, type, dsl);
        clusterService.start(crd, namespace);
        return crd;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    @Operation(summary = "Deprecated Get all Integrations",
            description = "Returns all the integrations on the cluster.",
            deprecated = true)
    public List<Integration> allIntegrations(
            final @Parameter(description = "Namespace of the cluster where "
                    + "the integrations are running.")
            @QueryParam("namespace") String namespace) {
        return clusterService.getIntegrations(namespace);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    @Operation(summary = "Deprecated Stop/Remove integration",
            description = "Remove the integration identified by name.",
            deprecated = true)
    public boolean integrations(
            final @Parameter(description = "Name of the integration to stop.")
            @PathParam("name") String name,
            final @Parameter(description = "Namespace of the cluster where "
                    + "the integration is running.")
            @QueryParam("namespace") String namespace) {
        return clusterService.stop(name, namespace);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}/logs")
    @Operation(summary = "Deprecated Get logs",
            description = "Get the integration's log.",
            deprecated = true)
    public String logs(
            final @Parameter(description = "Name of the integration "
                    + "of which logs should be retrieved.")
            @PathParam("name") String name,
            final @Parameter(description = "Namespace of the cluster "
                    + "where the integration is running.")
            @QueryParam("namespace") String namespace,
            final @Parameter(description = "Number of last N l"
                    + "ines to be retrieved.")
            @QueryParam("lines") int lines) {
        return clusterService.logs(namespace, name, lines);
    }

    @ServerExceptionMapper
    public Response mapException(final Exception x) {
        log.error("Error processing deployment.", x);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Error processing deployment: " + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
