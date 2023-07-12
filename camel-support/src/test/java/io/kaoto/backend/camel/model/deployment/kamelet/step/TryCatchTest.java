package io.kaoto.backend.camel.model.deployment.kamelet.step;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.kaoto.backend.camel.model.deployment.kamelet.step.TryCatch;
import org.junit.jupiter.api.Test;

class TryCatchTest {

    @Test
    void noFinally() throws Exception {
        var tryCatch = new TryCatch(null, null, null, null, null, null);
        assertNotNull(tryCatch.getRepresenterProperties());
    }
}
