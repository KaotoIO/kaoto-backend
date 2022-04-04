package io.kaoto.backend.api.resource;

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
import java.util.Map;

/**
 * 🐱class LanguageResource 🐱relationship compositionOf LanguageService, 0..1
 *
 * This endpoint will return information about the supported DSLs.
 */
@Path("/languages")
@ApplicationScoped
public class LanguageResource {

    private Logger log = Logger.getLogger(LanguageResource.class);

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
    public Map<String, Map<String, String>> getAll() {
        return languageService.getAll();
    }

    @ServerExceptionMapper
    public Response mapException(final Exception x) {
        log.error("Error getting supported languages.", x);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Error parsing supported languages.")
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
