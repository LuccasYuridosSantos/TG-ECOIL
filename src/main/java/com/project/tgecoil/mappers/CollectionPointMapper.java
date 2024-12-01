package com.project.tgecoil.mappers;

import com.project.tgecoil.client.vo.OpenStreetMapResponse;
import com.project.tgecoil.model.api.CollectionPointRequest;
import com.project.tgecoil.model.api.CollectionPointResponse;
import com.project.tgecoil.model.dto.Address;
import com.project.tgecoil.model.dto.CollectionPoint;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionPointMapper {

    CollectionPoint toCollectionPoint(final CollectionPointRequest request);

    CollectionPointResponse toCollectionPointResponse(final CollectionPoint collectionPoint);

    List<CollectionPointResponse> toCollectionPointResponse(final List<CollectionPoint> collectionPoints);

    Iterable<CollectionPoint> toCollectionPoints(@Valid List<CollectionPointRequest> requests);

    Address toAddress(final OpenStreetMapResponse.Address address);
}