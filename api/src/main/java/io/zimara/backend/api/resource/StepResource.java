package io.zimara.backend.api.resource;

import io.zimara.backend.api.metadata.Catalog;
import io.zimara.backend.model.Step;
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
 * This endpoint will return steps based on the parameters.
 */
@Path("/step")
@ApplicationScoped
public class StepResource {

    @Inject
    Catalog catalog;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/id/{id}")
    public Step stepById(@PathParam("id") String id) {
        return catalog.getReadOnlyCatalog().searchStepByID(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public Collection<Step> stepsByName(@PathParam("name") String name) {
        return catalog.getReadOnlyCatalog().searchStepsByName(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Step> allSteps() {
        return catalog.getReadOnlyCatalog().getAll();
    }


    @ServerExceptionMapper
    public Response mapException(Exception x) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error returning steps: " + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}