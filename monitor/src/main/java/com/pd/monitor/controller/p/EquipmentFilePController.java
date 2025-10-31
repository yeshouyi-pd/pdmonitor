package com.pd.monitor.controller.p;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.EquipmentFilePPic;
import com.pd.server.main.domain.EquipmentFilePPicExample;
import com.pd.server.main.domain.WaterProEquip;
import com.pd.server.main.domain.WaterProEquipExample;
import com.pd.server.main.dto.EquipmentFilePPicDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.*;
import com.pd.server.util.CopyUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/equipmentFileP")
public class EquipmentFilePController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFilePController.class);
    public static final String BUSINESS_NAME = "";

    /**
     * equipment_p_file_聚类 33 35
     */
    @Resource
    private EquipmentFilePClusterService equipmentFilePClusterService;

    /**
     * equipment_p_file图片pic 10 11
     */
    @Resource
    private EquipmentFilePPicService equipmentFilePPicService;

    /**
     * equipment_p_file文件 31 32 34 36
     */

    @Resource
    private EquipmentFilePTxtService equipmentFilePTxtService;


    /**
     * equipment_p_file_mp4 40"
     */
    @Resource
    private EquipmentFilePVideoService equipmentFilePVideoService;

    /**
     * equipment_p_file音档 20 21
     */
    @Resource
    private EquipmentFilePWavService equipmentFilePWavService;

    @Resource
    private WaterProEquipService waterProEquipService;


    /**
     *
     * @param pageDto
     * @return
     */
    @PostMapping("/lists")
    public ResponseDto lists(@RequestBody EquipmentFilePPicDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFilePPicExample equipmentFileExample = new EquipmentFilePPicExample();
        EquipmentFilePPicExample.Criteria ca = equipmentFileExample.createCriteria();
        if(!StringUtils.isBlank(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isBlank(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        if(!StringUtils.isBlank(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());//
        }
        if(!StringUtils.isBlank(pageDto.getJczl())){
            ca.andJczlEqualTo(pageDto.getJczl());
        }
        equipmentFileExample.setOrderByClause(" cjsj desc ");
        List<EquipmentFilePPic> lists = new ArrayList<>();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            WaterProEquipExample waterProEquipExample = new WaterProEquipExample();
            waterProEquipExample.createCriteria().andXmbhEqualTo(pageDto.getXmbh());
            List<WaterProEquip> waterProEquips = waterProEquipService.selectByExample(waterProEquipExample);
            //根据xmbh 查询关联的设备编号
            if(waterProEquips != null && !waterProEquips.isEmpty()){
                List<String> sbsnList = waterProEquips.stream()
                        .map(WaterProEquip::getSbsn)
                        .filter(sbsn -> !StringUtils.isEmpty(sbsn))
                        .collect(Collectors.toList());
                if(!sbsnList.isEmpty()){
                    ca.andSbbhIn(sbsnList);
                }
            }
        }
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        /**
         * 已经创建
         * sbbh cjsj
         * sbbh rq cjsj
         * rq cjsj
         * 索引
         * 防止深分页回表问题 查ID
         * 后面在根据ID in
         *
         */
        lists = equipmentFilePPicService.selectByExampleOnlyId(equipmentFileExample);
        PageInfo<EquipmentFilePPic> pageInfo = new PageInfo<>(lists);
        pageDto.setTotal(pageInfo.getTotal());

        if(!lists.isEmpty()){
            /**
             * 根据ID查询
             */
            List<Long> ids = lists.stream()
                    .map(EquipmentFilePPic::getId)
                    .collect(Collectors.toList());
            EquipmentFilePPicExample equipmentFileExampleId = new EquipmentFilePPicExample();
            equipmentFileExampleId.createCriteria().andIdIn(ids);
            lists = equipmentFilePPicService.selectByExample(equipmentFileExampleId);
        }

        List<EquipmentFilePPicDto> listDto = CopyUtil.copyList(lists, EquipmentFilePPicDto.class);
        pageDto.setList(listDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }




}
