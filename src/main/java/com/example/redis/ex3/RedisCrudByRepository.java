package com.example.redis.ex3;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

//domain 만들기
@Getter
@ToString
@RedisHash("redisCrud")
public class RedisCrudByRepository {
    @Id
    private Long id;
    private String description;
    private LocalDateTime updatedAt;

    @Builder
    public RedisCrudByRepository(Long id, String description, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.updatedAt = updatedAt;
    }
}

