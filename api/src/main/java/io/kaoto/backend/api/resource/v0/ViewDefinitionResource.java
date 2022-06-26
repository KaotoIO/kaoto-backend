package io.kaoto.backend.api.resource.v0;

import io.kaoto.backend.api.resource.v0.response.ViewDefinitionResourceResponse;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.api.service.viewdefinition.ViewDefinitionService;
import io.kaoto.backend.model.step.Step;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/viewdefinition")
@ApplicationScoped
@OpenAPIDefinition(
        info = @Info(
            title = "Deprecated View Definition API",
            version = "0.0.0",
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
 @Deprecated(since = "0.3.0", forRemoval = true)
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("text/yaml")
    @Operation(summary = "Deprecated Deprecated Get views",
            description = "Get view definitions for a specific integration."
            + " This is an idempotent operation.",
            deprecated = true)
    public ViewDefinitionResourceResponse views(
            final @RequestBody String crd,
            final @Parameter(description = "Name of the integration")
            @QueryParam("name") String name) {
        ViewDefinitionResourceResponse res =
                new ViewDefinitionResourceResponse();
        log.trace("Extracting steps from crd");
        for (StepParserService<Step> stepParserService : stepParserServices) {
            try {
                log.trace("Trying with service: " + stepParserService);
                if (stepParserService.appliesTo(crd)) {
                    res.setSteps(stepParserService.deepParse(crd).getSteps());
                    log.trace("Extracted " + res.getSteps().size() + " steps.");
                    if (!res.getSteps().isEmpty()) {
                        break;
                    }
                }
            } catch (Exception e) {
                log.warn("Parser " + stepParserService.getClass() + "threw an"
                        + " unexpected error.", e);
            }
        }
        res.setViews(viewDefinitionService.viewsPerStepList(res.getSteps()));
        return res;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Deprecated Get views based on steps",
            description = "Get view definitions for a specific integration."
                    + " This is an idempotent operation.",
            deprecated = true)
    public ViewDefinitionResourceResponse viewsPerStepList(
            final @RequestBody List<Step> steps,
            final @Parameter(description = "Name of the integration")
            @QueryParam("name") String name) {
        ViewDefinitionResourceResponse res =
                new ViewDefinitionResourceResponse();
        res.setSteps(steps);
        res.setViews(viewDefinitionService.viewsPerStepList(steps));
        return res;
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
