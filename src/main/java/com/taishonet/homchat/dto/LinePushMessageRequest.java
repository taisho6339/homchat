package com.taishonet.homchat.dto;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class LinePushMessageRequest {
    @NotBlank
    private String message;
}
