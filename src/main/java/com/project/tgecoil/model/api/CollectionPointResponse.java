package com.project.tgecoil.model.api;

public record CollectionPointResponse(
        Long id,
        String city,
        String state,
        String latitude,
        String longitude,
        String name
) {}