package com.pd.system.controller.admin;

import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.util.DateTools;
import com.pd.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RequestMapping("/uploadfile")
@RestController
public class UploadFileController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadFileController.class);

    public static final String BUSINESS_NAME = "文件上传";

    @Resource
    private RedisTemplate redisTemplate;



    @RequestMapping("/upload")
    public ResponseDto upload(@RequestParam MultipartFile file,String use) throws IOException {
        // 保存文件到本地
        String key = UuidUtil.getShortUuid();
        String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
        String basePath = picStorePath +use+File.separator+DateTools.getFormatDate(new Date(),"yyyyMM");
        File filepath = new File(basePath);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String filehz = fileName.substring(fileName.lastIndexOf("."));
        String fullPath = basePath+File.separator+key+filehz;
        File dest = new File(fullPath);
        file.transferTo(dest);//写入当本地磁盘
        String readpath = "/system/f/"+use+File.separator+DateTools.getFormatDate(new Date(),"yyyyMM")+File.separator+key+filehz;
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(readpath);
        return responseDto;
    }
}

