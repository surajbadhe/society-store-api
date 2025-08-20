package com.tvf.societystore.service;

import com.tvf.societystore.dto.bidding.BidRequest;
import com.tvf.societystore.dto.bidding.BidResponse;
import com.tvf.societystore.dto.poll.PollDTO;
import java.util.List;

public interface BiddingService {
    List<PollDTO> getOpenPollsForBidding();
    BidResponse submitBid(Long pollId, BidRequest request);
}
