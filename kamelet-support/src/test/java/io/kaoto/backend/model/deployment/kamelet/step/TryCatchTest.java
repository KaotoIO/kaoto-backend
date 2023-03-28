package io.kaoto.backend.model.deployment.kamelet.step;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TryCatchTest {

    @Test
    void noFinally() throws Exception {
        var tryCatch = new TryCatch(null, null, null, null, null, null);
        assertNotNull(tryCatch.getRepresenterProperties());
    }
}
