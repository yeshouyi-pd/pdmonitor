package com.pd.monitor.controller;

import com.pd.server.main.dto.EquipmentFilePVideoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFilePVideoService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/equipmentFilePVideo")
public class EquipmentFilePVideoController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFilePVideoController.class);
    public static final String BUSINESS_NAME = "equipment_p_file_mp4 40";

    @Resource
    private EquipmentFilePVideoService equipmentFilePVideoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFilePVideoService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFilePVideoDto equipmentFilePVideoDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFilePVideoDto.getBid(), "", 1, 50);
                ValidatorUtil.length(equipmentFilePVideoDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFilePVideoDto.getTplj(), "图片路径", 1, 500);
                ValidatorUtil.length(equipmentFilePVideoDto.getNf(), "采集年份", 1, 20);
                ValidatorUtil.length(equipmentFilePVideoDto.getYf(), "采集月份", 1, 20);
                ValidatorUtil.length(equipmentFilePVideoDto.getRq(), "采集日期", 1, 20);
                ValidatorUtil.length(equipmentFilePVideoDto.getXs(), "采集小时", 1, 20);
                ValidatorUtil.length(equipmentFilePVideoDto.getFz(), "采集分钟", 1, 20);
                ValidatorUtil.length(equipmentFilePVideoDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFilePVideoDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(equipmentFilePVideoDto.getWjlx(), "报警文件类型1图片2音频3文件", 1, 128);
                ValidatorUtil.length(equipmentFilePVideoDto.getJczl(), "监测种类1江豚2白海豚", 1, 1);
                ValidatorUtil.length(equipmentFilePVideoDto.getLy(), "来源1实时数据采集2非实时数据分析", 1, 1);
                ValidatorUtil.length(equipmentFilePVideoDto.getSm1(), "", 1, 6000);
                ValidatorUtil.length(equipmentFilePVideoDto.getSm2(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePVideoDto.getSm3(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePVideoDto.getSm4(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePVideoDto.getSm5(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePVideoDto.getSm6(), "物种类型", 1, 255);
                ValidatorUtil.length(equipmentFilePVideoDto.getType(), "type，codeset表中的文件种类", 1, 50);
                ValidatorUtil.length(equipmentFilePVideoDto.getTs(), "头数", 1, 10);
                ValidatorUtil.length(equipmentFilePVideoDto.getTxtlx(), "txt鏂囦欢绫诲瀷", 1, 5);
                ValidatorUtil.length(equipmentFilePVideoDto.getWjmc(), "文件名称截取，为了查询出对应的音频，文件，图片等数据", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        equipmentFilePVideoService.save(equipmentFilePVideoDto);
        responseDto.setContent(equipmentFilePVideoDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFilePVideoService.delete(id);
        return responseDto;
    }

}
