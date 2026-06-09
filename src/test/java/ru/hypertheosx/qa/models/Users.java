package ru.hypertheosx.qa.models;

import ru.hypertheosx.qa.data.UserFactory;

import java.util.List;
import java.util.stream.Stream;

public class Users {

    public static Stream<User> activeUsers() {
        return ALL_USERS.stream().filter(User::isActive);
    };

    public static Stream<User> lockedUsers() {
        return ALL_USERS.stream().filter(User::isLocked);
    };

    public static List<User> allUsers() {
        return ALL_USERS;
    };

    private static final List<User> ALL_USERS = List.of(
            UserFactory.standardUser(),
            UserFactory.lockedOutUser(),
            UserFactory.problemUser(),
            UserFactory.performanceGlitchUser(),
            UserFactory.errorUser(),
            UserFactory.visualUser()
    );

}
