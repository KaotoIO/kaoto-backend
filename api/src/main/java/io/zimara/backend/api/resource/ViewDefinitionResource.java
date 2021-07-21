package io.zimara.backend.api.resource;

import io.zimara.backend.api.service.viewdefinitions.ViewDefinitionService;
import io.zimara.backend.model.View;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * 🐱class ViewDefinitionResource
 * This endpoint will return a list of views based on the parameters.
 * <p>
 * 🐱example
 * <p>
 * ```
 * YAML
 * ```
 */
@Path("/viewdefinition")
@ApplicationScoped
public class ViewDefinitionResource {

    private Logger log = Logger.getLogger(ViewDefinitionResource.class);

    @Inject
    ViewDefinitionService viewDefinitionService;

    /*
     * 🐱method views:
     * 🐱param yaml: String
     *
     * Based on the YAML provided, offer a list of possible views
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("text/yaml")
    public List<View> views(@QueryParam("yaml") String yaml) {
        return viewDefinitionService.views(yaml);
    }


    @ServerExceptionMapper
    public Response mapException(Exception x) {
        log.error("Error processing views definitions.", x);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error processing views definitions." + x.getClass())
                .build();
    }

    @ServerExceptionMapper
    public Response mapException(org.yaml.snakeyaml.scanner.ScannerException x) {
        log.error("Error trying to return YAML.", x);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Couldn't parse the YAML provided." + x.getClass())
                .build();
    }

}