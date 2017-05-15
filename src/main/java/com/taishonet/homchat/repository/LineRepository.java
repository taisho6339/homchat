package com.taishonet.homchat.repository;

import com.taishonet.homchat.utils.RequestUtils;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LineRepository {
    private static final String LINE_REPLY_API = "https://api.line.me/v2/bot/message/reply";
    private static final String LINE_ACCESS_TOKEN = "Bearer geQoaqxQA2Nd9E9bKrCWZDuoFi/7+1GSkxJH8B3EFA3GYPGZv0gjPGfprECrOS2nysbzH9XJqZWm3KQnJWORJveMcEBOJvWrl/MG5CwxU8d5U59aWUAas6ALqwwt+H/yid8dfKwQKtyehLnooNw1oAdB04t89/1O/w1cDnyilFU=";
    private static final String LINE_REPLY_TOKEN_KEY = "replyToken";
    private static final String LINE_MESSAGES_KEY = "messages";

    public void reply(String replyToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.put(HttpHeaders.AUTHORIZATION, LINE_ACCESS_TOKEN);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set(LINE_REPLY_TOKEN_KEY, replyToken);

        List<String> messageList = new ArrayList<>();
        messageList.add("{\"type\":\"text\",\"message\":\"キュキュい！\"}");
        params.put(LINE_MESSAGES_KEY, messageList);

        System.out.println("Token:" + replyToken);

        try {
            RequestUtils.post(LINE_REPLY_API, headers, params);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
