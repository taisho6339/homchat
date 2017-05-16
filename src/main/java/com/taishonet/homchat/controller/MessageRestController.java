package com.taishonet.homchat.controller;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.taishonet.homchat.dto.EventPart;
import com.taishonet.homchat.dto.LineEvent;
import com.taishonet.homchat.repository.LineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by taisho6339 on 2017/05/13.
 */
@RestController
@RequestMapping("/message")
//@LineMessageHandler
public class MessageRestController {

    @Autowired
    private LineRepository lineRepository;

    @RequestMapping(path = "/receive", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
//    @EventMapping
    public void index(@RequestBody LineEvent lineEvent) {
//        System.out.println("event: " + event);
//        return new TextMessage("きゅいきゅい！");
        List<EventPart> eventPartList = lineEvent.getEvents();
        eventPartList.stream()
                .filter(eventPart -> !(StringUtils.isEmpty(eventPart.getReplyToken())))
                .forEach(eventPart -> lineRepository.reply(eventPart.getReplyToken()));
        //TODO: Trelloに投稿する
        //TODO: 投稿したことをLINEに通知する
    }
}