package com.project.tgecoil.client.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenStreetMapResponse {

    @JsonProperty("place_id")
    private long placeId;

    private String licence;

    @JsonProperty("osm_type")
    private String osmType;

    @JsonProperty("osm_id")
    private long osmId;

    private String lat;
    private String lon;

    @JsonProperty("class")
    private String classType;

    private String type;

    @JsonProperty("place_rank")
    private int placeRank;

    private double importance;
    private String addresstype;
    private String name;

    @JsonProperty("display_name")
    private String displayName;

    private Address address;

    private List<String> boundingbox;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Address {
        private String road;

        @JsonProperty("city_district")
        private String cityDistrict;

        private String town;
        private String municipality;
        private String county;

        @JsonProperty("state_district")
        private String stateDistrict;

        private String state;

        @JsonProperty("ISO3166-2-lvl4")
        private String iso3166Lvl4;

        private String region;
        private String postcode;
        private String country;

        @JsonProperty("country_code")
        private String countryCode;
    }
}