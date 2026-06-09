package ru.hypertheosx.qa.tests.smoke;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.hypertheosx.qa.base.BaseTest;
import ru.hypertheosx.qa.models.ActiveUsersProvider;
import ru.hypertheosx.qa.models.LockedUsersProvider;
import ru.hypertheosx.qa.models.User;

@Tags({
        @Tag("smoke"),
        @Tag("regression")
})
@Owner("hypertheosx")
@Epic("Авторизация")
@Feature("Вход в систему")
@Story("Прямая авторизация через форму")
@DisplayName("Тесты авторизаций пользователей")

public class LoginTest extends BaseTest {

    @ParameterizedTest(name = "Вход под пользователем: {0}")
    @ArgumentsSource(ActiveUsersProvider.class)
    @Severity(SeverityLevel.BLOCKER)
    @Issue("TEST-001")
    @Description("Проверяем, что корректный пользователь имеет возможность авторизоваться в системе")
    @DisplayName("Успешная авторизация")
    public void successfulLoginTest(User user) {

        authService
                .loginAs(user)
                .shouldBeLoggedIn();
    }

    @ParameterizedTest(name = "Вход под пользователем: {0}")
    @ArgumentsSource(LockedUsersProvider.class)
    @Severity(SeverityLevel.BLOCKER)
    @Issue("TEST-002")
    @Description("Проверяем, что некорректный пользователь не имеет возможность авторизоваться в системе")
    @DisplayName("Ошибка авторизации заблокированного пользователя")
    public void failureLoginTest(User user) {

        authService
                .loginAs(user)
                .shouldHaveErrorMessage();
    }
}
