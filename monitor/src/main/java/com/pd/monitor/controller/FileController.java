package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.exception.BusinessException;
import com.pd.server.exception.BusinessExceptionCode;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.FileAndFileinfoDto;
import com.pd.server.main.dto.FileDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.mapper.my.MyFileMapper;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.main.service.WaterProEquipService;
import com.pd.server.main.service.WaterProUserService;
import com.pd.server.main.service.WaterProjectService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * 预警文件上传分析 --界面端
 */
@RestController
@RequestMapping("/admin/file")
public class FileController   extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    public static final String BUSINESS_NAME = "预警文件上传分析";

    /**
     * 项目设备关联表
     */
    @Resource
    private WaterProEquipService waterProEquipService;

    /**
     * 项目人员用户关联表
     */
    @Resource
    private WaterProUserService waterProUserService;

    /**
     * 项目表
     */
    @Resource
    private WaterProjectService waterProjectService;

    /**
     * 设备名称
     */
    @Resource
    private WaterEquipmentService waterEquipmentService;



    @Resource
    private MyFileMapper myFileMapper;



    /**
     * 设备初始化 可以：value
     */
    @GetMapping("/queryallEquip")
    public ResponseDto queryallEquip() {
        ResponseDto responseDto = new ResponseDto();
        List<WaterEquipment> waterEquipment = waterEquipmentService.list(new WaterEquipmentExample());
        Map<String,String> map = new LinkedHashMap<String,String>();
        for(WaterEquipment vo : waterEquipment){
            if(StringUtils.isNotBlank(vo.getSbsn()) &&
                    StringUtils.isNotBlank(vo.getSbmc())){
                map.put(vo.getSbsn(),vo.getSbmc());
            }
        }
        responseDto.setContent(map);
        return responseDto;

    }



    /**
     * 项目初始化 可以：value
     */
    @GetMapping("/queryallProject")
    public ResponseDto queryallProject() {
        ResponseDto responseDto = new ResponseDto();
        List<WaterProject> waterProjects = waterProjectService.selectByExample(new WaterProjectExample());
        Map<String,String> map = new LinkedHashMap<String,String>();
        for(WaterProject vo : waterProjects){
            if(StringUtils.isNotBlank(vo.getXmbh()) &&
                    StringUtils.isNotBlank(vo.getXmmc())){
                map.put(vo.getXmbh(),vo.getXmmc());
            }
        }
        responseDto.setContent(map);
        return responseDto;

    }


    /**
     * 根据当前登录人员选择项目
     */
    @PostMapping("/queryProject")
    public ResponseDto queryProject(@RequestBody WaterProject wterProject) {
        ResponseDto responseDto = new ResponseDto();

        LoginUserDto loginUserDto = getRequestHeader();
        String usercode = loginUserDto.getLoginName();
        if(StringUtils.isBlank(usercode)){
            throw new WxStrException("登录超时！请重新登录");
        }

        List<String> liststr  = new ArrayList<String>();
        if(StringUtils.isNotBlank(wterProject.getXmmc())){
            WaterProjectExample example = new WaterProjectExample();
            WaterProjectExample.Criteria ca =  example.createCriteria();
            ca.andXmmcLike("%"+wterProject.getXmmc().trim()+"%");
            List<WaterProject> waterProjects = waterProjectService.selectByExample(example);
            if(!CollectionUtils.isEmpty(waterProjects)){
                for (WaterProject vo :waterProjects){
                    liststr.add(vo.getXmbh());
                }
            }
        }
        WaterProUserExample waterProUserExample = new WaterProUserExample();
        WaterProUserExample.Criteria  waterProUserca = waterProUserExample.createCriteria();
        waterProUserca.andUsercodeEqualTo(usercode);
        if(!CollectionUtils.isEmpty(liststr)){
            waterProUserca.andXmbhIn(liststr);
        }
        waterProUserExample.setOrderByClause(" cjsj desc");
        List<WaterProUser> waterProUsers = waterProUserService.selectByExample(waterProUserExample);
        waterProUsers = waterProUsers.stream().filter(distinctByKey(WaterProUser::getXmbh)).collect(Collectors.toList());
        responseDto.setContent(waterProUsers);
        return responseDto;

    }

    private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> concurrentHashMap = new ConcurrentHashMap<>();
        return t -> concurrentHashMap.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 查询设备 -- 根据项目
     */
    @PostMapping("/queryEquip")
    public ResponseDto queryEquip(@RequestBody WaterProEquip waterProEquip) {
        ResponseDto responseDto = new ResponseDto();
        WaterProEquipExample waterProEquipExample = new WaterProEquipExample();
        WaterProEquipExample.Criteria waterProEquipca = waterProEquipExample.createCriteria();
        waterProEquipca.andXmbhEqualTo(waterProEquip.getXmbh());
        List<WaterProEquip> waterProEquips = waterProEquipService.selectByExample(waterProEquipExample);
        responseDto.setContent(waterProEquips);
        return responseDto;

    }

    /**
     * 查看上传文件信息
     * @param fileDto
     * @return
     */
    @PostMapping("/fileAndFileinfo")
    public ResponseDto fileAndFileinfo(@RequestBody FileDto fileDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        String usercode = loginUserDto.getLoginName();
        if(StringUtils.isBlank(usercode)){
            throw new WxStrException("登录超时！请重新登录");
        }
        fileDto.setUsercode(usercode);
        List<FileAndFileinfoDto> query = myFileMapper.query(fileDto);
        responseDto.setContent(query);
        return responseDto;
    }


}
