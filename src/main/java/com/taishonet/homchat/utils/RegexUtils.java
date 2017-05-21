package com.taishonet.homchat.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by taisho6339 on 2017/05/21.
 */
public final class RegexUtils {
    private RegexUtils() {
    }

    public static Matcher patternMatch(String regex, String targetStr) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(targetStr);
    }
}
