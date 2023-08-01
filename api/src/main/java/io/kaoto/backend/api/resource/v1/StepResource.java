package io.kaoto.backend.api.resource.v1;

import com.fasterxml.jackson.annotation.JsonView;
import io.kaoto.backend.api.service.deployment.DeploymentService;
import io.kaoto.backend.api.service.dsl.DSLSpecification;
import io.kaoto.backend.api.service.step.StepService;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.model.Metadata;
import io.kaoto.backend.model.jsonviews.Views;
import io.kaoto.backend.model.step.Step;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.trace.Span;
import io.quarkus.vertx.http.Compressed;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * 🐱class StepResource
 * 🐱relationship dependsOn StepCatalog
 * <p>
 * This endpoint will return steps based on the parameters.
 */
@Path("/v1/steps")
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
    private MeterRegistry registry;

    @Inject
    public void setStepService(final StepService stepService) {
        this.stepService = stepService;
    }

    @Inject
    public void setDeploymentService(
            final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @Inject
    public void setRegistry(final MeterRegistry registry) {
        this.registry = registry;
    }

    /*
     * 🐱method allSteps : List[Step]
     * 🐱param type: String
     * 🐱param dsl: String
     * 🐱param kind: String
     *
     * Returns all the steps. If parameters are included in the query, it
     * will filter only those steps compatible with the constraints.
     *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Summary.class)
    @Operation(summary = "Get all steps",
            description = "Returns all the available steps that can be added"
                    + " to the integration.")
    @Compressed
    public Collection<Step> all(
            final @Parameter(description = "Filter by Domain Specific Language (DSL). "
                    + "Examples: 'KameletBinding' 'KameletBinding,Kamelet'")
            @QueryParam("dsl") String dsl,
            final @Parameter(description = "Filter by step type. Example: 'START' 'MIDDLE,END")
            @QueryParam("type") String type,
            final @Parameter(description = "Filter by kind of step. Examples: 'Kamelet' 'Kamelet,KameletBinding'")
            @QueryParam("kind") String kind,
            final @Parameter(description = "Maximum number of elements to return.")
            @QueryParam("limit") Long limit,
            final @Parameter(description = "Start returning from the nth element (combine with limit).")
            @QueryParam("start") Long start,
            final @Parameter(description = "Provides context: previous step, if exists.")
            @QueryParam("previousStep") String previousStep,
            final @Parameter(description = "Provides context: following step, if exists.")
            @QueryParam("followingStep") String followingStep) {
        final var allSteps = stepService.allSteps();
        var steps = allSteps.stream().sorted(Comparator.comparing(Metadata::getId));
        Span span = Span.current();
        if (span != null) {
            span.setAttribute("steps.total", allSteps.size());
            span.setAttribute("steps.dsl", dsl);
            span.setAttribute("steps.type", type);
            span.setAttribute("steps.kind", kind);
            span.setAttribute("steps.limit", limit != null ? limit.toString() : "null");
            span.setAttribute("steps.start", start != null ? start.toString() : "null");
            registry.gauge(KamelHelper.STEPS, allSteps.size());
        }

        if (start != null && start > 0) {
            steps = steps.skip(start);
        }

        if (limit != null && limit > 0) {
            steps = steps.limit(limit);
        }

        steps = steps.parallel();

        //DSL first because it is usually the parameter we will use
        if (dsl != null && !dsl.isEmpty()) {
            final var dgsStream = deploymentService.getParsers().stream().parallel()
                    .filter(s ->
                            Arrays.stream(dsl.split(",")).anyMatch(it -> it.equalsIgnoreCase(s.identifier())))
                    .toList();

            //First take all the kinds from the parameter list given
            List<String> kinds =
                    dgsStream.parallelStream().map(DSLSpecification::getKinds).flatMap(Collection::stream).toList();
            //And now we can filter by the kinds
            steps = steps.filter(step -> kinds.stream().anyMatch(s -> s.equalsIgnoreCase(step.getKind())));

            //And give context based on previous and following step, depending on the DSL
            for (var dgs : dgsStream) {
                if (dgs.getDeploymentGeneratorService() != null) {
                    steps = dgs.getDeploymentGeneratorService().filterCatalog(stepService.stepById(previousStep),
                            stepService.stepById(followingStep), steps);
                }
            }
        }

        //This may remove one third of the options
        if (type != null && !type.isEmpty()) {
            steps = steps.filter(step -> Arrays.stream(type.split(","))
                    .anyMatch(t -> t.equalsIgnoreCase(step.getType())));
        }

        //kind is the less filtering parameter
        if (kind != null && !kind.isEmpty()) {
            steps = steps.filter(step -> Arrays.stream(kind.split(","))
                    .anyMatch(k -> k.equalsIgnoreCase(step.getKind())));
        }

        final var result = steps.toList();
        if (span != null) {
            span.setAttribute("steps.return", result.size());
        }
        return result;
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
    @JsonView(Views.Complete.class)
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
    @JsonView(Views.Complete.class)
    @Path("/name/{name}")
    @Operation(summary = "Get step by name",
            description = "Returns all the details of steps based on the name. "
                    + "There may be more than one step with the same name, "
                    + "although configuration of catalogs should try to avoid"
                    + " duplications.")
    public Collection<Step> stepsByName(
            final @Parameter(description = "Name of the steps we want to "
                    + "retrieve.")
            @PathParam(KamelHelper.NAME) String name) {
        return stepService.stepsByName(name);
    }

    @ServerExceptionMapper
    public Response mapException(final Exception x) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error returning steps: " + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}
