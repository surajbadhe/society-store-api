package com.tvf.societystore.dto;


import jakarta.validation.constraints.NotBlank;

/**
 * DTO for an admin to create a new poll.
 */
public record PollRequest(
        @NotBlank String question
) {}
