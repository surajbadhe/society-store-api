package com.tvf.societystore.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO for a user's request to add a specific product to their cart.
 */
public record AddToCartRequest(
        @NotNull Long productId,
        @NotNull @Min(1) int quantity
) {}
