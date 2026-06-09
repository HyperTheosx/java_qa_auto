package ru.hypertheosx.qa.data;


import ru.hypertheosx.qa.config.AppConfig;
import ru.hypertheosx.qa.models.User;

import static ru.hypertheosx.qa.models.UserState.ACTIVE;
import static ru.hypertheosx.qa.models.UserState.LOCKED;

public final class TestUsers {

    public static User standardUser() {
        return new User(
                "standard_user",
                AppConfig.PASSWORD,
                ACTIVE
        );
    }

    public static User standardUser1() {
        return new User(
                "standard_user",
                AppConfig.PASSWORD,
                ACTIVE);
    }

    public static User standardUser2() {
        return new User(
                "standard_user",
                AppConfig.PASSWORD,
                ACTIVE);
    }

    public static User standardUser3() {
        return new User(
                "standard_user",
                AppConfig.PASSWORD,
                ACTIVE);
    }

    public static User standardUser4() {
        return new User(
                "standard_user",
                AppConfig.PASSWORD,
                ACTIVE);
    }

    public static User lockedOutUser() {
        return new User(
                "locked_out_user",
                AppConfig.PASSWORD,
                LOCKED);
    }

    public static User problemUser() {
        return new User(
                "problem_user",
                AppConfig.PASSWORD,
                ACTIVE);
    }

    public static User performanceGlitchUser() {
        return new User(
                "performance_glitch_user",
                AppConfig.PASSWORD,
                ACTIVE);
    }

    public static User errorUser() {
        return new User(
                "error_user",
                AppConfig.PASSWORD,
                ACTIVE);
    }

    public static User visualUser() {
        return new User(
                "visual_user",
                AppConfig.PASSWORD,
                ACTIVE);
    }
}
