package io.kaoto.backend.camel.model.deployment.camelroute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kaoto.backend.camel.KamelHelper;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.camel.model.deployment.kamelet.Bean;
import io.kaoto.backend.camel.model.deployment.kamelet.Flow;
import io.kaoto.backend.camel.model.deployment.kamelet.step.From;
import io.kaoto.backend.camel.model.deployment.rest.HttpVerb;
import io.kaoto.backend.camel.model.deployment.rest.Rest;
import io.kaoto.backend.camel.service.step.parser.camelroute.CamelRouteDeserializer;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * 🐱class CamelRoute
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = CamelRouteDeserializer.class)
@JsonSerialize(using = CamelRouteSerializer.class)
@RegisterForReflection
public class CamelRoute {

    protected static final Logger log = Logger.getLogger(CamelRoute.class);
    private List<Flow> flows;

    @JsonProperty("beans")
    private List<Bean> beans;

    @JsonProperty("route-configuration")
    @JsonAlias("routeConfiguration")
    private Object routeConfiguration;

    @JsonProperty("rest-configuration")
    @JsonAlias("restConfiguration")
    private Object restConfiguration;

    public CamelRoute() {
        super();
    }

    public CamelRoute(final List<Step> steps, final Map<String, Object> metadata, final StepCatalog catalog) {
        Flow f = processFlows(steps, catalog);
        processConfigs(metadata);
        processBeans(metadata);

        //We have an empty flow, but don't show it empty
        if ((getBeans() == null || getBeans().isEmpty()) && f == null
                && getRestConfiguration() == null && getRouteConfiguration() == null) {
            setFlows(new ArrayList<>());
            f = new Flow();
            f.setFrom(new From());
            f.getFrom().setSteps(new ArrayList<>());
            getFlows().add(f);
        }

        if (f != null && metadata.containsKey(KamelHelper.NAME)) {
            f.setId(String.valueOf(metadata.get(KamelHelper.NAME)));
            if (f.getFrom() instanceof Rest rest) {
                rest.setId(String.valueOf(metadata.get(KamelHelper.NAME)));
            }
        }
        if (f != null && metadata.containsKey("route-configuration-id")) {
            f.setRouteConfigurationId(String.valueOf(metadata.get("route-configuration-id")));
        }
        if (f != null && metadata.containsKey(KamelHelper.DESCRIPTION)) {
            f.setDescription(String.valueOf(metadata.get(KamelHelper.DESCRIPTION)));
        }
    }

    private Flow processFlows(final List<Step> steps, final StepCatalog catalog) {
        if (steps == null || steps.isEmpty()) {
            return null;
        }
        final var flow = new KamelPopulator(catalog).getFlow(steps);
        setFlows(new ArrayList<>());
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

    private void processConfigs(final Map<String, Object> metadata) {
        if (metadata != null && metadata.containsKey("route-configuration")) {
            setRouteConfiguration(metadata.get("route-configuration"));
        }
        if (metadata != null && metadata.containsKey("rest-configuration")) {
            setRestConfiguration(metadata.get("rest-configuration"));
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

    public Object getRouteConfiguration() {
        return routeConfiguration;
    }

    public void setRouteConfiguration(Object routeConfiguration) {
        this.routeConfiguration = routeConfiguration;
    }

    public Object getRestConfiguration() {
        return restConfiguration;
    }

    public void setRestConfiguration(Object restConfiguration) {
        this.restConfiguration = restConfiguration;
    }
}
