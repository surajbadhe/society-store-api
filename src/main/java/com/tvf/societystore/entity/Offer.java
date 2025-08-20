package com.tvf.societystore.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private User supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // A supplier can target multiple societies with the same offer
    @ManyToMany
    @JoinTable(
            name = "offer_societies",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "society_id")
    )
    private List<Society> targetSocieties;

    private LocalDateTime deadline;
    private String status; // e.g., "ACTIVE", "CLOSED", "CANCELLED"

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<OfferTier> priceTiers;

    // 👇 ADD THIS FIELD 👇
    @OneToMany(mappedBy = "offer")
    private List<OfferCommitment> commitments;
}
