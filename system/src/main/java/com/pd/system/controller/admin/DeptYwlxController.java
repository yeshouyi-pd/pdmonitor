package com.pd.system.controller.admin;

import com.pd.server.config.CodeType;
import com.pd.server.config.RedisCode;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.main.dto.*;
import com.pd.server.util.ValidatorUtil;
import com.pd.system.controller.conf.RedisConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/admin/deptYwlx")
public class DeptYwlxController {

    private static final Logger LOG = LoggerFactory.getLogger(DeptYwlxController.class);
    public static final String BUSINESS_NAME = "部门业务";
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 获取业务分类和业务类型
     */
    @GetMapping("/getywflandyewlx")
    public  ResponseDto getywflandylx(){
        ResponseDto responseDto = new ResponseDto();
        Map<String, Map<String, String>> nmap = new LinkedHashMap<String, Map<String, String>>();
        Map<String, Map<String, String>> all =(Map<String, Map<String, String>>)redisTemplate.opsForValue().get(RedisCode.CODESET);
        nmap.put("CODE_JDC_YWLX",all.get(CodeType.CODE_JDC_YWLX));
        nmap.put("CODE_JSR_YWLX",all.get(CodeType.CODE_JSR_YWLX));
        nmap.put("CODE_WF_YWLX",all.get(CodeType.CODE_WF_YWLX));
        nmap.put("YWZL_CODE",all.get(CodeType.YWZL_CODE));
        responseDto.setContent(nmap);

        return responseDto;
    }



}
