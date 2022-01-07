package io.kaoto.backend.api.resource;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * 🐱class StepResource
 * 🐱relationship dependsOn StepCatalog
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
                    url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class StepResource {

    private StepService stepService;

    @Inject
    public void setStepService(final StepService stepService) {
        this.stepService = stepService;
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
            final  @Parameter(description = "Name of the steps we want to "
                    + "retrieve.")
            @PathParam("name") String name) {
        return stepService.stepsByName(name);
    }

    /*
     * 🐱method allSteps : List[Step]
     *
     *  Returns all the steps.
     *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all steps",
            description = "Returns all the available steps that can be added"
                    + " to the integration.")
    public Collection<Step> allSteps() {
        return stepService.allSteps();
    }


    @ServerExceptionMapper
    public Response mapException(final Exception x) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error returning steps: " + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}
