package com.project.tgecoil.controller;


import com.project.tgecoil.model.api.CollectionPointRequest;
import com.project.tgecoil.model.api.CollectionPointResponse;
import com.project.tgecoil.service.CollectionPointService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/collection/points",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CollectionPointController {

    private final CollectionPointService service;

    @PostMapping
    public ResponseEntity<CollectionPointResponse> createCollectionPoint(@Valid @RequestBody
                                                                         CollectionPointRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<CollectionPointResponse>> createCollectionPoints(
            @Valid @RequestBody List<CollectionPointRequest> requests) {
        return ResponseEntity.ok(service.createBatch(requests));
    }


    @GetMapping
    public ResponseEntity<List<CollectionPointResponse>> getAllCollectionPoints() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CollectionPointResponse>> findCollectionPointByFilter(
            @NotBlank @RequestParam final String city) {
        return ResponseEntity.ok(service.findCollectionPointByFilter(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCollectionPoint(@NotNull @PathVariable final Long id,
                                                      @Valid @RequestBody CollectionPointRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectionPoint(@NotNull @PathVariable final Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}