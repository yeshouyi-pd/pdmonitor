package com.pd.wxbase.wx.conf;

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

    /**
     * 获取证件类型 want返回需要
     * @return
     */

    public List<KvMapDto>  getzljx(String cdeType){
        Map<String, Map<String, String>> all =(Map<String, Map<String, String>>)redisTstaticemplate.opsForValue().get(RedisCode.CODESET);
        Map<String, String>  map   = all.get(cdeType);
        List<KvMapDto>  list  = new ArrayList<KvMapDto>();
        if (null != map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                KvMapDto vo  = new KvMapDto();
                vo.setText(entry.getValue());
                vo.setCode(entry.getKey());
                list.add(vo);
            }

        }

        return  list;
    }

/*    *//**
     * 获取需要拍照的照片
     * @param cdeType
     * @returnap
     */
    public List<Map<String,Object>>  getuplodzljx(String cdeType){
        Map<String, Map<String, String>> all =(Map<String, Map<String, String>>)redisTstaticemplate.opsForValue().get(RedisCode.CODESET);
        Map<String, String>  map   = all.get(cdeType);
        List<Map<String,Object>>  mapobj  = new ArrayList<Map<String,Object>>();
        if (null != map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                Map<String,Object> thismap = new HashMap<String,Object>();
                CodesetDto code = new CodesetDto();
                code.setCode(entry.getKey());
                code.setName(entry.getValue());
                thismap.put("code",code);
                thismap.put("list",new ArrayList());
                mapobj.add(thismap);
            }

        }
        return  mapobj;
    }


}
