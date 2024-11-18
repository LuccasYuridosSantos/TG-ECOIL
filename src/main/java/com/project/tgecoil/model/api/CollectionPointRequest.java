package com.project.tgecoil.model.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CollectionPointRequest(
        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        @NotNull(message = "Latitude is required")
        @Pattern(
                regexp = "^([+\\-])?(?:90(?:\\.0{1,6})?|[1-8]?\\d(?:\\.\\d{1,6})?)$",
                message = "Invalid latitude, value permitted range 90 to -90"
        )
        String latitude,

        @NotNull(message = "Longitude is required")
        @Pattern(
                regexp = "^([+\\-])?(?:180(?:\\.0{1,6})?|(?:1[0-7]\\d|[1-9]?\\d)(?:\\.\\d{1,6})?)$",
                message = "Invalid longitude, value permitted range 180 to -180"
        )
        String longitude,

        @NotBlank(message = "Name is required")
        String name
) {}