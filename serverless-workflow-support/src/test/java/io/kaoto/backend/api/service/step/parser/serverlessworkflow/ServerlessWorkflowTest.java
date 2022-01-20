package io.kaoto.backend.api.service.step.parser.serverlessworkflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.kaoto.backend.api.service.deployment.generator.serverlessworkflow.ServerlessWorkflowGeneratorService;
import io.kaoto.backend.api.service.step.parser.StepParserService;
import io.kaoto.backend.model.step.Step;
import io.quarkus.test.junit.QuarkusTest;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

@QuarkusTest
class ServerlessWorkflowTest {

    private ServerlessWorkflowStepParserService service;
    private ServerlessWorkflowGeneratorService generatorService;

    @Inject
    public void setService(final ServerlessWorkflowStepParserService service) {
        this.service = service;
    }

    @Inject
    public void setGeneratorService(
            final ServerlessWorkflowGeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @Test
        //  @ParameterizedTest(name = "{index} - {0}.yaml")
        //  @ValueSource(strings = {"solvemathproblems", "helloworld"})
    void parse()
            throws JSONException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

        String sourceCode = null;
        try {
            sourceCode = Files.readString(Path.of(
                    ServerlessWorkflowTest.class
                            .getResource("helloworld.yaml")
                            .toURI()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        StepParserService.ParseResult<Step> res = service.deepParse(sourceCode);
        final var generatedCode =
                generatorService.parse(res.getSteps(), res.getMetadata());

        final var expectedStr =
                objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(
                                yamlMapper.readValue(sourceCode, Object.class));

        final var actualStr =
                objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(
                                yamlMapper
                                        .readValue(generatedCode,
                                                Object.class));

        JSONAssert.assertEquals(
                expectedStr,
                actualStr,
                JSONCompareMode.LENIENT);

    }
}
