package com.taishonet.homchat.eventhandler;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.taishonet.homchat.repository.TrelloRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@LineMessageHandler
public class LineEventHandler {

    private TrelloRepository repository;

    @Autowired
    public LineEventHandler(TrelloRepository repository) {
        this.repository = repository;
    }

    @EventMapping
    public TextMessage receiveMessage(MessageEvent<TextMessageContent> event) {
        String message = event.getMessage().getText();
        if (!message.contains("追加")) {
            return new TextMessage("きゅいきゅい！");
        }
        String[] commands = message.split("追加　");
        if (commands.length == 2) {
            String registeredUrl = repository.registerNewDateCardToTrello(commands[1]);
            return new TextMessage("きゅいきゅい！ つ" + registeredUrl);
        }
        return new TextMessage("きゅいきゅい！");
    }
}
