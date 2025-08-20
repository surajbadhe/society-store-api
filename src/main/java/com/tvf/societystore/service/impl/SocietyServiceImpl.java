package com.tvf.societystore.service.impl;

import com.tvf.societystore.dto.society.SocietyCreateRequest;
import com.tvf.societystore.dto.user.UserSummaryDTO;
import com.tvf.societystore.entity.Notification;
import com.tvf.societystore.entity.Society;
import com.tvf.societystore.entity.User;
import com.tvf.societystore.exception.ResourceNotFoundException;
import com.tvf.societystore.repository.NotificationRepository;
import com.tvf.societystore.repository.SocietyRepository;
import com.tvf.societystore.repository.UserRepository;
import com.tvf.societystore.service.SocietyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocietyServiceImpl implements SocietyService {

    private final UserRepository userRepository;
    private final SocietyRepository societyRepository;
    private final NotificationRepository notificationRepository;


    @Override
    public void requestToJoinSociety(Long societyId) {
        User currentUser = getCurrentUser();
        Society society = societyRepository.findById(societyId)
                .orElseThrow(() -> new ResourceNotFoundException("Society not found"));

        currentUser.setSociety(society);
        currentUser.setSocietyJoinStatus("PENDING");
        userRepository.save(currentUser);
    }

    @Override
    public List<UserSummaryDTO> getPendingJoinRequests() {
        User adminUser = getCurrentUser();
        Society adminSociety = adminUser.getSociety();
        if (adminSociety == null) {
            return List.of();
        }

        return userRepository.findBySocietyIdAndSocietyJoinStatus(adminSociety.getId(), "PENDING")
                .stream()
                .map(this::mapToUserSummaryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void approveJoinRequest(Long userId) {
        User userToApprove = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User to approve not found"));

        userToApprove.setSocietyJoinStatus("APPROVED");
        userRepository.save(userToApprove);

        // Create a notification for the approved user
        createNotification(userToApprove, "Your request to join " + userToApprove.getSociety().getName() + " has been approved!");
    }

    private User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Current user not found"));
    }

    private UserSummaryDTO mapToUserSummaryDTO(User user) {
        return new UserSummaryDTO(user.getId(), user.getName(), user.getEmail());
    }

    public Society createSociety(SocietyCreateRequest request) {
        User adminUser = getCurrentUser();

        Society society = new Society();
        society.setName(request.name());
        society.setAddress(request.address());
        society.setAdmin(adminUser);

        Society savedSociety = societyRepository.save(society);

        // Automatically link the admin to their new society
        adminUser.setSociety(savedSociety);
        adminUser.setSocietyJoinStatus("APPROVED");
        userRepository.save(adminUser);

        return savedSociety;
    }



    // Helper method for creating notifications
    private void createNotification(User user, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }
}
