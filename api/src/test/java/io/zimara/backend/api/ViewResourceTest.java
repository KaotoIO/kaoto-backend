package io.zimara.backend.api;

import io.quarkus.test.junit.QuarkusTest;
import io.zimara.backend.api.resource.ViewResource;
import io.zimara.backend.model.View;
import io.zimara.backend.model.view.IntegrationView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ViewResourceTest {
    private static String binding = "";

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        binding = Files.readString(Path.of(ViewResourceTest.class.getResource("twitter-search-source-binding.yaml").toURI()));
    }

    @Test
    void testViewEndpoint() throws InterruptedException {
        given()
                .when().queryParam("yaml", binding).get("/view")
                .then()
                .statusCode(200);
    }

    @Test
    void testParseYaml()  throws InterruptedException {
        //wait for catalog warm up
        while(!Catalog.isWarmedUp()) {
            Thread.sleep(200);
        }

        ViewResource vr = new ViewResource();
        List<View> views = vr.views(binding);

        Assertions.assertEquals(1, views.size());
        IntegrationView v = (IntegrationView) views.get(0);

        Assertions.assertEquals(2, v.getSteps().size());
        Assertions.assertEquals("twitter-search-source-binding", v.getName());
        Assertions.assertEquals("INTEGRATION", v.getType());
    }
}