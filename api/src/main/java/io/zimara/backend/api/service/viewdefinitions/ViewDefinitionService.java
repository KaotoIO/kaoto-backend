package io.zimara.backend.api.service.viewdefinitions;

import io.zimara.backend.api.service.parser.KameletBindingParserService;
import io.zimara.backend.api.service.parser.ParserService;
import io.zimara.backend.model.View;
import io.zimara.backend.model.view.IntegrationView;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ViewDefinitionService {

    private List<ParserService> parsers = new ArrayList<>();
    private Logger log = Logger.getLogger(ViewDefinitionService.class);

    public ViewDefinitionService() {
        //This should be autowired, perhaps?
        //jandex
        parsers.add(new KameletBindingParserService());
    }

    public List<View> views(@QueryParam("yaml") String yaml) {
        List<View> views = new ArrayList<>();
        for (var parser : parsers) {
            log.trace("Using " + parser.getIdentifier());
            if (parser.appliesTo(yaml)) {
                log.trace("Applying " + parser.getIdentifier());
                views.add(new IntegrationView(parser.parse(yaml), parser.getIdentifier()));
            }
        }
        return views;
    }
}
