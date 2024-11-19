package com.project.tgecoil.model.api;

import jakarta.validation.constraints.NotBlank;

public record UserClaimRequest(@NotBlank String userId) {
}
