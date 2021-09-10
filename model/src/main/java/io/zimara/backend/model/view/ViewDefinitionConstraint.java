package io.zimara.backend.model.view;

/**
 * 🐱class ViewDefinitionConstraint
 * 🐱aka List[ViewDefinitionConstraint]
 *
 * Represents a constraint on when to use a view definition.
 */
public class ViewDefinitionConstraint {

    /*
     * 🐱property mandatory: boolean
     *
     * Is this a mandatory constraint or an optional?
     */
    private boolean mandatory = false;

    /*
     * 🐱property operation: ConstraintOperation
     *
     * Operation defining this constraint (contains, greater than,...)
     */
    private ConstraintOperation operation;

    /*
     * 🐱property parameter: String
     *
     * Parameter of the constraint (number of steps, identifier of step,...)
     */
    private String parameter;

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(final boolean mandatory) {
        this.mandatory = mandatory;
    }

    public ConstraintOperation getOperation() {
        return operation;
    }

    public void setOperation(final ConstraintOperation operation) {
        this.operation = operation;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(final String parameter) {
        this.parameter = parameter;
    }
}
