package com.tvf.societystore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * A general-purpose DTO for both creating and displaying products.
 */
public record ProductDTO(
        Long id,
        @NotBlank String name,
        String description,
        @NotBlank String unit,
        @NotNull @Positive BigDecimal price,
        Long wholesalerId
) {}
