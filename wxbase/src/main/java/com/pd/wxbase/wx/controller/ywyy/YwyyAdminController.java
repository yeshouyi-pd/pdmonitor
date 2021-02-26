package com.pd.wxbase.wx.controller.ywyy;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 业务预约 后台 admin权限过滤
 */
@RestController
@RequestMapping("/admin/ywyy")
public class YwyyAdminController {
    public static final String BUSINESS_NAME = "微信预约【后台】";


    @Resource
    private RedisTemplate redisTemplate;

}
