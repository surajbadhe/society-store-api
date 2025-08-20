package com.tvf.societystore.service;
import com.tvf.societystore.dto.notification.NotificationDTO;
import com.tvf.societystore.entity.User;
import com.tvf.societystore.repository.NotificationRepository;
import com.tvf.societystore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public List<NotificationDTO> getMyNotifications() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByEmail(userEmail).orElseThrow();

        return notificationRepository.findByUserId(currentUser.getId())
                .stream()
                .map(n -> new NotificationDTO(n.getId(), n.getMessage(), n.isRead(), n.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
