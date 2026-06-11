package ru.hypertheosx.qa.data;


import ru.hypertheosx.qa.config.AppConfig;
import ru.hypertheosx.qa.models.User;
import ru.hypertheosx.qa.models.UserState;
import ru.hypertheosx.qa.models.UserType;

public final class TestUsers {

    public static User standardUser() {
        return new User(
                "standard_user",
                AppConfig.PASSWORD,
                UserState.ACTIVE,
                UserType.STANDARD
        );
    }

    public static User lockedOutUser() {
        return new User(
                "locked_out_user",
                AppConfig.PASSWORD,
                UserState.LOCKED,
                UserType.LOCKED
        );
    }

    public static User problemUser() {
        return new User(
                "problem_user",
                AppConfig.PASSWORD,
                UserState.ACTIVE,
                UserType.PROBLEM
        );
    }

    public static User performanceGlitchUser() {
        return new User(
                "performance_glitch_user",
                AppConfig.PASSWORD,
                UserState.ACTIVE,
                UserType.PERFORMANCE
        );
    }

    public static User errorUser() {
        return new User(
                "error_user",
                AppConfig.PASSWORD,
                UserState.ACTIVE,
                UserType.ERROR
        );
    }

    public static User visualUser() {
        return new User(
                "visual_user",
                AppConfig.PASSWORD,
                UserState.ACTIVE,
                UserType.VISUAL);
    }
}
