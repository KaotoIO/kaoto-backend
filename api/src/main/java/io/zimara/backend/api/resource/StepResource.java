package io.zimara.backend.api.resource;

import io.zimara.backend.api.service.step.StepService;
import io.zimara.backend.model.step.Step;
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
 * 🐱relationship compositionOf StepService, 0..1
 *
 * This endpoint will return steps based on the parameters.
 */
@Path("/step")
@ApplicationScoped
public class StepResource {

    @Inject
    StepService stepService;


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
    public Step stepById(@PathParam("id") String id) {
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
    public Collection<Step> stepsByName(@PathParam("name") String name) {
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
    public Collection<Step> allSteps() {
        return stepService.allSteps();
    }


    @ServerExceptionMapper
    public Response mapException(Exception x) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error returning steps: " + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}