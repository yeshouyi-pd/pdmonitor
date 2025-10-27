package com.pd.monitor.controller;

import com.pd.server.main.dto.EquipmentFilePClusterDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFilePClusterService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/equipmentFilePCluster")
public class EquipmentFilePClusterController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFilePClusterController.class);
    public static final String BUSINESS_NAME = "equipment_p_file_聚类 33 35";

    @Resource
    private EquipmentFilePClusterService equipmentFilePClusterService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFilePClusterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFilePClusterDto equipmentFilePClusterDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFilePClusterDto.getBid(), "", 1, 50);
                ValidatorUtil.length(equipmentFilePClusterDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFilePClusterDto.getTplj(), "图片路径", 1, 500);
                ValidatorUtil.length(equipmentFilePClusterDto.getNf(), "采集年份", 1, 20);
                ValidatorUtil.length(equipmentFilePClusterDto.getYf(), "采集月份", 1, 20);
                ValidatorUtil.length(equipmentFilePClusterDto.getRq(), "采集日期", 1, 20);
                ValidatorUtil.length(equipmentFilePClusterDto.getXs(), "采集小时", 1, 20);
                ValidatorUtil.length(equipmentFilePClusterDto.getFz(), "采集分钟", 1, 20);
                ValidatorUtil.length(equipmentFilePClusterDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFilePClusterDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(equipmentFilePClusterDto.getWjlx(), "报警文件类型1图片2音频3文件", 1, 128);
                ValidatorUtil.length(equipmentFilePClusterDto.getJczl(), "监测种类1江豚2白海豚", 1, 1);
                ValidatorUtil.length(equipmentFilePClusterDto.getLy(), "来源1实时数据采集2非实时数据分析", 1, 1);
                ValidatorUtil.length(equipmentFilePClusterDto.getSm1(), "", 1, 6000);
                ValidatorUtil.length(equipmentFilePClusterDto.getSm2(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePClusterDto.getSm3(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePClusterDto.getSm4(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePClusterDto.getSm5(), "", 1, 255);
                ValidatorUtil.length(equipmentFilePClusterDto.getSm6(), "物种类型", 1, 255);
                ValidatorUtil.length(equipmentFilePClusterDto.getType(), "type，codeset表中的文件种类", 1, 50);
                ValidatorUtil.length(equipmentFilePClusterDto.getTs(), "头数", 1, 10);
                ValidatorUtil.length(equipmentFilePClusterDto.getTxtlx(), "txt鏂囦欢绫诲瀷", 1, 5);
                ValidatorUtil.length(equipmentFilePClusterDto.getWjmc(), "文件名称截取，为了查询出对应的音频，文件，图片等数据", 1, 100);
                ValidatorUtil.length(equipmentFilePClusterDto.getKssj(), "开始时间", 1, 100);
                ValidatorUtil.length(equipmentFilePClusterDto.getJssj(), "结束时间", 1, 100);
                ValidatorUtil.length(equipmentFilePClusterDto.getJtnr(), "具体类容", 1, 6000);

        ResponseDto responseDto = new ResponseDto();
        equipmentFilePClusterService.save(equipmentFilePClusterDto);
        responseDto.setContent(equipmentFilePClusterDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFilePClusterService.delete(id);
        return responseDto;
    }

}
