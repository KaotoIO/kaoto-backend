package io.kaoto.backend.api.resource.v1;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

@QuarkusIntegrationTest
class StepResourceIT {

    @Test
    void checkCatalogLoaded() throws Exception {
        ValidatableResponse response = RestAssured.given().get("/v1/steps").then().statusCode(200);
        
        String json = response.extract().body().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        Step[] steps = objectMapper.readValue(json, Step[].class);
        assertThat(steps).isNotEmpty();
        Condition<Step> conditionSourceKamelet = new Condition<>(value -> value.getName().contains("-source"),
                "Step highly probable generated from source from kamelet catalog ");
        Condition<Step> conditionSinkKamelet = new Condition<>(value -> value.getName().contains("-sink"),
                "Step highly probable generated from sink from kamelet catalog ");
        assertThat(steps)
            .haveAtLeast(10, conditionSourceKamelet)
            .haveAtLeast(10, conditionSinkKamelet);
    }
    
}
