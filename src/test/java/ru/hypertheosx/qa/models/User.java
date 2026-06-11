package ru.hypertheosx.qa.models;

public record User(
        String username,
        String password,
        UserState userState,
        UserType userType
) {
    public boolean isActive() {
        return userState == UserState.ACTIVE;
    }

    public boolean isLocked() {
        return userState == UserState.LOCKED;
    }

    public boolean isStandard() {
        return userType == UserType.STANDARD;
    }

    public boolean isProblem() {
        return userType == UserType.PROBLEM;
    }

    public boolean isPerformanceProblem() {
        return userType == UserType.PERFORMANCE;
    }

    public boolean isErrorUser() {
        return userType == UserType.ERROR;
    }

    public boolean isVisualUser() {
        return userType == UserType.VISUAL;
    }
}
