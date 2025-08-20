package com.tvf.societystore.dto.poll;
import java.time.LocalDateTime;

/**
 * DTO for displaying a poll's details, including vote counts.
 */
public record PollDTO(
        Long id,
        String productDescription,
        Long productId,
        String status,
        LocalDateTime deadline,
        long yesVotes,
        long noVotes
) {}