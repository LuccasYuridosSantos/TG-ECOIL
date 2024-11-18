package com.project.tgecoil.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "container")
@Table(name = "container")
public class Container {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "container_id_seq")
    @SequenceGenerator(name = "container_id_seq", sequenceName = "container_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotBlank(message = "Unit of measure is required")
    private String unitOfMeasure;

    @NotNull(message = "Max capacity is required")
    @Positive(message = "Max capacity must be a positive number")
    private Double maxCapacity;

    @NotBlank(message = "Image URL is required")
    private String image;
}
