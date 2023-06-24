package com.example.redis.ex3;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*
    Redis 외부 서버 존재할 경우 불필요
    내장 서버로 환경 구성할 때 필요
 */
@RequiredArgsConstructor
@Profile("local")
@Configuration
public class EmbeddedRedisConfig {
    private final RedisProperties redisProperties;
    private RedisServer redisServer;

    @PostConstruct
    public void redisServer(){
        redisServer = new RedisServer(redisProperties.getPort());
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis(){
        if(redisServer != null){
            redisServer.stop();
        }
    }
}
