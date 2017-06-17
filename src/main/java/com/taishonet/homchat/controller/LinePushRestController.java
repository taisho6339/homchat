package com.taishonet.homchat.controller;

import com.taishonet.homchat.dto.LinePushMessageRequest;
import com.taishonet.homchat.service.LineService;
import com.taishonet.homchat.service.MenstruationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LinePushRestController {

    private LineService lineService;
    private MenstruationService menstruationService;

    @Autowired
    public LinePushRestController(LineService lineService, MenstruationService menstruationService) {
        this.lineService = lineService;
        this.menstruationService = menstruationService;
    }

    @RequestMapping(path = "/push", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void pushMessage(@Validated @RequestBody LinePushMessageRequest request) {
        lineService.pushMessage(request.getMessage());
    }

    @RequestMapping(path = "/push/menstruation", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void pushMenstruationMessage() {
        String message = menstruationService.getRegisteredMenstruation();
        if (StringUtils.isEmpty(message)) {
            return;
        }
        lineService.pushMessage(message);
    }
}
