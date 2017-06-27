package com.taishonet.homchat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MenstruationWeight {
    WEIGHT_SMALL(0, "弱", "弱だよ"),
    WEIGHT_MEDIUM(1, "中", "中だよ"),
    WEIGHT_LARGE(2, "強", "今回の生理は、かなり情緒不安定！！ささいなことで泣きたくなったりイライラしちゃうかも…\n" +
            "ななさは、生理の時のルールをしっかり守ろう！なるべく心も体も無理をしない\n" +
            "ひろき\n" +
            "ななさはマトモじゃないという前提でいよう\n" +
            "揉めそうになったときこそ、冷静になってハンドルを握ろう\n");

    private int code;
    private String label;
    private String remindMessage;
}
