package ru.hypertheosx.qa.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ru.hypertheosx.qa.config.AppConfig;
import ru.hypertheosx.qa.services.AuthService;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    protected final AuthService authService = new AuthService();

    @BeforeAll
    static void globalSetup() {

        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(false)
        );

        Configuration.baseUrl = AppConfig.BASE_URL;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 20000;
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
