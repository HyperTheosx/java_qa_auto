package ru.hypertheosx.qa.features.auth.data;

import net.datafaker.Faker;
import ru.hypertheosx.qa.features.auth.model.User;
import ru.hypertheosx.qa.features.auth.model.UserState;
import ru.hypertheosx.qa.features.auth.model.UserType;
import ru.hypertheosx.qa.shared.config.AppConfig;

public final class UserDataFactory {

    private static final Faker FAKER = new Faker();

    private UserDataFactory() {}

    public static User randomUser() {
        return User.builder()
                .username(FAKER.internet().username())
                .password(AppConfig.config.password())
                .userState(FAKER.options().option(UserState.class))
                .userType(FAKER.options().option(UserType.class))
                .build();
    }

    public static User randomActiveUser() {
        return User.builder()
                .username(FAKER.internet().username())
                .password(AppConfig.config.password())
                .userState(UserState.ACTIVE)
                .userType(FAKER.options().option(UserType.class))
                .build();
    }

    public static User randomUserWithType(UserType userType) {
        return User.builder()
                .username(FAKER.internet().username())
                .password(AppConfig.config.password())
                .userState(UserState.ACTIVE)
                .userType(userType)
                .build();
    }
}
