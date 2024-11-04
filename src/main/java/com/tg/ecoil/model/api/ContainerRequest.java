package com.tg.ecoil.model.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ContainerRequest(

        @NotBlank(message = "Name is required")
        String name,

        String description,

        @NotBlank(message = "Unit of measure is required")
        String unitOfMeasure,

        @NotNull(message = "Max capacity is required")
        @Positive(message = "Max capacity must be a positive number")
        Double maxCapacity,

        @NotBlank(message = "Image URL is required")
        String image
) {}
