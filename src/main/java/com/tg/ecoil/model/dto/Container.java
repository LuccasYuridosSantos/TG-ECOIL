package com.tg.ecoil.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "containers")
public class Container {
        @Id
        private String id;

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
