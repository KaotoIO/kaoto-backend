package io.kaoto.backend.api.resource.v1;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import io.kaoto.backend.api.resource.v1.model.Capabilities;
import io.kaoto.backend.api.service.language.LanguageService;
import io.kaoto.backend.deployment.ClusterService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

    @ConfigProperty(name = "quarkus.application.version")
    private String version;

    private static final Logger LOG = Logger.getLogger(CapabilitiesResource.class);

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
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/version")
    @Operation(summary = "Get Version",
            description = "Returns the running backend version.")
    public String showVersion() {
        return getVersion();
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
        LOG.error("Error getting capabilities.", x);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Error parsing capabilities.")
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
