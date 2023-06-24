package com.example.redis.ex3;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RedisByRepositoryResponseDto {
    private Long id;
    private String description;
    private LocalDateTime updatedAt;

    public RedisByRepositoryResponseDto(RedisCrudByRepository redisHash) {
        this.id = redisHash.getId();
        this.description = redisHash.getDescription();
        this.updatedAt = redisHash.getUpdatedAt();
    }
}