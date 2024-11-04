package com.tg.ecoil.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "collection_points")
public class CollectionPoint {
        @Id
        private String id;
        private String city;
        private String state;
        private String latitude;
        private String longitude;
        private String name;
}