package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.api.service.viewdefinition.ViewDefinitionService;
import io.kaoto.backend.model.step.Step;
import io.kaoto.backend.model.view.ViewDefinition;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    private Logger log = Logger.getLogger(ViewDefinitionResource.class);

    private ViewDefinitionService viewDefinitionService;

    private Instance<StepParserService<Step>> stepParserServices;

    @Inject
    public void setViewDefinitionService(
            final ViewDefinitionService viewDefinitionService) {
        this.viewDefinitionService = viewDefinitionService;
    }

    @Inject
    public void setStepParserServices(
            final Instance<StepParserService<Step>> stepParserServices) {
        this.stepParserServices = stepParserServices;
    }

    /*
     * 🐱method views:
     * 🐱param yaml: String
     *
     * Based on the YAML provided, offer a list of possible view definitions
     * and step extensions.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("text/yaml")
    @Operation(summary = "Get views",
            description = "Get view definitions for a specific resource."
            + " This is an idempotent operation.")
    public List<ViewDefinition> views(
            final @RequestBody String crd) {

        log.trace("Extracting steps from crd");
        List<Step> steps = null;
        for (StepParserService<Step> stepParserService : stepParserServices) {
            try {
                log.trace("Trying with service: " + stepParserService);
                if (stepParserService.appliesTo(crd)) {
                    steps = stepParserService.parse(crd);
                    log.trace("Extracted " + steps.size() + " steps.");
                    if (!steps.isEmpty()) {
                        break;
                    }
                    log.warn("There was a step parser service that applies "
                            + "but couldn't extract steps. We will try to "
                            + "find another step parser service, but this "
                            + "should not happen unless the crd is empty.");
                }
            } catch (Exception e) {
                log.warn("Parser " + stepParserService.getClass() + "threw an"
                        + " unexpected error.", e);
            }
        }
        return viewsPerStepList(steps);
    }

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
        log.error("Error processing views definitions.", x);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error processing views definitions: "
                        + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}
