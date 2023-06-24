package com.example.redis.ex3;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RedisCrudService {
    //방법1.Redis Repository 사용
    private final RedisCrudRepository redisCrudRepository;

    //방법2.Redis Template 사용
    private final StringRedisTemplate redisTemplate;
    private ZSetOperations<String, String> zSetOps;

    //방법1.======
    @Transactional
    public Long saveByRedisRepository(RedisCrudSaveRequestDto requestDto){
        return redisCrudRepository.save(requestDto.toRedisHash()).getId();
    }

    public RedisByRepositoryResponseDto getByRedisRepository(Long id){
        RedisCrudByRepository redisCrud = redisCrudRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nothing saved. id=" + id));
        return new RedisByRepositoryResponseDto(redisCrud);
    }

    //방법2.======
    @PostConstruct
    public void init(){
        zSetOps = redisTemplate.opsForZSet();
    }

    @Transactional
    public Boolean saveByRedisTemplate(String name, double count){
        Boolean aBoolean = zSetOps.add("redisCrudTemplate", name, count);
        return aBoolean;
    }

    public List<RedisByTemplateResponseDto> get1To9ByRedisTemplate(){
        Set<String> redisCrudTemplate = zSetOps.reverseRange("redisCrudTemplate", 0, 8);
        List<RedisByTemplateResponseDto> redisByTemplateResponseDtoSet = new ArrayList<>(redisCrudTemplate.size());
        Iterator<String> iterator = redisCrudTemplate.iterator();

        int i=1;
        while(iterator.hasNext()){
            redisByTemplateResponseDtoSet.add(RedisByTemplateResponseDto.of(i++,iterator.next()));
        }

        return redisByTemplateResponseDtoSet;
    }

}

/*
    <Redis Repository>
    만들어 놓은 저장소를 주입받아 사용
    save, findById 사용

    <Redis Template>
    StringRedisTemplate 주입받아 사용
    ZSet 자료구조조
 */