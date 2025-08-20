package com.tvf.societystore.service.impl;

import com.tvf.societystore.dto.bidding.BidRequest;
import com.tvf.societystore.dto.bidding.BidResponse;
import com.tvf.societystore.dto.poll.PollDTO;
import com.tvf.societystore.entity.Poll;
import com.tvf.societystore.entity.PollStatus;
import com.tvf.societystore.entity.SupplierBid;
import com.tvf.societystore.entity.User;
import com.tvf.societystore.exception.ResourceNotFoundException;
import com.tvf.societystore.repository.PollRepository;
import com.tvf.societystore.repository.SupplierBidRepository;
import com.tvf.societystore.repository.UserRepository;
import com.tvf.societystore.service.BiddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BiddingServiceImpl implements BiddingService {

    private final PollRepository pollRepository;
    private final SupplierBidRepository supplierBidRepository;
    private final UserRepository userRepository;
    // You will need to inject PollServiceImpl to reuse its mapToDTO method,
    // or create a dedicated PollMapper.
    private final PollServiceImpl pollService;

    @Override
    public List<PollDTO> getOpenPollsForBidding() {
        return pollRepository.findByStatus(PollStatus.AWAITING_BIDS)
                .stream()
                .map(pollService::mapToDTO) // Reusing the mapping logic
                .collect(Collectors.toList());
    }

    @Override
    public BidResponse submitBid(Long pollId, BidRequest request) {
        User currentUser = getCurrentUser();
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll not found"));

        if (poll.getStatus() != PollStatus.AWAITING_BIDS) {
            throw new IllegalStateException("This poll is not open for bidding.");
        }

        SupplierBid bid = new SupplierBid();
        bid.setPoll(poll);
        bid.setSupplier(currentUser);
        bid.setPrice(request.price());
        bid.setTerms(request.terms());
        bid.setCreatedAt(LocalDateTime.now());

        SupplierBid savedBid = supplierBidRepository.save(bid);
        return mapToResponse(savedBid);
    }

    private User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private BidResponse mapToResponse(SupplierBid bid) {
        return new BidResponse(
                bid.getId(),
                bid.getPoll().getId(),
                bid.getSupplier().getId(),
                bid.getSupplier().getName(),
                bid.getPrice(),
                bid.getTerms(),
                bid.getCreatedAt()
        );
    }
}
