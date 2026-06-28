package ru.hypertheosx.qa.shared.container;

import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codeborne.selenide.Configuration;

public class BrowserContainer {

    private static final Logger log = LoggerFactory.getLogger(BrowserContainer.class);
    private static final String CONTAINER_ENABLED_PROP = "useContainer";

    private Object container;

    public void startIfEnabled() {
        if (Configuration.remote != null && !Configuration.remote.isEmpty()) {
            log.info("Browser container skipped — Selenoid remote already configured");
            return;
        }

        if (!Boolean.getBoolean(CONTAINER_ENABLED_PROP)) {
            log.info("Browser container disabled (use -D{}=true to enable)", CONTAINER_ENABLED_PROP);
            return;
        }

        log.info("Starting browser container...");
        try {
            startContainer();
            log.info("Browser container started");
        } catch (Exception e) {
            log.error("Failed to start browser container", e);
        }
    }

    private void startContainer() throws Exception {
        Class<?> containerClass = Class.forName("org.testcontainers.containers.BrowserWebDriverContainer");
        Class<?> recordingModeClass = Class.forName(
                "org.testcontainers.containers.BrowserWebDriverContainer$VncRecordingMode");

        container = containerClass.getDeclaredConstructor().newInstance();
        containerClass.getMethod("withCapabilities", Object.class)
                .invoke(container, new ChromeOptions());
        containerClass.getMethod("withRecordingMode", recordingModeClass, Object.class)
                .invoke(container, Enum.valueOf((Class<Enum>) recordingModeClass, "RECORD_ALL"), null);
        containerClass.getMethod("start").invoke(container);

        String seleniumAddress = (String) containerClass.getMethod("getSeleniumAddress")
                .invoke(container).toString();
        Configuration.remote = seleniumAddress;
    }

    public void stop() {
        if (container != null) {
            try {
                log.info("Stopping browser container...");
                container.getClass().getMethod("stop").invoke(container);
                container = null;
            } catch (Exception e) {
                log.error("Failed to stop browser container", e);
            }
        }
    }

    public boolean isRunning() {
        if (container == null) return false;
        try {
            return (boolean) container.getClass().getMethod("isRunning").invoke(container);
        } catch (Exception e) {
            return false;
        }
    }
}
