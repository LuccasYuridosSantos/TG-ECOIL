package com.project.tgecoil.controller;

import com.project.tgecoil.model.api.ContainerRequest;
import com.project.tgecoil.model.api.ContainerResponse;
import com.project.tgecoil.service.ContainerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/containers",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ContainerController {

    private final ContainerService containerService;

    @PostMapping
    public ResponseEntity<ContainerResponse> createContainer(@Valid @RequestBody ContainerRequest request) {
        final var container = containerService.createContainer(request);
        return new ResponseEntity<>(container, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContainerResponse>> getAllContainers() {
        return ResponseEntity.ok(containerService.getAllContainers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContainerResponse> getContainerById(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(containerService.getContainerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContainerResponse> updateContainer(
            @NotNull @PathVariable Long id,
            @Valid @RequestBody ContainerRequest request) {
        containerService.updateContainer(id, request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContainer(@NotNull @PathVariable Long id) {
        containerService.deleteContainer(id);
        return ResponseEntity.noContent().build();
    }
}
