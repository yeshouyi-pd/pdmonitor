package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VoicePowerDevice;
import com.pd.server.main.domain.VoicePowerDeviceExample;
import com.pd.server.main.dto.VoicePowerDeviceDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.VoicePowerDeviceService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/voicePowerDevice")
public class VoicePowerDeviceController {

    private static final Logger LOG = LoggerFactory.getLogger(VoicePowerDeviceController.class);
    public static final String BUSINESS_NAME = "发送设备工作日志表";

    @Resource
    private VoicePowerDeviceService voicePowerDeviceService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody VoicePowerDeviceDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        VoicePowerDeviceExample example = new VoicePowerDeviceExample();
        VoicePowerDeviceExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        example.setOrderByClause(" send_time desc ");
        List<VoicePowerDevice> voicePowerDeviceList = voicePowerDeviceService.selectByExample(example);
        PageInfo<VoicePowerDevice> pageInfo = new PageInfo<>(voicePowerDeviceList);
        pageDto.setTotal(pageInfo.getTotal());
        List<VoicePowerDeviceDto> voicePowerDeviceDtoList = CopyUtil.copyList(voicePowerDeviceList, VoicePowerDeviceDto.class);
        pageDto.setList(voicePowerDeviceDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody VoicePowerDeviceDto voicePowerDeviceDto) {
        // 保存校验
                ValidatorUtil.length(voicePowerDeviceDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(voicePowerDeviceDto.getTopicName(), "主题名字", 1, 10);
                ValidatorUtil.length(voicePowerDeviceDto.getRq(), "日期", 1, 10);

        ResponseDto responseDto = new ResponseDto();
        voicePowerDeviceService.save(voicePowerDeviceDto);
        responseDto.setContent(voicePowerDeviceDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        voicePowerDeviceService.delete(id);
        return responseDto;
    }

}
