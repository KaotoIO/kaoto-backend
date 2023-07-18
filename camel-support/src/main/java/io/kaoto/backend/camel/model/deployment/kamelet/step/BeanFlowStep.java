package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.model.deployment.kamelet.FlowStep;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BeanFlowStep implements FlowStep {
    public static final String BEAN_LABEL = "bean";
    @Serial
    private static final long serialVersionUID = 5848053149715309142L;
    @JsonProperty(BEAN_LABEL)
    private BeanStep bean;

    public BeanFlowStep() {
        //Needed for serialization
    }

    @JsonCreator
    public BeanFlowStep(final @JsonProperty(value = BEAN_LABEL, required = true) Object bean) {
        super();
        if (bean instanceof BeanStep beanStep) {
            this.setBean(beanStep);
        } else if (bean instanceof Map map) {
            this.setBean(new BeanStep(map));
        } else if (bean != null) {
            this.setBean(new BeanStep());
            this.getBean().setBeanType(String.valueOf(bean));
        }
    }

    public BeanFlowStep(Step step) {
        setBean(new BeanStep(step));
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> res = new HashMap<>();
        final var representerProperties = getBean().getRepresenterProperties();
        if (representerProperties.size() == 1
                && (representerProperties.containsKey(BeanStep.REF_LABEL)
                || representerProperties.containsKey(BeanStep.BEAN_TYPE_LABEL)
                || representerProperties.containsKey(BeanStep.BEAN_TYPE_LABEL2))) {
            res.put(BEAN_LABEL, representerProperties.entrySet().iterator().next().getValue());
        } else {
            res.put(BEAN_LABEL, representerProperties);
        }
        return res;
    }

    @Override
    public Step getStep(final StepCatalog catalog, final KameletStepParserService kameletStepParserService,
                        final Boolean start, final Boolean end) {
        return getBean().getStep(catalog, BEAN_LABEL, kameletStepParserService);
    }

    public BeanStep getBean() {
        return bean;
    }

    public void setBean(BeanStep bean) {
        this.bean = bean;
    }
}
