package com.project.tgecoil.model.api;

import jakarta.validation.constraints.NotBlank;

public record UserClaimResponse(Long id, @NotBlank String userId, Boolean admin) {
}
