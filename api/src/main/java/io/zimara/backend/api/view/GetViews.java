package io.zimara.backend.api.view;

import io.quarkus.arc.log.LoggerName;
import io.zimara.backend.api.Catalog;
import io.zimara.backend.model.Step;
import io.zimara.backend.model.View;
import io.zimara.backend.model.view.IntegrationView;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 🐱class GetView
 * This endpoint will return a list of views based on the parameters.
 * <p>
 * 🐱example
 * <p>
 * ```
 * YAML
 * ```
 */
@Path("/view")
@ApplicationScoped
public class GetViews {

    @LoggerName("GetViews")
    private final Logger log = Logger.getLogger(GetViews.class);

    /*
     * 🐱method views:
     * 🐱param yaml: String
     *
     * Based on the YAML provided, offer a list of possible views
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<View> views(@QueryParam("yaml") String yaml) {
        List<View> views = new ArrayList<>();

        List<Step> steps = extractSteps();
        var integrationView = new IntegrationView(steps, "test view");
        views.add(integrationView);

        return views;
    }

    private List<Step> extractSteps() {
        List<Step> steps = new ArrayList<>();
        Iterator<Step> it = Catalog.getReadOnlyCatalog().getAll().iterator();

        Step s = it.next();
        while (it.hasNext() && s.getType() != "source") {
            it.next();
        }
        steps.add(s);
        while (it.hasNext() && s.getType() == "source") {
            it.next();
        }
        steps.add(s);

        return steps;
    }
}