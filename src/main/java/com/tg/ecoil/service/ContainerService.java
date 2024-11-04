package com.tg.ecoil.service;

import com.tg.ecoil.exceptions.ResourceNotFoundException;
import com.tg.ecoil.mappers.ContainerMapper;
import com.tg.ecoil.model.api.ContainerRequest;
import com.tg.ecoil.model.api.ContainerResponse;
import com.tg.ecoil.model.dto.Container;
import com.tg.ecoil.repository.ContainerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContainerService {

    private final ContainerRepository repository;
    private final ContainerMapper mapper;

    public ContainerResponse createContainer(final @Valid ContainerRequest request) {
        final var savedContainer = repository.save( mapper.toContainer(request));
        return mapper.toContainerResponse(savedContainer);
    }

    public List<ContainerResponse> getAllContainers() {
        return mapper.toContainerList(repository.findAll());
    }

    public ContainerResponse getContainerById(final String id) {
        return repository.findById(id).map(mapper::toContainerResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Container not found with id {}", id));
    }

    public void updateContainer(final String id, final @Valid ContainerRequest request) {
        repository.findById(id).ifPresentOrElse(container -> {
            final var updatedContainer = mapper.toContainer(request);
            updatedContainer.setId(id);
            repository.save(updatedContainer);
        }, () -> {
            throw new ResourceNotFoundException("Container not found with id {}", id);
        });
    }


    public void deleteContainer(final String id) {
        repository.deleteById(id);
    }
}
