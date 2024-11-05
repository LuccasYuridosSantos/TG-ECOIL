package com.tg.ecoil.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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

        public Container() {
        }

        public Container(final String id,
                         final String name,
                         final String description,
                         final String unitOfMeasure,
                         final Double maxCapacity,
                         final String image) {
                this.id = id;
                this.name = name;
                this.description = description;
                this.unitOfMeasure = unitOfMeasure;
                this.maxCapacity = maxCapacity;
                this.image = image;
        }

        public String getId() {
                return id;
        }

        public void setId(final String id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(final String name) {
                this.name = name;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(final String description) {
                this.description = description;
        }

        public String getUnitOfMeasure() {
                return unitOfMeasure;
        }

        public void setUnitOfMeasure(final String unitOfMeasure) {
                this.unitOfMeasure = unitOfMeasure;
        }

        public Double getMaxCapacity() {
                return maxCapacity;
        }

        public void setMaxCapacity(final Double maxCapacity) {
                this.maxCapacity = maxCapacity;
        }

        public String getImage() {
                return image;
        }

        public void setImage(final String image) {
                this.image = image;
        }
}
