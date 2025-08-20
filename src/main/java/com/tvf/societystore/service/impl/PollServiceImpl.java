package com.tvf.societystore.service.impl;

import com.tvf.societystore.dto.poll.PollDTO;
import com.tvf.societystore.dto.poll.PollRequest;
import com.tvf.societystore.entity.*;
import com.tvf.societystore.exception.ResourceNotFoundException;
import com.tvf.societystore.repository.*;
import com.tvf.societystore.service.PollService;
import com.tvf.societystore.entity.Poll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;
    private final PollVoteRepository pollVoteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public PollDTO createPoll(PollRequest request) {
        User currentUser = getCurrentUser();
        Poll poll = new Poll();
        poll.setCreatedBy(currentUser);
        poll.setSociety(currentUser.getSociety());
        poll.setProductDescription(request.productDescription());

        if (request.productId() != null) {
            Product product = productRepository.findById(request.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            poll.setProduct(product);
        }

        poll.setCreatedAt(LocalDateTime.now());
        poll.setStatus(PollStatus.OPEN);
        poll.setDeadline(request.deadline());

        Poll savedPoll = pollRepository.save(poll);
        return mapToDTO(savedPoll);
    }

    @Override
    public List<PollDTO> getPollsForMySociety() {
        User currentUser = getCurrentUser();
        return pollRepository.findBySocietyId(currentUser.getSociety().getId())
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void voteOnPoll(Long pollId, boolean vote) {
        User currentUser = getCurrentUser();
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll not found"));

        PollVote pollVote = new PollVote();
        pollVote.setPoll(poll);
        pollVote.setUser(currentUser);
        pollVote.setVote(vote);

        pollVoteRepository.save(pollVote);
    }

    @Override
    public void openPollForBidding(Long pollId) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll not found"));
        poll.setStatus(PollStatus.AWAITING_BIDS);
        pollRepository.save(poll);
    }

    @Override
    public void selectWinningBid(Long pollId, Long bidId) {
        // This is a simplified version. A real implementation would trigger
        // the creation of an OrderCommitment for each 'YES' voter.
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll not found"));
        poll.setStatus(PollStatus.CLOSED);
        // Additional logic to link the winning bid
        pollRepository.save(poll);
    }

    // You'll need to add findBySocietyId to PollRepository
    // public interface PollRepository extends JpaRepository<Poll, Long> {
    //     List<Poll> findBySocietyId(Long societyId);
    // }

    private User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    protected PollDTO mapToDTO(Poll poll) {
        if (poll == null) {
            return null;
        }

        // Logic to count the votes from the list of PollVote entities
        long yesVotes = poll.getVotes().stream().filter(PollVote::isVote).count();
        long noVotes = poll.getVotes().size() - yesVotes;

        return new PollDTO(
                poll.getId(),
                poll.getProductDescription(),
                poll.getProduct() != null ? poll.getProduct().getId() : null,
                poll.getStatus().name(),
                poll.getDeadline(),
                yesVotes,
                noVotes
        );
    }
}
