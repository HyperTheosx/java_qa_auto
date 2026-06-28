package ru.hypertheosx.qa.features.auth.workflow;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

import com.codeborne.selenide.Condition;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import ru.hypertheosx.qa.features.auth.model.User;
import ru.hypertheosx.qa.features.auth.ui.LoginPage;
import ru.hypertheosx.qa.features.cart.ui.ProductsPage;
import ru.hypertheosx.qa.shared.config.AppConfig;

public class AuthWorkflow {

    private final ProductsPage productsPage;
    private final LoginPage loginPage;

    public AuthWorkflow(LoginPage loginPage, ProductsPage productsPage) {
        this.loginPage = loginPage;
        this.productsPage = productsPage;
    }

    @Step("Login as: {user.username}")
    public AuthWorkflow loginAs(User user) {
        Allure.step("Open web application", () -> open(""));

        loginPage
                .withUsername(user.username())
                .withPassword(AppConfig.config.password())
                .submitLogin();

        return this;
    }

    @Step("Verify products page is displayed")
    public void shouldBeOpenProductsPage() {
        productsPage.productPageTitle
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Products"));
    }

    @Step("Verify error message is displayed")
    public void shouldHaveErrorMessage() {
        String expectedMessage = "Epic sadface: Sorry, this user has been locked out.";
        assertThat(loginPage.getErrorMessage()).isEqualTo(expectedMessage);
    }
}
