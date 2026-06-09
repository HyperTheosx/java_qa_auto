package ru.hypertheosx.qa.models;

public record User(
        String username,
        String password,
        UserState userState
) {
}
