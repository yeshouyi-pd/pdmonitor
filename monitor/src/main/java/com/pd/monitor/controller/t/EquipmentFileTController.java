package com.pd.monitor.controller.t;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.EquipmentFileTClusterDto;
import com.pd.server.main.dto.EquipmentFileTPicDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.basewx.my.GpsKVDto;
import com.pd.server.main.service.*;
import com.pd.server.util.CopyUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 拖曳
 */

@RestController
@RequestMapping("/admin/equipmentFileT")
public class EquipmentFileTController {


    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileTController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private WaterProEquipService waterProEquipService;
    @Resource
    private EquipmentFileTClusterService equipmentFileTClusterService;
    @Resource
    private EquipmentFileTPicService equipmentFileTPicService;
    @Resource
    private EquipmentFileTWavService equipmentFileTWavService;
    @Resource
    private EquipmentFileTTxtService equipmentFileTTxtService;
    @Resource
    private EquipmentFileTVideoService equipmentFileTVideoService;

    /**
     * 删除拖曳聚类事件
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTClusterService.delete(id);
        return responseDto;
    }

    /**
     * 修改拖曳聚类的头数
     */
    @PostMapping("/saveTCluster")
    public ResponseDto saveTCluster(@RequestBody EquipmentFileTClusterDto equipmentFileTClusterDto){
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTClusterService.save(equipmentFileTClusterDto);
        return responseDto;
    }

    /**
     * 拖曳设备，路线图页面查询事件的gps展示头数信息
     */
    @PostMapping("/selectGpsByDateRange")
    public ResponseDto selectGpsByDateRange(@RequestBody EquipmentFileTClusterDto equipmentFileTCluster){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileTClusterExample example = new EquipmentFileTClusterExample();
        EquipmentFileTClusterExample.Criteria ca = example.createCriteria();
        if(!org.apache.axis.utils.StringUtils.isEmpty(equipmentFileTCluster.getSbbh())){
            ca.andSbbhEqualTo(equipmentFileTCluster.getSbbh());
        }
        if(!org.apache.axis.utils.StringUtils.isEmpty(equipmentFileTCluster.getStime())){
            ca.andRqGreaterThanOrEqualTo(equipmentFileTCluster.getStime());
        }
        if(!org.apache.axis.utils.StringUtils.isEmpty(equipmentFileTCluster.getEtime())){
            ca.andRqLessThanOrEqualTo(equipmentFileTCluster.getEtime());
        }
        if(!StringUtils.isEmpty(equipmentFileTCluster.getXmbh())){
            WaterProEquipExample waterProEquipExample = new WaterProEquipExample();
            waterProEquipExample.createCriteria().andXmbhEqualTo(equipmentFileTCluster.getXmbh());
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
        List<GpsKVDto> gpsList = equipmentFileTClusterService.selectGpsByExample(example);
        responseDto.setContent(gpsList);
        return responseDto;
    }

    /**
     * 根据采集时间cjsj查询所有的文件(pic，wav，txt，video，pCluster)
     */
    @PostMapping("/allFile")
    public ResponseDto allFile(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        List<Object> resultList = new ArrayList<>();
        EquipmentFileTPicExample pPicExample = new EquipmentFileTPicExample();
        pPicExample.createCriteria().andCjsjEqualTo(jsonObject.getString("cjsj"));
        List<EquipmentFileTPic> pPics = equipmentFileTPicService.selectByExample(pPicExample);
        EquipmentFileTWavExample pWavExample = new EquipmentFileTWavExample();
        pWavExample.createCriteria().andCjsjEqualTo(jsonObject.getString("cjsj"));
        List<EquipmentFileTWav> pWavs = equipmentFileTWavService.selectByExample(pWavExample);
        EquipmentFileTTxtExample pTxtExample = new EquipmentFileTTxtExample();
        pTxtExample.createCriteria().andCjsjEqualTo(jsonObject.getString("cjsj"));
        List<EquipmentFileTTxt> pTxts = equipmentFileTTxtService.selectByExample(pTxtExample);
        EquipmentFileTVideoExample pVideoExample = new EquipmentFileTVideoExample();
        pVideoExample.createCriteria().andCjsjEqualTo(jsonObject.getString("cjsj"));
        List<EquipmentFileTVideo> pVideos = equipmentFileTVideoService.selectByExample(pVideoExample);
        EquipmentFileTClusterExample pClusterExample = new EquipmentFileTClusterExample();
        pClusterExample.createCriteria().andCjsjEqualTo(jsonObject.getString("cjsj"));
        List<EquipmentFileTCluster> pClusters = equipmentFileTClusterService.selectByExample(pClusterExample);
        resultList.addAll(pPics);
        resultList.addAll(pWavs);
        resultList.addAll(pTxts);
        resultList.addAll(pVideos);
        resultList.addAll(pClusters);
        responseDto.setContent(resultList);
        return responseDto;
    }
    
    /**
     *  拖曳的分页查询
     * @param pageDto
     * @return
     */
    @PostMapping("/lists")
    public ResponseDto lists(@RequestBody EquipmentFileTPicDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileTPicExample example = new EquipmentFileTPicExample();
        EquipmentFileTPicExample.Criteria ca = example.createCriteria();
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
        example.setOrderByClause(" cjsj desc ");
        List<EquipmentFileTPic> lists = new ArrayList<>();
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
        lists = equipmentFileTPicService.selectByExampleOnlyId(example);
        PageInfo<EquipmentFileTPic> pageInfo = new PageInfo<>(lists);
        pageDto.setTotal(pageInfo.getTotal());

        if(!lists.isEmpty()){
            /**
             * 根据ID查询
             */
            List<Long> ids = lists.stream()
                    .map(EquipmentFileTPic::getId)
                    .collect(Collectors.toList());
            EquipmentFileTPicExample tPicExample = new EquipmentFileTPicExample();
            tPicExample.createCriteria().andIdIn(ids);
            tPicExample.setOrderByClause(" cjsj desc ");
            lists = equipmentFileTPicService.selectByExample(tPicExample);
        }

        List<EquipmentFileTPicDto> listDto = CopyUtil.copyList(lists, EquipmentFileTPicDto.class);
        pageDto.setList(listDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     *  拖曳聚类的分页查询
     * @param pageDto
     * @return
     */
    @PostMapping("/listClusters")
    public ResponseDto listClusters(@RequestBody EquipmentFileTClusterDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileTClusterExample example = new EquipmentFileTClusterExample();
        EquipmentFileTClusterExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isBlank(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isBlank(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        if(!StringUtils.isBlank(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());//
        }
        example.setOrderByClause(" cjsj desc ");
        List<EquipmentFileTCluster> lists = new ArrayList<>();
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
        lists = equipmentFileTClusterService.selectByExampleOnlyId(example);
        PageInfo<EquipmentFileTCluster> pageInfo = new PageInfo<>(lists);
        pageDto.setTotal(pageInfo.getTotal());

        if(!lists.isEmpty()){
            /**
             * 根据ID查询
             */
            List<Long> ids = lists.stream()
                    .map(EquipmentFileTCluster::getId)
                    .collect(Collectors.toList());
            EquipmentFileTClusterExample tClusterExample = new EquipmentFileTClusterExample();
            tClusterExample.createCriteria().andIdIn(ids);
            tClusterExample.setOrderByClause(" cjsj desc ");
            lists = equipmentFileTClusterService.selectByExample(tClusterExample);
        }

        List<EquipmentFileTClusterDto> listDto = CopyUtil.copyList(lists, EquipmentFileTClusterDto.class);
        pageDto.setList(listDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
}
