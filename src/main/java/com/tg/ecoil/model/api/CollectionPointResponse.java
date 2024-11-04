package com.tg.ecoil.model.api;

public record CollectionPointResponse(
        String id,
        String city,
        String state,
        String latitude,
        String longitude,
        String name
) {}