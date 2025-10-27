package com.pd.monitor.controller;

import com.pd.server.main.dto.EquipmentFileTVideoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFileTVideoService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/equipmentFileTVideo")
public class EquipmentFileTVideoController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileTVideoController.class);
    public static final String BUSINESS_NAME = "equipment_t_file_mp4 40";

    @Resource
    private EquipmentFileTVideoService equipmentFileTVideoService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTVideoService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileTVideoDto equipmentFileTVideoDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileTVideoDto.getBid(), "", 1, 50);
                ValidatorUtil.length(equipmentFileTVideoDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileTVideoDto.getTplj(), "图片路径", 1, 500);
                ValidatorUtil.length(equipmentFileTVideoDto.getNf(), "采集年份", 1, 20);
                ValidatorUtil.length(equipmentFileTVideoDto.getYf(), "采集月份", 1, 20);
                ValidatorUtil.length(equipmentFileTVideoDto.getRq(), "采集日期", 1, 20);
                ValidatorUtil.length(equipmentFileTVideoDto.getXs(), "采集小时", 1, 20);
                ValidatorUtil.length(equipmentFileTVideoDto.getFz(), "采集分钟", 1, 20);
                ValidatorUtil.length(equipmentFileTVideoDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFileTVideoDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(equipmentFileTVideoDto.getWjlx(), "报警文件类型1图片2音频3文件", 1, 128);
                ValidatorUtil.length(equipmentFileTVideoDto.getJczl(), "监测种类1江豚2白海豚", 1, 80);
                ValidatorUtil.length(equipmentFileTVideoDto.getLy(), "来源1实时数据采集2非实时数据分析", 1, 1);
                ValidatorUtil.length(equipmentFileTVideoDto.getSm1(), "", 1, 6000);
                ValidatorUtil.length(equipmentFileTVideoDto.getSm2(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTVideoDto.getSm3(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTVideoDto.getSm4(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTVideoDto.getSm5(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTVideoDto.getSm6(), "物种类型", 1, 255);
                ValidatorUtil.length(equipmentFileTVideoDto.getType(), "type，codeset表中的文件种类", 1, 50);
                ValidatorUtil.length(equipmentFileTVideoDto.getTs(), "头数", 1, 10);
                ValidatorUtil.length(equipmentFileTVideoDto.getTxtlx(), "txt鏂囦欢绫诲瀷", 1, 5);
                ValidatorUtil.length(equipmentFileTVideoDto.getWjmc(), "文件名称截取，为了查询出对应的音频，文件，图片等数据", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileTVideoService.save(equipmentFileTVideoDto);
        responseDto.setContent(equipmentFileTVideoDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTVideoService.delete(id);
        return responseDto;
    }

}
