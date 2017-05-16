package com.taishonet.homchat.eventhandler;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.taishonet.homchat.repository.TrelloRepository;

import org.springframework.beans.factory.annotation.Autowired;

@LineMessageHandler
public class LineEventHandler {

    private TrelloRepository repository;

    @Autowired
    public LineEventHandler(TrelloRepository repository) {
        this.repository = repository;
    }

    @EventMapping
    public TextMessage receiveMessage(MessageEvent<TextMessageContent> event) {
        String registeredUrl = repository.registerNewDateCardToTrello(event.getMessage().getText());
        return new TextMessage("きゅいきゅい！ つ" + registeredUrl);
    }
}
