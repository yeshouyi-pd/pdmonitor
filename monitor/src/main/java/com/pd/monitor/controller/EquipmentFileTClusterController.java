package com.pd.monitor.controller;

import com.pd.server.main.dto.EquipmentFileTClusterDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFileTClusterService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/equipmentFileTCluster")
public class EquipmentFileTClusterController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileTClusterController.class);
    public static final String BUSINESS_NAME = "equipment_t_file_聚类 33 35";

    @Resource
    private EquipmentFileTClusterService equipmentFileTClusterService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTClusterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileTClusterDto equipmentFileTClusterDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileTClusterDto.getBid(), "", 1, 50);
                ValidatorUtil.length(equipmentFileTClusterDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileTClusterDto.getTplj(), "图片路径", 1, 500);
                ValidatorUtil.length(equipmentFileTClusterDto.getNf(), "采集年份", 1, 20);
                ValidatorUtil.length(equipmentFileTClusterDto.getYf(), "采集月份", 1, 20);
                ValidatorUtil.length(equipmentFileTClusterDto.getRq(), "采集日期", 1, 20);
                ValidatorUtil.length(equipmentFileTClusterDto.getXs(), "采集小时", 1, 20);
                ValidatorUtil.length(equipmentFileTClusterDto.getFz(), "采集分钟", 1, 20);
                ValidatorUtil.length(equipmentFileTClusterDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFileTClusterDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(equipmentFileTClusterDto.getWjlx(), "报警文件类型1图片2音频3文件", 1, 128);
                ValidatorUtil.length(equipmentFileTClusterDto.getJczl(), "监测种类1江豚2白海豚", 1, 80);
                ValidatorUtil.length(equipmentFileTClusterDto.getLy(), "来源1实时数据采集2非实时数据分析", 1, 1);
                ValidatorUtil.length(equipmentFileTClusterDto.getSm1(), "", 1, 6000);
                ValidatorUtil.length(equipmentFileTClusterDto.getSm2(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTClusterDto.getSm3(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTClusterDto.getSm4(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTClusterDto.getSm5(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTClusterDto.getSm6(), "物种类型", 1, 255);
                ValidatorUtil.length(equipmentFileTClusterDto.getType(), "type，codeset表中的文件种类", 1, 50);
                ValidatorUtil.length(equipmentFileTClusterDto.getTs(), "头数", 1, 10);
                ValidatorUtil.length(equipmentFileTClusterDto.getTxtlx(), "txt鏂囦欢绫诲瀷", 1, 5);
                ValidatorUtil.length(equipmentFileTClusterDto.getWjmc(), "文件名称截取，为了查询出对应的音频，文件，图片等数据", 1, 100);
                ValidatorUtil.length(equipmentFileTClusterDto.getKssj(), "开始时间", 1, 100);
                ValidatorUtil.length(equipmentFileTClusterDto.getJssj(), "结束时间", 1, 100);
                ValidatorUtil.length(equipmentFileTClusterDto.getJtnr(), "具体类容", 1, 2000);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileTClusterService.save(equipmentFileTClusterDto);
        responseDto.setContent(equipmentFileTClusterDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTClusterService.delete(id);
        return responseDto;
    }

}
