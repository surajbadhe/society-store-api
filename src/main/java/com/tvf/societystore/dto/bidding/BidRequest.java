package com.tvf.societystore.dto.bidding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
/**
 * DTO for a supplier to submit a bid on a poll.
 */
public record BidRequest(
        @NotNull @Positive BigDecimal price,
        String terms
) {}
