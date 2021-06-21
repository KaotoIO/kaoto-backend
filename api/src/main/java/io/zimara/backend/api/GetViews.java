package io.zimara.backend.api;

import io.zimara.backend.model.Step;
import io.zimara.backend.model.View;
import io.zimara.backend.model.step.KameletStep;
import io.zimara.backend.model.view.IntegrationView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * 🐱class GetView
 * This endpoint will return a list of views based on the parameters.
 *
 * 🐱example
 *
 * ```
 * YAML
 * ```
 */
@Path("/view")
public class GetViews {

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

        steps.add(new KameletStep("kamelet", "icon.png", null));

        return steps;
    }
}