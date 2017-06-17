package com.taishonet.homchat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class RedisRepository {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public String getForKey(String key, String defaultValue) {
        if (StringUtils.isEmpty(key) || !redisTemplate.hasKey(key)) {
            return defaultValue;
        }
        return redisTemplate.opsForValue().get(key);
    }

    public void setForKey(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }
        redisTemplate.opsForValue().set(key, value);
    }
}
