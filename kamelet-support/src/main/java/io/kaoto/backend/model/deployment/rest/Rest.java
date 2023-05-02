package io.kaoto.backend.model.deployment.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.kaoto.backend.KamelPopulator;
import io.kaoto.backend.api.metadata.catalog.StepCatalog;
import io.kaoto.backend.api.service.step.parser.kamelet.KameletStepParserService;
import io.kaoto.backend.model.deployment.kamelet.step.From;
import io.kaoto.backend.model.step.Branch;
import io.kaoto.backend.model.step.Step;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.LinkedList;
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
public class Rest extends From {
    protected static final Logger log = Logger.getLogger(Rest.class);
    private static final long serialVersionUID = 8685166491995899231L;
    public static final String CONSUMES_LABEL = "consumes";
    public static final String PRODUCES_LABEL = "produces";
    public static final String URI_LABEL = "uri";
    public static final String ID_LABEL = "id";
    public static final String DESCRIPTION_LABEL = "description";
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

    @JsonProperty(DESCRIPTION_LABEL)
    private String description;
    private KamelPopulator kamelPopulator;


    public Rest() {
        //empty for serialization
    }

    @JsonCreator
    public Rest(final @JsonProperty(PATH_LABEL) String path,
                         final @JsonProperty(GET_LABEL) List<HttpVerb> get,
                         final @JsonProperty(HEAD_LABEL) List<HttpVerb> head,
                         final @JsonProperty(POST_LABEL) List<HttpVerb> post,
                         final @JsonProperty(PUT_LABEL) List<HttpVerb> put,
                         final @JsonProperty(DELETE_LABEL) List<HttpVerb> delete,
                         final @JsonProperty(CONNECT_LABEL) List<HttpVerb> connect,
                         final @JsonProperty(TRACE_LABEL) List<HttpVerb> trace,
                         final @JsonProperty(PATCH_LABEL) List<HttpVerb> patch,
                         final @JsonProperty(DESCRIPTION_LABEL) String description) {
        super();
        setPath(path);
        setGet(get);
        setHead(head);
        setPost(post);
        setPut(put);
        setDelete(delete);
        setConnect(connect);
        setTrace(trace);
        setPatch(patch);
        setDescription(description);
    }

    public Rest(Step parentStep, StepCatalog catalog) {
        this();
        kamelPopulator = new KamelPopulator(catalog);

        for (var parameter : parentStep.getParameters()) {
            if (PATH_LABEL.equalsIgnoreCase(parameter.getId())) {
                this.setPath(String.valueOf(parameter.getValue()));
            } else if (DESCRIPTION_LABEL.equalsIgnoreCase(parameter.getId()) && parameter.getValue() != null) {
                this.setDescription(String.valueOf(parameter.getValue()));
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
                    setGet(new LinkedList<>());
                }
                list = populateVerb(step, getGet());
                break;
            case "camel-rest-verb-head":
                if (getHead() == null) {
                    setHead(new LinkedList<>());
                }
                list = populateVerb(step, getHead());
                break;
            case "camel-rest-verb-post":
                if (getPost() == null) {
                    setPost(new LinkedList<>());
                }
                list = populateVerb(step, getPost());
                break;
            case "camel-rest-verb-put":
                if (getPut() == null) {
                    setPut(new LinkedList<>());
                }
                list = populateVerb(step, getPut());
                break;
            case "camel-rest-verb-delete":
                if (getDelete() == null) {
                    setDelete(new LinkedList<>());
                }
                list = populateVerb(step, getDelete());
                break;
            case "camel-rest-verb-connect":
                if (getConnect() == null) {
                    setConnect(new LinkedList<>());
                }
                list = populateVerb(step, getConnect());
                break;
            case "camel-rest-verb-options":
                if (getOptions() == null) {
                    setOptions(new LinkedList<>());
                }
                list = populateVerb(step, getOptions());
                break;
            case "camel-rest-verb-trace":
                if (getTrace() == null) {
                    setTrace(new LinkedList<>());
                }
                list = populateVerb(step, getTrace());
                break;
            case "camel-rest-verb-patch":
                if (getPatch() == null) {
                    setPatch(new LinkedList<>());
                }
                list = populateVerb(step, getPatch());
                break;
            default:
                log.error("Malformed branch, we don't know which http verb it has");
                log.error("We are just going to assign this to GET because we have to put it somewhere.");
                if (getGet() == null) {
                    setGet(new LinkedList<>());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                } else if (p.getId().equalsIgnoreCase(ID_LABEL)) {
                    httpVerb.setId(String.valueOf(p.getValue()));
                } else if (p.getId().equalsIgnoreCase(URI_LABEL)) {
                    httpVerb.setUri(String.valueOf(p.getValue()));
                } else if (p.getId().equalsIgnoreCase(DESCRIPTION_LABEL)) {
                    httpVerb.setDescription(String.valueOf(p.getValue()));
                } else if (PARAM_LABEL.equalsIgnoreCase(p.getId())) {
                    httpVerb.setParameterList(new LinkedList<>());
                    for (var value : (List)p.getValue()) {
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
        Map<String, Object> props = new HashMap<>();

        props.put(PATH_LABEL, this.getPath());

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
        if (this.getDescription() != null) {
            props.put(DESCRIPTION_LABEL, this.getDescription());
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
        rest.setBranches(new LinkedList<>());
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
            } else if (DESCRIPTION_LABEL.equalsIgnoreCase(param.getId())) {
                param.setValue(this.getDescription());
            }
        }

        return rest;
    }

    private Branch createBranchHttpVerb(StepCatalog catalog, KameletStepParserService ksps,
                                        List<HttpVerb> httpVerbList, String httpVerb) {
        Branch b = new Branch();
        b.setIdentifier(httpVerb);
        var step = catalog.getReadOnlyCatalog().searchByID("camel-rest-verb-" + httpVerb);
        step.setBranches(new LinkedList<>());
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
                } else if (param.getId().equalsIgnoreCase(DESCRIPTION_LABEL)
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
