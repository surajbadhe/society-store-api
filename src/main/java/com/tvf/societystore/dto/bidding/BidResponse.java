package com.tvf.societystore.dto.bidding;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for displaying a bid made by a supplier.
 */
public record BidResponse(
        Long id,
        Long pollId,
        Long supplierId,
        String supplierName,
        BigDecimal price,
        String terms,
        LocalDateTime createdAt
) {}