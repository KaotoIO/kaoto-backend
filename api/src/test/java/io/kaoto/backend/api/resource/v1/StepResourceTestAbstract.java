package io.kaoto.backend.api.resource.v1;

import io.kaoto.backend.camel.metadata.parser.step.camelroute.CamelRestDSLParseCatalog;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.restassured.response.ValidatableResponse;
import jakarta.ws.rs.core.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

@TestHTTPEndpoint(StepResource.class)
public abstract class StepResourceTestAbstract {
    public static final String EXAMPLE_STEP_NAME = "infinispan-source";

    public static final int NUMBER_OF_STEPS = 1127;

    protected abstract void waitForWarmUpCatalog();

    @BeforeEach
    void setupTest() {
        waitForWarmUpCatalog();
    }

    @Test
    void getAll() {
        Step[] steps = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);

        assertThat(steps)
                .isNotEmpty()
                .extracting(Step::getKind)
                .as("Check that all kind of steps are available")
                .containsOnly("Camel-Connector", "Kamelet", "EIP", "EIP-BRANCH", "Knative",
                        "CAMEL-REST-ENDPOINT", "CAMEL-REST-DSL", "CAMEL-REST-VERB");

        assertThat(steps)
                .extracting(Step::getType)
                .as("Check that all types of steps are available")
                .containsOnly("START", "MIDDLE", "END");
    }

    private static Stream<Arguments> providedSupportedStepsForDslTest() {
        return Stream.of(
                Arguments.of("KameletBinding", new String[]{"Kamelet", "Knative"}),
                Arguments.of("Integration", new String[]{"Camel-Connector", "EIP", "EIP-BRANCH"}),
                Arguments.of("Kamelet", new String[]{"Camel-Connector", "EIP", "EIP-BRANCH"}),
                Arguments.of("Camel Route",
                        new String[]{"Camel-Connector", "EIP", "EIP-BRANCH", CamelRestDSLParseCatalog.CAMEL_REST_DSL,
                                CamelRestDSLParseCatalog.CAMEL_REST_VERB, CamelRestDSLParseCatalog.CAMEL_REST_ENDPOINT})
        );
    }

    @ParameterizedTest
    @MethodSource("providedSupportedStepsForDslTest")
    void getAllByDsl(String filterDsl, String[] supportedSteps) {
        Step[] steps = given()
                .when()
                .get("?dsl=" + filterDsl)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);
        assertThat(steps)
                .isNotEmpty()
                .extracting(Step::getKind)
                .as("Check that filter for DSL '" + filterDsl + "' returns only supported steps")
                .containsOnly(supportedSteps);
    }

    @Test
    void getAllByDslAll() {
        Step[] steps = given()
                .when()
                .get("?dsl=KameletBinding,Camel Route,Integration,Kamelet")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);
        assertThat(steps)
                .isNotEmpty()
                .extracting(Step::getKind)
                .as("Check that filter for all DSL contains all type of steps")
                .containsOnly("Kamelet", "Knative", "Camel-Connector", "EIP", "EIP-BRANCH",
                        CamelRestDSLParseCatalog.CAMEL_REST_DSL, CamelRestDSLParseCatalog.CAMEL_REST_VERB,
                        CamelRestDSLParseCatalog.CAMEL_REST_ENDPOINT);

        Step[] allSteps = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);
        assertThat(steps)
                .as("Check that filter with all DSLs contains same steps as without any filter")
                .containsExactlyInAnyOrder(allSteps);
    }

    @ParameterizedTest
    @ValueSource(strings = {"START", "MIDDLE", "END"})
    void getAllByType(String type) {
        Step[] steps = given()
                .when()
                .get("?type=" + type)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);
        assertThat(steps)
                .isNotEmpty()
                .extracting(Step::getType)
                .as("Check that filter for type '" + type + "' returns only expected steps")
                .containsOnly(type);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Camel-Connector", "Kamelet", "EIP", "EIP-BRANCH", "Knative",
            "CAMEL-REST-ENDPOINT", "CAMEL-REST-DSL", "CAMEL-REST-VERB"})
    void getAllByKind(String kind) {
        Step[] steps = given()
                .when()
                .get("?kind=" + kind)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);
        assertThat(steps)
                .isNotEmpty()
                .extracting(Step::getKind)
                .as("Check that filter for kind '" + kind + "' returns only expected steps")
                .containsOnly(kind);
    }


    @ParameterizedTest
    @ValueSource(strings = {
            //filter kind type
            "KameletBinding#Kamelet#START",
            // more kinds and types
            "KameletBinding#Kamelet,Knative#START,MIDDLE",
            // more dsls, however no kinds from that dsl were specified, no new step
            "KameletBinding,Kamelet#Knative#START",
            // kind from different dsl, return only which are explicitly specified
            "KameletBinding,Kamelet#Knative,EIP#START,MIDDLE"
    })
    void getAllCombineMultipleFiltersValid(String testCase) {
        String[] parameters = testCase.split("#");
        String dslsQuery = parameters[0];
        String kindsQuery = parameters[1];
        String types = parameters[2];

        Step[] steps = given()
                .when()
                .get(String.format("?dsl=%s&kind=%s&type=%s", dslsQuery, kindsQuery, types))
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);

        String[] expectKinds = (kindsQuery.contains(",")) ? kindsQuery.split(",") : new String[]{kindsQuery};
        assertThat(steps)
                .isNotEmpty()
                .extracting(Step::getKind)
                .as("Check that endpoint returns steps for explicitly specified DSLs '" + dslsQuery + "' and" +
                        " only those which match the 'kind' query param '" + parameters[1] + "'")
                .containsOnly(expectKinds);

        String[] expectTypes = (types.contains(",")) ? types.split(",") : new String[]{types};
        assertThat(steps)
                .isNotEmpty()
                .extracting(Step::getType)
                .as("Check that endpoint returns steps for explicitly specified DSLs '" + dslsQuery + "' and" +
                        " only those which match the 'type' query param '" + parameters[2] + "'")
                .containsOnly(expectTypes);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "KameletBinding#EIP",
            "Kamelet#Knative",
            "KameletBinding,Kamelet,Integration#" + CamelRestDSLParseCatalog.CAMEL_REST_DSL
    })
    void getAllCombineMultipleFiltersNoMatch(String testCase) {
        String[] parameters = testCase.split("#");
        String dslsQuery = parameters[0];
        String kindsQuery = parameters[1];
        Step[] steps = given()
                .when()
                .get(String.format("?dsl=%s&kind=%s", dslsQuery, kindsQuery))
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);
        assertThat(steps).isEmpty();
    }

    @Test
    void getAllLimit() {
        Step[] allSteps = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);

        Step[] limitSteps = given()
                .when()
                .get("?limit=" + 10)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);

        assertThat(limitSteps).isNotEmpty().hasSize(10);
        assertThat(limitSteps).isSubsetOf(allSteps);

        limitSteps = given()
                .when()
                .get("?limit=" + 100000)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);
        assertThat(limitSteps).hasSameSizeAs(allSteps);
    }

    @Test
    void getAllStart() {
        Step[] allSteps = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);

        Step[] subSetSteps = given()
                .when()
                .get("?start=" + 10)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);

        assertThat(subSetSteps).isNotEmpty().hasSize(allSteps.length - 10);
        assertThat(subSetSteps).isSubsetOf(allSteps);

        subSetSteps = given()
                .when()
                .get("?start=" + allSteps.length + 1)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);
        assertThat(subSetSteps).isEmpty();
    }

    @Test
    void getAllLimitAndStart() {
        Step[] allSteps = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);

        int maxStepByRequest = 100;
        // Get all steps sequentially by subsets
        for (int startIndex = 0; startIndex < allSteps.length; startIndex += maxStepByRequest) {
            Step[] subSetSteps = given()
                    .when()
                    .get(String.format("?limit=%s&start=%s", maxStepByRequest, startIndex))
                    .then()
                    .statusCode(Response.Status.OK.getStatusCode())
                    .extract().body().as(Step[].class);

            assertThat(subSetSteps).hasSizeLessThanOrEqualTo(maxStepByRequest); //last one can have fewer steps
            assertThat(subSetSteps)
                    .as("Check that returned subset array is the same as the subset array from all steps array")
                    .containsExactly(getParticularSubSet(allSteps, startIndex, maxStepByRequest));
        }
    }

    private Step[] getParticularSubSet(Step[] originalArray, int startIndex, int range) {
        // Make sure that range after start index is not out of array range
        int finishIndex = Math.min(startIndex + range, originalArray.length);
        return Arrays.copyOfRange(originalArray, startIndex, finishIndex);
    }

    @Test
    void getByName() {
        Step[] steps = given()
                .when()
                .get("/name/" + EXAMPLE_STEP_NAME)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);
        assertThat(steps).isNotNull().isNotEmpty().hasSize(1);
        assertThat(steps[0].getName()).isEqualTo(EXAMPLE_STEP_NAME);

        steps = given()
                .when()
                .get("/name/log")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);
        assertThat(steps).isNotNull().isNotEmpty().hasSize(3);
        assertThat(steps).allMatch(step -> step.getName().contains("log"));
    }

    @Test
    void getByNameInvalid() {
        Step[] steps = given()
                .when()
                .get("/name/nonexistent-step")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);
        assertThat(steps).isNotNull().isEmpty();
    }

    @Test
    void getById() {
        Step step = given()
                .when()
                .get("/id/" + EXAMPLE_STEP_NAME + "-START")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step.class);

        assertThat(step).isNotNull();
        assertThat(step.getName()).isEqualTo(EXAMPLE_STEP_NAME);
        assertThat(step.getId()).isEqualTo(EXAMPLE_STEP_NAME + "-START");
    }

    @Test
    void getByIdInvalid() {
        given()
                .when()
                .get("/id/nonexistent-step")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    void checkAllRequiredPropertiesInitialized() {
        Step[] steps = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);

        assertThat(steps)
                .allMatch(step -> step.getRequired() != null);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "?dsl=KameletBinding,Camel Route,Integration,Kamelet",
            "?dsl=KameletBinding,Camel Route,Integration,Kamelet&kind=Camel-Connector",
            "?dsl=KameletBinding,Camel Route,Integration,Kamelet&kind=Camel-Connector&type=MIDDLE,START,END"
    })
    void getAllResponseTimeTest(String queryParams) {
        ValidatableResponse response = given()
                .when()
                .get(queryParams)
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
        response.time(Matchers.lessThan(2L), SECONDS);

        Step[] steps = response.extract().body().as(Step[].class);
        assertThat(steps).isNotEmpty();
    }

    @Test
    void allStepsCheckNumber() {
        Step[] steps = given()
                .when()
                .get()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().body().as(Step[].class);

        assertThat(steps).isNotNull().isNotEmpty()
                .as("Check that API return all steps which are expected.")
                .withFailMessage(
                        "\nThe Number of steps that were returned by StepResource API '%s' is " +
                                "different as it was expected '%s'.\n" +
                                "Possible causes:" +
                                "\n- Some resources in application.yaml were updated (repository.step) and new steps " +
                                "could appear, so NUMBER_OF_STEPS constant needs to be updated accordingly." +
                                "\n- If only tests running in Native mode and only running locally(not in GitHub " +
                                "workflows) failed, check if you are not logged into cluster that contains " +
                                "different Kamelets" +
                                "\nAll steps returned by StepResource API:\n[%s]", steps.length, NUMBER_OF_STEPS,
                        Arrays.stream(steps).map(Step::getId).collect(Collectors.joining("\n")))
                .hasSize(NUMBER_OF_STEPS);
    }
}
