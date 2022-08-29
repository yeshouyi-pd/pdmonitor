package com.pd.monitor.controller;

import com.pd.server.main.dto.EquipmentFileTodayDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFileTodayService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/equipmentFileToday")
public class EquipmentFileTodayController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileTodayController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private EquipmentFileTodayService equipmentFileTodayService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTodayService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileTodayDto equipmentFileTodayDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileTodayDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileTodayDto.getTplj(), "图片路径", 1, 500);
                ValidatorUtil.length(equipmentFileTodayDto.getNf(), "采集年份", 1, 20);
                ValidatorUtil.length(equipmentFileTodayDto.getYf(), "采集月份", 1, 20);
                ValidatorUtil.length(equipmentFileTodayDto.getRq(), "采集日期", 1, 20);
                ValidatorUtil.length(equipmentFileTodayDto.getXs(), "采集小时", 1, 20);
                ValidatorUtil.length(equipmentFileTodayDto.getFz(), "采集分钟", 1, 20);
                ValidatorUtil.length(equipmentFileTodayDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFileTodayDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(equipmentFileTodayDto.getWjlx(), "报警文件类型1图片2音频3视频", 1, 128);
                ValidatorUtil.length(equipmentFileTodayDto.getJczl(), "监测种类1江豚2白海豚", 1, 1);
                ValidatorUtil.length(equipmentFileTodayDto.getLy(), "来源1实时数据采集2非实时数据分析", 1, 1);
                ValidatorUtil.length(equipmentFileTodayDto.getSm1(), "", 1, 1024);
                ValidatorUtil.length(equipmentFileTodayDto.getSm2(), "", 1, 1024);
                ValidatorUtil.length(equipmentFileTodayDto.getSm3(), "", 1, 1024);
                ValidatorUtil.length(equipmentFileTodayDto.getSm4(), "", 1, 1024);
                ValidatorUtil.length(equipmentFileTodayDto.getSm5(), "", 1, 1024);
                ValidatorUtil.length(equipmentFileTodayDto.getSm6(), "", 1, 1024);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileTodayService.save(equipmentFileTodayDto);
        responseDto.setContent(equipmentFileTodayDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTodayService.delete(id);
        return responseDto;
    }

}
