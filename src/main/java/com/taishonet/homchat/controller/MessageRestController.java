package com.taishonet.homchat.controller;

import com.taishonet.homchat.dto.LineEvent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by taisho6339 on 2017/05/13.
 */
@RestController
@RequestMapping("/message")
public class MessageRestController {

    @RequestMapping(path = "/receive", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void index(@RequestBody LineEvent lineEvent) {
        System.out.println("line:Event" + lineEvent.getEvents().get(0).getReplyToken());
    }
}