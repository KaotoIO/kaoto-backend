package io.kaoto.backend.api.resource.v1;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.fabric8.kubernetes.client.CustomResource;
import io.kaoto.backend.api.service.deployment.generator.DeploymentGeneratorService;
import io.kaoto.backend.api.service.deployment.generator.kamelet.KameletRepresenter;
import io.kaoto.backend.deployment.ClusterService;
import io.kaoto.backend.model.deployment.Integration;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.NoCache;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * 🐱class DeploymentsResource
 *
 * This endpoint will interact with the cluster starting, stopping, and listing
 * running resources.
 */
@Path("/v1/deployments")
@ApplicationScoped
public class DeploymentsResource {

    private Logger log = Logger.getLogger(DeploymentsResource.class);
    private ClusterService clusterService;
    private Instance<DeploymentGeneratorService> parsers;

    @Inject
    public void setClusterService(
            final ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    @Inject
    public void setParsers(final Instance<DeploymentGeneratorService> parsers) {
        this.parsers = parsers;
    }

    /*
     * 🐱method all: String
     * 🐱param namespace: String
     *
     * Returns the list of all resources
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    @Operation(summary = "Get all Resources",
            description = "Returns all the resources on the cluster.")
    public List<Integration> all(
            final @Parameter(description = "Namespace of the cluster where "
                    + "the resources are running.")
            @QueryParam("namespace") String namespace) {
        return clusterService.getIntegrations(namespace);
    }

    /*
     * 🐱method start: String
     * 🐱param crd: String
     * 🐱param namespace: String
     * 🐱param name: String
     *
     * Deploys a CRD provided.
     */
    @POST
    @Consumes("text/yaml")
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    @Operation(summary = "Start",
            description = "Deploy and start the given CRD"
                    + " on the cluster. Deployment will be done "
                    + "as a custom resource.")
    public String start(
            final @RequestBody String crd,
            final @Parameter(description = "Name of the resource to start.")
            @PathParam("name") String name,
            final @Parameter(description = "Namespace of the cluster where "
                    + "we want to deploy it.")
            @QueryParam("namespace") String namespace) {

        String securedcrd = securityCheck(crd);
        clusterService.start(securedcrd, namespace);
        return securedcrd;
    }

    private String securityCheck(final String crd) {

        ObjectMapper yamlMapper =
                new ObjectMapper(new YAMLFactory())
                    .configure(
                            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                            false);

        boolean valid = false;
        for (var parser : parsers) {
            for (Class<? extends CustomResource> c
                    : parser.supportedCustomResources()) {
                try {
                    yamlMapper.readValue(crd, c);
                    valid = true;
                } catch (Exception e) {
                    log.trace("We tried to parse with " + c.getName() + " and"
                            + " it didn't work.");
                }
            }
        }

        if (!valid) {
            throw new IllegalArgumentException("Couldn't understand the yaml "
                    + "sent. Check the syntax and try again.");
        }

        return crd;
    }

    /*
     * 🐱method get: String
     * 🐱param name: String
     * 🐱param namespace: String
     *
     * Returns the CRD of the running resource, if exists.
     *
     */
    @GET
    @Produces("text/yaml")
    @Path("/{name}")
    @Operation(summary = "Get CRD",
            description = "Returns the custom resource identified by name.")
    public String resource(
            final @Parameter(description = "Name of the resource to get.")
            @PathParam("name") String name,
            final @Parameter(description = "Namespace of the cluster where "
                    + "the resource is running.")
            @QueryParam("namespace") String namespace) {
        CustomResource cr = clusterService.get(namespace, name);
        if (cr == null) {
            throw new NotFoundException("Resource with name " + name + " not "
                    + "found.");
        }

        for (var parser : parsers) {
            for (Class<? extends CustomResource> c
                    : parser.supportedCustomResources()) {
                try {
                    Yaml yaml = new Yaml(new Constructor(c),
                            new KameletRepresenter());
                    return yaml.dumpAsMap(cr);
                } catch (Exception e) {
                    log.trace("We tried to parse with " + c.getName() + " and"
                            + " it didn't work.");
                }
            }
        }

        throw new NotFoundException("Unrecognized resource type with name "
                + name + ".");
    }


    /*
     * 🐱method stop: String
     * 🐱param name: String
     * 🐱param namespace: String
     *
     * Stops and deletes a running resource by name
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    @Operation(summary = "Stop/Remove",
            description = "Remove the resource identified by name.")
    public boolean stop(
            final @Parameter(description = "Name of the resource to stop.")
            @PathParam("name") String name,
            final @Parameter(description = "Namespace of the cluster where "
                    + "the resource is running.")
            @QueryParam("namespace") String namespace) {
        return clusterService.stop(name, namespace);
    }


    /*
     * 🐱method logs: String
     * 🐱param name: String
     * 🐱param namespace: String
     *
     * Returns a stream of the log
     */
    @GET
    @NoCache
    @Path("/{name}/logs")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Get logs",
            description = "Get the resource's log.")
    @Blocking
    public Multi<String> logs(
            final @Parameter(description = "Name of the resource "
                    + "of which logs should be retrieved.")
            @PathParam("name") String name,
            final @Parameter(description = "Namespace of the cluster "
                    + "where the resource is running.")
            @QueryParam("namespace") String namespace,
            final @Parameter(description = "Number of last N lines to be "
                    + "retrieved.")
            @QueryParam("lines") int lines) {

        return clusterService.streamlogs(namespace, name, lines);

    }

    @ServerExceptionMapper
    public Response mapException(final Exception x) {
        log.error("Error processing deployment.", x);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Error processing deployment: " + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

    @ServerExceptionMapper
    public Response mapException(final NotFoundException x) {
        log.error("Error processing deployment.", x);

        return Response.status(Response.Status.NOT_FOUND)
                .entity("Error processing deployment: " + x.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
