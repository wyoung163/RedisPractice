package com.example.redis.ex3;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RedisController {
    private final RedisCrudService redisCrudService;
    private final StringRedisTemplate redisTemplate;

    @PostMapping("/save/repository")
    public Long saveByRedisRepository(@RequestBody RedisCrudSaveRequestDto requestDto){
        return redisCrudService.saveByRedisRepository(requestDto);
    }

    @GetMapping("/get/repository/{id}")
    public RedisByRepositoryResponseDto getByRedisRepository(@PathVariable Long id){
        return redisCrudService.getByRedisRepository(id);
    }

    //java로 치면 key-value에서 name-count
    @PostMapping("/save/template")
    public Boolean saveByRedisTemplate(@RequestParam String name, @RequestParam double count){
        return redisCrudService.saveByRedisTemplate(name,count);
    }

    //1위부터 9위까지 get
    @GetMapping("/get1To9/template")
    public List<RedisByTemplateResponseDto> get1To9ByRedisTemplate(){
        return redisCrudService.get1To9ByRedisTemplate();
    }

}