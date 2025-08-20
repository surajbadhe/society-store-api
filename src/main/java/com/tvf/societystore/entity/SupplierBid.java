package com.tvf.societystore.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "supplier_bids")
public class SupplierBid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private User supplier; // A User with the WHOLESALER role

    @Column(nullable = false)
    private BigDecimal price; // The price per unit offered by the supplier

    private String terms; // e.g., "Delivery within 3 days"
    private LocalDateTime createdAt;
}
