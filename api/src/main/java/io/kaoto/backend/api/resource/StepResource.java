package io.kaoto.backend.api.resource;

import io.kaoto.backend.api.service.deployment.DeploymentService;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.step.StepService;
import io.kaoto.backend.model.step.Step;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

/**
 * 🐱class StepResource 🐱relationship dependsOn StepCatalog
 *
 * This endpoint will return steps based on the parameters.
 */
@Path("/step")
@ApplicationScoped
@OpenAPIDefinition(
        info = @Info(
                title = "Steps API",
                version = "1.0.0",
                description = "The backend parses the steps provided "
                        + "and returns both a valid integration and "
                        + "the source code associated.",
                contact = @Contact(
                        name = "Kaoto Team",
                        url = "https://kaoto.io"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                ))
)
public class StepResource {

    private StepService stepService;
    private DeploymentService deploymentService;

    @Inject
    public void setStepService(final StepService stepService) {
        this.stepService = stepService;
    }

    @Inject
    public void setDeploymentService(
            final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }


    /*
     * 🐱method stepById : Step
     * 🐱param id: String
     *
     *  Returns the first step identified by the parameter.
     *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/id/{id}")
    @Operation(summary = "Get step by ID",
            description = "Returns all the details of a specific step "
                    + "based on the identifier.")
    public Step stepById(
            final @Parameter(
                    description = "Identifier of the step we want to retrieve.")
            @PathParam("id") String id) {
        return stepService.stepById(id);
    }

    /*
     * 🐱method stepsByName : List[Step]
     * 🐱param name: String
     *
     *  Returns all the steps identified by the name.
     *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    @Operation(summary = "Get step by name",
            description = "Returns all the details of steps based on the name. "
                    + "There may be more than one step with the same name, "
                    + "although configuration of catalogs should try to avoid"
                    + " duplications.")
    public Collection<Step> stepsByName(
            final @Parameter(description = "Name of the steps we want to "
                    + "retrieve.")
            @PathParam("name") String name) {
        return stepService.stepsByName(name);
    }

    /*
     * 🐱method stepsByKind : List[Step]
     * 🐱param type: String
     *
     *  Returns all the steps of the type.
     *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/kind/{kind}")
    @Operation(summary = "Get step by kind",
            description = "Returns all the details of steps based on the kind"
                    + ". ")
    public Collection<Step> stepsByKind(
            final @Parameter(description = "Kind of the steps we want to "
                    + "retrieve.")
            @PathParam("kind") String kind) {
        return stepService.allSteps().stream().parallel()
                .filter(step -> kind.equalsIgnoreCase(step.getKind())).toList();
    }

    /*
     * 🐱method stepsByIntegrationType : List[Step]
     * 🐱param type: String
     *
     *  Returns all the steps that fit a specific integration type.
     *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/integrationType/{integrationType}")
    @Operation(summary = "Get step by integration type",
            description = "Returns all the steps that fit a specific "
                    + "integration type. ")
    public Collection<Step> stepsByIntegrationType(
            final @Parameter(description = "Integration type we want to use.")
            @PathParam("integrationType") String integrationType) {
        List<String> kind = deploymentService.getParsers().stream().parallel()
                .filter(s -> integrationType.equalsIgnoreCase(s.identifier()))
                .map(DeploymentGeneratorService::getKinds)
                .flatMap(Collection::stream)
                .toList();

        return stepService.allSteps().stream().parallel()
                .filter(step -> kind.stream()
                        .anyMatch(s -> s.equalsIgnoreCase(step.getKind())))
                .toList();
    }

    /*
     * 🐱method allSteps : List[Step]
     * 🐱param type: String
     * 🐱param integrationType: String
     * 🐱param kind: String
     *
     * Returns all the steps. If parameters are included in the query, it
     * will filter only those steps compatible with the constraints.
     *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all steps",
            description = "Returns all the available steps that can be added"
                    + " to the integration.")
    public Collection<Step> allSteps(
            final @Parameter(description = "Filter by step type. Example: "
                    + "'START'")
            @QueryParam("type") String type,
            final @Parameter(description = "Filter by DSL. Example: "
                    + "'KameletBinding'")
            @QueryParam("integrationType") String integrationType,
            final @Parameter(description = "Filter by kind of step. Example: "
                    + "'Kamelet'")
            @QueryParam("kind") String kind) {
        var steps = stepService.allSteps().stream().parallel();
        if (type != null && !type.isEmpty()) {
            steps = steps.filter(step -> type.equalsIgnoreCase(step.getType()));
        }
        if (integrationType != null && !integrationType.isEmpty()) {
            List<String> kinds =
                    deploymentService.getParsers().stream().parallel()
                    .filter(s ->
                            integrationType.equalsIgnoreCase(s.identifier()))
                    .map(DeploymentGeneratorService::getKinds)
                    .flatMap(Collection::stream)
                    .toList();
             steps = steps.filter(step -> kinds.stream()
                    .anyMatch(s -> s.equalsIgnoreCase(step.getKind())));
        }
        if (kind != null && !kind.isEmpty()) {
            steps = steps.filter(step -> kind.equalsIgnoreCase(step.getKind()));
        }
        return steps.toList();
    }


    @ServerExceptionMapper
    public Response mapException(final Exception x) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error returning steps: " + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}
