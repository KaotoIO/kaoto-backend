package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.resource.v1.model.Capabilities;
import io.kaoto.backend.api.service.language.LanguageService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 🐱class LanguageResource 🐱relationship compositionOf LanguageService, 0..1
 *
 * This endpoint will return information about the supported DSLs.
 */
@Path("/v1/capabilities")
@ApplicationScoped
public class CapabilitiesResource {

    private Logger log = Logger.getLogger(CapabilitiesResource.class);

    private LanguageService languageService;

    @Inject
    public void setLanguageService(final LanguageService languageService) {
        this.languageService = languageService;
    }

    /*
     * 🐱method getAll: Map
     *
     * Returns information of all the supported DSLs.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    @Operation(summary = "Get Languages",
            description = "Returns a list of all potential supported "
                    + "languages.")
    public Capabilities getAll() {
        Capabilities capabilities = new Capabilities();
        capabilities.setDSLs(languageService.getAll());
        return capabilities;
    }

    @ServerExceptionMapper
    public Response mapException(final Exception x) {
        log.error("Error getting capabilities.");

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Error parsing capabilities.")
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
