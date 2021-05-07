package com.pd.monitor.wx.wxutlis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

public class AccountsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AccountsServlet.class);

    /**
     * 微信允许最长Access_token有效时间（ms）
     */
    public static final long MAX_TIME = 7200 * 1000;

    /**
     * 服务器地址
     */
    public static String serverUrl;

    /**
     * 临时票据
     */
    public static Ticket ticket = null;

    /**
     * 网页调用token
     */
    public static String accessToken = null;

    /**
     * 临时票据
     */
    public static String jspTicet = null;

    private static RedisTemplate redisTstaticemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @PostConstruct
    protected void init() {
        redisTstaticemplate = redisTemplate;
    }

}
