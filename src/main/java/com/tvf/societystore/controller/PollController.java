package com.tvf.societystore.controller;

import com.tvf.societystore.dto.poll.PollDTO;
import com.tvf.societystore.dto.poll.PollRequest;
import com.tvf.societystore.service.PollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/polls")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;

    @PostMapping
    @PreAuthorize("hasAuthority('RESIDENT')")
    public ResponseEntity<PollDTO> createPoll(@Valid @RequestBody PollRequest request) {
        PollDTO createdPoll = pollService.createPoll(request);
        return new ResponseEntity<>(createdPoll, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('RESIDENT', 'ADMIN')")
    public ResponseEntity<List<PollDTO>> getSocietyPolls() {
        return ResponseEntity.ok(pollService.getPollsForMySociety());
    }

    @PostMapping("/{pollId}/vote")
    @PreAuthorize("hasAuthority('RESIDENT')")
    public ResponseEntity<Void> vote(@PathVariable Long pollId, @RequestParam boolean userVote) {
        pollService.voteOnPoll(pollId, userVote);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{pollId}/open-for-bidding")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> openForBidding(@PathVariable Long pollId) {
        pollService.openPollForBidding(pollId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{pollId}/select-bid/{bidId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> selectBid(@PathVariable Long pollId, @PathVariable Long bidId) {
        pollService.selectWinningBid(pollId, bidId);
        return ResponseEntity.ok().build();
    }
}