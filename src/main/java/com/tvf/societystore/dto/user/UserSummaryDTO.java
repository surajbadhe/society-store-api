package com.tvf.societystore.dto.user;

/**
 * A simplified DTO for displaying user information, e.g., in an admin's dashboard.
 */
public record UserSummaryDTO(
        Long id,
        String name,
        String email
) {}
