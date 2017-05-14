package com.taishonet.homchat.utils;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class RequestUtils {
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private RequestUtils() {
    }

    public static String post(String apiUrl, Map<String, String> headers, MultiValueMap<String, String> params) throws URISyntaxException {
        if (StringUtils.isEmpty(apiUrl)) {
            return null;
        }
        RequestEntity requestEntity = createRequestEntity(apiUrl, headers);
        ResponseEntity<String> responseEntity = REST_TEMPLATE.exchange(requestEntity, String.class);
        return responseEntity.getBody() != null ? responseEntity.getBody() : "";
    }

    private static RequestEntity createRequestEntity(String apiURL, Map<String, String> headers) throws URISyntaxException {
        RequestEntity.BodyBuilder builder = RequestEntity
                .post(new URI(apiURL));

        if (headers == null || headers.isEmpty()) {
            return builder.build();
        }
        headers.keySet()
                .forEach(key -> builder.header(headers.get(key)));

        return builder.build();
    }
}
