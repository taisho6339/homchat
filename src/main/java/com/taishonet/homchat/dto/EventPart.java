package com.taishonet.homchat.dto;

import lombok.Data;

/**
 * Created by taisho6339 on 2017/05/13.
 */
@Data
public class EventPart {
    private String replyToken;
    private String type;
    private String timeStamp;

    @Data
    public static class Source {
        private String type;
        private String userId;
    }

    @Data
    public static class Message {
        private String id;
        private String type;
        private String text;
    }
}
