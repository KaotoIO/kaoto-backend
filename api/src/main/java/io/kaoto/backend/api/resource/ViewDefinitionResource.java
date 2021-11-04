package io.kaoto.backend.api.resource;

import io.kaoto.backend.api.resource.response.ViewDefinitionResourceResponse;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.api.service.viewdefinition.ViewDefinitionService;
import io.kaoto.backend.model.step.Step;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.yaml.snakeyaml.scanner.ScannerException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 🐱class ViewDefinitionResource
 * 🐱relationship compositionOf ViewDefinitionService, 0..1
 *
 * This endpoint will return a list of views based on the parameters.
 *
 */
@Path("/viewdefinition")
@ApplicationScoped
public class ViewDefinitionResource {

    private Logger log = Logger.getLogger(ViewDefinitionResource.class);

    private ViewDefinitionService viewDefinitionService;

    private StepParserService<Step> stepParserService;

    @Inject
    public void setViewDefinitionService(
            final ViewDefinitionService viewDefinitionService) {
        this.viewDefinitionService = viewDefinitionService;
    }

    @Inject
    public void setStepParserService(
            final StepParserService<Step> stepParserService) {
        this.stepParserService = stepParserService;
    }
    /*
     * 🐱method views:
     * 🐱param yaml: String
     *
     * Based on the YAML provided, offer a list of possible views
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("text/yaml")
    public ViewDefinitionResourceResponse views(
            final @RequestBody String yaml) {
        ViewDefinitionResourceResponse res =
                new ViewDefinitionResourceResponse();
        res.setSteps(stepParserService.parse(yaml));
        res.setViews(viewDefinitionService.views(yaml, res.getSteps()));
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

    @ServerExceptionMapper
    public Response mapException(final ScannerException x) {
        log.error("Error trying to return YAML.", x);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Couldn't parse the YAML provided: "
                        + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
