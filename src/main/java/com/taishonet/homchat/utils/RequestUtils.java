package com.taishonet.homchat.utils;

import com.taishonet.homchat.dto.HttpRequest;

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

    public static String post(String apiUrl, Map<String, String> headers, HttpRequest params) throws URISyntaxException {
        if (StringUtils.isEmpty(apiUrl)) {
            return null;
        }

        RequestEntity<HttpRequest> requestEntity = createRequestEntity(apiUrl, headers, params);
        System.out.println("RequestInfo:" + requestEntity.toString());
        try {
            ResponseEntity<String> responseEntity = REST_TEMPLATE.exchange(requestEntity, String.class);
            return responseEntity.getBody() != null ? responseEntity.getBody() : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static RequestEntity<HttpRequest> createRequestEntity(String apiURL, Map<String, String> headers, HttpRequest params) throws URISyntaxException {
        RequestEntity.BodyBuilder builder = RequestEntity.post(new URI(apiURL));
        if (headers == null || headers.isEmpty()) {
            return builder.body(params);
        }
        headers.keySet()
                .forEach(key -> builder.header(key, headers.get(key)));
        return builder.body(params);
    }
}
