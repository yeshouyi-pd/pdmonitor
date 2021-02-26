package com.pd.wxbase.wx.wxutlis.utils;

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
     * 开发者ID
     */
    public static String appid = "wxb7807839fc5db896";

    /**
     * 开发者密码
     */
    public static String appsecret = "4ceb0dc6b59e16a1a478226333a58c09";

    /**
     * 服务器地址
     */
    public static String serverUrl;

    /**
     * 微信前端服务地址
     */
    public static String wxqdurl;

    /**
     * 微信token
     */
    public static String token;

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
