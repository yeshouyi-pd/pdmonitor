package com.pd.monitor.controller;

import com.pd.server.main.dto.EquipmentFileTTxtDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFileTTxtService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/equipmentFileTTxt")
public class EquipmentFileTTxtController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileTTxtController.class);
    public static final String BUSINESS_NAME = "equipment_t_file文件 31 32 34 36";

    @Resource
    private EquipmentFileTTxtService equipmentFileTTxtService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTTxtService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileTTxtDto equipmentFileTTxtDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileTTxtDto.getBid(), "", 1, 50);
                ValidatorUtil.length(equipmentFileTTxtDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileTTxtDto.getTplj(), "图片路径", 1, 500);
                ValidatorUtil.length(equipmentFileTTxtDto.getNf(), "采集年份", 1, 20);
                ValidatorUtil.length(equipmentFileTTxtDto.getYf(), "采集月份", 1, 20);
                ValidatorUtil.length(equipmentFileTTxtDto.getRq(), "采集日期", 1, 20);
                ValidatorUtil.length(equipmentFileTTxtDto.getXs(), "采集小时", 1, 20);
                ValidatorUtil.length(equipmentFileTTxtDto.getFz(), "采集分钟", 1, 20);
                ValidatorUtil.length(equipmentFileTTxtDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFileTTxtDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(equipmentFileTTxtDto.getWjlx(), "报警文件类型1图片2音频3文件", 1, 128);
                ValidatorUtil.length(equipmentFileTTxtDto.getJczl(), "监测种类1江豚2白海豚", 1, 80);
                ValidatorUtil.length(equipmentFileTTxtDto.getLy(), "来源1实时数据采集2非实时数据分析", 1, 1);
                ValidatorUtil.length(equipmentFileTTxtDto.getSm1(), "", 1, 6000);
                ValidatorUtil.length(equipmentFileTTxtDto.getSm2(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTTxtDto.getSm3(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTTxtDto.getSm4(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTTxtDto.getSm5(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTTxtDto.getSm6(), "物种类型", 1, 255);
                ValidatorUtil.length(equipmentFileTTxtDto.getType(), "type，codeset表中的文件种类", 1, 50);
                ValidatorUtil.length(equipmentFileTTxtDto.getTs(), "头数", 1, 10);
                ValidatorUtil.length(equipmentFileTTxtDto.getTxtlx(), "txt鏂囦欢绫诲瀷", 1, 5);
                ValidatorUtil.length(equipmentFileTTxtDto.getWjmc(), "文件名称截取，为了查询出对应的音频，文件，图片等数据", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileTTxtService.save(equipmentFileTTxtDto);
        responseDto.setContent(equipmentFileTTxtDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTTxtService.delete(id);
        return responseDto;
    }

}
