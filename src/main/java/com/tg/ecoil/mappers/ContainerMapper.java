package com.tg.ecoil.mappers;

import com.tg.ecoil.model.api.ContainerRequest;
import com.tg.ecoil.model.api.ContainerResponse;
import com.tg.ecoil.model.dto.Container;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContainerMapper {

   List<ContainerResponse> toContainerList(List<Container> containers);


    Container toContainer(@Valid ContainerRequest request);

    ContainerResponse toContainerResponse(Container savedContainer);
}
