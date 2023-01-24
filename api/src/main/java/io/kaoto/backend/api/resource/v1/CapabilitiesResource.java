package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.resource.v1.model.Capabilities;
import io.kaoto.backend.api.service.language.LanguageService;
import io.kaoto.backend.deployment.ClusterService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 🐱class CapabilitiesResource
 *
 * 🐱relationship compositionOf LanguageService, 0..1
 *
 * This endpoint will return information about what are the capabilities of
 * the backend API of Kaoto.
 *
 * For example, the supported DSLs.
 */
@Path("/v1/capabilities")
@ApplicationScoped
public class CapabilitiesResource {

    private ClusterService clusterService;

    private Logger log = Logger.getLogger(CapabilitiesResource.class);

    private LanguageService languageService;

    @Inject
    public void setLanguageService(final LanguageService languageService) {
        this.languageService = languageService;
    }

    @Inject
    public void setClusterService(
            final ClusterService clusterService) {
        this.clusterService = clusterService;
    }
    /*
     * 🐱method getAll: Capabilities
     *
     * Returns information of all the supported capabilities.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    @Operation(summary = "Get Languages",
            description = "Returns a list of all potential supported "
                    + "languages.")
    public Capabilities getAll() {
        Capabilities capabilities = new Capabilities();
        capabilities.setDsls(languageService.getAll());
        return capabilities;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/namespace")
    @Operation(summary = "Get Namespace",
    description = "Returns namespace where Kaoto backend is currently running")
    public String getNamespace() {
        return String.format("{\"namespace\": \"%s\"}",
                clusterService.getDefaultNamespace());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{dsl}/schema/")
    @Operation(summary = "Get validation schema for particular dsl",
            description = "Returns a validation schema of specified DSL if exists. If not empty string is returned.")
    public String getValidationSchema(
            @Parameter (description = "Target DSL for the validation schema") @PathParam("dsl") String dsl) {
        return languageService.getValidationSchema(dsl);
    }


    @ServerExceptionMapper
    public Response mapException(final Exception x) {
        log.error("Error getting capabilities.", x);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Error parsing capabilities.")
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
