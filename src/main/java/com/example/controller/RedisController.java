package com.example.controller;

import com.example.vo.TestLocalDateTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping(path = "/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/put")
    public String put() {
        TestLocalDateTimeVO vo = new TestLocalDateTimeVO();
        vo.setName("test1");
        vo.setLocalDateTime(LocalDateTime.now());
        vo.setDate(new Date());
        redisTemplate.opsForValue().set("test", vo);
        return "OK";
    }

    @GetMapping("/get/{key}")
    public TestLocalDateTimeVO get(@PathVariable("key") String key) {
        return (TestLocalDateTimeVO) redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/remove/{key}")
    public String remove(@PathVariable("key") String key) {
        redisTemplate.delete(key);
        return "OK";
    }
}

