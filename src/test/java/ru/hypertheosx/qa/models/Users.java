package ru.hypertheosx.qa.models;

import ru.hypertheosx.qa.data.TestUsers;

import java.util.List;
import java.util.stream.Stream;

public class Users {

    private Users() {}

    public static Stream<User> activeUsers() {
        return ALL_USERS.stream().filter(User::isActive);
    }

    public static Stream<User> lockedUsers() {
        return ALL_USERS.stream().filter(User::isLocked);
    }

    public static List<User> all() {
        return ALL_USERS;
    }

    private static final List<User> ALL_USERS = List.of(
            TestUsers.standardUser(),
            TestUsers.lockedOutUser(),
            TestUsers.problemUser(),
            TestUsers.performanceGlitchUser(),
            TestUsers.errorUser(),
            TestUsers.visualUser()
    );

}
