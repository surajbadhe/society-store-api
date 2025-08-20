package com.tvf.societystore.dto.poll;


import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * DTO for a resident to create a new poll.
 */
public record PollRequest(
        @NotBlank String productDescription,
        Long productId, // Optional: for linking to an existing product
        @NotNull @Future LocalDateTime deadline
) {}
