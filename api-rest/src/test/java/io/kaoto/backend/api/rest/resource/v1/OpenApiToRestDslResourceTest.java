package io.kaoto.backend.api.rest.resource.v1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestHTTPEndpoint(OpenApiToRestDslResource.class)
class OpenApiToRestDslResourceTest {

    @Test
    void jsonToYaml() throws Exception {
        String json = Files.readString(Path.of(
                OpenApiToRestDslResourceTest.class.getResource(
                                "openapi-petstore.json")
                        .toURI()));

        var res = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .post("/")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        String answer = res.extract().body().asString();
        verifyYaml(answer);
    }

    @Test
    void yamlToYaml() throws Exception {
        String json = Files.readString(Path.of(
                OpenApiToRestDslResourceTest.class.getResource(
                                "openapi-petstore.yaml")
                        .toURI()));

        var res = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .post("/")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        String answer = res.extract().body().asString();
        verifyYaml(answer);
    }

    private void verifyYaml(String answer) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        var loader = new Yaml(new SafeConstructor(new LoaderOptions()));
        ArrayList list = loader.load(answer);
        var actual = (ArrayNode) mapper.convertValue(list, JsonNode.class);
        list = loader.load(Files.readString(Path.of(
                OpenApiToRestDslResourceTest.class.getResource(
                                "restdsl-petstore.yaml")
                        .toURI())));
        var expected = (ArrayNode) mapper.convertValue(list, JsonNode.class);
        assertThat(actual).isEqualTo(expected);
    }

}
