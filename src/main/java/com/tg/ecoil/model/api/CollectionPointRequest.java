package com.tg.ecoil.model.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CollectionPointRequest(
        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        @NotNull(message = "Latitude is required")
        @Pattern(regexp = "^-?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)$", message = "Invalid latitude")
        String latitude,

        @NotNull(message = "Longitude is required")
        @Pattern(regexp = "^-?((1[0-7]\\d(\\.\\d+)?|\\d{1,2}(\\.\\d+)?|180(\\.0+)?))$", message = "Invalid longitude")
        String longitude,

        @NotBlank(message = "Name is required")
        String name
) {}