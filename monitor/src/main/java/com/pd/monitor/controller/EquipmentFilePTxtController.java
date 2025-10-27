package com.pd.monitor.controller;

import com.pd.server.main.dto.EquipmentFilePTxtDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFilePTxtService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/equipmentFilePTxt")
public class EquipmentFilePTxtController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFilePTxtController.class);
    public static final String BUSINESS_NAME = "equipment_p_file文件 31 32 34 36";

    @Resource
    private EquipmentFilePTxtService equipmentFilePTxtService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFilePTxtService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFilePTxtDto equipmentFilePTxtDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFilePTxtDto.getBid(), "", 1, 50);
                ValidatorUtil.length(equipmentFilePTxtDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFilePTxtDto.getTplj(), "图片路径", 1, 500);
                ValidatorUtil.length(equipmentFilePTxtDto.getNf(), "采集年份", 1, 20);
                ValidatorUtil.length(equipmentFilePTxtDto.getYf(), "采集月份", 1, 20);
                ValidatorUtil.length(equipmentFilePTxtDto.getRq(), "采集日期", 1, 20);
                ValidatorUtil.length(equipmentFilePTxtDto.getXs(), "采集小时", 1, 20);
                ValidatorUtil.length(equipmentFilePTxtDto.getFz(), "采集分钟", 1, 20);
                ValidatorUtil.length(equipmentFilePTxtDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFilePTxtDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(equipmentFilePTxtDto.getWjlx(), "报警文件类型1图片2音频3文件", 1, 128);
                ValidatorUtil.length(equipmentFilePTxtDto.getJczl(), "监测种类1江豚2白海豚", 1, 1);
                ValidatorUtil.length(equipmentFilePTxtDto.getLy(), "来源1实时数据采集2非实时数据分析", 1, 1);
                ValidatorUtil.length(equipmentFilePTxtDto.getSm1(), "", 1, 6000);
                ValidatorUtil.length(equipmentFilePTxtDto.getSm2(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePTxtDto.getSm3(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePTxtDto.getSm4(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePTxtDto.getSm5(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePTxtDto.getSm6(), "物种类型", 1, 255);
                ValidatorUtil.length(equipmentFilePTxtDto.getType(), "type，codeset表中的文件种类", 1, 50);
                ValidatorUtil.length(equipmentFilePTxtDto.getTs(), "头数", 1, 10);
                ValidatorUtil.length(equipmentFilePTxtDto.getTxtlx(), "txt鏂囦欢绫诲瀷", 1, 5);
                ValidatorUtil.length(equipmentFilePTxtDto.getWjmc(), "文件名称截取，为了查询出对应的音频，文件，图片等数据", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        equipmentFilePTxtService.save(equipmentFilePTxtDto);
        responseDto.setContent(equipmentFilePTxtDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFilePTxtService.delete(id);
        return responseDto;
    }

}
