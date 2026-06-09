package ru.hypertheosx.qa.models;

import ru.hypertheosx.qa.data.UserFactory;

import java.util.List;
import java.util.stream.Stream;

import static ru.hypertheosx.qa.models.UserState.ACTIVE;

public class UserProvider {

    public static Stream<User> activeUsers() {
        return ALL_USERS.stream().filter(user -> user.userState() == ACTIVE);
    }

    public static Stream<User> lockedUsers() {
        return ALL_USERS.stream().filter(user -> user.userState() != ACTIVE);
    }

    private static final List<User> ALL_USERS = List.of(
            UserFactory.standardUser(),
            UserFactory.standardUser1(),
            UserFactory.standardUser2(),
            UserFactory.standardUser3(),
            UserFactory.standardUser4(),
            UserFactory.lockedOutUser(),
            UserFactory.problemUser(),
            UserFactory.performanceGlitchUser(),
            UserFactory.errorUser(),
            UserFactory.visualUser()
    );



}
