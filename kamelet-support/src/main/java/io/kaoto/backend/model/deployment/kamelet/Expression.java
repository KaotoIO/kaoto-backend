package io.kaoto.backend.model.deployment.kamelet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Step;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"constant", "simple"})
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Expression implements FlowStep {
    @Serial
    private static final long serialVersionUID = 7630089193555236497L;
    public static final String CONSTANT_LABEL = KameletRepresenter.CONSTANT;
    public static final String SIMPLE_LABEL = KameletRepresenter.SIMPLE;
    public static final String NAME_LABEL = KameletRepresenter.NAME;

    @JsonCreator
    public Expression(
            final @JsonProperty(value = CONSTANT_LABEL) String constant,
            final @JsonProperty(value = SIMPLE_LABEL) String simple) {
        super();
        setConstant(constant);
        setSimple(simple);
    }

    public Expression(Object obj) {
        super();

        if (obj instanceof Expression e) {
            setConstant(e.getConstant());
            setSimple(e.getSimple());
        } else if (obj instanceof Map map) {
            if (map.containsKey(CONSTANT_LABEL) && map.get(CONSTANT_LABEL) != null) {
                setConstant(map.get(CONSTANT_LABEL).toString());
            }
            if (map.containsKey(SIMPLE_LABEL) && map.get(SIMPLE_LABEL) != null) {
                setSimple(map.get(SIMPLE_LABEL).toString());
            }
        }
    }

    public Expression() {

    }

    @JsonProperty(CONSTANT_LABEL)
    private String constant;

    @JsonProperty(SIMPLE_LABEL)
    private String simple;

    @JsonProperty(KameletRepresenter.NAME)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(final String constant) {
        this.constant = constant;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(final String simple) {
        this.simple = simple;
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {

        Map<String, Object> properties = new HashMap<>();
        if (this.getConstant() != null) {
            properties.put(CONSTANT_LABEL, this.getConstant());
        }

        if (this.getSimple() != null) {
            properties.put(SIMPLE_LABEL, this.getSimple());
        }

        if (this.getName() != null) {
            properties.put(NAME_LABEL, this.getName());
        }
        return properties;
    }

    @Override
    public Step getStep(final StepCatalog catalog,
                        final KameletStepParserService
                                kameletStepParserService) {
        return null;
    }
}
