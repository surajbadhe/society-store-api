package com.tvf.societystore.mapper;

import com.tvf.societystore.dto.offer.OfferDTO;
import com.tvf.societystore.dto.offer.OfferTierDTO;
import com.tvf.societystore.entity.Offer;
import com.tvf.societystore.entity.OfferCommitment;
import com.tvf.societystore.entity.OfferTier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OfferMapper {

    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "product", source = "product") // Assuming you have a ProductMapper
    @Mapping(target = "currentQuantity", source = "offer", qualifiedByName = "calculateCurrentQuantity")
    @Mapping(target = "currentTier", source = "offer", qualifiedByName = "calculateCurrentTier")
    OfferDTO toDto(Offer offer);

    OfferTierDTO toDto(OfferTier offerTier);

    @Named("calculateCurrentQuantity")
    default int calculateCurrentQuantity(Offer offer) {
        if (offer.getCommitments() == null) {
            return 0;
        }
        return offer.getCommitments().stream()
                .mapToInt(OfferCommitment::getQuantity)
                .sum();
    }

    @Named("calculateCurrentTier")
    default OfferTierDTO calculateCurrentTier(Offer offer) {
        if (offer.getCommitments() == null || offer.getPriceTiers() == null) {
            return null;
        }
        int currentQuantity = calculateCurrentQuantity(offer);

        return offer.getPriceTiers().stream()
                .sorted(Comparator.comparing(OfferTier::getMinQuantity).reversed())
                .filter(tier -> currentQuantity >= tier.getMinQuantity())
                .findFirst()
                .map(this::toDto)
                .orElse(null);
    }
}