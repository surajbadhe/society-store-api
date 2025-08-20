package com.tvf.societystore.service.impl;

import com.tvf.societystore.dto.offer.OfferCreateRequest;
import com.tvf.societystore.dto.offer.OfferDTO;
import com.tvf.societystore.entity.*;
import com.tvf.societystore.exception.ResourceNotFoundException;
import com.tvf.societystore.mapper.OfferMapper;
import com.tvf.societystore.repository.*;
import com.tvf.societystore.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ProductRepository productRepository;
    private final SocietyRepository societyRepository;
    private final OfferCommitmentRepository offerCommitmentRepository;
    private final UserRepository userRepository;
    private final OfferMapper offerMapper; // Inject the mapper
    @Override
    @Transactional
    public OfferDTO createOffer(OfferCreateRequest request) {
        User currentUser = getCurrentUser();
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Offer offer = new Offer();
        offer.setSupplier(currentUser);
        offer.setProduct(product);
        offer.setDeadline(request.deadline());
        offer.setStatus("ACTIVE");

        List<Society> targetSocieties = societyRepository.findAllById(request.targetSocietyIds());
        offer.setTargetSocieties(targetSocieties);

        List<OfferTier> tiers = request.priceTiers().stream().map(tierDTO -> {
            OfferTier tier = new OfferTier();
            tier.setOffer(offer);
            tier.setMinQuantity(tierDTO.minQuantity());
            tier.setPricePerUnit(tierDTO.pricePerUnit());
            return tier;
        }).collect(Collectors.toList());
        offer.setPriceTiers(tiers);

        Offer savedOffer = offerRepository.save(offer);
        return mapToDTO(savedOffer);
    }

    @Override
    public List<OfferDTO> getActiveOffersForMySociety() {
        User currentUser = getCurrentUser();
        if (currentUser.getSociety() == null) {
            return List.of(); // Or throw an exception
        }
        Long societyId = currentUser.getSociety().getId();
        return offerRepository.findActiveOffersBySocietyId(societyId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void joinOffer(Long offerId, int quantity) {
        User currentUser = getCurrentUser();
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found"));

        // Add validation here to check if offer is active and user belongs to a target society

        OfferCommitment commitment = new OfferCommitment();
        commitment.setOffer(offer);
        commitment.setUser(currentUser);
        commitment.setQuantity(quantity);
        commitment.setPaymentStatus("PENDING");

        offerCommitmentRepository.save(commitment);
    }

    // You'll need to create this custom query in OfferRepository
    // @Query("SELECT o FROM Offer o JOIN o.targetSocieties s WHERE s.id = :societyId AND o.status = 'ACTIVE'")
    // List<Offer> findActiveOffersBySocietyId(@Param("societyId") Long societyId);

    private User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Current user not found"));
    }

    private OfferDTO mapToDTO(Offer offer) {
        // The implementation is now handled by MapStruct!
        return offerMapper.toDto(offer);
    }
}
