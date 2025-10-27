package com.pd.monitor.controller;

import com.pd.server.main.dto.EquipmentFilePWavDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFilePWavService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/equipmentFilePWav")
public class EquipmentFilePWavController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFilePWavController.class);
    public static final String BUSINESS_NAME = "equipment_p_file音档 20 21";

    @Resource
    private EquipmentFilePWavService equipmentFilePWavService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFilePWavService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFilePWavDto equipmentFilePWavDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFilePWavDto.getBid(), "", 1, 50);
                ValidatorUtil.length(equipmentFilePWavDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFilePWavDto.getTplj(), "图片路径", 1, 500);
                ValidatorUtil.length(equipmentFilePWavDto.getNf(), "采集年份", 1, 20);
                ValidatorUtil.length(equipmentFilePWavDto.getYf(), "采集月份", 1, 20);
                ValidatorUtil.length(equipmentFilePWavDto.getRq(), "采集日期", 1, 20);
                ValidatorUtil.length(equipmentFilePWavDto.getXs(), "采集小时", 1, 20);
                ValidatorUtil.length(equipmentFilePWavDto.getFz(), "采集分钟", 1, 20);
                ValidatorUtil.length(equipmentFilePWavDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFilePWavDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(equipmentFilePWavDto.getWjlx(), "报警文件类型1图片2音频3文件", 1, 128);
                ValidatorUtil.length(equipmentFilePWavDto.getJczl(), "监测种类1江豚2白海豚", 1, 1);
                ValidatorUtil.length(equipmentFilePWavDto.getLy(), "来源1实时数据采集2非实时数据分析", 1, 1);
                ValidatorUtil.length(equipmentFilePWavDto.getSm1(), "", 1, 6000);
                ValidatorUtil.length(equipmentFilePWavDto.getSm2(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePWavDto.getSm3(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePWavDto.getSm4(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePWavDto.getSm5(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePWavDto.getSm6(), "物种类型", 1, 255);
                ValidatorUtil.length(equipmentFilePWavDto.getType(), "type，codeset表中的文件种类", 1, 50);
                ValidatorUtil.length(equipmentFilePWavDto.getTs(), "头数", 1, 10);
                ValidatorUtil.length(equipmentFilePWavDto.getTxtlx(), "txt鏂囦欢绫诲瀷", 1, 5);
                ValidatorUtil.length(equipmentFilePWavDto.getWjmc(), "文件名称截取，为了查询出对应的音频，文件，图片等数据", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        equipmentFilePWavService.save(equipmentFilePWavDto);
        responseDto.setContent(equipmentFilePWavDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFilePWavService.delete(id);
        return responseDto;
    }

}
