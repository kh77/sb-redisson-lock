package com.sm.config;

import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class RedisLock {

    private static final String LOCK_KEY = "app-lock";
    private RLock lock;
    private final RedissonClient redisson;

    @PostConstruct
    public void init() {
        lock = redisson.getLock(LOCK_KEY);
    }

    public boolean tryLock() {
        //tryLock(long time, TimeUnit unit)
        return lock.tryLock();
    }

    public void unlock() {
        lock.unlock();
    }

}
