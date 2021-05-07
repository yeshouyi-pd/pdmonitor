package com.pd.monitor.controller;

import com.pd.server.main.dto.EquipmentFileDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/equipmentFile")
public class EquipmentFileController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileController.class);
    public static final String BUSINESS_NAME = "水噪声图片";

    @Resource
    private EquipmentFileService equipmentFileService;

    /**
     * 统计报警次数
     * @param equipmentFileDto
     * @return
     */

    @PostMapping("/findSbbh")
    public ResponseDto findSbbh(@RequestBody EquipmentFileDto equipmentFileDto){
        ResponseDto responseDto = new ResponseDto();
        List<String> sbbhList = equipmentFileService.findSbbh(equipmentFileDto);
        responseDto.setContent(sbbhList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody EquipmentFileDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileDto equipmentFileDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileDto.getTplj(), "图片路径", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileService.save(equipmentFileDto);
        responseDto.setContent(equipmentFileDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileService.delete(id);
        return responseDto;
    }

}
