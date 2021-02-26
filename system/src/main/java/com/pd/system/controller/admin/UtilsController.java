package com.pd.system.controller.admin;

import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 该Controller 提供公用方法  不过滤
 * 建议无数据库请求
 */
@RestController
@RequestMapping("/utils")
public class UtilsController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    public static final String BUSINESS_NAME = "通用请求";

    @Resource
    public RedisTemplate redisTemplate;

    /**
     * 获取所有的业务
     * @return
     */
    @GetMapping("/gatCode")
    public ResponseDto gatCode(){
        ResponseDto responseDto = new ResponseDto();
        Map<String, Map<String, String>> nmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        responseDto.setContent(nmap);
        return responseDto;
    }
}
