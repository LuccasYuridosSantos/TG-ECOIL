package com.project.tgecoil.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "collection_point")
@Table(name = "collection_point")
public class CollectionPoint {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "collection_point_id_seq")
    @SequenceGenerator(name = "collection_point_id_seq", sequenceName = "collection_point_id_seq", allocationSize = 1)
    private Long id;
    private String city;
    private String state;
    private String latitude;
    private String longitude;
    private String name;

    @OneToOne
    private Address address;
}
