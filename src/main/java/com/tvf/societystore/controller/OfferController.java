package com.tvf.societystore.controller;

import com.tvf.societystore.dto.offer.OfferCreateRequest;
import com.tvf.societystore.dto.offer.OfferDTO;
import com.tvf.societystore.service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    @PreAuthorize("hasAuthority('WHOLESALER')")
    public ResponseEntity<OfferDTO> createOffer(@Valid @RequestBody OfferCreateRequest request) {
        OfferDTO createdOffer = offerService.createOffer(request);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('RESIDENT')")
    public ResponseEntity<List<OfferDTO>> getActiveOffers() {
        return ResponseEntity.ok(offerService.getActiveOffersForMySociety());
    }

    @PostMapping("/{offerId}/join")
    @PreAuthorize("hasAuthority('RESIDENT')")
    public ResponseEntity<Void> joinOffer(@PathVariable Long offerId, @RequestParam int quantity) {
        offerService.joinOffer(offerId, quantity);
        return ResponseEntity.ok().build();
    }
}
