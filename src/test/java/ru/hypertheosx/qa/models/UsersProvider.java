package ru.hypertheosx.qa.models;

import java.util.stream.Stream;

public class UsersProvider {

    public static Stream<User> allUsers() {
        return Users.all().stream();
    }

    public static Stream<User> activeUsers() {
        return Users.activeUsers();
    }

    public static Stream<User> lockedUsers() {
        return Users.lockedUsers();
    }

    public static Stream<User> standardUsers() {
        return Users.standardUsers();
    }

    public static Stream<User> problemUsers() {
        return Users.problemUsers();
    }

    public static Stream<User> performanceUsers() {
        return Users.performanceProblemsUsers();
    }

    public static Stream<User> errorUsers() {
        return Users.errorUsers();
    }

    public static Stream<User> visualUsers() {
        return Users.visualUsers();
    }

}
