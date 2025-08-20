package com.tvf.societystore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "offer_commitments")
public class OfferCommitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int quantity;

    private String paymentStatus; // e.g., "PENDING", "PAID"
}
