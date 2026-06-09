package ru.hypertheosx.qa.models;

public record User(
        String username,
        String password,
        UserState userState
) {
    public boolean isActive() {
        return userState == UserState.ACTIVE;
    }

    public boolean isLocked() {
        return userState == UserState.LOCKED;
    }
}
