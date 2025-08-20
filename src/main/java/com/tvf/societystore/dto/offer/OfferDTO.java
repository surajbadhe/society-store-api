package com.tvf.societystore.dto.offer;

import com.tvf.societystore.dto.product.ProductDTO;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for displaying a detailed view of an active offer to residents.
 */
public record OfferDTO(
        Long id,
        ProductDTO product,
        Long supplierId,
        LocalDateTime deadline,
        String status,
        List<OfferTierDTO> priceTiers,
        // These fields will be calculated in the service layer before sending
        int currentQuantity,
        OfferTierDTO currentTier
) {}