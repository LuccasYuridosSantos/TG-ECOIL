package com.project.tgecoil.service;

import com.project.tgecoil.exceptions.ResourceNotFoundException;
import com.project.tgecoil.mappers.CollectionPointMapper;
import com.project.tgecoil.model.api.CollectionPointRequest;
import com.project.tgecoil.model.api.CollectionPointResponse;
import com.project.tgecoil.repository.AddressRepository;
import com.project.tgecoil.repository.CollectionPointRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionPointService {

    private final CollectionPointRepository repository;
    private final AddressRepository addressRepository;
    private final CollectionPointMapper mapper;
    private final CompleteAddressService addressService;

    public CollectionPointResponse create(final @Valid CollectionPointRequest request) {
        final var openStreetMapResponse =
                addressService.getCollectionPointByLatLon(request.latitude(), request.longitude());
        final var collectionPoint = mapper.toCollectionPoint(request);
        final var address = addressRepository.save(mapper.toAddress(openStreetMapResponse.getAddress()));
        collectionPoint.setAddress(address);
        final var save = repository.save(collectionPoint);
        return mapper.toCollectionPointResponse(save);
    }

    public List<CollectionPointResponse> getAll() {
        return mapper.toCollectionPointResponse(repository.findAll());
    }

    public List<CollectionPointResponse> findCollectionPointByFilter(final String city) {
        final var result = repository.findAllByCityIgnoreCase(city);
        return mapper.toCollectionPointResponse(result);
    }

    public void update(final Long id, final CollectionPointRequest request) {
    repository.findById(id).ifPresentOrElse(collectionPoint -> {
        final var updatedCollectionPoint = mapper.toCollectionPoint(request);
        final var address = mapper.toAddress(addressService.getCollectionPointByLatLon(request.latitude(), request.longitude()).getAddress());
        final var savedAddress = addressRepository.save(address);
        updatedCollectionPoint.setId(id);
        updatedCollectionPoint.setAddress(savedAddress);
        repository.save(updatedCollectionPoint);
    }, () -> {
        throw new ResourceNotFoundException("Collection point not found with id %s", id);
    });
}


    public void delete(final Long id) {
        repository.deleteById(id);
    }

    public List<CollectionPointResponse> createBatch(final @Valid List<CollectionPointRequest> requests) {
        final var collectionPoints = requests.stream()
                .map(request -> {
                    final var openStreetResponse =
                            addressService.getCollectionPointByLatLon(request.latitude(), request.longitude());
                    final var address = addressRepository.save(mapper.toAddress(openStreetResponse.getAddress()));
                    final var point = mapper.toCollectionPoint(request);
                    point.setAddress(address);
                    return point;
                })
                .toList();

        final var result = repository.saveAll(collectionPoints);
        return mapper.toCollectionPointResponse(result);

    }
}