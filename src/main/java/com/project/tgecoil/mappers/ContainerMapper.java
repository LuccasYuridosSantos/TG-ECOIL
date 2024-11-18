package com.project.tgecoil.mappers;

import com.project.tgecoil.model.api.ContainerRequest;
import com.project.tgecoil.model.api.ContainerResponse;
import com.project.tgecoil.model.dto.Container;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContainerMapper {

   List<ContainerResponse> toContainerList(List<Container> containers);


    Container toContainer(@Valid ContainerRequest request);

    ContainerResponse toContainerResponse(Container savedContainer);
}
