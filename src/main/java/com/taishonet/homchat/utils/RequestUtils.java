package com.taishonet.homchat.utils;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.log4j.Log4j;

@Log4j
public class RequestUtils {
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private RequestUtils() {
    }

    public static String get(String apiUrl, Map<String, String> headers, Map<String, String> params) throws URISyntaxException {
        if (StringUtils.isEmpty(apiUrl)) {
            return null;
        }

        String query = "";
        if (params != null && !params.isEmpty()) {
            query = params.keySet()
                    .stream()
                    .map(key -> key + "=" + params.get(key))
                    .collect(Collectors.joining("&", "?", ""));
        }

        RequestEntity.HeadersBuilder builder = RequestEntity.get(new URI(apiUrl + query));
        if (headers != null) {
            headers.keySet()
                    .forEach(key -> builder.header(key, headers.get(key)));
        }

        return requestInternal(builder.build());
    }

    public static String post(String apiUrl, Map<String, String> headers, MultiValueMap<String, String> params) throws URISyntaxException {
        if (StringUtils.isEmpty(apiUrl)) {
            return null;
        }

        RequestEntity.BodyBuilder builder = RequestEntity.post(new URI(apiUrl));
        if (headers != null) {
            headers.keySet()
                    .forEach(key -> builder.header(key, headers.get(key)));
        }

        if (params != null) {
            requestInternal(builder.body(params));
        }

        return requestInternal(builder.build());
    }

    private static String requestInternal(RequestEntity requestEntity) {
        try {
            ResponseEntity<String> responseEntity = REST_TEMPLATE.exchange(
                    requestEntity,
                    String.class
            );
            return responseEntity.getBody() != null ? responseEntity.getBody() : "";
        } catch (HttpClientErrorException e) {
            log.warn(e.getResponseBodyAsString());
            return "";
        }
    }
}
