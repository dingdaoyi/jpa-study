package com.yanbing.manage.managecontrol.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Description： TODO
 * <p>
 * Author: dingyw
 * <p>
 * Date: Created in 2019-06-12 14:08
 * <p>
 * Company: xzjc
 * <p>
 * Copyright: Copyright (c) 2019
 * <p>
 * Version: 1.0.0
 * <p>
 * Modified By:
 */
@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private ReactiveStringRedisTemplate reactiveRedisTemplate;

    @PostMapping
    public Mono<ResponseEntity<String>> setRedis(@RequestParam("name")  String name) {
        return reactiveRedisTemplate.opsForValue().set("aa", name)
                .map(aBoolean ->
                      aBoolean ?ResponseEntity.ok( "操作成功") : ResponseEntity.ok( "添加失败"));

    }

    @GetMapping("aa")
    public Mono<String> getRedis() {
        return reactiveRedisTemplate.opsForValue().get("aa");

    }
}
