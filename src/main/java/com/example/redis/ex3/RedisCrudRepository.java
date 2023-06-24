package com.example.redis.ex3;

import org.springframework.data.repository.CrudRepository;

// 저장소 만들기
public interface RedisCrudRepository extends CrudRepository<RedisCrudByRepository, Long> {
}
