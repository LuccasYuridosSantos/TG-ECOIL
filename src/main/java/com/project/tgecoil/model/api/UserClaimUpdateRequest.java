package com.project.tgecoil.model.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserClaimUpdateRequest(@NotBlank String userId, @NotNull Boolean admin) {
}
