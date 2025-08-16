package com.tvf.societystore.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "bulk_orders")
public class BulkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "society_id", nullable = false)
    private Society society;

    @Enumerated(EnumType.STRING)
    private BulkOrderStatus status;

    private LocalDateTime createdAt;
    private BigDecimal totalPrice;
    private BigDecimal discountApplied;

    @OneToMany(mappedBy = "bulkOrder", cascade = CascadeType.ALL)
    private List<BulkOrderItem> items;
}