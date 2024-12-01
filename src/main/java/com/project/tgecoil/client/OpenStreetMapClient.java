package com.project.tgecoil.client;

import com.project.tgecoil.client.vo.OpenStreetMapResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OpenStreetMapClient {

    private static final String URL_NOMINATIM = "https://nominatim.openstreetmap.org/reverse";
    private static final String FORMAT_PARAM = "format";
    private static final String LAT_PARAM = "lat";
    private static final String LON_PARAM = "lon";
    private static final String ZOOM_PARAM = "zoom";
    private static final String ADDRESS_DETAILS_PARAM = "addressdetails";
    private static final String JSON_PARAM_TYPE_RETURN = "json";
    private static final int ADDRESS_DETAILS_PARAM_VALUE = 1;
    private static final int ZOOM_PARAM_VALUE = 18;
    private final RestTemplate restTemplate;

    public OpenStreetMapClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OpenStreetMapResponse getReverseGeocoding(final String latitude, final String longitude) {
        String url = UriComponentsBuilder.fromHttpUrl(URL_NOMINATIM)
                .queryParam(FORMAT_PARAM, JSON_PARAM_TYPE_RETURN)
                .queryParam(LAT_PARAM, latitude)
                .queryParam(LON_PARAM, longitude)
                .queryParam(ZOOM_PARAM, ZOOM_PARAM_VALUE)
                .queryParam(ADDRESS_DETAILS_PARAM, ADDRESS_DETAILS_PARAM_VALUE)
                .toUriString();
        return restTemplate.getForObject(url, OpenStreetMapResponse.class);
    }
}
