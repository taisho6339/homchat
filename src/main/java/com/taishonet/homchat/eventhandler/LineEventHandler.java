package com.taishonet.homchat.eventhandler;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.taishonet.homchat.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.log4j.Log4j;

@LineMessageHandler
@Log4j
public class LineEventHandler {

    private MessageService messageService;

    @Autowired
    public LineEventHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @EventMapping
    public TextMessage receiveMessage(MessageEvent<TextMessageContent> event) {
        return messageService.dispatchMessageCommand(event.getMessage().getText());
    }
}
