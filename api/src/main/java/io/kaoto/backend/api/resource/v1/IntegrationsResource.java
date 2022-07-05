package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.api.resource.v1.model.Integration;
import io.kaoto.backend.api.service.deployment.DeploymentService;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.language.LanguageService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.step.Step;
import org.eclipse.microprofile.openapi.annotations.Operation;
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
@Path("/v1/integrations")
@ApplicationScoped
public class IntegrationsResource {

    private Logger log = Logger.getLogger(IntegrationsResource.class);

    @Inject
    public void setDeploymentService(
            final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }
    private DeploymentService deploymentService;

    @Inject
    public void setStepParserServices(
            final Instance<StepParserService<Step>> stepParserServices) {
        this.stepParserServices = stepParserServices;
    }
    private Instance<StepParserService<Step>> stepParserServices;

    @Inject
    public void setParsers(
            final Instance<DeploymentGeneratorService> services) {
        this.deploymentGeneratorServices = services;
    }
    private Instance<DeploymentGeneratorService> deploymentGeneratorServices;

    private LanguageService languageService;

    @Inject
    public void setLanguageService(final LanguageService languageService) {
        this.languageService = languageService;
    }
    /*
     * 🐱method crd: Map<String, Map<String>>
     * 🐱param dsl: String
     *
     * Idempotent operation that given a CRD or given a JSON status, returns
     * the other one.
     *
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/yaml")
    @Path("/")
    @Operation(summary = "Get CRD",
            description = "Returns the associated custom "
                    + "resource definition."
                    + " This is an idempotent operation.")
    public String cdr(
            final @RequestBody Integration request,
            final @Parameter(description = "DSL to use. For example: "
                    + "'Kamelet Binding'.")
            @QueryParam("dsl") String dsl) {
        return deploymentService.crd(request, dsl);
    }



    /*
     * 🐱method crd: Map<String, Map<String>>
     * 🐱param dsl: String
     *
     * Idempotent operation that given a CRD or given a JSON status, returns
     * the other one.
     *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("text/yaml")
    @Path("/")
    @Operation(summary = "Get Integration Object",
            description = "Given the associated custom "
                    + "resource definition, returns the JSON object."
                    + " This is an idempotent operation.")
    public Integration integration(
            final @RequestBody String crd,
            final @Parameter(description = "DSL to use. For example: "
                    + "'Kamelet Binding'.")
            @QueryParam("dsl") String dsl) {
        Integration integration = new Integration();

        for (StepParserService<Step> stepParserService : stepParserServices) {
            try {
                if (stepParserService.identifier().equalsIgnoreCase(dsl)
                        && stepParserService.appliesTo(crd)) {
                    var parsed = stepParserService.deepParse(crd);
                    integration.setSteps(parsed.getSteps());
                    integration.setMetadata(parsed.getMetadata());
                    integration.setParameters(parsed.getParameters());
                    break;
                }
            } catch (Exception e) {
                log.warn("Parser " + stepParserService.getClass() + "threw an"
                        + " unexpected error.", e);
            }
        }

        return integration;
    }



    /*
     * 🐱method crd: Map<String, Map<String>>
     * 🐱param dsl: String
     *
     * Idempotent operation that given a CRD or given a JSON status, returns
     * the other one.
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

        for (DeploymentGeneratorService parser : deploymentGeneratorServices) {
           if (parser.appliesTo(steps)) {
               dsls.add(parser.identifier());
           }
        }

        if (dsls.isEmpty()) {
            for (var l : languageService.getAll()) {
                dsls.add(l.get("name"));
            }
        }

        return dsls;
    }

    @ServerExceptionMapper
    public Response mapException(final Exception x) {
        log.error("Error processing deployment.", x);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Error processing deployment: " + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
