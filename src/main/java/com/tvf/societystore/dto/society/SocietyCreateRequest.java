package com.tvf.societystore.dto.society;

import jakarta.validation.constraints.NotBlank;

public record SocietyCreateRequest(
        @NotBlank String name,
        String address
) {}
