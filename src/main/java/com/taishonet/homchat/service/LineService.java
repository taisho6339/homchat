package com.taishonet.homchat.service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineService {
    private LineMessagingClient lineMessagingClient;
    //TODO: YMLに吐き出して、環境変数化したい。めんどいからあとで
    private static final String LINE_GROUP_ID = "C1f2757255753aed135c765566438df17";

    @Autowired
    public LineService(LineMessagingClient lineMessagingClient) {
        this.lineMessagingClient = lineMessagingClient;
    }

    public void pushMessage(String message) {
        PushMessage pushMessage = new PushMessage(LINE_GROUP_ID, new TextMessage(message));
        lineMessagingClient.pushMessage(pushMessage);
    }
}
