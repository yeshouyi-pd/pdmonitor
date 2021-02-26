package com.pd.wxbase.wx.controller;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.dto.JdccxLogDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.JdccxLogService;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.wxutlis.utils.SendPostUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/jdccxLog")
public class JdccxLogController {

    private static final Logger LOG = LoggerFactory.getLogger(JdccxLogController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private JdccxLogService jdccxLogService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        jdccxLogService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody JdccxLogDto jdccxLogDto) {
        // 保存校验
            ValidatorUtil.length(jdccxLogDto.getCreateBy(), "录入人", 1, 128);
            ValidatorUtil.length(jdccxLogDto.getQqcs(), "请求参数", 1, 128);
            ValidatorUtil.length(jdccxLogDto.getFhztm(), "返回状态码code", 1, 10);
            ValidatorUtil.length(jdccxLogDto.getCjh(), "返回车架号", 1, 128);
            ValidatorUtil.length(jdccxLogDto.getZcrq(), "返回登记日期", 1, 128);
            ValidatorUtil.length(jdccxLogDto.getSccj(), "返回生产厂家", 1, 500);
            ValidatorUtil.length(jdccxLogDto.getDw(), "返回登记地点", 1, 500);
            ValidatorUtil.length(jdccxLogDto.getSjhm(), "返回手机号码", 1, 20);
            ValidatorUtil.length(jdccxLogDto.getHpzl(), "返回号牌种类", 1, 10);
            ValidatorUtil.length(jdccxLogDto.getZhhm(), "返回证件号", 1, 50);
            ValidatorUtil.length(jdccxLogDto.getHphm(), "返回车辆号码", 1, 50);
            ValidatorUtil.length(jdccxLogDto.getSyr(), "返回所有人", 1, 20);

        ResponseDto responseDto = new ResponseDto();
        jdccxLogService.save(jdccxLogDto);
        responseDto.setContent(jdccxLogDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        jdccxLogService.delete(id);
        return responseDto;
    }

    @PostMapping("/getDdcQuery")
    public ResponseDto getDdcQuery(@RequestBody Map<String, String> map){
        String hphm = map.get("hphm");
        String userName = map.get("userName");
        ResponseDto responseDto = new ResponseDto();
        if(!StringUtils.isEmpty(hphm)){
            String url = "http://74.31.50.100:8080/hnddcb/busXbFjdcxx/getTwoHundredBusXbFjdcxxInfo.do";
            Map<String, Object> parameters = new HashMap<String,Object>();
            parameters.put("hphm",hphm);
            JSONObject result = SendPostUtil.sendPost(url,parameters);
            JdccxLogDto jdccxLogDto = new JdccxLogDto();
            jdccxLogDto.setCreateBy(userName);
            jdccxLogDto.setCreateTime(new Date());
            jdccxLogDto.setQqcs(parameters.toString());
            jdccxLogDto.setFhztm(result.getString("code"));
            jdccxLogDto.setFhjg(result.toJSONString());
            if(result.getString("code").equals("1")){
                JSONArray dataArr = result.getJSONArray("data");
                if(!StringUtils.isEmpty(dataArr)&&dataArr.size()>0){
                    JSONObject entity = dataArr.getJSONObject(0);
                    jdccxLogDto.setCjh(entity.getString("cjh"));
                    jdccxLogDto.setZcrq(entity.getString("zcrq"));
                    jdccxLogDto.setSccj(entity.getString("sccj"));
                    jdccxLogDto.setDw(entity.getString("dw"));
                    jdccxLogDto.setSjhm(entity.getString("sjhm"));
                    jdccxLogDto.setHpzl(entity.getString("hpzl"));
                    jdccxLogDto.setZhhm(entity.getString("zhhm"));
                    jdccxLogDto.setHphm(entity.getString("hphm"));
                    jdccxLogDto.setSyr(entity.getString("syr"));
                }
            }
            jdccxLogService.save(jdccxLogDto);
            responseDto.setContent(jdccxLogDto);
        }else {
            responseDto.setSuccess(false);
            responseDto.setMessage("请求失败，请求参数为空");
        }
        return  responseDto;
    }
}
