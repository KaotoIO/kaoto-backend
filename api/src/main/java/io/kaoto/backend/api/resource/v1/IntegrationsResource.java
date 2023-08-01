package io.kaoto.backend.api.resource.v1;

import java.util.ArrayList;
import java.util.List;

import io.kaoto.backend.camel.KamelHelper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import io.kaoto.backend.api.resource.v1.model.Integration;
import io.kaoto.backend.api.service.deployment.DeploymentService;
import io.kaoto.backend.api.service.dsl.DSLSpecification;
import io.kaoto.backend.api.service.language.LanguageService;
import io.kaoto.backend.model.step.Step;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * 🐱class IntegrationsResource
 * 🐱relationship compositionOf DeploymentService, 0..1
 *
 * This endpoint will return the yaml needed to deploy
 * the related integration and the
 * endpoints to interact with deployments.
 */
@Path("/v1/integrations")
@ApplicationScoped
public class IntegrationsResource {

    private static final Logger LOG = Logger.getLogger(IntegrationsResource.class);
    private DeploymentService deploymentService;
    private Instance<DSLSpecification> dslSpecifications;
    private LanguageService languageService;

    @Inject
    public void setDeploymentService(
            final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @Inject
    public void setParsers(final Instance<DSLSpecification> services) {
        this.dslSpecifications = services;
    }

    @Inject
    public void setLanguageService(final LanguageService languageService) {
        this.languageService = languageService;
    }

    /*
     * 🐱method crd: Map
     * 🐱param dsl: String
     * 🐱param integration: Integration
     *
     * Idempotent operation that given given a JSON status, returns the CRD.
     *
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/yaml")
    @Path("/")
    @Operation(summary = "Get CRD",
            description = "Returns the associated custom resource definition. This is an idempotent operation.")
    public String cdr(
            final @RequestBody Integration request,
            final @Parameter(description = "DSL to use. For example: 'Kamelet Binding'.")
            @QueryParam("dsl") String dsl) {
        return deploymentService.crd(request, dsl);
    }


    /*
     * 🐱method integration: Map
     * 🐱param dsl: String
     * 🐱param crd: String
     *
     * Idempotent operation that given a CRD, returns the JSON status.
     *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("text/yaml")
    @Path("/")
    @Operation(summary = "Get Integration Object",
            description = "Given the associated custom resource definition, returns the JSON object."
                    + " This is an idempotent operation.")
    public Integration integration(
            final @RequestBody String crd,
            final @Parameter(description = "DSL to use. For example: "
                    + "'Kamelet Binding'.")
            @QueryParam("dsl") String dsl) {
        Integration integration = new Integration();

        boolean found = false;
        for (DSLSpecification stepParserService : dslSpecifications) {
            try {
                if (stepParserService.identifier().equalsIgnoreCase(dsl) && stepParserService.appliesTo(crd)) {
                    var parsed = stepParserService.getStepParserService().deepParse(crd);
                    integration.setSteps(parsed.getSteps());
                    integration.setMetadata(parsed.getMetadata());
                    integration.setParameters(parsed.getParameters());
                    integration.setDsl(dsl);
                    found = true;
                    break;
                }
            } catch (Exception e) {
                LOG.warn("Parser " + stepParserService.getClass() + "threw an unexpected error.", e);
            }
        }

        if (!found) {
            for (var stepParserService : dslSpecifications) {
                try {
                    if (stepParserService.appliesTo(crd)) {
                        var parsed = stepParserService.getStepParserService().deepParse(crd);
                        integration.setSteps(parsed.getSteps());
                        integration.setMetadata(parsed.getMetadata());
                        integration.setParameters(parsed.getParameters());
                        integration.setDsl(stepParserService.identifier());
                        LOG.warn("Gurl, the DSL you gave me is so wrong. This is a " + stepParserService.identifier()
                                + " not a " + dsl);
                        break;
                    }
                } catch (Exception e) {
                    LOG.warn("Parser " + stepParserService.getClass()
                            + "threw an unexpected error.", e);
                }
            }
        }

        return integration;
    }


    /*
     * 🐱method compatibleDSL: Map
     * 🐱param steps: List[Step]
     *
     * Idempotent operation that given list of steps, returns the list of
     * compatible languages.
     *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/dsls")
    @Operation(summary = "Get Compatible DSLs",
            description = "Given the list of steps, returns the list of"
                    + " potential DSL compatible with said list."
                    + " This is an idempotent operation.")
    public List<String> compatibleDSL(final @RequestBody List<Step> steps) {
        List<String> dsls = new ArrayList<>();

        for (DSLSpecification parser : dslSpecifications) {
            if (parser.appliesTo(steps)) {
                dsls.add(parser.identifier());
            }
        }

        if (dsls.isEmpty()) {
            for (var l : languageService.getAll()) {
                dsls.add(String.valueOf(l.get(KamelHelper.NAME)));
            }
        }

        return dsls;
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
