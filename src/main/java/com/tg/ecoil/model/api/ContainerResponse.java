package com.tg.ecoil.model.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ContainerResponse(
        String id,

        String name,

        String description,

        String unitOfMeasure,

        Double maxCapacity,

        String image
) {}
