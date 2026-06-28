package ru.hypertheosx.qa.features.auth.ui;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import ru.hypertheosx.qa.shared.element.ButtonLabels;
import ru.hypertheosx.qa.shared.element.ElementFactory;
import ru.hypertheosx.qa.shared.element.InputLabels;

public class LoginPage {

    public SelenideElement usernameInput = ElementFactory.byDataTest(InputLabels.USERNAME);
    public SelenideElement passwordInput = ElementFactory.byDataTest(InputLabels.PASSWORD);
    public SelenideElement loginBtn = ElementFactory.byDataTest(ButtonLabels.LOGIN_BUTTON);

    private final SelenideElement loginError = $("[data-test='error']");

    public LoginPage withUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    public LoginPage withPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public void submitLogin() {
        loginBtn.click();
    }

    public String getErrorMessage() {
        return loginError.getText();
    }
}
