package uz.softclub;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class StartUp {
    private static final Logger LOGGER = Logger.getLogger("StartUp");

    // Method called when the application is starting
    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The application is starting...");
    }

    // Method called when the application is terminating
    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }
}
