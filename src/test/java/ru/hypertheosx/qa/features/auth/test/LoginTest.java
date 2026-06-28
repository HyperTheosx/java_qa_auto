package ru.hypertheosx.qa.features.auth.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import io.qameta.allure.*;
import ru.hypertheosx.qa.features.auth.assertion.UserAssert;
import ru.hypertheosx.qa.features.auth.model.ActiveUsersProvider;
import ru.hypertheosx.qa.features.auth.model.LockedUsersProvider;
import ru.hypertheosx.qa.features.auth.model.User;
import ru.hypertheosx.qa.shared.base.BaseTest;

@Tags({
        @Tag("smoke"),
        @Tag("regression")
})
@Owner("hypertheosx")
@Epic("Authorization")
@Feature("Login")
@Story("Direct login via form")
@DisplayName("User authorization tests")
public class LoginTest extends BaseTest {

    @ParameterizedTest(name = "Login as: {0}")
    @ArgumentsSource(ActiveUsersProvider.class)
    @Severity(SeverityLevel.BLOCKER)
    @Issue("JIRA-001")
    @Description("Verify active user can log in successfully")
    @DisplayName("Successful login")
    public void successfulLoginTest(User user) {
        authWorkflow
                .loginAs(user)
                .shouldBeOpenProductsPage();

        UserAssert.assertThat(user).isActive();
    }

    @ParameterizedTest(name = "Login as: {0}")
    @ArgumentsSource(LockedUsersProvider.class)
    @Severity(SeverityLevel.BLOCKER)
    @Issue("JIRA-002")
    @Description("Verify locked user cannot log in")
    @DisplayName("Locked user login failure")
    public void failureLoginTest(User user) {
        authWorkflow
                .loginAs(user)
                .shouldHaveErrorMessage();

        UserAssert.assertThat(user).isLocked();
    }
}
