package com.taishonet.homchat.enums;

import com.taishonet.homchat.utils.RegexUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageCommand {
    ADD_TRELLO(0, "追加", (targetStr) -> {
        Map<String, String> params = new HashMap<>();
        Matcher matcher = RegexUtils.patternMatch("追加、([^、]+)、?([^、]*)", targetStr);
        if (!matcher.find()) {
            return params;
        }
        params.put("name", matcher.group(1));
        params.put("desc", matcher.group(2));
        return params;
    }),
    LOOK_TRELLO(1, "参照", null),
    REGISTER_MENSTRUATION(2, "生理", (targetStr) -> {
        //TODO 共通化
        Map<String, String> params = new HashMap<>();
        Matcher matcher = RegexUtils.patternMatch("生理、([^、]+)", targetStr);
        if (!matcher.find()) {
            return params;
        }
        params.put("weight", matcher.group(1));
        return params;
    }),
    NO_COMMAND(99, "その他", null);
    //TODO HELPコマンドを作る

    private int code;
    private String command;
    private Function<String, Map<String, String>> parseArgumentsFunction;
}
