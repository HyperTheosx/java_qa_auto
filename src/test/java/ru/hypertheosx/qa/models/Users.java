package ru.hypertheosx.qa.models;

import ru.hypertheosx.qa.data.TestUsers;

import java.util.List;
import java.util.stream.Stream;

public class Users {

    private Users() {
    }

    public static Stream<User> activeUsers() {
        return ALL_USERS.stream().filter(User::isActive);
    }

    public static Stream<User> lockedUsers() {
        return ALL_USERS.stream().filter(User::isLocked);
    }

    public static List<User> all() {
        return ALL_USERS;
    }

    public static Stream<User> standardUsers() {
        return ALL_USERS.stream().filter(User::isStandard);
    }

    public static Stream<User> problemUsers() {
        return ALL_USERS.stream().filter(User::isProblem);
    }

    public static Stream<User> performanceProblemsUsers() {
        return ALL_USERS.stream().filter(User::isPerformanceProblem);
    }

    public static Stream<User> errorUsers() {
        return ALL_USERS.stream().filter(User::isErrorUser);
    }

    public static Stream<User> visualUsers() {
        return ALL_USERS.stream().filter(User::isVisualUser);
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
