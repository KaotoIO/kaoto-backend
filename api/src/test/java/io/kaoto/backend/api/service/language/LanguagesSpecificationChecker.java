package io.kaoto.backend.api.service.language;

import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.Assertions;

import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LanguagesSpecificationChecker {


    /**
     * Check DSL specifications
     *
     * @param dsls - collection of all supported DSLs
     */
    public static void checkAllLanguagesSpecifications(Collection<Map<String, Object>> dsls) {
        assertThat(dsls)
                .as("Check all supported DSLs")
                .hasSize(4)
                .extracting(map -> String.valueOf(map.get("name")))
                .containsExactlyInAnyOrder("Integration", "Camel Route", "Kamelet", "KameletBinding");

        for (var language : dsls) {
            switch (String.valueOf(language.get("name"))) {
                case "KameletBinding" -> {
                    new LanguageSpecificationChecker(language)
                            .checkInput("true")
                            .checkOutput("true")
                            .checkDefault("true")
                            .checkValidationSchema("/v1/capabilities/KameletBinding/schema")
                            .checkDeployable("true")
                            .checkMultipleFlows("false")
                            .checkResourceDescription("true")
                            .checkDescription("Kamelet Bindings are used to create simple integrations" +
                                    " that link a start step to an end step with optional intermediate action steps.")
                            .checkStepKinds("[KAMELET, KNATIVE]")
                            .checkVocabulary(Map.of("stepsName", "Steps"));
                }
                case "Kamelet" -> {
                    new LanguageSpecificationChecker(language)
                            .checkInput("true")
                            .checkOutput("true")
                            .checkDefault(null)
                            .checkValidationSchema("/v1/capabilities/Kamelet/schema")
                            .checkDeployable("true")
                            .checkMultipleFlows("false")
                            .checkResourceDescription("true")
                            .checkDescription("A Kamelet is a snippet of a route. It defines meta" +
                                    " building blocks or steps that can be reused on integrations.")
                            .checkStepKinds("[CAMEL-CONNECTOR, EIP, EIP-BRANCH]")
                            .checkVocabulary(Map.of("stepsName", "Steps"));
                }
                case "Camel Route" -> {
                    new LanguageSpecificationChecker(language)
                            .checkInput("true")
                            .checkOutput("true")
                            .checkDefault(null)
                            .checkValidationSchema("/v1/capabilities/Camel Route/schema")
                            .checkDeployable("false")
                            .checkMultipleFlows("true")
                            .checkResourceDescription("false")
                            .checkDescription("A camel route is a non deployable in cluster workflow" +
                                    " of actions and steps.")
                            .checkStepKinds("[CAMEL-CONNECTOR, EIP, EIP-BRANCH, CAMEL-REST-DSL," +
                                    " CAMEL-REST-VERB, CAMEL-REST-ENDPOINT]")
                            .checkVocabulary(Map.of("stepsName", "Steps"));
                }
                case "Integration" -> {
                    new LanguageSpecificationChecker(language)
                            .checkInput("true")
                            .checkOutput("true")
                            .checkDefault(null)
                            .checkValidationSchema("/v1/capabilities/Integration/schema")
                            .checkDeployable("true")
                            .checkMultipleFlows("true")
                            .checkResourceDescription("true")
                            .checkDescription("An Integration defines a workflow of actions and steps.")
                            .checkStepKinds("[CAMEL-CONNECTOR, EIP, EIP-BRANCH]")
                            .checkVocabulary(Map.of("stepsName", "Steps"));
                }
                default -> Assertions.fail(
                        "Unrecognized DSL name '" + language.get("name") +
                                "', please provide test case for it in LanguageSpecificationChecker");
            }
        }
    }

    private static class LanguageSpecificationChecker {

        Map<String, Object> language;

        String languageName;

        LanguageSpecificationChecker(Map<String, Object> language) {
            this.language = language;
            this.languageName = String.valueOf(language.get("name"));
        }


        public LanguageSpecificationChecker checkInput(String desiredInput) {
            ObjectAssert<Object> assertion = assertThat(language.get("input"))
                    .as(String.format("Check input value in %s language specification", languageName));
            if (desiredInput == null) {
                assertion.isNull();
            } else {
                assertion.isNotNull().isEqualTo(desiredInput);
            }
            return this;
        }

        public LanguageSpecificationChecker checkOutput(String desiredOutput) {
            ObjectAssert<Object> assertion = assertThat(language.get("output"))
                    .as(String.format("Check output value in %s language specification", languageName));
            if (desiredOutput == null) {
                assertion.isNull();
            } else {
                assertion.isNotNull().isEqualTo(desiredOutput);
            }
            return this;
        }

        public LanguageSpecificationChecker checkDefault(String desireDefault) {
            ObjectAssert<Object> assertion = assertThat(language.get("default"))
                    .as(String.format("Check default value in %s language specification", languageName));
            if (desireDefault == null) {
                assertion.isNull();
            } else {
                assertion.isNotNull().isEqualTo(desireDefault);
            }
            return this;
        }

        public LanguageSpecificationChecker checkValidationSchema(String desiredSchema) {
            ObjectAssert<Object> assertion = assertThat(language.get("validationSchema"))
                    .as(String.format("Check validation schema value in %s language specification", languageName));
            if (desiredSchema == null) {
                assertion.isNull();
            } else {
                assertion.isNotNull().isEqualTo(desiredSchema);
            }
            return this;
        }

        public LanguageSpecificationChecker checkDeployable(String desiredDeployable) {
            ObjectAssert<Object> assertion = assertThat(language.get("deployable"))
                    .as(String.format("Check deployable value in %s language specification", languageName));
            if (desiredDeployable == null) {
                assertion.isNull();
            } else {
                assertion.isNotNull().isEqualTo(desiredDeployable);
            }
            return this;
        }

        public LanguageSpecificationChecker checkMultipleFlows(String desiredMultipleFlows) {
            ObjectAssert<Object> assertion = assertThat(language.get("supportsMultipleFlows"))
                    .as(String.format("Check supportsMultipleFlows value in %s language specification", languageName));
            if (desiredMultipleFlows == null) {
                assertion.isNull();
            } else {
                assertion.isNotNull().isEqualTo(desiredMultipleFlows);
            }
            return this;
        }

        public LanguageSpecificationChecker checkDescription(String desiredDescription) {
            ObjectAssert<Object> assertion = assertThat(language.get("description"))
                    .as(String.format("Check description value in %s language specification", languageName));
            if (desiredDescription == null) {
                assertion.isNull();
            } else {
                assertion.isNotNull().isEqualTo(desiredDescription);
            }
            return this;
        }

        public LanguageSpecificationChecker checkStepKinds(String desiredStepKinds) {
            ObjectAssert<Object> assertion = assertThat(language.get("stepKinds"))
                    .as(String.format("Check stepKinds value in %s language specification", languageName));
            if (desiredStepKinds == null) {
                assertion.isNull();
            } else {
                assertion.isNotNull().isEqualTo(desiredStepKinds);
            }
            return this;
        }

        public LanguageSpecificationChecker checkVocabulary(Map<String, String> desiredVocabulary) {
            ObjectAssert<Object> assertion = assertThat(language.get("vocabulary"))
                    .as(String.format("Check vocabulary value in %s language specification", languageName));
            if (desiredVocabulary == null) {
                assertion.isNull();
            } else {
                assertion.isNotNull()
                        .isInstanceOf(Map.class)
                        .isEqualTo(desiredVocabulary);
            }
            return this;
        }

        public LanguageSpecificationChecker checkResourceDescription(String desiredSupportsResourceDescription) {
            ObjectAssert<Object> assertion = assertThat(language.get("supportsResourceDescription"))
                    .as(String.format("Check supportsResourceDescription value in %s language specification",
                            languageName));
            if (desiredSupportsResourceDescription == null) {
                assertion.isNull();
            } else {
                assertion.isNotNull().isEqualTo(desiredSupportsResourceDescription);
            }
            return this;
        }
    }
}
