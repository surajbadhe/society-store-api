package com.tvf.societystore.dto.cart;


import com.tvf.societystore.dto.product.ProductDTO;

/**
 * Represents a single item within a user's cart in the API response.
 */
public record CartItemResponse(
        Long cartItemId,
        ProductDTO product,
        int quantity
) {}
