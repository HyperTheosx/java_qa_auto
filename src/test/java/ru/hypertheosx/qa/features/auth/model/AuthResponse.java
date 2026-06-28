package ru.hypertheosx.qa.features.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(
        @JsonProperty("token") String token,
        @JsonProperty("userId") String userId,
        @JsonProperty("expiresIn") Long expiresIn
) {}
