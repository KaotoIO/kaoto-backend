package io.kaoto.backend.model.deployment.camelroute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.model.deployment.kamelet.Flow;
import io.kaoto.backend.model.deployment.rest.HttpVerb;
import io.kaoto.backend.model.deployment.rest.Rest;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * 🐱class CamelRoute
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CamelRoute {

    protected static final Logger log = Logger.getLogger(CamelRoute.class);
    private List<Flow> flows;

    public CamelRoute() {
        super();
    }

    public CamelRoute(final List<Step> steps, final StepCatalog catalog) {
        final var flow = new KamelPopulator(catalog).getFlow(steps);
        setFlows(new LinkedList<>());
        if (flow instanceof Rest) {
            //These are contextual variables in case the user is not using branches
            Flow f = null;
            List<HttpVerb> httpVerbs = null;
            HttpVerb httpVerb = null;
            for (Step step : steps) {
                if ("CAMEL-REST-DSL".equalsIgnoreCase(step.getKind())) {
                    f = new Flow();
                    f.setFrom(new Rest(step, catalog));
                    getFlows().add(f);
                } else if (f != null && "CAMEL-REST-VERB".equalsIgnoreCase(step.getKind())){
                    //Someone added it as a next step instead of a branch
                    //Don't worry, we get you
                    httpVerbs = ((Rest) f.getFrom()).stepToHttpVerb(step);
                } else if (f != null && httpVerbs != null && "CAMEL-REST-ENDPOINT".equalsIgnoreCase(step.getKind())){
                    //Someone added it as a next step instead of a branch
                    //Don't worry, we get you
                    httpVerb = ((Rest) f.getFrom()).addEndpointToHttpVerb(httpVerbs, null, step);
                }  else if (f != null && httpVerb != null && httpVerb.getTo() == null){
                    //Someone added it as a next step instead of a branch
                    //Don't worry, we get you
                    ((Rest) f.getFrom()).setToHttpVerb(step, httpVerb);
                } else {
                    //uh-uh, this is bad
                    log.error("Malformed REST, don't know what to do.");
                }
            }
        } else {
            var f = new Flow();
            f.setFrom(flow);
            getFlows().add(f);
        }
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(final List<Flow> flows) {
        this.flows = flows;
    }


}
