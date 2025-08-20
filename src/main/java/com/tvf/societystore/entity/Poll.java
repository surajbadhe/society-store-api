package com.tvf.societystore.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

//@Data
//@Entity
//@Table(name = "polls")
//public class Poll {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "society_id", nullable = false)
//    private Society society;
//
//    @Column(nullable = false)
//    private String question;
//
//    private LocalDateTime createdAt;
//
//    @Enumerated(EnumType.STRING)
//    private PollStatus status;
//
//    @OneToMany(mappedBy = "poll")
//    private List<PollVote> votes;
//}

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "polls")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who created the poll
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "society_id", nullable = false)
    private Society society;

    // New field for a user-typed product idea (e.g., "Tata Salt 1kg")
    @Column(nullable = false)
    private String productDescription;

    // New optional link to an existing product in the database
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime createdAt;
    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    private PollStatus status;

    @OneToMany(mappedBy = "poll")
    private List<PollVote> votes;

    // This will link to the bids submitted by suppliers for this poll
    @OneToMany(mappedBy = "poll")
    private List<SupplierBid> bids;
}
