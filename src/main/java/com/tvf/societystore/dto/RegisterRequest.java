package com.tvf.societystore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for capturing new user registration details.
 */
public record RegisterRequest(
        @NotBlank @Size(min = 3) String name,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String password
) {}
