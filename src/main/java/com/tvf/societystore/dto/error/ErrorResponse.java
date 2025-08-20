package com.tvf.societystore.dto.error;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A standard DTO for sending error responses.
 * @param statusCode The HTTP status code.
 * @param timestamp The time the error occurred.
 * @param message A user-friendly error message.
 * @param details A list of specific validation errors, if any.
 */
public record ErrorResponse(
        int statusCode,
        LocalDateTime timestamp,
        String message,
        List<String> details
) {}