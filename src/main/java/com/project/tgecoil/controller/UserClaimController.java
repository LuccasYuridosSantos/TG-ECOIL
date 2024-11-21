package com.project.tgecoil.controller;

import com.project.tgecoil.model.api.UserClaimRequest;
import com.project.tgecoil.model.api.UserClaimResponse;
import com.project.tgecoil.model.api.UserClaimUpdateRequest;
import com.project.tgecoil.service.UserClaimService;
import jakarta.validation.constraints.NotBlank;
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
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/user/claim",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserClaimController {

    private final UserClaimService service;

    @PostMapping
    public ResponseEntity<UserClaimResponse> insert(@RequestBody final UserClaimRequest request) {
        return ResponseEntity.ok(service.insert(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserClaimResponse> findUserId(@NotBlank @PathVariable final String userId) {
        return ResponseEntity.ok(service.get(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserClaimResponse> updateUserId(@RequestBody final UserClaimUpdateRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserId(@NotBlank @PathVariable final String userId) {
        service.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
