package ru.hypertheosx.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static ru.hypertheosx.qa.components.elements.ButtonLabels.LOGIN_BUTTON;
import static ru.hypertheosx.qa.components.elements.ElementFactory.inputField;
import static ru.hypertheosx.qa.components.elements.InputLabels.PASSWORD;
import static ru.hypertheosx.qa.components.elements.InputLabels.USERNAME;

public class LoginPage {

    public SelenideElement usernameInput = inputField(USERNAME);
    public SelenideElement passwordInput = inputField(PASSWORD);
    public SelenideElement loginBtn = inputField(LOGIN_BUTTON);

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
