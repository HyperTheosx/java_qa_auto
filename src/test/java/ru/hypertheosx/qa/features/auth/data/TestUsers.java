package ru.hypertheosx.qa.features.auth.data;

import ru.hypertheosx.qa.features.auth.model.User;
import ru.hypertheosx.qa.features.auth.model.UserState;
import ru.hypertheosx.qa.features.auth.model.UserType;
import ru.hypertheosx.qa.shared.config.AppConfig;

public final class TestUsers {

    private TestUsers() {}

    public static User standardUser() {
        return User.builder()
                .username("standard_user")
                .password(AppConfig.config.password())
                .userState(UserState.ACTIVE)
                .userType(UserType.STANDARD)
                .build();
    }

    public static User lockedOutUser() {
        return User.builder()
                .username("locked_out_user")
                .password(AppConfig.config.password())
                .userState(UserState.LOCKED)
                .userType(UserType.LOCKED)
                .build();
    }

    public static User problemUser() {
        return User.builder()
                .username("problem_user")
                .password(AppConfig.config.password())
                .userState(UserState.ACTIVE)
                .userType(UserType.PROBLEM)
                .build();
    }

    public static User performanceGlitchUser() {
        return User.builder()
                .username("performance_glitch_user")
                .password(AppConfig.config.password())
                .userState(UserState.ACTIVE)
                .userType(UserType.PERFORMANCE)
                .build();
    }

    public static User errorUser() {
        return User.builder()
                .username("error_user")
                .password(AppConfig.config.password())
                .userState(UserState.ACTIVE)
                .userType(UserType.ERROR)
                .build();
    }

    public static User visualUser() {
        return User.builder()
                .username("visual_user")
                .password(AppConfig.config.password())
                .userState(UserState.ACTIVE)
                .userType(UserType.VISUAL)
                .build();
    }
}
