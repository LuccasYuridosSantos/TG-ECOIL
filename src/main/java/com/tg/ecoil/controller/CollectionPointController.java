package com.tg.ecoil.controller;


import com.tg.ecoil.model.api.CollectionPointRequest;
import com.tg.ecoil.model.api.CollectionPointResponse;
import com.tg.ecoil.service.CollectionPointService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<CollectionPointResponse> createCollectionPoint(@RequestBody CollectionPointRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<CollectionPointResponse>> getAllCollectionPoints() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CollectionPointResponse>> findCollectionPointByFilter(@NotBlank final String city) {
        return ResponseEntity.ok(service.findCollectionPointByFilter(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCollectionPoint(@NotBlank final String id,
                                                      @RequestBody CollectionPointRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectionPoint(@NotBlank final String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}