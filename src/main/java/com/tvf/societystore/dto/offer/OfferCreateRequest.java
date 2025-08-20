package com.tvf.societystore.dto.offer;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for a supplier to create a new tiered offer.
 */
public record OfferCreateRequest(
        @NotNull Long productId,
        @NotEmpty List<Long> targetSocietyIds,
        @NotNull @Future LocalDateTime deadline,
        @NotEmpty List<OfferTierDTO> priceTiers
) {}
