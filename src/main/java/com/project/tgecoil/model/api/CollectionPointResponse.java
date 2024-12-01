package com.project.tgecoil.model.api;

import com.project.tgecoil.client.vo.OpenStreetMapResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionPointResponse {
    private Long id;
    private String city;
    private String state;
    private String latitude;
    private String longitude;
    private String name;
    private OpenStreetMapResponse.Address address;
}