package com.project.tgecoil.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "collection_point")
@Table(name = "collection_point")
public class CollectionPoint {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "schedule_id_seq")
    @SequenceGenerator(name = "schedule_id_seq", sequenceName = "schedule_id_seq", allocationSize = 1)
    private Long id;
    private String city;
    private String state;
    private String latitude;
    private String longitude;
    private String name;

}
