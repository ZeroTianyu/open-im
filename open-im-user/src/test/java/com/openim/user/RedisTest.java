package com.openim.user;

import jakarta.annotation.Resource;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedissonClient redissonClient;




}
