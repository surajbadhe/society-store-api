package com.tvf.societystore.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "offer_tiers")
public class OfferTier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @Column(nullable = false)
    private int minQuantity; // e.g., 100

    @Column(nullable = false)
    private BigDecimal pricePerUnit; // e.g., 77.00
}