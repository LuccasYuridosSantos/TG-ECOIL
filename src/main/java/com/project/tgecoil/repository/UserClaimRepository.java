package com.project.tgecoil.repository;

import com.project.tgecoil.model.dto.UserClaim;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserClaimRepository extends JpaRepository<UserClaim, Long> {

    Optional<UserClaim> findUserClaimByUserId(@NotBlank String userId);

    void deleteByUserId(@NotBlank String userId);
}
