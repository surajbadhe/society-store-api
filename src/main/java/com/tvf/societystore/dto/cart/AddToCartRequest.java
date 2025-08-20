package com.tvf.societystore.dto.cart;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for a user's request to add a specific product to their cart.
 */
public record AddToCartRequest(
        @NotNull Long productId,
        @NotNull @Min(1) int quantity
) {}
