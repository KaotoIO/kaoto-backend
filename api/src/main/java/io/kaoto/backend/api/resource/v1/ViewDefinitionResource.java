package io.kaoto.backend.api.resource.v1;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import io.kaoto.backend.api.service.viewdefinition.ViewDefinitionService;
import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.view.ViewDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * 🐱class ViewDefinitionResource
 * 🐱relationship compositionOf ViewDefinitionService, 0..1
 *
 * This endpoint will return a list of views based on the parameters.
 *
 */
@Path("/v1/view-definitions")
@ApplicationScoped
@OpenAPIDefinition(
        info = @Info(
            title = "View Definition API",
            version = "1.0.0",
            description = "With this API, backend and frontend agree on what "
                    + "views and extensions can be used for the "
                    + "integration workflow.",
            contact = @Contact(
                    name = "Kaoto Team",
                    url = "https://kaoto.io"),
            license = @License(
                    name = "Apache 2.0",
                    url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class ViewDefinitionResource {

    private static final Logger LOG = Logger.getLogger(ViewDefinitionResource.class);

    @Inject
    public void setViewDefinitionService(
            final ViewDefinitionService viewDefinitionService) {
        this.viewDefinitionService = viewDefinitionService;
    }
    private ViewDefinitionService viewDefinitionService;

    /*
     * 🐱method viewsPerStepList:
     * 🐱param DeploymentResourceYamlRequest: Step[]
     *
     * Based on the list of steps provided, offer a list of possible view
     * definitions and step extensions.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get views based on steps",
            description = "Get view definitions for a specific resource."
                    + " This is an idempotent operation.")
    public List<ViewDefinition> viewsPerStepList(
            final @RequestBody List<Step> steps) {
        return viewDefinitionService.viewsPerStepList(steps);
    }


    @ServerExceptionMapper
    public Response mapException(final Exception x) {
        LOG.error("Error processing views definitions.", x);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error processing views definitions: "
                        + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}
