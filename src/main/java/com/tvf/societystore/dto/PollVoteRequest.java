package com.tvf.societystore.dto;

import jakarta.validation.constraints.NotNull;

/**
 * DTO for a resident to cast their vote.
 */
public record PollVoteRequest(
        @NotNull boolean vote // true for YES, false for NO
) {}
