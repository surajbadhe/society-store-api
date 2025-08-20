package com.tvf.societystore.repository;

import com.tvf.societystore.entity.Poll;
import com.tvf.societystore.entity.Poll;
import com.tvf.societystore.entity.PollStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PollRepository extends JpaRepository<com.tvf.societystore.entity.Poll, Long> {

    List<Poll> findBySocietyId(Long societyId);

    List<Poll> findByStatus(PollStatus status);
}
