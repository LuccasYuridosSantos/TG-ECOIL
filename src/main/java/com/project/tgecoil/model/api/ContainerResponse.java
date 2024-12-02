package com.project.tgecoil.model.api;

public record ContainerResponse(
        String id,

        String name,

        String description,

        String unitOfMeasure,

        Double maxCapacity,

        String image
) {}
