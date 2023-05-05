package io.kaoto.backend.api.resource.v2;

import io.kaoto.backend.api.resource.v1.model.Integration;
import io.kaoto.backend.api.service.deployment.DeploymentService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.step.Step;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * 🐱class IntegrationsResource
 * 🐱relationship compositionOf DeploymentService, 0..1
 *
 * This endpoint will return the yaml needed to deploy
 * the related integration and the
 * endpoints to interact with deployments.
 */
@Path("/v2/integrations")
@ApplicationScoped
public class IntegrationsResource {

    private final Logger LOG = Logger.getLogger(IntegrationsResource.class);

    @Inject
    public void setDeploymentService(
            final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }
    private DeploymentService deploymentService;

    @Inject
    public void setStepParserServices(final Instance<StepParserService<Step>> stepParserServices) {
        this.stepParserServices = stepParserServices;
    }
    private Instance<StepParserService<Step>> stepParserServices;

    /*
     * 🐱method CRDs: Map
     * 🐱param dsl: String
     * 🐱param integration: List<Integration>
     *
     * Idempotent operation that given an array of integrations, returns the corresponding CRDs.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/yaml")
    @Path("/")
    @CacheResult(cacheName = "api")
    @Operation(summary = "Get CRDs",
            description = "Returns the associated custom resource definitions. This is an idempotent operation.")
    public String crds(
            final @RequestBody List<Integration> request,
            final @Parameter(description = "DSL to use. For example: 'Kamelet Binding'.")
            @QueryParam("dsl") String dsl) {
        return deploymentService.crds(request, dsl);
    }

    /*
     * 🐱method integration: Map
     * 🐱param dsl: String
     * 🐱param crd: String
     *
     * Idempotent operation that given a CRD, returns the JSON representation.
     *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("text/yaml")
    @Path("/")
    @CacheResult(cacheName = "api")
    @Operation(summary = "Get Integration Object",
            description = "Given the associated custom resource definition, returns the JSON object."
                    + " This is an idempotent operation.")
    public List<Integration> integration(
            final @RequestBody String crd,
            final @Parameter(description = "DSL to use. For example: "
                    + "'Kamelet Binding'.")
            @QueryParam("dsl") String dsl) {
        List<Integration> integrations = new ArrayList<>();

        boolean found = false;
        if (dsl != null) {
            for (StepParserService<Step> stepParserService : stepParserServices) {
                try {
                    if (stepParserService.identifier().equalsIgnoreCase(dsl) && stepParserService.appliesTo(crd)) {
                        var parsed = stepParserService.getParsedFlows(crd);
                        decorateIntegration(dsl, integrations, parsed);
                        found = true;
                        break;
                    }
                } catch (Exception e) {
                    LOG.warn("Parser " + stepParserService.getClass() + "threw an unexpected error.", e);
                }
            }
        }

        if (!found) {
            for (var stepParserService : stepParserServices) {
                try {
                    if (stepParserService.appliesTo(crd)) {
                        var parsed = stepParserService.getParsedFlows(crd);
                        decorateIntegration(stepParserService.identifier(), integrations, parsed);
                        LOG.warn("Gurl, the DSL you gave me is so wrong. This is a " + stepParserService.identifier()
                                + " not a " + dsl);
                        break;
                    }
                } catch (Exception e) {
                    LOG.trace("Parser " + stepParserService.getClass() + "threw an unexpected error.", e);
                }
            }
        }

        return integrations;
    }

    private void decorateIntegration(String dsl, List<Integration> integrations,
                                     List<StepParserService.ParseResult<Step>> parsed) {
        for (var result : parsed) {
            Integration integration = new Integration();
            integration.setSteps(result.getSteps());
            integration.setMetadata(result.getMetadata());
            integration.setParameters(result.getParameters());
            integration.setDsl(dsl);

            integrations.add(integration);
        }
    }

    @ServerExceptionMapper
    public Response mapException(final Exception x) {
        LOG.error("Error processing deployment.", x);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Error processing deployment: " + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
