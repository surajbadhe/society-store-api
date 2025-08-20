package com.tvf.societystore.controller;

import com.tvf.societystore.dto.bidding.BidRequest;
import com.tvf.societystore.dto.bidding.BidResponse;
import com.tvf.societystore.dto.poll.PollDTO;
import com.tvf.societystore.service.BiddingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bids")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('WHOLESALER')")
public class BiddingController {

    private final BiddingService biddingService;

    @GetMapping("/open-polls")
    public ResponseEntity<List<PollDTO>> getOpenPolls() {
        return ResponseEntity.ok(biddingService.getOpenPollsForBidding());
    }

    @PostMapping("/poll/{pollId}")
    public ResponseEntity<BidResponse> submitBid(@PathVariable Long pollId, @Valid @RequestBody BidRequest request) {
        BidResponse response = biddingService.submitBid(pollId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
