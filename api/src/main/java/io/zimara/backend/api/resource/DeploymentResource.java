package io.zimara.backend.api.resource;

import io.zimara.backend.api.resource.request.DeploymentResourceYamlRequest;
import io.zimara.backend.api.service.deployment.DeploymentService;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 🐱class DeploymentResource
 * 🐱relationship compositionOf DeploymentService, 0..1
 * <p>
 * This endpoint will return the yaml needed to deploy
 * the related integration and the
 * endpoints to interact with deployments.
 */
@Path("/deployment")
@ApplicationScoped
public class DeploymentResource {

    private Logger log = Logger.getLogger(DeploymentResource.class);

    @Inject
    public void setDeploymentService(
            final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    private DeploymentService deploymentService;

    /*
     * 🐱method yaml: String
     * 🐱param steps: DeploymentResourceYamlRequest
     *
     * Based on the steps provided, offer a yaml deployment
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/yaml")
    @Path("/yaml")
    public String yaml(final DeploymentResourceYamlRequest request) {
        return deploymentService.yaml(request.getName(), request.getSteps());
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
