package com.project.tgecoil.service;

import com.project.tgecoil.exceptions.ResourceNotFoundException;
import com.project.tgecoil.mappers.CollectionPointMapper;
import com.project.tgecoil.model.api.CollectionPointRequest;
import com.project.tgecoil.model.api.CollectionPointResponse;
import com.project.tgecoil.repository.CollectionPointRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionPointService {

    private final CollectionPointRepository repository;
    private final CollectionPointMapper mapper;

    public CollectionPointResponse create(final @Valid CollectionPointRequest request) {
        final var save = repository.save(mapper.toCollectionPoint(request));
        return mapper.toCollectionPointResponse(save);
    }

    public List<CollectionPointResponse> getAll() {
        return mapper.toCollectionPointResponse(repository.findAll());
    }

    public List<CollectionPointResponse> findCollectionPointByFilter(final String city) {
        final var result = repository.findAllByCityIgnoreCase(city);

        return mapper.toCollectionPointResponse(result);
    }

    public void update(final Long id,
                       final CollectionPointRequest request) {
        repository.findById(id).ifPresentOrElse(container -> {
            final var collectionPoint = mapper.toCollectionPoint(request);
            collectionPoint.setId(id);
            repository.save(collectionPoint);
        }, () -> {
            throw new ResourceNotFoundException("Collection point not found with id %s", id);
        });
    }


    public void delete(final Long id) {
        repository.deleteById(id);
    }

    public List<CollectionPointResponse> createBatch(final @Valid List<CollectionPointRequest> requests) {
        final var result = repository.saveAll(mapper.toCollectionPoints(requests));
        return mapper.toCollectionPointResponse(result);
    }
}