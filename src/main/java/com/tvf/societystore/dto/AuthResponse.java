package com.tvf.societystore.dto;

/**
 * A simple DTO to send the JWT back to the client after a successful login or registration.
 */
public record AuthResponse(String token) {}
