package com.fiap.hackaton.cache;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import redis.embedded.RedisServer;

@TestConfiguration
public class TestRedisConfiguration {

    private final RedisServer redisServer;

    public TestRedisConfiguration(@Value("${spring.data.redis.port:6379}") int redisPort) {
        this.redisServer = new RedisServer(redisPort);
        this.redisServer.start();
    }

    @Bean
    public RedisServer redisServer() {
        return redisServer;
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null && redisServer.isActive()) {
            redisServer.stop();
        }
    }
}