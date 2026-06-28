package ru.hypertheosx.qa.features.auth.assertion;

import java.util.Objects;

import org.assertj.core.api.AbstractAssert;

import ru.hypertheosx.qa.features.auth.model.User;
import ru.hypertheosx.qa.features.auth.model.UserState;
import ru.hypertheosx.qa.features.auth.model.UserType;

public class UserAssert extends AbstractAssert<UserAssert, User> {

    public static UserAssert assertThat(User actual) {
        return new UserAssert(actual);
    }

    public UserAssert(User actual) {
        super(actual, UserAssert.class);
    }

    public UserAssert isActive() {
        isNotNull();
        if (!actual.isActive()) {
            failWithMessage("Expected user <%s> to be ACTIVE but was <%s>",
                    actual.username(), actual.userState());
        }
        return this;
    }

    public UserAssert isLocked() {
        isNotNull();
        if (!actual.isLocked()) {
            failWithMessage("Expected user <%s> to be LOCKED but was <%s>",
                    actual.username(), actual.userState());
        }
        return this;
    }

    public UserAssert hasUserType(UserType expected) {
        isNotNull();
        if (!Objects.equals(actual.userType(), expected)) {
            failWithMessage("Expected user <%s> to have type <%s> but was <%s>",
                    actual.username(), expected, actual.userType());
        }
        return this;
    }

    public UserAssert hasUserState(UserState expected) {
        isNotNull();
        if (actual.userState() != expected) {
            failWithMessage("Expected user <%s> to have state <%s> but was <%s>",
                    actual.username(), expected, actual.userState());
        }
        return this;
    }

    public UserAssert hasUsername(String expected) {
        isNotNull();
        if (!Objects.equals(actual.username(), expected)) {
            failWithMessage("Expected username <%s> but was <%s>",
                    expected, actual.username());
        }
        return this;
    }
}
