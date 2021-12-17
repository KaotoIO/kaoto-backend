package io.kaoto.backend.api.resource;

import io.kaoto.backend.api.resource.request.DeploymentResourceYamlRequest;
import io.kaoto.backend.api.service.deployment.DeploymentService;
import io.kaoto.backend.deployment.ClusterService;
import io.kaoto.backend.model.deployment.Integration;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * 🐱class DeploymentResource
 * 🐱relationship compositionOf DeploymentService, 0..1
 *
 * This endpoint will return the yaml needed to deploy
 * the related integration and the
 * endpoints to interact with deployments.
 */
@Path("/integrations")
@ApplicationScoped
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

    private DeploymentService deploymentService;
    private ClusterService clusterService;

    /*
     * 🐱method yaml: String
     * 🐱param steps: DeploymentResourceYamlRequest
     *
     * Idempotent operation that, based on the steps provided,
     * offer the source code / custom resource to deploy the integration.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/yaml")
    @Path("/customResource")
    public String customResourceDefinition(
            final @RequestBody DeploymentResourceYamlRequest request) {
        return deploymentService.yaml(request.getName(), request.getSteps());
    }

    /*
     * 🐱method start: String
     * 🐱param steps: DeploymentResourceYamlRequest
     *
     * Deploys an integration provided.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/yaml")
    @Path("/")
    public String start(
            final @RequestBody DeploymentResourceYamlRequest request) {
        String yaml = deploymentService.yaml(
                request.getName(), request.getSteps());
        if (clusterService.start(yaml)) {
            return yaml;
        }
        return "Error deploying " + request.getName();
    }

    /*
     * 🐱method integrations: String
     *
     * Returns the list of all integrations
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Integration> integrations() {
        log.info("Integration");
        return clusterService.getIntegrations();
    }


    /*
     * 🐱method stop: String
     *
     * Stops and deletes an integration by name
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public boolean integrations(final @PathParam("name") String name) {
        Integration i = new Integration();
        i.setName(name);
        return clusterService.stop(i);
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
