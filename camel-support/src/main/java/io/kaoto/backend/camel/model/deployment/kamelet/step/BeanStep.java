package io.kaoto.backend.camel.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.Map;


public class BeanStep extends EIPStep {
    public static final String REF_LABEL = "ref";
    public static final String METHOD_LABEL = "method";
    public static final String BEAN_TYPE_LABEL = "beanType";
    public static final String BEAN_TYPE_LABEL2 = "bean-type";
    public static final String SCOPE_LABEL = "scope";
    public static final String ID_LABEL = "id";

    @JsonProperty(REF_LABEL)
    private String ref;

    @JsonProperty(METHOD_LABEL)
    private String method;

    @JsonProperty(BEAN_TYPE_LABEL)
    @JsonAlias(BEAN_TYPE_LABEL2)
    private String beanType;

     @JsonProperty(SCOPE_LABEL)
    private String scope;

     @JsonProperty(KamelHelper.DESCRIPTION)
    private String description;

    public BeanStep() {
    }

    public BeanStep(Step step) {
        super(step);
    }

    public BeanStep(Map<String, Object> map) {
        super(map);
        if (map.containsKey(REF_LABEL)) {
            this.setRef(String.valueOf(map.get(REF_LABEL)));
        }
        if (map.containsKey(METHOD_LABEL)) {
            this.setMethod(String.valueOf(map.get(METHOD_LABEL)));
        }
        if (map.containsKey(SCOPE_LABEL)) {
            this.setScope(String.valueOf(map.get(SCOPE_LABEL)));
        }
        if (map.containsKey(KamelHelper.DESCRIPTION)) {
            this.setDescription(String.valueOf(map.get(KamelHelper.DESCRIPTION)));
        }
        if (map.containsKey(ID_LABEL)) {
            this.setId(String.valueOf(map.get(ID_LABEL)));
        }

        if (map.containsKey(BEAN_TYPE_LABEL)) {
            this.setBeanType(String.valueOf(map.get(BEAN_TYPE_LABEL)));
        } else if (map.containsKey(BEAN_TYPE_LABEL2)) {
            this.setBeanType(String.valueOf(map.get(BEAN_TYPE_LABEL2)));
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getDefaultRepresenterProperties();
        if (this.getRef() != null) {
            properties.put(REF_LABEL, this.getRef());
        }
        if (this.getMethod() != null) {
            properties.put(METHOD_LABEL, this.getMethod());
        }
        if (this.getScope() != null) {
            properties.put(SCOPE_LABEL, this.getScope());
        }
        if (this.getBeanType() != null) {
            properties.put(BEAN_TYPE_LABEL, this.getBeanType());
        }
        if (this.getDescription() != null) {
            properties.put(KamelHelper.DESCRIPTION, this.getDescription());
        }
        return properties;
    }


    @Override
    protected void assignAttribute(final Parameter parameter) {
        switch (parameter.getId()) {
            case REF_LABEL:
                this.setRef(parameter.getValue().toString());
                break;
            case METHOD_LABEL:
                this.setMethod(parameter.getValue().toString());
                break;
            case SCOPE_LABEL:
                this.setScope(parameter.getValue().toString());
                break;
            case BEAN_TYPE_LABEL:
            case BEAN_TYPE_LABEL2:
                this.setBeanType(parameter.getValue().toString());
                break;
            case KamelHelper.DESCRIPTION:
                this.setDescription(parameter.getValue().toString());
                break;
            case ID_LABEL:
                this.setId(parameter.getValue().toString());
                break;
            default:
                break;
        }
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        switch (parameter.getId()) {
            case REF_LABEL:
                parameter.setValue(this.getRef());
                break;
            case METHOD_LABEL:
                parameter.setValue(this.getMethod());
                break;
            case SCOPE_LABEL:
                parameter.setValue(this.getScope());
                break;
            case BEAN_TYPE_LABEL:
                parameter.setValue(this.getBeanType());
                break;
            case ID_LABEL:
                parameter.setValue(this.getId());
                break;
            case KamelHelper.DESCRIPTION:
                parameter.setValue(this.getDescription());
                break;
            default:
                break;
        }
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBeanType() {
        return beanType;
    }

    public void setBeanType(String beanType) {
        this.beanType = beanType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
