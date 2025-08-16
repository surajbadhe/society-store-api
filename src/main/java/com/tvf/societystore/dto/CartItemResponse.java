package com.tvf.societystore.dto;


/**
 * Represents a single item within a user's cart in the API response.
 */
public record CartItemResponse(
        Long cartItemId,
        ProductDTO product,
        int quantity
) {}
