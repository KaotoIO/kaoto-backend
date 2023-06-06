package io.kaoto.backend.api.resource.support;

import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.jboss.logging.Logger;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static io.quarkus.bootstrap.util.IoUtils.readFile;

/**
 * Functions that help native testing to use the same functionality as JVM testing does with injection
 */
public class NativeHelper {

    private static final Logger log = Logger.getLogger(NativeHelper.class);


    public static void waitForWarmUpCatalog(String catalogClassName) {
        // when quarkus run native image before native tests, they set path to log to api/target/quarkus.log
        // and it cannot be overridden
        Path quarkusNativeLog = Paths.get("target", "quarkus.log");
        log.info("Using Quarkus log file from " + quarkusNativeLog);
        Awaitility.await()
                .timeout(Duration.ofSeconds(20))
                .pollDelay(Durations.ONE_SECOND)
                .dontCatchUncaughtExceptions()
                .until(() -> {
                    try {
                        return readFile(quarkusNativeLog).contains(
                                String.format("Catalog class %s_Subclass warmed up in", catalogClassName));
                    } catch (NoSuchFileException e) {
                        throw new IllegalStateException("The quarkus.log file doesn't exist in the api/target" +
                                " folder! This can happen when integration tests are executing against" +
                                " a running application. Usage NativeHelper in remote native testing mode" +
                                " is not implemented yet!");
                    }
                });
    }
}
