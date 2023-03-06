package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.model.deployment.kamelet.expression.Expression;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;

import java.util.HashMap;
import java.util.Map;


@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceCall extends Expression {


    public static final String NAME_LABEL = "name";

    public static final String URI_LABEL = "uri";

    public static final String COMPONENT_LABEL = "component";

    public static final String PATTERN_LABEL = "pattern";

    public static final String CONFIGURATION_REF_LABEL = "configuration-ref";
    public static final String CONFIGURATION_REF_LABEL2 = "configurationRef";

    public static final String SERVICE_DISCOVERY_REF_LABEL = "service-discovery-ref";
    public static final String SERVICE_DISCOVERY_REF_LABEL2 = "serviceDiscoveryRef";

    public static final String SERVICE_FILTER_REF_LABEL = "service-filter-ref";
    public static final String SERVICE_FILTER_REF_LABEL2 = "serviceFilterRef";

    public static final String SERVICE_CHOOSER_REF_LABEL = "service-chooser-ref";
    public static final String SERVICE_CHOOSER_REF_LABEL2 = "serviceChooserRef";

    public static final String LOAD_BALANCER_REF_LABEL = "load-balancer-ref";
    public static final String LOAD_BALANCER_REF_LABEL2 = "loadBalancerRef";

    public static final String EXPRESSION_REF_LABEL = "expression-ref";
    public static final String EXPRESSION_REF_LABEL2 = "expressionRef";

    public static final String SERVICE_DISCOVERY_CONFIGURATION_LABEL = "service-discovery-configuration";
    public static final String SERVICE_DISCOVERY_CONFIGURATION_LABEL2 = "serviceDiscoveryConfiguration";
    public static final String SERVICE_DISCOVERY_CONFIGURATION_LABEL3 = "static-service-discovery";
    public static final String SERVICE_DISCOVERY_CONFIGURATION_LABEL4 = "staticServiceDiscovery";
    public static final String SERVICE_DISCOVERY_CONFIGURATION_LABEL5 = "consul-service-discovery";
    public static final String SERVICE_DISCOVERY_CONFIGURATION_LABEL6 = "consulServiceDiscovery";
    public static final String SERVICE_DISCOVERY_CONFIGURATION_LABEL7 = "dns-service-discovery";
    public static final String SERVICE_DISCOVERY_CONFIGURATION_LABEL8 = "dnsServiceDiscovery";
    public static final String SERVICE_DISCOVERY_CONFIGURATION_LABEL9 = "kubernetes-service-discovery";
    public static final String SERVICE_DISCOVERY_CONFIGURATION_LABEL10 = "kubernetesServiceDiscovery";

    public static final String SERVICE_FILTER_CONFIGURATION_LABEL = "service-filter-configuration";
    public static final String SERVICE_FILTER_CONFIGURATION_LABEL2 = "serviceFilterConfiguration";
    public static final String SERVICE_FILTER_CONFIGURATION_LABEL3 = "blacklist-service-filter";
    public static final String SERVICE_FILTER_CONFIGURATION_LABEL4 = "blacklistServiceFilter";
    public static final String SERVICE_FILTER_CONFIGURATION_LABEL5 = "service-filter";
    public static final String SERVICE_FILTER_CONFIGURATION_LABEL6 = "serviceFilter";

    public static final String LOAD_BALANCER_CONFIGURATION_LABEL = "load-balancer-configuration";
    public static final String LOAD_BALANCER_CONFIGURATION_LABEL2 = "loadBalancerConfiguration";

    public static final String DESCRIPTION_LABEL = "description";

    private String name;

    private String uri;

    private String component;

    private String pattern;

    private String configurationRef;

    private String serviceDiscoveryRef;

    private String serviceFilterRef;

    private String serviceChooserRef;

    private String loadBalancerRef;

    private String expressionRef;

    private Object serviceDiscoveryConfiguration;

    private Object serviceFilterConfiguration;

    private Object loadBalancerConfiguration;

    private Map<String, String> description;

    public ServiceCall() {
        //Empty for serialization purposes
    }

    @JsonCreator
    public ServiceCall(final @JsonProperty(NAME_LABEL) String name,
                       final @JsonProperty(EXPRESSION_LABEL) Expression expression,
                       final @JsonProperty(SIMPLE_LABEL) String simple,
                       final @JsonProperty(JQ_LABEL) String jq,
                       final @JsonProperty(CONSTANT_LABEL) String constant,
                       final @JsonProperty(URI_LABEL) String uri,
                       final @JsonProperty(COMPONENT_LABEL) String component,
                       final @JsonProperty(PATTERN_LABEL) String pattern,
                       final @JsonProperty(CONFIGURATION_REF_LABEL) String configurationRef,
                       final @JsonProperty(CONFIGURATION_REF_LABEL2) String configurationRef2,
                       final @JsonProperty(SERVICE_DISCOVERY_REF_LABEL) String serviceDiscoveryRef,
                       final @JsonProperty(SERVICE_DISCOVERY_REF_LABEL2) String serviceDiscoveryRef2,
                       final @JsonProperty(SERVICE_FILTER_REF_LABEL) String serviceFilterRef,
                       final @JsonProperty(SERVICE_FILTER_REF_LABEL2) String serviceFilterRef2,
                       final @JsonProperty(SERVICE_CHOOSER_REF_LABEL) String serviceChooserRef,
                       final @JsonProperty(SERVICE_CHOOSER_REF_LABEL2) String serviceChooserRef2,
                       final @JsonProperty(LOAD_BALANCER_REF_LABEL) String loadBalancerRef,
                       final @JsonProperty(LOAD_BALANCER_REF_LABEL2) String loadBalancerRef2,
                       final @JsonProperty(EXPRESSION_REF_LABEL) String expressionRef,
                       final @JsonProperty(EXPRESSION_REF_LABEL2) String expressionRef2,
                       final @JsonProperty(
                               SERVICE_DISCOVERY_CONFIGURATION_LABEL) Map<String, Object> serviceDiscoveryConfiguration,
                       final @JsonProperty(
                               SERVICE_DISCOVERY_CONFIGURATION_LABEL2) Map<String, Object> serviceDiscoveryConfig2,
                       final @JsonProperty(
                               SERVICE_DISCOVERY_CONFIGURATION_LABEL3) Map<String, Object> staticServiceDiscovery,
                       final @JsonProperty(
                               SERVICE_DISCOVERY_CONFIGURATION_LABEL4) Map<String, Object> staticServiceDiscovery2,
                       final @JsonProperty(
                               SERVICE_DISCOVERY_CONFIGURATION_LABEL5) Map<String, Object> consulServiceDiscovery,
                       final @JsonProperty(
                               SERVICE_DISCOVERY_CONFIGURATION_LABEL6) Map<String, Object> consulServiceDiscovery2,
                       final @JsonProperty(
                               SERVICE_DISCOVERY_CONFIGURATION_LABEL7) Map<String, Object> dnsServiceDiscovery,
                       final @JsonProperty(
                               SERVICE_DISCOVERY_CONFIGURATION_LABEL8) Map<String, Object> dnsServiceDiscovery2,
                       final @JsonProperty(
                               SERVICE_DISCOVERY_CONFIGURATION_LABEL9) Map<String, Object> kubernetesServiceDiscovery,
                       final @JsonProperty(
                               SERVICE_DISCOVERY_CONFIGURATION_LABEL10) Map<String, Object> kubernetesServiceDiscovery2,
                       final @JsonProperty(
                               SERVICE_FILTER_CONFIGURATION_LABEL) Map<String, Object> serviceFilterConfiguration,
                       final @JsonProperty(
                               SERVICE_FILTER_CONFIGURATION_LABEL2) Map<String, Object> serviceFilterConfiguration2,
                       final @JsonProperty(
                               SERVICE_FILTER_CONFIGURATION_LABEL3) Map<String, Object> blacklistServiceFilter,
                       final @JsonProperty(
                               SERVICE_FILTER_CONFIGURATION_LABEL4) Map<String, Object> blacklistServiceFilter2,
                       final @JsonProperty(SERVICE_FILTER_CONFIGURATION_LABEL5) Map<String, Object> serviceFilter,
                       final @JsonProperty(SERVICE_FILTER_CONFIGURATION_LABEL6) Map<String, Object> serviceFilter2,
                       final @JsonProperty(
                               LOAD_BALANCER_CONFIGURATION_LABEL) Map<String, Object> loadBalancerConfiguration,
                       final @JsonProperty(
                               LOAD_BALANCER_CONFIGURATION_LABEL2) Map<String, Object> loadBalancerConfiguration2,
                       final @JsonProperty(DESCRIPTION_LABEL) Map<String, String> description) {
        super(expression, constant, simple, jq, null, null, null, null);
        setName(name);
        setUri(uri);
        setComponent(component);
        setPattern(pattern);
        setConfigurationRef(configurationRef != null ? configurationRef : configurationRef2);
        setServiceDiscoveryRef(serviceDiscoveryRef != null ? serviceDiscoveryRef : serviceDiscoveryRef2);
        setServiceFilterRef(serviceFilterRef != null ? serviceFilterRef : serviceFilterRef2);
        setServiceChooserRef(serviceChooserRef != null ? serviceChooserRef : serviceChooserRef2);
        setLoadBalancerRef(loadBalancerRef != null ? loadBalancerRef : loadBalancerRef2);
        setExpressionRef(expressionRef != null ? expressionRef : expressionRef2);
        setDescription(description);
        setLoadBalancerConfiguration(loadBalancerConfiguration != null ? loadBalancerConfiguration :
                loadBalancerConfiguration2);
        configureOptionalServiceDiscovery(serviceDiscoveryConfiguration, serviceDiscoveryConfig2,
                SERVICE_DISCOVERY_CONFIGURATION_LABEL);
        configureOptionalServiceDiscovery(staticServiceDiscovery, staticServiceDiscovery2,
                SERVICE_DISCOVERY_CONFIGURATION_LABEL3);
        configureOptionalServiceDiscovery(consulServiceDiscovery, consulServiceDiscovery2,
                SERVICE_DISCOVERY_CONFIGURATION_LABEL5);
        configureOptionalServiceDiscovery(dnsServiceDiscovery, dnsServiceDiscovery2,
                SERVICE_DISCOVERY_CONFIGURATION_LABEL7);
        configureOptionalServiceDiscovery(kubernetesServiceDiscovery, kubernetesServiceDiscovery2,
                SERVICE_DISCOVERY_CONFIGURATION_LABEL9);
        configureOptionalServiceFilter(serviceFilterConfiguration, serviceFilterConfiguration2,
                SERVICE_FILTER_CONFIGURATION_LABEL);
        configureOptionalServiceFilter(blacklistServiceFilter, blacklistServiceFilter2,
                SERVICE_FILTER_CONFIGURATION_LABEL3);
        configureOptionalServiceFilter(serviceFilter, serviceFilter2, SERVICE_FILTER_CONFIGURATION_LABEL5);
    }

    private void configureOptionalServiceDiscovery(final Map<String, Object> serviceDiscovery,
                                                   final Map<String, Object> serviceDiscovery2, final String label) {
        if (serviceDiscovery != null) {
            configureServiceDiscovery(serviceDiscovery, label);
        } else if (serviceDiscovery2 != null) {
            configureServiceDiscovery(serviceDiscovery2, label);
        }
    }

    private void configureOptionalServiceFilter(final Map<String, Object> serviceFilter,
                                                final Map<String, Object> serviceFilter2, final String label) {
        if (serviceFilter != null) {
            configureServiceFilter(serviceFilter, label);
        } else if (serviceFilter2 != null) {
            configureServiceFilter(serviceFilter2, label);
        }
    }

    private void configureServiceFilter(final Map<String, Object> serviceDiscovery, final String label) {
        Map<String, Object> map = new HashMap<>();
        if (getServiceFilterConfiguration() != null && getServiceFilterConfiguration() instanceof Map m) {
            map = m;
        }
        map.put(label, serviceDiscovery);
        setServiceFilterConfiguration(map);
    }

    private void configureServiceDiscovery(final Map<String, Object> serviceDiscovery, final String label) {
        Map<String, Object> map = new HashMap<>();
        if (getServiceDiscoveryConfiguration() != null && getServiceDiscoveryConfiguration() instanceof Map m) {
            map = m;
        }
        map.put(label, serviceDiscovery);
        setServiceDiscoveryConfiguration(map);
    }

    public ServiceCall(Step step) {
        super(step);
    }


    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> properties = super.getRepresenterProperties();

        if (this.getName() != null) {
            properties.put(NAME_LABEL, this.getName());
        }
        if (this.getUri() != null) {
            properties.put(URI_LABEL, this.getUri());
        }
        if (this.getComponent() != null) {
            properties.put(COMPONENT_LABEL, this.getComponent());
        }
        if (this.getPattern() != null) {
            properties.put(PATTERN_LABEL, this.getPattern());
        }
        if (this.getConfigurationRef() != null) {
            properties.put(CONFIGURATION_REF_LABEL, this.getConfigurationRef());
        }
        if (this.getServiceDiscoveryRef() != null) {
            properties.put(SERVICE_DISCOVERY_REF_LABEL, this.getServiceDiscoveryRef());
        }
        if (this.getServiceFilterRef() != null) {
            properties.put(SERVICE_FILTER_REF_LABEL, this.getServiceFilterRef());
        }
        if (this.getServiceChooserRef() != null) {
            properties.put(SERVICE_CHOOSER_REF_LABEL, this.getServiceChooserRef());
        }
        if (this.getLoadBalancerRef() != null) {
            properties.put(LOAD_BALANCER_REF_LABEL, this.getLoadBalancerRef());
        }
        if (this.getExpressionRef() != null) {
            properties.put(EXPRESSION_REF_LABEL, this.getExpressionRef());
        }
        if (this.getServiceDiscoveryConfiguration() != null) {
            properties.putAll((Map<? extends String, ?>) this.getServiceDiscoveryConfiguration());
        }
        if (this.getServiceFilterConfiguration() != null) {
            properties.putAll((Map<? extends String, ?>) this.getServiceFilterConfiguration());
        }
        if (this.getLoadBalancerConfiguration() != null) {
            properties.put(LOAD_BALANCER_CONFIGURATION_LABEL, this.getLoadBalancerConfiguration());
        }
        if (this.getDescription() != null) {
            properties.put(DESCRIPTION_LABEL, this.getDescription());
        }

        return properties;
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        super.assignAttribute(parameter);
        switch (parameter.getId()) {
            case NAME_LABEL:
                this.setName(String.valueOf(parameter.getValue()));
                break;
            case URI_LABEL:
                this.setUri(String.valueOf(parameter.getValue()));
                break;
            case COMPONENT_LABEL:
                this.setComponent(String.valueOf(parameter.getValue()));
                break;
            case PATTERN_LABEL:
                this.setPattern(String.valueOf(parameter.getValue()));
                break;
            case CONFIGURATION_REF_LABEL:
            case CONFIGURATION_REF_LABEL2:
                this.setConfigurationRef(String.valueOf(parameter.getValue()));
                break;
            case SERVICE_DISCOVERY_REF_LABEL:
            case SERVICE_DISCOVERY_REF_LABEL2:
                this.setServiceDiscoveryRef(String.valueOf(parameter.getValue()));
                break;
            case SERVICE_FILTER_REF_LABEL:
            case SERVICE_FILTER_REF_LABEL2:
                this.setServiceFilterRef(String.valueOf(parameter.getValue()));
                break;
            case SERVICE_CHOOSER_REF_LABEL:
            case SERVICE_CHOOSER_REF_LABEL2:
                this.setServiceChooserRef(String.valueOf(parameter.getValue()));
                break;
            case LOAD_BALANCER_REF_LABEL:
            case LOAD_BALANCER_REF_LABEL2:
                this.setLoadBalancerRef(String.valueOf(parameter.getValue()));
                break;
            case EXPRESSION_REF_LABEL:
            case EXPRESSION_REF_LABEL2:
                this.setExpressionRef(String.valueOf(parameter.getValue()));
                break;
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL2:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL3:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL4:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL5:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL6:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL7:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL8:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL9:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL10:
                this.setServiceDiscoveryConfiguration(parameter.getValue());
                break;
            case SERVICE_FILTER_CONFIGURATION_LABEL:
            case SERVICE_FILTER_CONFIGURATION_LABEL2:
            case SERVICE_FILTER_CONFIGURATION_LABEL3:
            case SERVICE_FILTER_CONFIGURATION_LABEL4:
            case SERVICE_FILTER_CONFIGURATION_LABEL5:
            case SERVICE_FILTER_CONFIGURATION_LABEL6:
                this.setServiceFilterConfiguration(parameter.getValue());
                break;
            case LOAD_BALANCER_CONFIGURATION_LABEL:
            case LOAD_BALANCER_CONFIGURATION_LABEL2:
                this.setLoadBalancerConfiguration(parameter.getValue());
                break;
            case DESCRIPTION_LABEL:
                this.setDescription((Map<String, String>) parameter.getValue());
                break;
            default:
                break;
        }
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        super.assignProperty(parameter);
        switch (parameter.getId()) {

            case NAME_LABEL:
                parameter.setValue(this.getName());
                break;
            case URI_LABEL:
                parameter.setValue(this.getUri());
                break;
            case COMPONENT_LABEL:
                parameter.setValue(this.getComponent());
                break;
            case PATTERN_LABEL:
                parameter.setValue(this.getPattern());
                break;
            case CONFIGURATION_REF_LABEL:
            case CONFIGURATION_REF_LABEL2:
                parameter.setValue(this.getConfigurationRef());
                break;
            case SERVICE_DISCOVERY_REF_LABEL:
            case SERVICE_DISCOVERY_REF_LABEL2:
                parameter.setValue(this.getServiceDiscoveryRef());
                break;
            case SERVICE_FILTER_REF_LABEL:
            case SERVICE_FILTER_REF_LABEL2:
                parameter.setValue(this.getServiceFilterRef());
                break;
            case SERVICE_CHOOSER_REF_LABEL:
            case SERVICE_CHOOSER_REF_LABEL2:
                parameter.setValue(this.getServiceChooserRef());
                break;
            case LOAD_BALANCER_REF_LABEL:
            case LOAD_BALANCER_REF_LABEL2:
                parameter.setValue(this.getLoadBalancerRef());
                break;
            case EXPRESSION_REF_LABEL:
            case EXPRESSION_REF_LABEL2:
                parameter.setValue(this.getExpressionRef());
                break;
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL2:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL3:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL4:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL5:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL6:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL7:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL8:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL9:
            case SERVICE_DISCOVERY_CONFIGURATION_LABEL10:
                parameter.setValue(this.getServiceDiscoveryConfiguration());
                break;
            case SERVICE_FILTER_CONFIGURATION_LABEL:
            case SERVICE_FILTER_CONFIGURATION_LABEL2:
            case SERVICE_FILTER_CONFIGURATION_LABEL3:
            case SERVICE_FILTER_CONFIGURATION_LABEL4:
            case SERVICE_FILTER_CONFIGURATION_LABEL5:
            case SERVICE_FILTER_CONFIGURATION_LABEL6:
                parameter.setValue(this.getServiceFilterConfiguration());
                break;
            case LOAD_BALANCER_CONFIGURATION_LABEL:
            case LOAD_BALANCER_CONFIGURATION_LABEL2:
                parameter.setValue(this.getLoadBalancerConfiguration());
                break;
            case DESCRIPTION_LABEL:
                parameter.setValue(this.getDescription());
                break;
            default:
                break;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(final String component) {
        this.component = component;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    public String getConfigurationRef() {
        return configurationRef;
    }

    public void setConfigurationRef(final String configurationRef) {
        this.configurationRef = configurationRef;
    }

    public String getServiceDiscoveryRef() {
        return serviceDiscoveryRef;
    }

    public void setServiceDiscoveryRef(final String serviceDiscoveryRef) {
        this.serviceDiscoveryRef = serviceDiscoveryRef;
    }

    public String getServiceFilterRef() {
        return serviceFilterRef;
    }

    public void setServiceFilterRef(final String serviceFilterRef) {
        this.serviceFilterRef = serviceFilterRef;
    }

    public String getLoadBalancerRef() {
        return loadBalancerRef;
    }

    public void setLoadBalancerRef(final String loadBalancerRef) {
        this.loadBalancerRef = loadBalancerRef;
    }

    public String getExpressionRef() {
        return expressionRef;
    }

    public void setExpressionRef(final String expressionRef) {
        this.expressionRef = expressionRef;
    }

    public Object getServiceDiscoveryConfiguration() {
        return serviceDiscoveryConfiguration;
    }

    public void setServiceDiscoveryConfiguration(final Object serviceDiscoveryConfiguration) {
        this.serviceDiscoveryConfiguration = serviceDiscoveryConfiguration;
    }

    public Object getServiceFilterConfiguration() {
        return serviceFilterConfiguration;
    }

    public void setServiceFilterConfiguration(final Object serviceFilterConfiguration) {
        this.serviceFilterConfiguration = serviceFilterConfiguration;
    }

    public Object getLoadBalancerConfiguration() {
        return loadBalancerConfiguration;
    }

    public void setLoadBalancerConfiguration(final Object loadBalancerConfiguration) {
        this.loadBalancerConfiguration = loadBalancerConfiguration;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }

    public String getServiceChooserRef() {
        return serviceChooserRef;
    }

    public void setServiceChooserRef(final String serviceChooserRef) {
        this.serviceChooserRef = serviceChooserRef;
    }
}
