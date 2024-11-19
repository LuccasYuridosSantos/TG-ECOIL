package com.project.tgecoil.service;

import com.project.tgecoil.exceptions.ResourceNotFoundException;
import com.project.tgecoil.mappers.ContainerMapper;
import com.project.tgecoil.model.api.ContainerRequest;
import com.project.tgecoil.model.api.ContainerResponse;
import com.project.tgecoil.repository.ContainerRepository;
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
        final var savedContainer = repository.save(mapper.toContainer(request));
        return mapper.toContainerResponse(savedContainer);
    }

    public List<ContainerResponse> getAllContainers() {
        return mapper.toContainerList(repository.findAll());
    }

    public ContainerResponse getContainerById(final Long id) {
        return repository.findById(id)
                .map(mapper::toContainerResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Container not found with id %s", id));
    }

    public void updateContainer(final Long id,
                                final @Valid ContainerRequest request) {
        repository.findById(id).ifPresentOrElse(container -> {
            final var updatedContainer = mapper.toContainer(request);
            updatedContainer.setId(id);
            repository.save(updatedContainer);
        }, () -> {
            throw new ResourceNotFoundException("Container not found with id %s", id);
        });
    }


    public void deleteContainer(final Long id) {
        repository.deleteById(id);
    }
}
