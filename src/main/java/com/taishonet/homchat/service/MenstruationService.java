package com.taishonet.homchat.service;

import com.taishonet.homchat.enums.MenstruationWeight;
import com.taishonet.homchat.repository.RedisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Service
public class MenstruationService {
    private static final String KEY_MENSTRUATION = "MENSTRUATION_WEIGHT";
    private RedisRepository redisRepository;

    @Autowired
    public MenstruationService(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    public String getRegisteredMenstruation() {
        String codeStr = redisRepository.getForKey(KEY_MENSTRUATION, null);
        if (StringUtils.isEmpty(codeStr)) {
            return "生理の重みを入力してね！";
        }
        int code = Integer.valueOf(codeStr);
        MenstruationWeight menstruationWeight = Arrays.stream(MenstruationWeight.values())
                .filter(val -> val.getCode() == code).findFirst().orElse(MenstruationWeight.WEIGHT_MEDIUM);
        return menstruationWeight.getRemindMessage();
    }

    public String registerMenstruation(String weight) {
        MenstruationWeight menstruationWeight = Arrays.stream(MenstruationWeight.values())
                .filter(val -> val.getLabel().equals(weight))
                .findFirst().orElse(MenstruationWeight.WEIGHT_MEDIUM);
        redisRepository.setForKey(KEY_MENSTRUATION, String.valueOf(menstruationWeight.getCode()));
        return menstruationWeight.getLabel() + "で登録したきゅい！";
    }
}
