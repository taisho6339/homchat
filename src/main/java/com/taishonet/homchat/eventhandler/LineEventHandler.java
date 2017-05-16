package com.taishonet.homchat.eventhandler;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class LineEventHandler {
    @EventMapping
    public TextMessage receiveMessage(MessageEvent<TextMessageContent> event) {
        //TODO: Trelloに投稿する
        //TODO: 投稿したことをLINEに通知する
        return new TextMessage("きゅいきゅい！");
    }
}
