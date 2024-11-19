package com.project.tgecoil.mappers;

import com.project.tgecoil.model.api.UserClaimRequest;
import com.project.tgecoil.model.api.UserClaimResponse;
import com.project.tgecoil.model.api.UserClaimUpdateRequest;
import com.project.tgecoil.model.dto.UserClaim;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserClaimMapper {

    List<UserClaimResponse> toUserClaimResponseList(List<UserClaim> userClaims);

    UserClaimResponse toUserClaimResponse(@Valid UserClaim userClaim);

    UserClaim toUserClaim(@Valid UserClaimRequest request);

    UserClaim toUserClaim(@Valid UserClaimUpdateRequest response);
}
