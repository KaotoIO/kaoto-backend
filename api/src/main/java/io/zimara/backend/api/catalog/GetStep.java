package io.zimara.backend.api.catalog;

import io.quarkus.arc.log.LoggerName;
import io.zimara.backend.api.Catalog;
import io.zimara.backend.model.Step;
import io.zimara.backend.model.View;
import io.zimara.backend.model.step.kamelet.KameletStep;
import io.zimara.backend.model.view.IntegrationView;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 🐱class GetView
 * This endpoint will return a list of steps based on the parameters.
 *
 */
@Path("/step")
public class GetStep {

    @LoggerName("GetStep")
    private Logger log = Logger.getLogger(GetStep.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/id/{id}")
    public Step stepById(@PathParam("id") String id) {
        return Catalog.getCatalog().searchStepByID(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public Collection<Step> stepsByName(@PathParam("name") String name) {
        return Catalog.getCatalog().searchStepsByName(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Step> allSteps() {
        return Catalog.getCatalog().getAll();
    }
}