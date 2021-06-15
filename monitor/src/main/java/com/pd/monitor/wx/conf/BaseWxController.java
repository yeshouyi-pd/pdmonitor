package com.pd.monitor.wx.conf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.CodesetDto;
import com.pd.server.main.dto.KvMapDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.mapper.my.MyDeptMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


public class BaseWxController {

    public  static RedisTemplate  redisTstaticemplate;

    public  static MyDeptMapper myDeptstaticMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private  MyDeptMapper myDeptMapper;



    @PostConstruct
    protected void init() {
        redisTstaticemplate = redisTemplate;
        myDeptstaticMapper = myDeptMapper;
    }


    public static LoginUserDto getRequestHeader() {
        LoginUserDto loginUserDto =new LoginUserDto();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String token = request.getHeader("token");
        Object object  =  redisTstaticemplate.opsForValue().get(token);
        JSONObject loginUserDtojSON = JSON.parseObject(String.valueOf(object));
        if(null != object){
            loginUserDto = JSONObject.toJavaObject(loginUserDtojSON,LoginUserDto.class);
        }
        return loginUserDto;
    }



    /**
     * 获取部门
     * @param deptcode
     * @return
     */
    public static List<String> getUpdeptcode(String deptcode){
        String depts = "";
       List<String> deptcodelist = new ArrayList<String>();
         if(StringUtils.isEmpty(deptcode)){
            LoginUserDto  user = getRequestHeader();
            depts = myDeptstaticMapper.getUpdeptcode(user.getDeptcode());
        }else {
            depts = myDeptstaticMapper.getUpdeptcode(deptcode);
        }
        if(!StringUtils.isEmpty(depts)){
            deptcodelist =  Arrays.asList(depts.replaceAll(" ", "").split(","));
        }

        return  deptcodelist;
    }
}
