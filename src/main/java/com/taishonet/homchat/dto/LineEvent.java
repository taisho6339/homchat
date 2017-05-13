package com.taishonet.homchat.dto;

import java.util.List;

import lombok.Data;

/**
 * Created by taisho6339 on 2017/05/13.
 */
@Data
public class LineEvent {
    private List<EventPart> events;
}
