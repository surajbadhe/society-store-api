package com.tvf.societystore.dto.notification;

import java.time.LocalDateTime;

/**
 * DTO for displaying a notification to a user.
 */
public record NotificationDTO(
        Long id,
        String message,
        boolean isRead,
        LocalDateTime createdAt
) {}