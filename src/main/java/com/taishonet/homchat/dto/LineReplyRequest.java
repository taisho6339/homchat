package com.taishonet.homchat.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineReplyRequest implements HttpRequest {
    private String replyToken;
    private List<LineMessage> messages = new ArrayList<>();

    public void addMessage(LineMessage message) {
        messages.add(message);
    }
}

