package com.tg.ecoil.mappers;

import com.tg.ecoil.model.api.CollectionPointRequest;
import com.tg.ecoil.model.api.CollectionPointResponse;
import com.tg.ecoil.model.dto.CollectionPoint;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionPointMapper {

    CollectionPoint toCollectionPoint(final CollectionPointRequest request);

    CollectionPointResponse toCollectionPointResponse(final CollectionPoint collectionPoint);

    List<CollectionPointResponse> toCollectionPointResponse(final List<CollectionPoint> collectionPoints);
}