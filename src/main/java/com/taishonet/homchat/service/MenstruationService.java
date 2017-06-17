package com.taishonet.homchat.service;

import com.taishonet.homchat.enums.MenstruationWeight;
import com.taishonet.homchat.repository.RedisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MenstruationService {
    private static final String KEY_MENSTRUATION = "MENSTRUATION_WEIGHT";
    private RedisRepository redisRepository;

    @Autowired
    public MenstruationService(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    public String registerMenstruation(String weight) {
        MenstruationWeight menstruationWeight = Arrays.stream(MenstruationWeight.values())
                .filter(val -> val.getLabel().equals(weight))
                .findFirst().orElse(MenstruationWeight.WEIGHT_MEDIUM);
        redisRepository.setForKey(KEY_MENSTRUATION, String.valueOf(menstruationWeight.getCode()));
        log.warn("登録したよ");
        return menstruationWeight.getLabel() + "で登録したきゅい！";
    }
}
