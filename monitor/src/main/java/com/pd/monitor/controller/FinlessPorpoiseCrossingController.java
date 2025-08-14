package com.pd.monitor.controller;


import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.FinlessPorpoiseTraverse;
import com.pd.server.main.domain.FinlessPorpoiseTraverseExample;
import com.pd.server.main.dto.FinlessPorpoiseTraverseDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.FinlessPorpoiseTraverseService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/finlessPorpoiseCrossing")
public class FinlessPorpoiseCrossingController {

    private static final Logger LOG = LoggerFactory.getLogger(FinlessPorpoiseCrossingController.class);
    @Resource
    private FinlessPorpoiseTraverseService finlessPorpoiseTraverseService;

    @PostMapping("/crossingImg")
    public ResponseDto UAVFlyVideo(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        try {
            if(StringUtils.isEmpty(jsonObject.getString("cjsj")) || StringUtils.isEmpty(jsonObject.getString("tplj"))
                    || StringUtils.isEmpty(jsonObject.getString("xmbh")) || StringUtils.isEmpty(jsonObject.getString("xmbhmc"))){
                responseDto.setSuccess(false);
                responseDto.setMessage("参数错误");
                return responseDto;
            }
            String isFlag = "^(\\d{4})-(\\d{2})-(\\d{2})\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";//判断时间
            if(!jsonObject.getString("cjsj").matches(isFlag)){
                responseDto.setSuccess(false);
                responseDto.setMessage("时间格式错误！");
                return responseDto;
            }
            FinlessPorpoiseTraverseExample example = new FinlessPorpoiseTraverseExample();
            FinlessPorpoiseTraverseExample.Criteria ca = example.createCriteria();
            ca.andTpljEqualTo(jsonObject.getString("tplj"));
            List<FinlessPorpoiseTraverse> tempList = finlessPorpoiseTraverseService.selectByExample(example);
            if(tempList.size()>0){
                responseDto.setSuccess(false);
                responseDto.setMessage("重复数据！");
                return responseDto;
            }
            FinlessPorpoiseTraverseDto dto = new FinlessPorpoiseTraverseDto();
            dto.setXmbh(jsonObject.getString("xmbh"));
            dto.setXmbhmc(jsonObject.getString("xmbhmc"));
            dto.setCjsj(jsonObject.getString("cjsj"));
            dto.setTplj(jsonObject.getString("tplj"));
            dto.setRq(dto.getCjsj().substring(0,11));
            finlessPorpoiseTraverseService.save(dto);
            responseDto.setSuccess(true);
            return responseDto;
        }catch (Exception e){
            LOG.error("入参："+jsonObject.toJSONString());
            LOG.error("江豚穿越保存接口异常："+e.getMessage());
            responseDto.setSuccess(false);
            responseDto.setMessage("系统异常！");
            return responseDto;
        }
    }
}
