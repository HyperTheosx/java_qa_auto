package ru.hypertheosx.qa.services;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import ru.hypertheosx.qa.config.AppConfig;
import ru.hypertheosx.qa.models.User;
import ru.hypertheosx.qa.pages.LoginPage;
import ru.hypertheosx.qa.pages.ProductsPage;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthService {

    private final ProductsPage productsPage = new ProductsPage();
    private final LoginPage loginPage = new LoginPage();

    @Step("Авторизация под пользователем: {user.username}")
    public AuthService loginAs(User user) {

        Allure.step("Открыть web-приложение", () -> {
            open("");
        });

        loginPage
                .withUsername(user.username())
                .withPassword(AppConfig.PASSWORD)
                .submitLogin();

        return this;

    }

    @Step("Проверка, что корректный пользователь авторизуется без ошибки и попадает на страницу Products")
    public void shouldBeOpenProductsPage() {
        productsPage.productPageTitle
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Products"));
    }

    @Step("Проверка, что отображается сообщение об ошибке при попытке авторизоваться некоррректным пользователем")
    public void shouldHaveErrorMessage() {
        String expectedMessage = "Epic sadface: Sorry, this user has been locked out.";
        assertThat(loginPage.getErrorMessage()).isEqualTo(expectedMessage);
    }
}
