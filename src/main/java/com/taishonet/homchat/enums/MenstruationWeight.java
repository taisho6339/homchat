package com.taishonet.homchat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MenstruationWeight {
    WEIGHT_SMALL(0, "弱", "弱だよ"),
    WEIGHT_MEDIUM(1, "中", "中だよ"),
    WEIGHT_LARGE(2, "強", "強だよ");

    private int code;
    private String label;
    private String remindMessage;
}
