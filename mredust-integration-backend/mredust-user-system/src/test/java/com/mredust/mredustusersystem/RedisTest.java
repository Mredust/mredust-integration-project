package com.mredust.mredustusersystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @description: RedisTest
 * @author: Mredust
 * @date: 2025/4/5 13:23
 */
@SpringBootTest
 class RedisTest {
    @Autowired
    public RedisTemplate redisTemplate;
    @Test
     void test(){
        redisTemplate.opsForValue().set("test","mredust-test");
    }
}
