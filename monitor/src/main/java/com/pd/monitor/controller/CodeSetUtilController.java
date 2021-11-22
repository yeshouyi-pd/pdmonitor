package com.pd.monitor.controller;

import com.pd.server.config.CodeType;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.WaterProUserExample;
import com.pd.server.main.domain.WaterProjectExample;
import com.pd.server.main.dto.*;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/CodeSetUtil")
public class CodeSetUtilController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(CodeSetUtilController.class);
    public static final String BUSINESS_NAME = "获取配置信息";

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private DeptService deptService;

    @Resource
    private UserService userService;

    @Resource
    private WaterEquipmentService waterEquipmentService;

    @Resource
    private WaterProUserService waterProUserService;

    /**
     * 资源树查询
     */
    @GetMapping("/load-deptTree/{deptcode}")
    public ResponseDto loadTree(@PathVariable String deptcode) {
        ResponseDto responseDto = new ResponseDto();
        List<String> deptcodestr =  getUpdeptcode(deptcode);
        List<DeptDto>deptDtoList = deptService.loadTree(deptcodestr);
        responseDto.setContent(deptDtoList);
        return responseDto;
    }

    /**
     * 查询所有部门
     */
    @GetMapping("/getAllDept")
    public ResponseDto getAllDept() {
        ResponseDto responseDto = new ResponseDto();
        List<DeptDto>deptDtoList = deptService.getAllDept();
        responseDto.setContent(deptDtoList);
        return responseDto;
    }

    /**
     * 分页查询所有人员
     */
    @PostMapping("/getAllUser")
    public ResponseDto getAllUser(@RequestBody UserDto userDto) {
        ResponseDto responseDto = new ResponseDto();
        userService.list(userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }

    /**
     * 分页查询所有设备
     */
    @PostMapping("/getAllEuqipment")
    public ResponseDto getAllEuqipment(@RequestBody WaterEquipmentDto waterEquipmentDto){
        ResponseDto responseDto = new ResponseDto();
        waterEquipmentService.listByPage(waterEquipmentDto);
        responseDto.setContent(waterEquipmentDto);
        return responseDto;
    }

    /**
     * 根据usercode查询所有的项目
     */
    @PostMapping("/findWaterProjectAllByExample")
    public ResponseDto findWaterProjectAllByExample(@RequestBody WaterProUserDto waterProUserDto){
        ResponseDto responseDto = new ResponseDto();
        WaterProUserExample example = new WaterProUserExample();
        WaterProUserExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(waterProUserDto.getUsercode())){
            ca.andUsercodeEqualTo(waterProUserDto.getUsercode());
        }
        List<String> xmbhLists = waterProUserService.findXmbhByExample(example);
        responseDto.setContent(xmbhLists);
        return responseDto;
    }

    /**
     * 获取水质检测项
     * @return
     */
    @GetMapping("/getSzjcx")
    public ResponseDto getSzjcx(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.SZJCX_CODE);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取设备型号
     * @return
     */
    @GetMapping("/getSblb")
    public ResponseDto getSblb(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.SBLB_CODE);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取部门名称
     * @return
     */
    @GetMapping("/getdeptname")
    public ResponseDto getdeptname(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取机构类别
     * @return
     */
    @GetMapping("/getJglb")
    public ResponseDto getJglb(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.JGLB_CODE);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取项目用途
     * @return
     */
    @GetMapping("/getXmyt")
    public ResponseDto getXmyt(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.XMYT_CODE);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取项目编号对应的项目名称
     */
    @GetMapping("/getXmbhAndXmmc")
    public ResponseDto getXmbhAndXmmc(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.PROJECTCODENAME);
        responseDto.setContent(map);
        return responseDto;
    }
}
