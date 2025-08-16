package com.tvf.societystore.dto;


import java.math.BigDecimal;

/**
 * Represents a single aggregated item within a bulk order.
 */
public record BulkOrderItemResponse(
        Long productId,
        String productName,
        int totalQuantity,
        BigDecimal finalPrice
) {}
