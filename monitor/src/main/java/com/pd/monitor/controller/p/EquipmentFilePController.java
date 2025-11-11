package com.pd.monitor.controller.p;



import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.utils.CalculateUtil;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.service.*;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;
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
     * 鲸豚出现统计
     * 按小时统计，根据设备编号，小时进行分组统计出现次数
     * 然后小时出现次数/总次数，统计百分比
     */
    @PostMapping("/statisticsAlarmNumsByHour")
    public ResponseDto statisticsAlarmNumsByHour(@RequestBody AlarmNumbersDto entityDto){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFilePPicExample example = new EquipmentFilePPicExample();
        EquipmentFilePPicExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(entityDto.getSbbh())){
            ca.andSbbhEqualTo(entityDto.getSbbh());
        }
        if(!StringUtils.isEmpty(entityDto.getDeptcode())){
            ca.andDeptcodeEqualTo(entityDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(entityDto.getStime())){
            ca.andXsGreaterThanOrEqualTo(entityDto.getStime()+" 00");
        }
        if(!StringUtils.isEmpty(entityDto.getEtime())){
            ca.andXsLessThanOrEqualTo(entityDto.getEtime()+" 23");
        }
        List<AlarmNumbersDto> lists = equipmentFilePPicService.statisticsAlarmNumsByHour(example);
        Optional<Integer> op = lists.stream().filter(Objects::nonNull).map(AlarmNumbersDto::getAlarmNum).reduce(Integer::sum);
        List<String> xAixsData = lists.stream().filter(Objects::nonNull).map(u->u.getXs()).collect(Collectors.toList());
        List<String> yAixsData = new ArrayList<>();
        if(op.isPresent()){
            yAixsData = lists.stream().filter(Objects::nonNull).map(u-> CalculateUtil.calculateResultOfPercent(u.getAlarmNum(),op.get())).collect(Collectors.toList());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xAixsData",xAixsData);
        map.put("yAixsData",yAixsData);
        responseDto.setContent(map);
        return responseDto;
    }



    /**
     * 鲸豚出现统计
     * 按分钟统计，根据设备编号，分钟进行分组统计出现次数 group by sbbh, fz
     */
    @PostMapping("/statisticsAlarmNumsByMinute")
    public ResponseDto statisticsAlarmNumsByMinute(@RequestBody AlarmNumbersDto entityDto){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFilePPicExample example = new EquipmentFilePPicExample();
        EquipmentFilePPicExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(entityDto.getSbbh())){
            ca.andSbbhEqualTo(entityDto.getSbbh());
        }
        if(!StringUtils.isEmpty(entityDto.getDeptcode())){
            ca.andDeptcodeEqualTo(entityDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(entityDto.getStime())){
            ca.andFzGreaterThanOrEqualTo(entityDto.getStime());
        }
        if(!StringUtils.isEmpty(entityDto.getEtime())){
            ca.andFzLessThanOrEqualTo(entityDto.getEtime());
        }
        List<AlarmNumbersDto> lists = equipmentFilePPicService.statisticsAlarmNumsByMinute(example);
        List<String> xAixsData = lists.stream().filter(Objects::nonNull).map(u->u.getFz()).collect(Collectors.toList());
        List<Integer> yAixsData = lists.stream().filter(Objects::nonNull).map(u-> u.getAlarmNum()).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("xAixsData",xAixsData);
        map.put("yAixsData",yAixsData);
        responseDto.setContent(map);
        return responseDto;
    }


    /**
     * 岳阳无人机视频选择关联聚类
     * 无人机视频管理模块，目前只有岳阳的设备有这个模块
     */
    @PostMapping("/yueYangChooseEvent")
    public ResponseDto yueYangChooseEvent(@RequestBody EquipmentFilePClusterDto equipmentFilePClusterDto){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFilePClusterExample example = new EquipmentFilePClusterExample();
        EquipmentFilePClusterExample.Criteria ca = example.createCriteria();
        Map<String, String> attrMap = WxRedisConfig.getAttrMap();
        if(!StringUtils.isEmpty(equipmentFilePClusterDto.getSbbh())){
            ca.andSbbhEqualTo(equipmentFilePClusterDto.getSbbh());
        }else{
            ca.andSbbhIn(Arrays.asList(attrMap.get("yueYangSbbh").split(",")));
        }
        ca.andKssjGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getMinutesLater(DateUtil.toDate(equipmentFilePClusterDto.getEtime(),"yyyy-MM-dd HH:mm:ss"),-10),"yyyy-MM-dd HH:mm:ss"));
        ca.andKssjLessThanOrEqualTo(equipmentFilePClusterDto.getEtime()+" 23:59:59");
        example.setOrderByClause(" Kssj desc ");
        PageHelper.startPage(equipmentFilePClusterDto.getPage(), equipmentFilePClusterDto.getSize());
        List<EquipmentFilePCluster> lists = equipmentFilePClusterService.selectByExample(example);
        PageInfo<EquipmentFilePCluster> pageInfo = new PageInfo<>(lists);
        equipmentFilePClusterDto.setTotal(pageInfo.getTotal());
        List<EquipmentFilePClusterDto> listDto = CopyUtil.copyList(lists, EquipmentFilePClusterDto.class);
        equipmentFilePClusterDto.setList(listDto);
        responseDto.setContent(equipmentFilePClusterDto);
        return responseDto;
    }

    /**
     * 岳阳无人机视频选择关联声谱图
     * 无人机视频管理模块，目前只有岳阳的设备有这个模块
     */
    @PostMapping("/yueYangChoose")
    public ResponseDto yueYangChoose(@RequestBody EquipmentFilePPicDto equipmentFilePPicDto){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFilePPicExample example = new EquipmentFilePPicExample();
        EquipmentFilePPicExample.Criteria ca = example.createCriteria();
        Map<String, String> attrMap = WxRedisConfig.getAttrMap();
        if(!StringUtils.isEmpty(equipmentFilePPicDto.getSbbh())){
            ca.andSbbhEqualTo(equipmentFilePPicDto.getSbbh());
        }else{
            ca.andSbbhIn(Arrays.asList(attrMap.get("yueYangSbbh").split(",")));
        }
        ca.andCjsjGreaterThanOrEqualTo(DateUtil.getMinutesLater(DateUtil.toDate(equipmentFilePPicDto.getEtime(),"yyyy-MM-dd HH:mm:ss"),-10));
        ca.andCjsjLessThanOrEqualTo(equipmentFilePPicDto.getEtime()+" 23:59:59");
        example.setOrderByClause(" cjsj desc ");
        PageHelper.startPage(equipmentFilePPicDto.getPage(), equipmentFilePPicDto.getSize());
        List<EquipmentFilePPic> lists = equipmentFilePPicService.selectByExample(example);
        PageInfo<EquipmentFilePPic> pageInfo = new PageInfo<>(lists);
        equipmentFilePPicDto.setTotal(pageInfo.getTotal());
        List<EquipmentFilePPicDto> listDto = CopyUtil.copyList(lists, EquipmentFilePPicDto.class);
        equipmentFilePPicDto.setList(listDto);
        responseDto.setContent(equipmentFilePPicDto);
        return responseDto;
    }

    /**
     * 根据采集时间cjsj查询视频video
     */
    @PostMapping("/videoList")
    public ResponseDto videoList(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFilePVideoExample pVideoExample = new EquipmentFilePVideoExample();
        pVideoExample.createCriteria().andCjsjEqualTo(jsonObject.getString("cjsj"));
        List<EquipmentFilePVideo> pClusters = equipmentFilePVideoService.selectByExample(pVideoExample);
        responseDto.setContent(pClusters);
        return responseDto;
    }

    /**
     * 根据采集时间cjsj查询所有的文件(pic，wav，txt，video，pCluster)
     */
    @PostMapping("/allFile")
    public ResponseDto allFile(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        List<Object> resultList = new ArrayList<>();
        EquipmentFilePPicExample pPicExample = new EquipmentFilePPicExample();
        pPicExample.createCriteria().andCjsjEqualTo(jsonObject.getString("cjsj"));
        List<EquipmentFilePPic> pPics = equipmentFilePPicService.selectByExample(pPicExample);
        EquipmentFilePWavExample pWavExample = new EquipmentFilePWavExample();
        pWavExample.createCriteria().andCjsjEqualTo(jsonObject.getString("cjsj"));
        List<EquipmentFilePWav> pWavs = equipmentFilePWavService.selectByExample(pWavExample);
        EquipmentFilePTxtExample pTxtExample = new EquipmentFilePTxtExample();
        pTxtExample.createCriteria().andCjsjEqualTo(jsonObject.getString("cjsj"));
        List<EquipmentFilePTxt> pTxts = equipmentFilePTxtService.selectByExample(pTxtExample);
        EquipmentFilePVideoExample pVideoExample = new EquipmentFilePVideoExample();
        pVideoExample.createCriteria().andCjsjEqualTo(jsonObject.getString("cjsj"));
        List<EquipmentFilePVideo> pVideos = equipmentFilePVideoService.selectByExample(pVideoExample);
        EquipmentFilePClusterExample pClusterExample = new EquipmentFilePClusterExample();
        pClusterExample.createCriteria().andCjsjEqualTo(jsonObject.getString("cjsj"));
        List<EquipmentFilePCluster> pClusters = equipmentFilePClusterService.selectByExample(pClusterExample);
        resultList.addAll(pPics);
        resultList.addAll(pWavs);
        resultList.addAll(pTxts);
        resultList.addAll(pVideos);
        resultList.addAll(pClusters);
        responseDto.setContent(resultList);
        return responseDto;
    }

    /**
     *  图片的分页查询
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
            equipmentFileExampleId.setOrderByClause(" cjsj desc ");
            lists = equipmentFilePPicService.selectByExample(equipmentFileExampleId);
        }

        List<EquipmentFilePPicDto> listDto = CopyUtil.copyList(lists, EquipmentFilePPicDto.class);
        pageDto.setList(listDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }


    /**
     *  聚类事件的分页查询
     * @param pageDto
     * @return
     */
    @PostMapping("/listClusters")
    public ResponseDto listClusters(@RequestBody EquipmentFilePPicDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFilePClusterExample clusterExample = new EquipmentFilePClusterExample();
        EquipmentFilePClusterExample.Criteria ca = clusterExample.createCriteria();
        if(!StringUtils.isBlank(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isBlank(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        if(!StringUtils.isBlank(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());//
        }
        clusterExample.setOrderByClause(" kssj desc ");
        List<EquipmentFilePCluster> lists = new ArrayList<>();
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
        lists = equipmentFilePClusterService.selectByExampleOnlyId(clusterExample);
        PageInfo<EquipmentFilePCluster> pageInfo = new PageInfo<>(lists);
        pageDto.setTotal(pageInfo.getTotal());

        if(!lists.isEmpty()){
            /**
             * 根据ID查询
             */
            List<Long> ids = lists.stream()
                    .map(EquipmentFilePCluster::getId)
                    .collect(Collectors.toList());
            EquipmentFilePClusterExample equipmentFileExampleId = new EquipmentFilePClusterExample();
            equipmentFileExampleId.createCriteria().andIdIn(ids);
            equipmentFileExampleId.setOrderByClause(" kssj desc ");
            lists = equipmentFilePClusterService.selectByExample(equipmentFileExampleId);
        }

        List<EquipmentFilePClusterDto> listDto = CopyUtil.copyList(lists, EquipmentFilePClusterDto.class);
        pageDto.setList(listDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }




}
