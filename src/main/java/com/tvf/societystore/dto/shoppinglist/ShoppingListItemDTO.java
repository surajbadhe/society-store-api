package com.tvf.societystore.dto.shoppinglist;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for an item in a user's personal shopping list.
 */
public record ShoppingListItemDTO(
        Long id,
        @NotBlank String productDescription,
        @NotNull @Min(1) int quantity,
        String frequency // e.g., "WEEKLY", "MONTHLY"
) {}
