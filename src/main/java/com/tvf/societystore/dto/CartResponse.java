package com.tvf.societystore.dto;

import java.util.List;

/**
 * Represents the user's entire cart, containing a list of cart items.
 */
public record CartResponse(
        Long userId,
        List<CartItemResponse> items
) {}
