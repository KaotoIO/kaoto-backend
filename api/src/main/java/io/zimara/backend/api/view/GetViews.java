package io.zimara.backend.api.view;

import io.quarkus.arc.log.LoggerName;
import io.smallrye.mutiny.Multi;
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
import java.util.function.Supplier;
import java.util.stream.Stream;

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

    final Logger log = Logger.getLogger(GetViews.class);

    /*
     * 🐱method views:
     * 🐱param yaml: String
     *
     * Based on the YAML provided, offer a list of possible views
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<View> views(@QueryParam("yaml") String yaml) {
        return Multi.createFrom().items(extractViews());
    }

    private Stream<View> extractViews() {
        Stream.Builder<View> viewStream = Stream.builder();

        viewStream.add(new IntegrationView(null, "test view"));

        return viewStream.build();
    }

}