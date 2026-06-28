package ru.hypertheosx.qa.features.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginRequest(
        @JsonProperty("username") String username,
        @JsonProperty("password") String password
) {}
