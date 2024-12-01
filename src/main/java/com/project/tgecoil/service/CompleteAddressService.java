package com.project.tgecoil.service;

import com.project.tgecoil.client.OpenStreetMapClient;
import com.project.tgecoil.client.vo.OpenStreetMapResponse;
import com.project.tgecoil.model.api.CollectionPointRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompleteAddressService {

   private final OpenStreetMapClient client;

    public OpenStreetMapResponse getCollectionPointByLatLon(final String latitude, final String longitude) {
        final var response = client.getReverseGeocoding(latitude, longitude);
        return response;
    }
}
