package com.pd.environment.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.SeaSurfaceSalinityDto;
import com.pd.server.main.service.SeaSurfaceSalinityService;
import com.pd.server.util.Base64Image;
import com.pd.server.util.DateTools;
import com.pd.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/seaSurfaceSalinity")
public class SeaSurfaceSalinityRevController {


    private static final Logger LOG = LoggerFactory.getLogger(CurrentMeterRevController.class);
    public static final String BUSINESS_NAME = "接收海表盐度图";

    @Resource
    private SeaSurfaceSalinityService seaSurfaceSalinityService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 上传图片
     */
    @PostMapping("/uploadImg")
    public ResponseDto saveData(@RequestBody JSONObject jsonObject) {
        ResponseDto responseDto = new ResponseDto();
        try {
            if(StringUtils.isEmpty(jsonObject.get("imgBase64")) || StringUtils.isEmpty(jsonObject.get("tprq"))){
                responseDto.setSuccess(false);
                responseDto.setMessage("参数错误");
                return responseDto;
            }
            String imgBase64 = jsonObject.getString("imgBase64");
            String tprq = jsonObject.getString("tprq");
            String isFlag = "^(\\d{4})-(\\d{2})-(\\d{2})$";//判断时间
            if(!tprq.matches(isFlag)){
                responseDto.setSuccess(false);
                responseDto.setMessage("图片日期格式错误");
                return responseDto;
            }
            Map<String,String> map = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
            String fileDriectory = createFileDriectory(map.get("pathwindows"));
            String filename = new Date().getTime() + "_" + UuidUtil.getShortUuid() + ".jpg";
            Base64Image.GenerateImage(imgBase64.substring(imgBase64.lastIndexOf(",")+1), fileDriectory + filename);
            SeaSurfaceSalinityDto entity = new SeaSurfaceSalinityDto();
            entity.setImgUrl("http://49.239.193.146:50091/system/f/" + DateTools.getYM() + "/"+ filename);
            entity.setTprq(tprq);
            seaSurfaceSalinityService.save(entity);
            responseDto.setSuccess(true);
            responseDto.setMessage("保存成功");
        }catch (Exception e){
            LOG.error("接收到的数据："+JSONObject.toJSONString(jsonObject)+"====错误原因："+e.getMessage());
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }

    /**
     * 创建目录
     * @param sysstorePath
     * @return
     */
    public static String createFileDriectory(String sysstorePath){
        String picDirectory = "";
        java.io.File storePath = new java.io.File(sysstorePath);
        if (!storePath.exists() && !storePath.isDirectory()) {
            LOG.info("创建目录-----------" + storePath.getAbsoluteFile());
            storePath.mkdir();
        }
        picDirectory = sysstorePath  + DateTools.getYM() + "/";
        java.io.File file = new File(picDirectory);
        if (!file.exists() && !file.isDirectory()) {
            LOG.info("创建目录-----------" + file.getAbsoluteFile());
            file.mkdir();
        }
        return picDirectory;
    }


}
