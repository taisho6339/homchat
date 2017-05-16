package com.taishonet.homchat.utils;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.client.HttpClientErrorException;
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

        RequestEntity requestEntity = createPostRequestEntity(apiUrl, headers, params);
        System.out.println(requestEntity.toString());
        System.out.println(requestEntity.getBody().toString());
        try {
            ResponseEntity<String> responseEntity = REST_TEMPLATE.exchange(requestEntity, String.class);
            return responseEntity.getBody() != null ? responseEntity.getBody() : "";
        } catch (HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
            e.printStackTrace();
            return "";
        }
    }

    private static RequestEntity createPostRequestEntity(String apiURL, Map<String, String> headers, MultiValueMap<String, String> params) throws URISyntaxException {
        RequestEntity.BodyBuilder builder = RequestEntity.post(new URI(apiURL));
        if (headers != null) {
            headers.keySet()
                    .forEach(key -> builder.header(key, headers.get(key)));
        }
        if (params != null) {
            return builder.body(params);
        }
        return builder.build();
    }
}
