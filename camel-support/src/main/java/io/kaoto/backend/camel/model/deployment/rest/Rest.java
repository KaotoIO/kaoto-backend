package io.kaoto.backend.camel.model.deployment.rest;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.camel.KamelPopulator;
import io.kaoto.backend.camel.model.deployment.kamelet.step.From;
import io.kaoto.backend.camel.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * ```
 * - rest:
 * path: "/api/v3"
 * put:
 * - consumes: "application/json,application/xml"
 * id: "updatePet"
 * uri: "/pet"
 * param:
 * - name: "body"
 * required: true
 * type: "body"
 * to:
 * uri: "direct:updatePet"
 * ```
 */
@JsonDeserialize(using = JsonDeserializer.None.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Rest extends From {
    public static final String CONSUMES_LABEL = "consumes";
    public static final String PRODUCES_LABEL = "produces";
    public static final String URI_LABEL = "uri";
    public static final String ID_LABEL = "id";
    public static final String PATH_LABEL = "path";
    public static final String GET_LABEL = "get";
    public static final String HEAD_LABEL = "head";
    public static final String POST_LABEL = "post";
    public static final String PUT_LABEL = "put";
    public static final String DELETE_LABEL = "delete";
    public static final String CONNECT_LABEL = "connect";
    public static final String OPTIONS_LABEL = "options";
    public static final String TRACE_LABEL = "trace";
    public static final String PATCH_LABEL = "patch";
    public static final String PARAM_LABEL = "param";
    public static final String CAMEL_REST_CONSUMES = "CAMEL-REST-CONSUMES";
    public static final String CAMEL_REST_DSL = "CAMEL-REST-DSL";
    public static final String API_DOCS_LABEL = "api-docs";
    public static final String BINDING_MODE_LABEL = "binding-mode";
    public static final String CLIENT_REQUEST_VALIDATION_LABEL = "client-request-validation";
    public static final String ENABLE_CORS_LABEL = "enable-cors";
    public static final String SKIP_BINDING_ON_ERROR_CODE_LABEL = "skip-binding-on-error-code";
    public static final String TAG_LABEL = "tag";
    public static final String SECURITY_DEFINITIONS_LABEL = "security-definitions";
    public static final String SECURITY_REQUIREMENTS_LABEL = "security-requirements";
    protected static final Logger log = Logger.getLogger(Rest.class);
    private static final long serialVersionUID = 8685166491995899231L;
    @JsonProperty(PATH_LABEL)
    private String path;
    @JsonProperty(GET_LABEL)
    private List<HttpVerb> get;
    @JsonProperty(HEAD_LABEL)
    private List<HttpVerb> head;
    @JsonProperty(POST_LABEL)
    private List<HttpVerb> post;
    @JsonProperty(PUT_LABEL)
    private List<HttpVerb> put;
    @JsonProperty(DELETE_LABEL)
    private List<HttpVerb> delete;
    @JsonProperty(CONNECT_LABEL)
    private List<HttpVerb> connect;
    @JsonProperty(OPTIONS_LABEL)
    private List<HttpVerb> options;
    @JsonProperty(TRACE_LABEL)
    private List<HttpVerb> trace;
    @JsonProperty(PATCH_LABEL)
    private List<HttpVerb> patch;

    @JsonProperty(API_DOCS_LABEL)
    @JsonAlias("apiDocs")
    private Boolean apiDocs;

    @JsonProperty(BINDING_MODE_LABEL)
    @JsonAlias("bindingMode")
    private String bindingMode;

    @JsonProperty(CLIENT_REQUEST_VALIDATION_LABEL)
    @JsonAlias("clientRequestValidation")
    private Boolean clientRequestValidation;

    @JsonProperty(ENABLE_CORS_LABEL)
    @JsonAlias("enableCors")
    private Boolean enableCors;

    @JsonProperty(SKIP_BINDING_ON_ERROR_CODE_LABEL)
    @JsonAlias("skipBindingOnErrorCode")
    private Boolean skipBindingOnErrorCode;

    @JsonProperty(ID_LABEL)
    private String id;

    @JsonProperty(TAG_LABEL)
    private String tag;

    @JsonProperty(SECURITY_DEFINITIONS_LABEL)
    @JsonAlias("securityDefinitions")
    private Object securityDefinitions;

    @JsonProperty(SECURITY_REQUIREMENTS_LABEL)
    @JsonAlias("securityRequirements")
    private Object[] securityRequirements;


    private KamelPopulator kamelPopulator;

    public Rest() {
        //Needed for serialization
    }

    public Rest(Step parentStep, StepCatalog catalog) {
        kamelPopulator = new KamelPopulator(catalog);

        for (var parameter : parentStep.getParameters()) {
            if (parameter.getValue() != null) {
                if (PATH_LABEL.equalsIgnoreCase(parameter.getId())) {
                    this.setPath(String.valueOf(parameter.getValue()));
                } else if (Rest.SKIP_BINDING_ON_ERROR_CODE_LABEL.equalsIgnoreCase(parameter.getId())) {
                    this.setSkipBindingOnErrorCode(Boolean.valueOf(String.valueOf(parameter.getValue())));
                } else if (Rest.API_DOCS_LABEL.equalsIgnoreCase(parameter.getId())) {
                    this.setApiDocs(Boolean.valueOf(String.valueOf(parameter.getValue())));
                } else if (Rest.CLIENT_REQUEST_VALIDATION_LABEL.equalsIgnoreCase(parameter.getId())) {
                    this.setClientRequestValidation(Boolean.valueOf(String.valueOf(parameter.getValue())));
                } else if (Rest.ENABLE_CORS_LABEL.equalsIgnoreCase(parameter.getId())) {
                    this.setEnableCors(Boolean.valueOf(String.valueOf(parameter.getValue())));
                } else if (Rest.BINDING_MODE_LABEL.equalsIgnoreCase(parameter.getId())) {
                    this.setBindingMode(String.valueOf(parameter.getValue()));
                } else if (Rest.ID_LABEL.equalsIgnoreCase(parameter.getId())) {
                    this.setId(String.valueOf(parameter.getValue()));
                } else if (Rest.TAG_LABEL.equalsIgnoreCase(parameter.getId())) {
                    this.setTag(String.valueOf(parameter.getValue()));
                } else if (Rest.SECURITY_DEFINITIONS_LABEL.equalsIgnoreCase(parameter.getId())) {
                    this.setSecurityDefinitions(parameter.getValue());
                } else if (Rest.SECURITY_REQUIREMENTS_LABEL.equalsIgnoreCase(parameter.getId())) {
                    this.setSecurityRequirements((Object[]) parameter.getValue());
                }
            }
        }

        if (parentStep.getBranches() != null) {
            for (Branch b : parentStep.getBranches()) {
                if (!b.getSteps().isEmpty()) {
                    var step = b.getSteps().get(0);
                    stepToHttpVerb(step);
                }
            }
        }
    }

    public List<HttpVerb> stepToHttpVerb(Step step) {
        List<HttpVerb> list = null;
        switch (step.getId().toLowerCase()) {
            case "camel-rest-verb-get":
                if (getGet() == null) {
                    setGet(new ArrayList<>());
                }
                list = populateVerb(step, getGet());
                break;
            case "camel-rest-verb-head":
                if (getHead() == null) {
                    setHead(new ArrayList<>());
                }
                list = populateVerb(step, getHead());
                break;
            case "camel-rest-verb-post":
                if (getPost() == null) {
                    setPost(new ArrayList<>());
                }
                list = populateVerb(step, getPost());
                break;
            case "camel-rest-verb-put":
                if (getPut() == null) {
                    setPut(new ArrayList<>());
                }
                list = populateVerb(step, getPut());
                break;
            case "camel-rest-verb-delete":
                if (getDelete() == null) {
                    setDelete(new ArrayList<>());
                }
                list = populateVerb(step, getDelete());
                break;
            case "camel-rest-verb-connect":
                if (getConnect() == null) {
                    setConnect(new ArrayList<>());
                }
                list = populateVerb(step, getConnect());
                break;
            case "camel-rest-verb-options":
                if (getOptions() == null) {
                    setOptions(new ArrayList<>());
                }
                list = populateVerb(step, getOptions());
                break;
            case "camel-rest-verb-trace":
                if (getTrace() == null) {
                    setTrace(new ArrayList<>());
                }
                list = populateVerb(step, getTrace());
                break;
            case "camel-rest-verb-patch":
                if (getPatch() == null) {
                    setPatch(new ArrayList<>());
                }
                list = populateVerb(step, getPatch());
                break;
            default:
                log.error("Malformed branch, we don't know which http verb it has");
                log.error("We are just going to assign this to GET because we have to put it somewhere.");
                if (getGet() == null) {
                    setGet(new ArrayList<>());
                }
                list = populateVerb(step, getGet());
                break;
        }

        return list;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<HttpVerb> getGet() {
        return get;
    }

    public void setGet(List<HttpVerb> get) {
        this.get = get;
    }

    public List<HttpVerb> getHead() {
        return head;
    }

    public void setHead(List<HttpVerb> head) {
        this.head = head;
    }

    public List<HttpVerb> getPost() {
        return post;
    }

    public void setPost(List<HttpVerb> post) {
        this.post = post;
    }

    public List<HttpVerb> getPut() {
        return put;
    }

    public void setPut(List<HttpVerb> put) {
        this.put = put;
    }

    public List<HttpVerb> getDelete() {
        return delete;
    }

    public void setDelete(List<HttpVerb> delete) {
        this.delete = delete;
    }

    public List<HttpVerb> getConnect() {
        return connect;
    }

    public void setConnect(List<HttpVerb> connect) {
        this.connect = connect;
    }

    public List<HttpVerb> getOptions() {
        return options;
    }

    public void setOptions(List<HttpVerb> options) {
        this.options = options;
    }

    public List<HttpVerb> getTrace() {
        return trace;
    }

    public void setTrace(List<HttpVerb> trace) {
        this.trace = trace;
    }

    public List<HttpVerb> getPatch() {
        return patch;
    }

    public void setPatch(List<HttpVerb> patch) {
        this.patch = patch;
    }

    public Boolean getApiDocs() {
        return apiDocs;
    }

    public void setApiDocs(Boolean apiDocs) {
        this.apiDocs = apiDocs;
    }

    public String getBindingMode() {
        return bindingMode;
    }

    public void setBindingMode(String bindingMode) {
        this.bindingMode = bindingMode;
    }

    public Boolean getClientRequestValidation() {
        return clientRequestValidation;
    }

    public void setClientRequestValidation(Boolean clientRequestValidation) {
        this.clientRequestValidation = clientRequestValidation;
    }

    public Boolean getEnableCors() {
        return enableCors;
    }

    public void setEnableCors(Boolean enableCors) {
        this.enableCors = enableCors;
    }

    public Boolean getSkipBindingOnErrorCode() {
        return skipBindingOnErrorCode;
    }

    public void setSkipBindingOnErrorCode(Boolean skipBindingOnErrorCode) {
        this.skipBindingOnErrorCode = skipBindingOnErrorCode;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getSecurityDefinitions() {
        return securityDefinitions;
    }

    public void setSecurityDefinitions(Object securityDefinitions) {
        this.securityDefinitions = securityDefinitions;
    }

    public Object[] getSecurityRequirements() {
        return securityRequirements;
    }

    public void setSecurityRequirements(Object[] securityRequirements) {
        this.securityRequirements = securityRequirements;
    }

    private List<HttpVerb> populateVerb(final Step step, final List<HttpVerb> httpVerbList) {

        if (step.getBranches() != null) {
            for (Branch b : step.getBranches()) {
                if (!b.getSteps().isEmpty()) {
                    var s = b.getSteps().get(0);
                    addEndpointToHttpVerb(httpVerbList, b, s);
                }
            }
        }

        return httpVerbList;
    }

    public HttpVerb addEndpointToHttpVerb(List<HttpVerb> httpVerbList, Branch b, Step s) {
        HttpVerb httpVerb = new HttpVerb();
        for (var p : s.getParameters()) {
            if (p.getValue() != null) {
                if (p.getId().equalsIgnoreCase(CONSUMES_LABEL)) {
                    httpVerb.setConsumes(String.valueOf(p.getValue()));
                } else if (p.getId().equalsIgnoreCase(PRODUCES_LABEL)) {
                    httpVerb.setProduces(String.valueOf(p.getValue()));
                }  else if (p.getId().equalsIgnoreCase(KamelHelper.DESCRIPTION)) {
                    httpVerb.setDescription(String.valueOf(p.getValue()));
                } else if (p.getId().equalsIgnoreCase(ID_LABEL)) {
                    httpVerb.setId(String.valueOf(p.getValue()));
                } else if (p.getId().equalsIgnoreCase(URI_LABEL)) {
                    httpVerb.setUri(String.valueOf(p.getValue()));
                } else if (PARAM_LABEL.equalsIgnoreCase(p.getId())) {
                    httpVerb.setParameterList(new ArrayList<>());
                    for (var value : (List) p.getValue()) {
                        if (value instanceof RestParameter restParameter) {
                            httpVerb.getParameterList().add(restParameter);
                        } else if (value instanceof Map map) {
                            httpVerb.getParameterList().add(new RestParameter(map));
                        }
                    }
                }
            }
        }
        httpVerbList.add(httpVerb);
        if (b != null && b.getSteps().size() > 1) {
            setToHttpVerb(b.getSteps().get(1), httpVerb);
        }
        return httpVerb;
    }

    public void setToHttpVerb(Step step, HttpVerb httpVerb) {
        httpVerb.setTo(kamelPopulator.getCamelConnector(step, true));
    }

    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> props = new LinkedHashMap<>();

        if (this.getId() != null) {
            props.put(ID, this.getId());
        }
        props.put(PATH_LABEL, this.getPath());

        if (this.getApiDocs() != null) {
            props.put(API_DOCS_LABEL, this.getApiDocs());
        }
        if (this.getBindingMode() != null) {
            props.put(BINDING_MODE_LABEL, this.getBindingMode());
        }
        if (this.getClientRequestValidation() != null) {
            props.put(CLIENT_REQUEST_VALIDATION_LABEL, this.getClientRequestValidation());
        }
        if (this.getEnableCors() != null) {
            props.put(ENABLE_CORS_LABEL, this.getEnableCors());
        }
        if (this.getSkipBindingOnErrorCode() != null) {
            props.put(SKIP_BINDING_ON_ERROR_CODE_LABEL, this.getSkipBindingOnErrorCode());
        }
        if (this.getTag() != null) {
            props.put(TAG_LABEL, this.getTag());
        }
        if (this.getSecurityDefinitions() != null) {
            props.put(SECURITY_DEFINITIONS_LABEL, this.getSecurityDefinitions());
        }
        if (this.getSecurityRequirements() != null) {
            props.put(SECURITY_REQUIREMENTS_LABEL, this.getSecurityRequirements());
        }

        if (this.getGet() != null) {
            props.put(GET_LABEL, this.getGet());
        }
        if (this.getHead() != null) {
            props.put(HEAD_LABEL, this.getHead());
        }
        if (this.getPost() != null) {
            props.put(POST_LABEL, this.getPost());
        }
        if (this.getPut() != null) {
            props.put(PUT_LABEL, this.getPut());
        }
        if (this.getDelete() != null) {
            props.put(DELETE_LABEL, this.getDelete());
        }
        if (this.getConnect() != null) {
            props.put(CONNECT_LABEL, this.getConnect());
        }
        if (this.getOptions() != null) {
            props.put(OPTIONS_LABEL, this.getOptions());
        }
        if (this.getTrace() != null) {
            props.put(TRACE_LABEL, this.getTrace());
        }
        if (this.getPatch() != null) {
            props.put(PATCH_LABEL, this.getPatch());
        }

        return props;
    }


    public Step getStep(KameletStepParserService ksps, Boolean start, Boolean end) {
        StepCatalog catalog = ksps.getCatalog();

        var rest = catalog.getReadOnlyCatalog().searchByID(CAMEL_REST_DSL);
        if (rest == null) {
            log.error("No rest component in the catalog!");
            return null;
        }
        rest.setBranches(new ArrayList<>());
        if (this.getGet() != null) {
            rest.getBranches().add(createBranchHttpVerb(catalog, ksps, this.getGet(), GET_LABEL));
        }
        if (this.getHead() != null) {
            rest.getBranches().add(createBranchHttpVerb(catalog, ksps, this.getHead(), HEAD_LABEL));
        }
        if (this.getPost() != null) {
            rest.getBranches().add(createBranchHttpVerb(catalog, ksps, this.getPost(), POST_LABEL));
        }
        if (this.getPut() != null) {
            rest.getBranches().add(createBranchHttpVerb(catalog, ksps, this.getPut(), PUT_LABEL));
        }
        if (this.getDelete() != null) {
            rest.getBranches().add(createBranchHttpVerb(catalog, ksps, this.getDelete(), DELETE_LABEL));
        }
        if (this.getConnect() != null) {
            rest.getBranches().add(createBranchHttpVerb(catalog, ksps, this.getConnect(), CONNECT_LABEL));
        }
        if (this.getOptions() != null) {
            rest.getBranches().add(createBranchHttpVerb(catalog, ksps, this.getOptions(), OPTIONS_LABEL));
        }
        if (this.getTrace() != null) {
            rest.getBranches().add(createBranchHttpVerb(catalog, ksps, this.getTrace(), TRACE_LABEL));
        }
        if (this.getPatch() != null) {
            rest.getBranches().add(createBranchHttpVerb(catalog, ksps, this.getPatch(), PATCH_LABEL));
        }

        for (var param : rest.getParameters()) {
            if (PATH_LABEL.equalsIgnoreCase(param.getId())) {
                param.setValue(this.getPath());
            } else if (Rest.SKIP_BINDING_ON_ERROR_CODE_LABEL.equalsIgnoreCase(param.getId())) {
                param.setValue(this.getSkipBindingOnErrorCode());
            } else if (Rest.API_DOCS_LABEL.equalsIgnoreCase(param.getId())) {
                param.setValue(this.getApiDocs());
            } else if (Rest.CLIENT_REQUEST_VALIDATION_LABEL.equalsIgnoreCase(param.getId())) {
                param.setValue(this.getClientRequestValidation());
            } else if (Rest.ENABLE_CORS_LABEL.equalsIgnoreCase(param.getId())) {
                param.setValue(this.getEnableCors());
            } else if (Rest.BINDING_MODE_LABEL.equalsIgnoreCase(param.getId())) {
                param.setValue(this.getBindingMode());
            } else if (Rest.ID_LABEL.equalsIgnoreCase(param.getId())) {
                param.setValue(this.getId());
            } else if (Rest.TAG_LABEL.equalsIgnoreCase(param.getId())) {
                param.setValue(this.getTag());
            } else if (Rest.SECURITY_DEFINITIONS_LABEL.equalsIgnoreCase(param.getId())) {
                param.setValue(this.getSecurityDefinitions());
            } else if (Rest.SECURITY_REQUIREMENTS_LABEL.equalsIgnoreCase(param.getId())) {
                param.setValue(this.getSecurityRequirements());
            }
        }

        return rest;
    }

    private Branch createBranchHttpVerb(StepCatalog catalog, KameletStepParserService ksps,
                                        List<HttpVerb> httpVerbList, String httpVerb) {
        Branch b = new Branch();
        b.setIdentifier(httpVerb);
        var step = catalog.getReadOnlyCatalog().searchByID("camel-rest-verb-" + httpVerb);
        step.setBranches(new ArrayList<>());
        for (HttpVerb endpoint : httpVerbList) {
            step.getBranches().add(createBranchConsumes(catalog, ksps, endpoint));
        }
        b.getSteps().add(step);
        return b;
    }

    private Branch createBranchConsumes(StepCatalog catalog, KameletStepParserService ksps,
                                        HttpVerb endpoint) {
        Branch b = new Branch();
        b.setIdentifier(endpoint.getUri() + " " + endpoint.getConsumes());
        var step = catalog.getReadOnlyCatalog().searchByID(Rest.CAMEL_REST_CONSUMES);
        if (step != null) {
            b.getSteps().add(step);
            for (var param : step.getParameters()) {
                if (param.getId().equalsIgnoreCase(CONSUMES_LABEL)
                        && endpoint.getConsumes() != null) {
                    param.setValue(endpoint.getConsumes());
                } else if (param.getId().equalsIgnoreCase(PRODUCES_LABEL)
                        && endpoint.getProduces() != null) {
                    param.setValue(endpoint.getProduces());
                } else if (param.getId().equalsIgnoreCase(URI_LABEL)
                        && endpoint.getUri() != null) {
                    param.setValue(endpoint.getUri());
                } else if (param.getId().equalsIgnoreCase(ID_LABEL)
                        && endpoint.getId() != null) {
                    param.setValue(endpoint.getId());
                } else if (param.getId().equalsIgnoreCase(KamelHelper.DESCRIPTION)
                        && endpoint.getDescription() != null) {
                    param.setValue(endpoint.getDescription());
                } else if (param.getId().equalsIgnoreCase(PARAM_LABEL)
                        && endpoint.getParameterList() != null) {
                    param.setValue(endpoint.getParameterList());
                }
            }
        }
        if (endpoint.getTo() != null) {
            step = ksps.processStep(endpoint.getTo(), false, true);
            if (step != null) {
                b.getSteps().add(step);
            }
        }
        return b;
    }
}
