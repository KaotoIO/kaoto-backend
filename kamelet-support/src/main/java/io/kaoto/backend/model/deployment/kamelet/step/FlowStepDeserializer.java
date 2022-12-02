package io.kaoto.backend.model.deployment.kamelet.step;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kaoto.backend.model.deployment.kamelet.FlowStep;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

public class FlowStepDeserializer extends JsonDeserializer {
    private final Logger log = Logger.getLogger(FlowStepDeserializer.class);

    @Override
    public Object deserialize(
            final JsonParser jsonParser,
            final DeserializationContext deserializationContext) {
        try {
            JsonNode n = jsonParser.getCodec().readTree(jsonParser);
            return deserializeFlowStep(n);
        } catch (Exception e) {
            log.error("Error trying to deserialize step: " + e.getMessage());
        }

        return new UriFlowStep();
    }

    public FlowStep deserializeFlowStep(final JsonNode n) throws JsonProcessingException {
        for (var step : getFlowSteps().entrySet()) {
            if (n.get(step.getKey()) != null) {
                return (FlowStep) new ObjectMapper().readValue(n.toPrettyString(), step.getValue());
            }
        }

        return new UriFlowStep();
    }

    private Map<String, Class> getFlowSteps() {
        Map<String, Class> steps = new HashMap<>();

        steps.put("aggregate", AggregateFlowStep.class);
        steps.put("claim-check", ClaimCheckFlowStep.class);
        steps.put("claimCheck", ClaimCheckFlowStep.class);
        steps.put("circuitBreaker", CircuitBreakerFlowStep.class);
        steps.put("circuit-breaker", CircuitBreakerFlowStep.class);
        steps.put("choice", ChoiceFlowStep.class);
        steps.put("convertBodyTo", ConvertBodyToFlowStep.class);
        steps.put("convert-body-to", ConvertBodyToFlowStep.class);
        steps.put("delay", DelayFlowStep.class);
        steps.put("dynamic-router", DynamicRouterFlowStep.class);
        steps.put("dynamicRouter", DynamicRouterFlowStep.class);
        steps.put("enrich", EnrichFlowStep.class);
        steps.put("filter", FilterFlowStep.class);
        steps.put("from", From.class);
        steps.put("idempotent-consumer", IdempotentConsumerFlowStep.class);
        steps.put("idempotentConsumer", IdempotentConsumerFlowStep.class);
        steps.put("marshal", MarshalFlowStep.class);
        steps.put("load-balance", LoadBalanceFlowStep.class);
        steps.put("loadBalance", LoadBalanceFlowStep.class);
        steps.put("log", LogFlowStep.class);
        steps.put("loop", LoopFlowStep.class);
        steps.put("multicast", MulticastFlowStep.class);
        steps.put("recipient-list", RecipientListFlowStep.class);
        steps.put("recipientList", RecipientListFlowStep.class);
        steps.put("removeHeader", RemoveHeaderFlowStep.class);
        steps.put("remove-header", RemoveHeaderFlowStep.class);
        steps.put("remove-headers", RemoveHeadersFlowStep.class);
        steps.put("removeHeaders", RemoveHeadersFlowStep.class);
        steps.put("remove-properties", RemovePropertiesFlowStep.class);
        steps.put("removeProperties", RemovePropertiesFlowStep.class);
        steps.put("remove-property", RemovePropertyFlowStep.class);
        steps.put("removeProperty", RemovePropertyFlowStep.class);
        steps.put("resequence", ResequenceFlowStep.class);
        steps.put("rollback", RollbackFlowStep.class);
        steps.put("routing-slip", RoutingSlipFlowStep.class);
        steps.put("routingSlip", RoutingSlipFlowStep.class);
        steps.put("saga", SagaFlowStep.class);
        steps.put("sample", SampleFlowStep.class);
        steps.put("set-body", SetBodyFlowStep.class);
        steps.put("setBody", SetBodyFlowStep.class);
        steps.put("set-header", SetHeaderFlowStep.class);
        steps.put("setHeader", SetHeaderFlowStep.class);
        steps.put("set-property", SetPropertyFlowStep.class);
        steps.put("setProperty", SetPropertyFlowStep.class);
        steps.put("script", ScriptFlowStep.class);
        steps.put("split", SplitFlowStep.class);
        steps.put("pipeline", PipelineFlowStep.class);
        steps.put("poll-enrich", PollEnrichFlowStep.class);
        steps.put("pollEnrich", PollEnrichFlowStep.class);
        steps.put("process", ProcessFlowStep.class);
        steps.put("to", ToFlowStep.class);
        steps.put("transform", TransformFlowStep.class);
        steps.put("unmarshal", UnmarshalFlowStep.class);
        steps.put("uri", UriFlowStep.class);

        return steps;
    }
}
