package com.tvf.societystore.dto.offer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
/**
 * DTO representing a single price tier in an offer.
 */
public record OfferTierDTO(
        @NotNull @Positive int minQuantity,
        @NotNull @Positive BigDecimal pricePerUnit
) {}