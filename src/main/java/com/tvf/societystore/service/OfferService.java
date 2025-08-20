package com.tvf.societystore.service;

import com.tvf.societystore.dto.offer.OfferCreateRequest;
import com.tvf.societystore.dto.offer.OfferDTO;
import java.util.List;

public interface OfferService {
    OfferDTO createOffer(OfferCreateRequest request);
    List<OfferDTO> getActiveOffersForMySociety();
    void joinOffer(Long offerId, int quantity);
}