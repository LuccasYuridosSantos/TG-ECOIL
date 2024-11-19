package com.project.tgecoil.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_claim")
@Table(name = "user_claim")
public class UserClaim {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "user_claim_id_seq")
    @SequenceGenerator(name = "user_claim_id_seq", sequenceName = "user_claim_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String userId;

    private boolean admin;

}
