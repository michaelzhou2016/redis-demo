package com.example.controller;

import com.example.vo.TestLocalDateTimeVO;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/test")
public class RedissonController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/lock")
    public List<TestLocalDateTimeVO> acquireLock() {
        var lock = redissonClient.getLock("testLock");
        lock.lock();
        try {
            // 模拟业务逻辑
            TestLocalDateTimeVO vo = new TestLocalDateTimeVO();
            vo.setName("Lock acquired and critical section executed.");
            vo.setLocalDateTime(LocalDateTime.now());
            vo.setDate(new Date());
            TimeUnit.MINUTES.sleep(1L);
            return List.of(vo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}

