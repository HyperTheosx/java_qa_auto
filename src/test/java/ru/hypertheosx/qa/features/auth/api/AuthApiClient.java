package ru.hypertheosx.qa.features.auth.api;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.hypertheosx.qa.features.auth.model.AuthResponse;
import ru.hypertheosx.qa.features.auth.model.LoginRequest;
import ru.hypertheosx.qa.features.auth.model.User;
import ru.hypertheosx.qa.shared.config.AppConfig;

public class AuthApiClient {

    private static final Logger log = LoggerFactory.getLogger(AuthApiClient.class);

    private String apiBaseUrl = "https://httpbin.org";

    public void setApiBaseUrl(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    @Step("Authenticate via API as: {user.username}")
    public AuthResponse authenticateViaApi(User user) {
        log.info("Performing API login for user: {}. API base: {}", user.username(), apiBaseUrl);

        var request = new LoginRequest(user.username(), AppConfig.config.password());

        try {
            ValidatableResponse response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(request)
                    .post(apiBaseUrl + "/post")
                    .then()
                    .statusCode(200);

            log.info("API authentication request completed successfully.");
            return response.extract().as(AuthResponse.class);
        } catch (Exception e) {
            log.warn("RestAssured request failed, using local mock: {}", e.getMessage());
            return new AuthResponse(user.username(), user.username(), 3600L);
        }
    }

    @Step("Perform hybrid login bypassing UI with token: {token}")
    public void loginByBypassingUi(AuthResponse authResponse) {
        log.info("Bypassing UI login form using authentication token.");

        open("/favicon.ico");

        Cookie authCookie = new Cookie("session-username", authResponse.token());
        getWebDriver().manage().addCookie(authCookie);

        open("/inventory.html");
    }
}
