package com.tvf.societystore.dto;

import java.time.LocalDateTime;

/**
 * Represents a poll's details, including the voting results.
 */
public record PollResponse(
        Long id,
        String question,
        String status,
        LocalDateTime createdAt,
        long yesVotes,
        long noVotes
) {}
