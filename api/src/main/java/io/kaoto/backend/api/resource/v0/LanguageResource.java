package io.kaoto.backend.api.resource.v0;

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
import java.util.Collection;
import java.util.Map;

@Path("/languages")
@ApplicationScoped
@Deprecated(since = "0.3.0", forRemoval = true)
public class LanguageResource {

    private Logger log = Logger.getLogger(LanguageResource.class);

    private LanguageService languageService;

    @Inject
    public void setLanguageService(final LanguageService languageService) {
        this.languageService = languageService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    @Operation(summary = "Deprecated Get Languages",
            description = "Returns a list of all potential supported languages",
            deprecated = true)
    public Collection<Map<String, String>> getAll() {
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
