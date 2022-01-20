package io.kaoto.backend.model.parameter;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * 🐱miniclass StringParameter (Parameter)
 *
 */
@JsonTypeName("string")
public class StringParameter extends Parameter<String> {

    // string
    private String maxLength;
    private String minLength;
    private String pattern;
    private String format;

    public StringParameter(final String id, final String title,
                           final String description, final String v,
                           final String format) {
        super(id, title, description, v);
        this.setFormat(format);
    }

    public StringParameter() {
        super();
    }

    /*
     * 🐱property maxLength: Integer
     *
     * Maximum size of the string.
     */
    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(final String maxLength) {
        this.maxLength = maxLength;
    }

    /*
     * 🐱property minLength: Integer
     *
     * Minimum size of the string.
     */
    public String getMinLength() {
        return minLength;
    }

    public void setMinLength(final String minLength) {
        this.minLength = minLength;
    }

    /*
     * 🐱property pattern: Regex (String)
     *
     * Valid data must match this regular expression.
     */
    public String getPattern() {
        return pattern;
    }

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    /*
     * 🐱property format: String
     *
     * Valid data must match the format with this name. See input types on HTML.
     */
    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    @Override
    public String getType() {
        return "string";
    }
}
