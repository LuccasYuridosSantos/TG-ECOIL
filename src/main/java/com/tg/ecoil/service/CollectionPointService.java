package com.tg.ecoil.service;

import com.tg.ecoil.exceptions.ResourceNotFoundException;
import com.tg.ecoil.mappers.CollectionPointMapper;
import com.tg.ecoil.model.api.CollectionPointRequest;
import com.tg.ecoil.model.api.CollectionPointResponse;
import com.tg.ecoil.repository.CollectionPointRepository;
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

    public void update(final String id,
                       final CollectionPointRequest request) {
        repository.findById(id).ifPresentOrElse(container -> {
            final var collectionPoint = mapper.toCollectionPoint(request);
            collectionPoint.setId(id);
            repository.save(collectionPoint);
        }, () -> {
            throw new ResourceNotFoundException("Collection point not found with id {}", id);
        });
    }


    public void delete(final String id) {
        repository.deleteById(id);
    }
}