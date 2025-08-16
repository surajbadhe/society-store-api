package com.tvf.societystore.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a full bulk order for a society.
 */
public record BulkOrderResponse(
        Long id,
        Long societyId,
        String status,
        LocalDateTime createdAt,
        BigDecimal totalPrice,
        BigDecimal discountApplied,
        List<BulkOrderItemResponse> items
) {}
