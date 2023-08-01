package io.kaoto.backend.camel.model.deployment.kamelet.expression;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.kaoto.backend.camel.KamelHelper;
import io.kaoto.backend.model.parameter.Parameter;
import io.kaoto.backend.model.step.Step;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.HashMap;
import java.util.Map;

@RegisterForReflection
public class Tokenizer extends Expression {
    public static final String TOKEN_LABEL = "token";
    public static final String END_TOKEN_LABEL = "end-token-label";
    public static final String END_TOKEN_LABEL2 = "endTokenLabel";

    public static final String INHERIT_NAMESPACE_TAG_NAME_LABEL = "inherit-namespace-tag-name";
    public static final String INHERIT_NAMESPACE_TAG_NAME_LABEL2 = "inheritNamespaceTagName";

    public static final String HEADER_NAME_LABEL = "header-name";
    public static final String HEADER_NAME_LABEL2 = "headerName";

    public static final String REGEX_LABEL = "regex";

    public static final String XML_LABEL = "xml";

    public static final String INCLUDE_TOKENS_LABEL = "include-tokens";
    public static final String INCLUDE_TOKENS_LABEL2 = "includeTokens";

    public static final String GROUP_LABEL = "group";

    public static final String GROUP_DELIMITER_LABEL = "group-delimiters";
    public static final String GROUP_DELIMITER_LABEL2 = "groupDelimiter";

    public static final String SKIP_FIRST_LABEL = "skip-first";
    public static final String SKIP_FIRST_LABEL2 = "skipFirst";

    public static final String TRIM_LABEL = "trim";
    public static final String TOKENIZE_LABEL = "tokenize";

    @JsonProperty(TOKEN_LABEL)
    private String token;

    @JsonProperty(END_TOKEN_LABEL)
    @JsonAlias(END_TOKEN_LABEL2)
    private String endToken;

    @JsonProperty(INHERIT_NAMESPACE_TAG_NAME_LABEL)
    @JsonAlias(INHERIT_NAMESPACE_TAG_NAME_LABEL2)
    private String inheritNamespaceTagName;

    @JsonProperty(HEADER_NAME_LABEL)
    @JsonAlias(HEADER_NAME_LABEL2)
    private String headerName;

    @JsonProperty(REGEX_LABEL)
    private Boolean regex;

    @JsonProperty(XML_LABEL)
    private Boolean xml;

    @JsonProperty(INCLUDE_TOKENS_LABEL)
    @JsonAlias(INCLUDE_TOKENS_LABEL2)
    private String includeTokens;

    @JsonProperty(GROUP_LABEL)
    private String group;

    @JsonProperty(GROUP_DELIMITER_LABEL)
    @JsonAlias(GROUP_DELIMITER_LABEL2)
    private String groupDelimiter;

    @JsonProperty(SKIP_FIRST_LABEL)
    @JsonAlias(SKIP_FIRST_LABEL2)
    private Boolean skipFirst;

    @JsonProperty(TRIM_LABEL)
    private Boolean trim;

    public Tokenizer() {
        //Needed for serialization
    }

    public Tokenizer(final Step step) {
        super(step);
    }

    @JsonCreator
    public Tokenizer(final Object obj) {
        if (obj instanceof String t) {
            setToken(t);
        } else if (obj instanceof Tokenizer e) {
            copyFrom(e);
        } else if (obj instanceof Map map) {
            copyFrom(KamelHelper.GENERIC_MAPPER.convertValue(map, Tokenizer.class));
            setPropertiesFromMap(map, this);
        }
    }

    private void copyFrom(Tokenizer e) {
        setConstant(e.getConstant());
        setSimple(e.getSimple());
        setJq(e.getJq());

        this.setToken(e.getToken());
        this.setEndToken(e.getEndToken());
        this.setInheritNamespaceTagName(e.getInheritNamespaceTagName());
        this.setHeaderName(e.getHeaderName());
        this.setRegex(e.getRegex());
        this.setXml(e.getXml());
        this.setIncludeTokens(e.getIncludeTokens());
        this.setGroup(e.getGroup());
        this.setGroupDelimiter(e.getGroupDelimiter());
        this.setSkipFirst(e.getSkipFirst());
        this.setTrim(e.getTrim());
    }

    @Override
    protected void assignAttribute(final Parameter parameter) {
        super.assignAttribute(parameter);
        switch (parameter.getId()) {
            case TOKEN_LABEL:
                this.setToken(parameter.getValue().toString());
                break;
            case END_TOKEN_LABEL:
            case END_TOKEN_LABEL2:
                this.setEndToken(parameter.getValue().toString());
                break;
            case INHERIT_NAMESPACE_TAG_NAME_LABEL:
            case INHERIT_NAMESPACE_TAG_NAME_LABEL2:
                this.setInheritNamespaceTagName(parameter.getValue().toString());
                break;
            case HEADER_NAME_LABEL:
            case HEADER_NAME_LABEL2:
                this.setHeaderName(parameter.getValue().toString());
                break;
            case REGEX_LABEL:
                this.setRegex(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case XML_LABEL:
                this.setXml(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case INCLUDE_TOKENS_LABEL:
            case INCLUDE_TOKENS_LABEL2:
                this.setIncludeTokens(parameter.getValue().toString());
                break;
            case GROUP_LABEL:
                this.setGroup(parameter.getValue().toString());
                break;
            case GROUP_DELIMITER_LABEL:
            case GROUP_DELIMITER_LABEL2:
                this.setGroupDelimiter(parameter.getValue().toString());
                break;
            case SKIP_FIRST_LABEL:
            case SKIP_FIRST_LABEL2:
                this.setSkipFirst(Boolean.valueOf(parameter.getValue().toString()));
                break;
            case TRIM_LABEL:
                this.setTrim(Boolean.valueOf(parameter.getValue().toString()));
                break;
            default:
                break;
        }
    }

    @Override
    protected void assignProperty(final Parameter parameter) {
        super.assignProperty(parameter);
        switch (parameter.getId()) {
            case TOKEN_LABEL:
                parameter.setValue(this.getToken());
                break;
            case END_TOKEN_LABEL:
            case END_TOKEN_LABEL2:
                parameter.setValue(this.getEndToken());
                break;
            case INHERIT_NAMESPACE_TAG_NAME_LABEL:
            case INHERIT_NAMESPACE_TAG_NAME_LABEL2:
                parameter.setValue(this.getInheritNamespaceTagName());
                break;
            case HEADER_NAME_LABEL:
            case HEADER_NAME_LABEL2:
                parameter.setValue(this.getHeaderName());
                break;
            case REGEX_LABEL:
                parameter.setValue(this.getRegex());
                break;
            case XML_LABEL:
                parameter.setValue(this.getXml());
                break;
            case INCLUDE_TOKENS_LABEL:
            case INCLUDE_TOKENS_LABEL2:
                parameter.setValue(this.getIncludeTokens());
                break;
            case GROUP_LABEL:
                parameter.setValue(this.getGroup());
                break;
            case GROUP_DELIMITER_LABEL:
            case GROUP_DELIMITER_LABEL2:
                parameter.setValue(this.getGroupDelimiter());
                break;
            case SKIP_FIRST_LABEL:
            case SKIP_FIRST_LABEL2:
                parameter.setValue(this.getSkipFirst());
                break;
            case TRIM_LABEL:
                parameter.setValue(this.getTrim());
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> getRepresenterProperties() {
        Map<String, Object> tokenize = new HashMap<>();
        if (this.getToken() != null) {
            tokenize.put(TOKEN_LABEL, this.getToken());
        }
        if (this.getEndToken() != null) {
            tokenize.put(END_TOKEN_LABEL, this.getEndToken());
        }
        if (this.getInheritNamespaceTagName() != null) {
            tokenize.put(INHERIT_NAMESPACE_TAG_NAME_LABEL, this.getInheritNamespaceTagName());
        }
        if (this.getHeaderName() != null) {
            tokenize.put(HEADER_NAME_LABEL, this.getHeaderName());
        }
        if (this.getRegex() != null) {
            tokenize.put(REGEX_LABEL, this.getRegex());
        }
        if (this.getXml() != null) {
            tokenize.put(XML_LABEL, this.getXml());
        }
        if (this.getIncludeTokens() != null) {
            tokenize.put(INCLUDE_TOKENS_LABEL, this.getIncludeTokens());
        }
        if (this.getGroup() != null) {
            tokenize.put(GROUP_LABEL, this.getGroup());
        }
        if (this.getGroupDelimiter() != null) {
            tokenize.put(GROUP_DELIMITER_LABEL, this.getGroupDelimiter());
        }
        if (this.getSkipFirst() != null) {
            tokenize.put(SKIP_FIRST_LABEL, this.getSkipFirst());
        }
        if (this.getTrim() != null) {
            tokenize.put(TRIM_LABEL, this.getTrim());
        }
        Map<String, Object> properties = super.getRepresenterProperties();
        if (tokenize.size() == 1 && tokenize.containsKey(TOKEN_LABEL)) {
            properties.put(TOKENIZE_LABEL, tokenize.get(TOKEN_LABEL));
        } else if (!tokenize.isEmpty()) {
            properties.put(TOKENIZE_LABEL, tokenize);
        }
        return properties;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public String getEndToken() {
        return endToken;
    }

    public void setEndToken(final String endToken) {
        this.endToken = endToken;
    }

    public String getInheritNamespaceTagName() {
        return inheritNamespaceTagName;
    }

    public void setInheritNamespaceTagName(final String inheritNamespaceTagName) {
        this.inheritNamespaceTagName = inheritNamespaceTagName;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(final String headerName) {
        this.headerName = headerName;
    }

    public Boolean getRegex() {
        return regex;
    }

    public void setRegex(final Boolean regex) {
        this.regex = regex;
    }

    public Boolean getXml() {
        return xml;
    }

    public void setXml(final Boolean xml) {
        this.xml = xml;
    }

    public String getIncludeTokens() {
        return includeTokens;
    }

    public void setIncludeTokens(final String includeTokens) {
        this.includeTokens = includeTokens;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(final String group) {
        this.group = group;
    }

    public String getGroupDelimiter() {
        return groupDelimiter;
    }

    public void setGroupDelimiter(final String groupDelimiter) {
        this.groupDelimiter = groupDelimiter;
    }

    public Boolean getSkipFirst() {
        return skipFirst;
    }

    public void setSkipFirst(final Boolean skipFirst) {
        this.skipFirst = skipFirst;
    }

    public Boolean getTrim() {
        return trim;
    }

    public void setTrim(final Boolean trim) {
        this.trim = trim;
    }
}
