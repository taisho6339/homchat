package com.taishonet.homchat.controller;

import com.taishonet.homchat.dto.LinePushMessageRequest;
import com.taishonet.homchat.service.LineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    public LinePushRestController(LineService lineService) {
        this.lineService = lineService;
    }

    @RequestMapping(path = "/push", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void pushMessage(@Validated @RequestBody LinePushMessageRequest request) {
        lineService.pushMessage(request.getMessage());
    }
}
