package com.tvf.societystore.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO for capturing user login credentials.
 * @param email The user's email.
 * @param password The user's password.
 */
public record LoginRequest(
        @NotBlank @Email String email,
        @NotBlank String password
) {}
