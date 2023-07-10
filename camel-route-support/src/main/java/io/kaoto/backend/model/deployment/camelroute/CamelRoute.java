package io.kaoto.backend.model.deployment.camelroute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.camelroute.CamelRouteDeserializer;
import io.kaoto.backend.model.deployment.kamelet.Bean;
import io.kaoto.backend.model.deployment.kamelet.Flow;
import io.kaoto.backend.model.deployment.kamelet.step.From;
import io.kaoto.backend.model.deployment.rest.HttpVerb;
import io.kaoto.backend.model.deployment.rest.Rest;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.logging.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 🐱class CamelRoute
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = CamelRouteDeserializer.class)
@RegisterForReflection
public class CamelRoute {

    protected static final Logger log = Logger.getLogger(CamelRoute.class);
    private List<Flow> flows;

    @JsonProperty("beans")
    private List<Bean> beans;

    public CamelRoute() {
        super();
    }

    public CamelRoute(final List<Step> steps, final Map<String, Object> metadata, final StepCatalog catalog) {
        Flow f = processFlows(steps, catalog);
        processBeans(metadata);

        //We have an empty flow, but don't show it empty
        if ((getBeans() == null || getBeans().isEmpty()) && f == null) {
            setFlows(new LinkedList<>());
            f = new Flow();
            f.setFrom(new From());
            f.getFrom().setSteps(new LinkedList<>());
            getFlows().add(f);
        }

        if (f != null && metadata.containsKey("name")) {
            f.setId(String.valueOf(metadata.get("name")));
        }
        if (f != null && metadata.containsKey("route-configuration-id")) {
            f.setRouteConfigurationId(String.valueOf(metadata.get("route-configuration-id")));
        }
        if (f != null && metadata.containsKey("description")) {
            f.setDescription(String.valueOf(metadata.get("description")));
        }
    }

    private Flow processFlows(final List<Step> steps, final StepCatalog catalog) {
        if (steps == null || steps.isEmpty()) {
            return null;
        }
        final var flow = new KamelPopulator(catalog).getFlow(steps);
        setFlows(new LinkedList<>());
        Flow f = null;
        if (flow instanceof Rest) {
            //These are contextual variables in case the user is not using branches
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
            f = new Flow();
            f.setFrom(flow);
            getFlows().add(f);
        }

        return f;
    }

    private void processBeans(final Map<String, Object> metadata) {
        if (metadata != null && metadata.containsKey("beans") && metadata.get("beans") instanceof List) {
            setBeans((List<Bean>) metadata.get("beans"));
        }
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(final List<Flow> flows) {
        this.flows = flows;
    }

    public List<Bean> getBeans() { return beans; }

    public void setBeans(List<Bean> beans) { this.beans = beans; }

}
