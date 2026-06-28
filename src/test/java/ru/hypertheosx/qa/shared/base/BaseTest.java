package ru.hypertheosx.qa.shared.base;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import ru.hypertheosx.qa.features.auth.api.AuthApiClient;
import ru.hypertheosx.qa.features.auth.workflow.AuthWorkflow;
import ru.hypertheosx.qa.features.cart.ui.ProductsPage;
import ru.hypertheosx.qa.shared.config.AppConfig;
import ru.hypertheosx.qa.shared.config.TestConfig;
import ru.hypertheosx.qa.shared.dsl.TestScenario;
import ru.hypertheosx.qa.shared.extension.ExecutionLoggerExtension;
import ru.hypertheosx.qa.shared.extension.ScreenshotOnFailureExtension;

@ExtendWith({SpringExtension.class, ScreenshotOnFailureExtension.class, ExecutionLoggerExtension.class})
@ContextConfiguration(classes = TestConfig.class)
public class BaseTest {

    private static final AtomicBoolean configured = new AtomicBoolean(false);

    @Autowired
    protected AuthApiClient authApiClient;
    @Autowired
    protected AuthWorkflow authWorkflow;
    @Autowired
    private ProductsPage productsPage;

    @BeforeAll
    static void globalSetup() {
        if (!configured.compareAndSet(false, true)) {
            return;
        }

        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(false)
        );

        Configuration.baseUrl = AppConfig.config.baseUrl();
        Configuration.browser = AppConfig.config.browser();
        Configuration.browserSize = AppConfig.config.browserSize();
        Configuration.headless = AppConfig.config.headless();
        Configuration.timeout = AppConfig.config.timeout();
        Configuration.pageLoadTimeout = AppConfig.config.pageLoadTimeout();

        String remoteUrl = AppConfig.config.remoteUrl();
        if (!remoteUrl.isBlank()) {
            Configuration.remote = remoteUrl;
            configureRemoteCapabilities();
        }

        saveAllureEnvironment();
    }

    private static void configureRemoteCapabilities() {
        String browser = AppConfig.config.browser().toLowerCase();
        MutableCapabilities capabilities;

        if (browser.contains("firefox")) {
            capabilities = new FirefoxOptions();
        } else {
            capabilities = new ChromeOptions();
        }

        if (AppConfig.config.remoteVideoEnabled()) {
            capabilities.setCapability("enableVideo", true);
            capabilities.setCapability("videoFrameRate", 24);
        }
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("sessionTimeout", "5m");

        Configuration.browserCapabilities = capabilities;
    }

    private static void saveAllureEnvironment() {
        Properties properties = new Properties();
        properties.setProperty("OS", System.getProperty("os.name"));
        properties.setProperty("Java Version", System.getProperty("java.version"));
        properties.setProperty("Browser", AppConfig.config.browser());
        properties.setProperty("Headless Mode", String.valueOf(AppConfig.config.headless()));
        properties.setProperty("Base URL", AppConfig.config.baseUrl());

        String remoteUrl = AppConfig.config.remoteUrl();
        if (!remoteUrl.isBlank()) {
            properties.setProperty("Remote URL", remoteUrl);
        }

        try (FileOutputStream out = new FileOutputStream("build/allure-results/environment.properties")) {
            properties.store(out, "Allure Environment Properties");
        } catch (IOException e) {
            org.slf4j.LoggerFactory.getLogger(BaseTest.class)
                    .error("Could not write Allure environment file", e);
        }
    }

    protected TestScenario scenario() {
        return new TestScenario(authWorkflow, productsPage);
    }

    @AfterEach
    void tearDown() {
        try {
            if (hasWebDriverStarted()) {
                getWebDriver().manage().deleteAllCookies();
                try {
                    executeJavaScript("window.localStorage.clear(); window.sessionStorage.clear();");
                } catch (Exception ignored) {
                }
            }
        } finally {
            closeWebDriver();
        }
    }
}
