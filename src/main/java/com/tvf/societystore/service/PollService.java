package com.tvf.societystore.service;

import com.tvf.societystore.dto.poll.PollDTO;
import com.tvf.societystore.dto.poll.PollRequest;
import java.util.List;

public interface PollService {
    PollDTO createPoll(PollRequest request);
    List<PollDTO> getPollsForMySociety();
    void voteOnPoll(Long pollId, boolean vote);
    void openPollForBidding(Long pollId);
    void selectWinningBid(Long pollId, Long bidId);
}
