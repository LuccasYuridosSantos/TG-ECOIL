package com.project.tgecoil.service;

import com.project.tgecoil.exceptions.ResourceDuplicatedException;
import com.project.tgecoil.exceptions.ResourceNotFoundException;
import com.project.tgecoil.mappers.UserClaimMapper;
import com.project.tgecoil.model.api.UserClaimRequest;
import com.project.tgecoil.model.api.UserClaimResponse;
import com.project.tgecoil.model.api.UserClaimUpdateRequest;
import com.project.tgecoil.repository.UserClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserClaimService {

    private final UserClaimRepository repository;
    private final UserClaimMapper mapper;

    public UserClaimResponse insert(final UserClaimRequest request) {
        repository.findUserClaimByUserId(request.userId()).ifPresent(userClaim -> {
            throw new ResourceDuplicatedException("UserClaim already exists for userId: %s", request.userId());
        });
        return mapper.toUserClaimResponse(repository.save(mapper.toUserClaim(request)));
    }

    public UserClaimResponse update(final UserClaimUpdateRequest request) {
        final var userClaim = repository.findUserClaimByUserId(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("UserClaim not found for userId: %s",
                        request.userId()));
        final var updatedUserClaim = mapper.toUserClaim(request);
        updatedUserClaim.setId(userClaim.getId());
        return mapper.toUserClaimResponse(repository.save(updatedUserClaim));
    }

    public UserClaimResponse get(final String userId) {
        return mapper.toUserClaimResponse(repository.findUserClaimByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserClaim not found for userId: %s", userId)));
    }

    public void delete(final String userId) {
        repository.findUserClaimByUserId(userId)
                .ifPresentOrElse(repository::delete,
                        () -> {
                            throw new ResourceNotFoundException("UserClaim not found for userId: %s", userId);
                        });
    }
}
