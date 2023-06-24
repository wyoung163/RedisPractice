package com.example.redis.ex3;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RedisByTemplateResponseDto {
    private int rank;
    private String name;

    @Builder
    public RedisByTemplateResponseDto(int rank, String name) {
        this.rank = rank;
        this.name = name;
    }

    public static RedisByTemplateResponseDto of(int rank, String name){
        return RedisByTemplateResponseDto.builder()
                .rank(rank)
                .name(name)
                .build();
    }
}
