/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.redis.controller;

import com.springboot.demo.redis.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-07-31 17:06
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisUtil redisUtil;


    @GetMapping(value = "/redis")
    public String redis() {
        redisUtil.del("key1");
        redisUtil.set("key1", "1");
//        redisUtil.incr("key1", 1l);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUserName("张d三");
        userInfo.setInsertTime(new Date());
        stringRedisTemplate.opsForValue().set("key2", "12");
        redisUtil.set("user1", userInfo);
//        redisUtil.hset("hset1", "key1", "value1");
//        UserInfo u = (UserInfo) redisUtil.get("user1");
//        System.out.println(u.getUserName());
//        System.out.println(redisUtil.get("user1").toString());
        return redisUtil.get("key1").toString();


//        stringRedisTemplate.opsForValue().set("xiaocai", "888");
//        logger.info(stringRedisTemplate.opsForValue().get("xiaocai"));
//        stringRedisTemplate.expire("key1", 10, TimeUnit.SECONDS);
//        return stringRedisTemplate.opsForValue().get("key1");

//        redisTemplate.opsForValue();//操作字符串
//        redisTemplate.opsForHash();//操作hash
//        redisTemplate.opsForList();//操作list
//        redisTemplate.opsForSet();//操作set
//        redisTemplate.opsForZSet();//操作有序set
    }
}
