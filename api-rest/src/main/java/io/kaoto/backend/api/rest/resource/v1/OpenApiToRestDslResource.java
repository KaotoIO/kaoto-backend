package io.kaoto.backend.api.rest.resource.v1;

import java.util.Map;

import org.apache.camel.generator.openapi.RestDslGenerator;
import org.apache.camel.quarkus.core.CamelRuntime;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.openapi.models.OasDocument;
import io.quarkus.arc.Arc;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/v1/rest/openApiToRest")
@ApplicationScoped
@OpenAPIDefinition(
        info = @Info(
                title = "Open API REST DSL generator API",
                version = "1.0.0",
                description =  "This API consumes Open API specification "
                        + "and generate Camel REST DSL.",
                contact = @Contact(
                        name = "Kaoto Team",
                        url = "https://kaoto.io"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class OpenApiToRestDslResource {

    private Logger log = Logger.getLogger(OpenApiToRestDslResource.class);

    @POST
    @Path("/")
    @Produces({"text/yaml", MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, "text/yaml"})
    @Operation(summary = "Generate REST DSL from Open API specification",
            description = "Consume Open API specification and generate Camel REST DSL in YAML format.")
    public String generate(
            final @RequestBody String spec,
            final @Parameter(description = "Camel Rest DSL format, either YAML or XML. "
                    + "It assumes YAML if not specified.", example = "xml")
            @QueryParam("output") String output
            ) throws Exception {
        var runtime = Arc.container().instance(CamelRuntime.class).get();
        var camelContext = runtime.getCamelContext();
        var jacksonNode = readOpenApiSpec(spec);
        OasDocument specDoc = (OasDocument) Library.readDocument(jacksonNode);
        return "xml".equalsIgnoreCase(output)
                ? RestDslGenerator.toXml(specDoc).generate(camelContext)
                : RestDslGenerator.toYaml(specDoc).generate(camelContext);
    }

    private JsonNode readOpenApiSpec(final String input) {
        var mapper = new ObjectMapper();
        try {
            return mapper.readTree(input);
        } catch (Exception e) {
            log.debug("Failed to parse input as JSON, trying YAML", e);
            Yaml loader = new Yaml(new SafeConstructor(new LoaderOptions()));
            Map map = loader.load(input);
            return mapper.convertValue(map, JsonNode.class);
        }
    }

}
