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
@Entity(name = "address")
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "address_id_seq")
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    private Long id;
    private String road;
    private String municipality;
    private String county;
    private String state;
    private String region;
    private String postcode;
    private String country;
    private String cityDistrict;
    private String stateDistrict;
    private String iso3166Lvl4;
    private String countryCode;

    @OneToOne(mappedBy = "address")
    private CollectionPoint collectionPoint;
}
