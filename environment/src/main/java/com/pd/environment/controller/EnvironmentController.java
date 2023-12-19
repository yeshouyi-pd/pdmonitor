package com.pd.environment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/environment")
public class EnvironmentController {

    private static final Logger LOG = LoggerFactory.getLogger(CurrentMeterRevController.class);
    public static final String BUSINESS_NAME = "接收环境数据";


    @Resource
    private RedisTemplate redisTemplate;

}
