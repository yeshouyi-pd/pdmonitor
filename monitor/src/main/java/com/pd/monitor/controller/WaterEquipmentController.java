package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.MonitorEquipmentDto;
import com.pd.server.main.dto.WaterEquipmentDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.*;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateTools;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/waterEquipment")
public class WaterEquipmentController  extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterEquipmentController.class);
    public static final String BUSINESS_NAME = "设备管理";

    @Resource
    private WaterEquipmentService waterEquipmentService;
    @Resource
    private DeptService deptService;
    @Resource
    private CodesetService codesetService;
    @Resource
    private WaterEquiplogService waterEquiplogService;
    @Resource
    private AttrService attrService;

    /**
     * 监测点数据中心树
     */
    @GetMapping("/findMonitorEquipmentTreeByFile")
    public ResponseDto findMonitorEquipmentTreeByFile(){
        ResponseDto responseDto = new ResponseDto();
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria deptca = deptExample.createCriteria();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        if(!CollectionUtils.isEmpty(list)){
            deptca.andDeptcodeIn(list);
        }
        List<Dept> waterDataList = deptService.list(deptExample);
        Map<String, String> map = waterDataList.stream().collect(Collectors.toMap(p -> p.getDeptcode(), p -> p.getDeptname()));
        WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria weCa = waterEquipmentExample.createCriteria();
        weCa.andSblbEqualTo("0001");
        if(!CollectionUtils.isEmpty(list)){
            weCa.andDeptcodeIn(list);
        }
        List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
        Map<String,List<WaterEquipment>> deptcodeMap = waterEquipmentList.stream().collect(Collectors.groupingBy(WaterEquipment::getDeptcode));
        List<MonitorEquipmentDto> lists = new ArrayList<>();
        for(String key : deptcodeMap.keySet()){
            MonitorEquipmentDto entity = new MonitorEquipmentDto();
            List<MonitorEquipmentDto> childrenList = new ArrayList<>();
            entity.setName(map.get(key));
            entity.setCode(key);
            entity.setType("1");
            entity.setChildren(childrenList);
            List<WaterEquipment> waterEquipments = deptcodeMap.get(key);
            for(WaterEquipment item : waterEquipments){
                MonitorEquipmentDto obj = new MonitorEquipmentDto();
                obj.setName(item.getSbmc()+"("+item.getSbsn()+")");
                obj.setCode(item.getSbsn());
                obj.setType("2");
                childrenList.add(obj);
            }
            lists.add(entity);
        }
        responseDto.setContent(lists);
        return responseDto;
    }

    /**
     * 监测点数据中心树
     */
    @GetMapping("/findMonitorEquipmentTreeByFile/{xmbh}")
    public ResponseDto findMonitorEquipmentTreeByFile(@PathVariable String xmbh){
        ResponseDto responseDto = new ResponseDto();
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria deptca = deptExample.createCriteria();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        if(!CollectionUtils.isEmpty(list)){
            deptca.andDeptcodeIn(list);
        }
        List<Dept> waterDataList = deptService.list(deptExample);
        Map<String, String> map = waterDataList.stream().collect(Collectors.toMap(p -> p.getDeptcode(), p -> p.getDeptname()));
        WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria weCa = waterEquipmentExample.createCriteria();
        weCa.andSblbEqualTo("0001");
        if(!CollectionUtils.isEmpty(list)){
            weCa.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(xmbh)){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(xmbh))){
                weCa.andSbsnIn(user.getXmbhsbsns().get(xmbh));
            }
        }
        List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
        Map<String,List<WaterEquipment>> deptcodeMap = waterEquipmentList.stream().collect(Collectors.groupingBy(WaterEquipment::getDeptcode));
        List<MonitorEquipmentDto> lists = new ArrayList<>();
        for(String key : deptcodeMap.keySet()){
            MonitorEquipmentDto entity = new MonitorEquipmentDto();
            List<MonitorEquipmentDto> childrenList = new ArrayList<>();
            entity.setName(map.get(key));
            entity.setCode(key);
            entity.setType("1");
            entity.setChildren(childrenList);
            List<WaterEquipment> waterEquipments = deptcodeMap.get(key);
            for(WaterEquipment item : waterEquipments){
                MonitorEquipmentDto obj = new MonitorEquipmentDto();
                obj.setName(item.getSbmc()+"("+item.getSbsn()+")");
                obj.setCode(item.getSbsn());
                obj.setType("2");
                childrenList.add(obj);
            }
            lists.add(entity);
        }
        responseDto.setContent(lists);
        return responseDto;
    }

    /**
     * 监测点数据中心树
     */
    @GetMapping("/findMonitorEqupmentTree")
    public ResponseDto findMonitorEquipmentTree(){
        ResponseDto responseDto = new ResponseDto();
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria deptca = deptExample.createCriteria();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        if(!CollectionUtils.isEmpty(list)){
            deptca.andDeptcodeIn(list);
        }
        List<Dept> waterDataList = deptService.list(deptExample);
        Map<String, String> map = waterDataList.stream().collect(Collectors.toMap(p -> p.getDeptcode(), p -> p.getDeptname()));
        WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria weCa = waterEquipmentExample.createCriteria();
        weCa.andSblbEqualTo("0002");
        if (!CollectionUtils.isEmpty(list)){
            weCa.andDeptcodeIn(list);
        }
        List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
        Map<String,List<WaterEquipment>> deptcodeMap = waterEquipmentList.stream().collect(Collectors.groupingBy(WaterEquipment::getDeptcode));
        List<MonitorEquipmentDto> lists = new ArrayList<>();
        for(String key : deptcodeMap.keySet()){
            MonitorEquipmentDto entity = new MonitorEquipmentDto();
            List<MonitorEquipmentDto> childrenList = new ArrayList<>();
            entity.setName(map.get(key));
            entity.setCode(key);
            entity.setType("1");
            entity.setChildren(childrenList);
            List<WaterEquipment> waterEquipments = deptcodeMap.get(key);
            for(WaterEquipment item : waterEquipments){
                MonitorEquipmentDto obj = new MonitorEquipmentDto();
                obj.setName(item.getSbmc()+"("+item.getSbsn()+")");
                obj.setCode(item.getId());
                obj.setType("2");
                childrenList.add(obj);
            }
            lists.add(entity);
        }
        responseDto.setContent(lists);
        return responseDto;
    }

    /**
     * 监测点数据中心树
     */
    @GetMapping("/findMonitorEqupmentTree/{xmbh}")
    public ResponseDto findMonitorEquipmentTree(@PathVariable String xmbh){
        ResponseDto responseDto = new ResponseDto();
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria deptca = deptExample.createCriteria();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        if(!CollectionUtils.isEmpty(list)){
            deptca.andDeptcodeIn(list);
        }
        List<Dept> waterDataList = deptService.list(deptExample);
        Map<String, String> map = waterDataList.stream().collect(Collectors.toMap(p -> p.getDeptcode(), p -> p.getDeptname()));
        WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria weCa = waterEquipmentExample.createCriteria();
        weCa.andSblbEqualTo("0002");
        if (!CollectionUtils.isEmpty(list)){
            weCa.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(xmbh)){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(xmbh))){
                weCa.andSbsnIn(user.getXmbhsbsns().get(xmbh));
            }
        }
        List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
        Map<String,List<WaterEquipment>> deptcodeMap = waterEquipmentList.stream().collect(Collectors.groupingBy(WaterEquipment::getDeptcode));
        List<MonitorEquipmentDto> lists = new ArrayList<>();
        for(String key : deptcodeMap.keySet()){
            MonitorEquipmentDto entity = new MonitorEquipmentDto();
            List<MonitorEquipmentDto> childrenList = new ArrayList<>();
            entity.setName(map.get(key));
            entity.setCode(key);
            entity.setType("1");
            entity.setChildren(childrenList);
            List<WaterEquipment> waterEquipments = deptcodeMap.get(key);
            for(WaterEquipment item : waterEquipments){
                MonitorEquipmentDto obj = new MonitorEquipmentDto();
                obj.setName(item.getSbmc()+"("+item.getSbsn()+")");
                obj.setCode(item.getId());
                obj.setType("2");
                childrenList.add(obj);
            }
            lists.add(entity);
        }
        responseDto.setContent(lists);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/findAll")
    public ResponseDto findAll(@RequestBody WaterEquipmentDto waterEquipmentDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = waterEquipmentExample.createCriteria();
        ca.andGpsIsNotNull();
        if(!CollectionUtils.isEmpty(list)){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(waterEquipmentDto.getSblb())){
            ca.andSblbEqualTo(waterEquipmentDto.getSblb());
        }
        if(!StringUtils.isEmpty(waterEquipmentDto.getXmbh())){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(waterEquipmentDto.getXmbh()))){
                ca.andSbsnIn(user.getXmbhsbsns().get(waterEquipmentDto.getXmbh()));
            }
        }
        List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
        List<WaterEquipmentDto> waterEquipmentDtoList = CopyUtil.copyList(waterEquipmentList, WaterEquipmentDto.class);
        responseDto.setContent(waterEquipmentDtoList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WaterEquipmentDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = waterEquipmentExample.createCriteria();
        if(!CollectionUtils.isEmpty(list)){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(pageDto.getSbsn())){
            ca.andSbsnEqualTo(pageDto.getSbsn());
        }
        if(!StringUtils.isEmpty(pageDto.getSblb())){
            ca.andSblbEqualTo(pageDto.getSblb());
        }
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(loginUserDto.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbsnIn(loginUserDto.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
        PageInfo<WaterEquipment> pageInfo = new PageInfo<>(waterEquipmentList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaterEquipmentDto> waterEquipmentDtoList = CopyUtil.copyList(waterEquipmentList, WaterEquipmentDto.class);
        pageDto.setList(waterEquipmentDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterEquipmentDto waterEquipmentDto) {
        // 保存校验
                ValidatorUtil.require(waterEquipmentDto.getSbmc(), "设备名称");
                ValidatorUtil.length(waterEquipmentDto.getSbmc(), "设备名称", 1, 128);
                ValidatorUtil.length(waterEquipmentDto.getSbsn(), "设备SN", 1, 128);
                //ValidatorUtil.require(waterEquipmentDto.getPort(), "设备端口");
                //ValidatorUtil.require(waterEquipmentDto.getIp(), "设备IP");
                ValidatorUtil.length(waterEquipmentDto.getIp(), "设备IP", 1, 45);
                ValidatorUtil.require(waterEquipmentDto.getDeptcode(), "所属监测点");
                ValidatorUtil.length(waterEquipmentDto.getDeptcode(), "所属监测点", 1, 45);
                ValidatorUtil.require(waterEquipmentDto.getCenterCode(), "所属数据中心");
                ValidatorUtil.length(waterEquipmentDto.getCenterCode(), "所属数据中心", 1, 45);
                //ValidatorUtil.require(waterEquipmentDto.getSblb(), "设备类别");
                ValidatorUtil.length(waterEquipmentDto.getSblb(), "设备类别", 1, 45);
                //ValidatorUtil.require(waterEquipmentDto.getDqzl(), "设备读取指令");
                ValidatorUtil.length(waterEquipmentDto.getDqzl(), "设备读取指令", 1, 45);
                ValidatorUtil.length(waterEquipmentDto.getSbxh(), "设备型号", 1, 45);
                ValidatorUtil.length(waterEquipmentDto.getGps(), "设备gps坐标", 1, 45);
                ValidatorUtil.length(waterEquipmentDto.getFzr(), "设备负责人", 1, 128);
                ValidatorUtil.length(waterEquipmentDto.getFzrdh(), "负责人电话", 1, 45);
                ValidatorUtil.length(waterEquipmentDto.getSblc(), "设备量程", 1, 45);
                ValidatorUtil.length(waterEquipmentDto.getJdfw(), "设备进度范围", 1, 128);
                ValidatorUtil.length(waterEquipmentDto.getFzwz(), "设备放置位置", 1, 450);
                ValidatorUtil.length(waterEquipmentDto.getSbcj(), "设备厂家", 1, 450);
                ValidatorUtil.length(waterEquipmentDto.getSbzt(), "设备状态1正常2离线3设备故障", 1, 1);
                ValidatorUtil.length(waterEquipmentDto.getBz(), "备注", 1, 2000);
                ValidatorUtil.length(waterEquipmentDto.getCreateBy(), "创建人", 1, 128);
                ValidatorUtil.length(waterEquipmentDto.getUpdateBy(), "更新人", 1, 128);

        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        try{
            waterEquipmentService.save(waterEquipmentDto, loginUserDto);
            responseDto.setContent(waterEquipmentDto);
        }catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waterEquipmentService.delete(id);
        return responseDto;
    }

    /**
     * 获取设备状态
     * @return
     */
    @PostMapping("/getWaterState")
    public ResponseDto getWaterState(@RequestBody WaterEquipmentDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        if(!CollectionUtils.isEmpty(list)){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(loginUserDto.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbsnIn(loginUserDto.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        List<WaterEquipment> zclist = new ArrayList<WaterEquipment>();
        List<WaterEquipment> yclist = new ArrayList<WaterEquipment>();
        Map<String,List<WaterEquipment>> map = new HashMap<String,List<WaterEquipment>>();
        List<WaterEquipment> waterList = waterEquipmentService.list(example);
        for (int i = 0; i < waterList.size(); i++) {
            WaterEquipment equipment = waterList.get(i);
            if("1".equals(equipment.getSbzt())){
                zclist.add(equipment);
            }else{
                yclist.add(equipment);
            }
        }
        map.put("zclist",zclist);
        map.put("yclist",yclist);
        responseDto.setContent(map);
        return responseDto;
    }


}
