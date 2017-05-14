package com.taishonet.homchat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LineMessage {
    private String type;
    private String message;
}
