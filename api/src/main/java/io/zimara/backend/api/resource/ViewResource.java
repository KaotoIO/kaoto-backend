package io.zimara.backend.api.resource;

import io.zimara.backend.api.service.KameletBindingParserService;
import io.zimara.backend.api.service.ParserService;
import io.zimara.backend.model.View;
import io.zimara.backend.model.view.IntegrationView;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * 🐱class ViewResource
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
public class ViewResource {

    private List<ParserService> parsers = new ArrayList<>();

    public ViewResource() {
        //This should be autowired, perhaps?
        //jandex
        parsers.add(new KameletBindingParserService());
    }

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
        for (var parser : parsers) {
            if (parser.appliesTo(yaml)) {
                views.add(new IntegrationView(parser.parse(yaml), parser.getIdentifier()));
            }
        }
        return views;
    }
}