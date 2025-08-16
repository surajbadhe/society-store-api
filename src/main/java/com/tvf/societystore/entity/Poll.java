package com.tvf.societystore.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "society_id", nullable = false)
    private Society society;

    @Column(nullable = false)
    private String question;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private PollStatus status;

    @OneToMany(mappedBy = "poll")
    private List<PollVote> votes;
}
