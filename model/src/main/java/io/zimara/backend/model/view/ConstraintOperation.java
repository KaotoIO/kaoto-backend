package io.zimara.backend.model.view;

/**
 * 🐱miniclass ConstraintOperation (ViewDefinition)
 *
 * Type of constraint operation.
 *  CONTAINS_STEP_IDENTIFIER
 *  CONTAINS_STEP_NAME
 *  CONTAINS_STEP_TYPE
 *  SIZE_GREATER_THAN
 *  SIZE_SMALLER_THAN
 *  SIZE_EQUALS
 */
public enum ConstraintOperation {
    /**
     * 🐱option CONTAINS_STEP_IDENTIFIER: String ; True if the list of steps in the integration contains a step with this identifier.
     */
    CONTAINS_STEP_IDENTIFIER,
    /**
     * 🐱option CONTAINS_STEP_NAME: String ; True if the list of steps in the integration contains a step with this identifier.
     */
    CONTAINS_STEP_NAME,
    /**
     * 🐱option CONTAINS_STEP_TYPE: String ; True if the list of steps in the integration contains a step with this name.
     */
    CONTAINS_STEP_TYPE,
    /**
     * 🐱option SIZE_GREATER_THAN: String ; True if the size of the list of steps is greater than this number.
     */
    SIZE_GREATER_THAN,
    /**
     * 🐱option SIZE_SMALLER_THAN: String ; True if the size of the list of steps is smaller than this number.
     */
    SIZE_SMALLER_THAN,
    /**
     * 🐱option SIZE_EQUALS: String ; True if the size of the list of steps is equals to this number.
     */
    SIZE_EQUALS
}
