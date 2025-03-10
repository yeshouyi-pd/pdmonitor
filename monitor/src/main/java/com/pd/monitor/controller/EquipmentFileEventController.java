package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.mobileCode.FileInfo;
import com.pd.monitor.mobileCode.FileInfoSoap;
import com.pd.monitor.mobileCode.RetClass;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.EquipmentFileEventDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFileEventService;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/equipmentFileEvent")
public class EquipmentFileEventController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileEventController.class);
    public static final String BUSINESS_NAME = "A4设备聚类事件";

    @Resource
    private EquipmentFileEventService equipmentFileEventService;
    @Resource
    private EquipmentFileService equipmentFileService;

    @PostMapping("/playVedio")
    public ResponseDto playVedio(@RequestBody EquipmentFileEventDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        FileInfo fileInfo = new FileInfo();
        FileInfoSoap fileInfoSoap = fileInfo.getFileInfoSoap();
        RetClass retClass = fileInfoSoap.getPlayUrl("LW001","2022_12_29_16_02_10_2022_12_29_16_02_59_4_A4_TD33.mp4");
        responseDto.setContent(retClass.getPlayUrl());
        return responseDto;
    }

    @PostMapping("/videoList")
    public ResponseDto vedioList(@RequestBody EquipmentFileEventDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileEvent fileEvent = equipmentFileEventService.selectByPrimaryKey(pageDto.getId());
        List<EquipmentFile> result = new ArrayList<>();
        if(!StringUtils.isEmpty(fileEvent.getEquipmentFileId())){
            EquipmentFile equipmentFile = equipmentFileService.selectByPrimaryKey(fileEvent.getEquipmentFileId());
            if(equipmentFile!=null && !StringUtils.isEmpty(equipmentFile.getWjmc())){
                EquipmentFileExample example = new EquipmentFileExample();
                EquipmentFileExample.Criteria ca = example.createCriteria();
                ca.andWjmcEqualTo(equipmentFile.getWjmc());
                ca.andWjlxEqualTo("4");
                result = equipmentFileService.listAll(example);
            }
        }
        responseDto.setContent(result);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody EquipmentFileEventDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileEventExample equipmentFileEventExample = new EquipmentFileEventExample();
        EquipmentFileEventExample.Criteria ca = equipmentFileEventExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        equipmentFileEventExample.setOrderByClause(" kssj desc ");
        List<EquipmentFileEvent> equipmentFileEventList = new ArrayList<>();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            equipmentFileEventList = equipmentFileEventService.selectByExampleSpecial(pageDto);
        }else{
            equipmentFileEventList = equipmentFileEventService.list(equipmentFileEventExample);
        }
        PageInfo<EquipmentFileEvent> pageInfo = new PageInfo<>(equipmentFileEventList);
        pageDto.setTotal(pageInfo.getTotal());
        List<EquipmentFileEventDto> equipmentFileEventDtoList = CopyUtil.copyList(equipmentFileEventList, EquipmentFileEventDto.class);
        pageDto.setList(equipmentFileEventDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileEventDto equipmentFileEventDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileEventDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(equipmentFileEventDto.getKssj(), "开始时间", 1, 100);
                ValidatorUtil.length(equipmentFileEventDto.getJssj(), "结束时间", 1, 100);
                ValidatorUtil.length(equipmentFileEventDto.getRq(), "日期", 1, 50);
                ValidatorUtil.length(equipmentFileEventDto.getTs(), "头数", 1, 20);
                ValidatorUtil.length(equipmentFileEventDto.getJtnr(), "具体内容", 1, 6000);
                ValidatorUtil.length(equipmentFileEventDto.getDeptcode(), "部门编号", 1, 50);
                ValidatorUtil.length(equipmentFileEventDto.getEquipmentFileId(), "equipment_file表主键，用于下载视频", 1, 50);
                ValidatorUtil.length(equipmentFileEventDto.getXmbh(), "项目编号", 1, 50);
                ValidatorUtil.length(equipmentFileEventDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(equipmentFileEventDto.getSm(), "说明", 1, 255);
                ValidatorUtil.length(equipmentFileEventDto.getSm1(), "", 1, 255);
                ValidatorUtil.length(equipmentFileEventDto.getSm2(), "", 1, 255);
                ValidatorUtil.length(equipmentFileEventDto.getSm3(), "", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileEventService.save(equipmentFileEventDto);
        responseDto.setContent(equipmentFileEventDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileEventService.delete(id);
        return responseDto;
    }

}
