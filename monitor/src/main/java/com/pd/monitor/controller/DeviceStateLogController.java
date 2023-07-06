package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.DeviceStateLog;
import com.pd.server.main.domain.DeviceStateLogExample;
import com.pd.server.main.dto.DeviceStateLogDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.DeviceStateLogService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/deviceStateLog")
public class DeviceStateLogController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceStateLogController.class);
    public static final String BUSINESS_NAME = "设备状态日志记录表";

    @Resource
    private DeviceStateLogService deviceStateLogService;

    @PostMapping("/listByRq")
    public ResponseDto listByRq(@RequestBody DeviceStateLogDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        DeviceStateLogExample deviceStateLogExample = new DeviceStateLogExample();
        DeviceStateLogExample.Criteria ca = deviceStateLogExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(userDto.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbbhIn(userDto.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        deviceStateLogExample.setOrderByClause(" rq desc ");
        List<DeviceStateLog> deviceStateLogList = deviceStateLogService.selectByExample(deviceStateLogExample);
        Map<String,List<DeviceStateLog>> rqMap = deviceStateLogList.stream().collect(Collectors.groupingBy(DeviceStateLog::getRq,LinkedHashMap::new,Collectors.toList()));
        Map<String,Map<String,String>> resultMap = new LinkedHashMap<>();
        for(String rq : rqMap.keySet()){
            List<DeviceStateLog> list = rqMap.get(rq);
            Map<String,String> sbbhMap = list.stream().collect(Collectors.toMap(p -> p.getSbbh(), p-> p.getZt()));
            resultMap.put(rq,sbbhMap);
        }
        responseDto.setContent(resultMap);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody DeviceStateLogDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DeviceStateLogExample deviceStateLogExample = new DeviceStateLogExample();
        DeviceStateLogExample.Criteria ca = deviceStateLogExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(userDto.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbbhIn(userDto.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        deviceStateLogExample.setOrderByClause(" rq desc ");
        List<DeviceStateLog> deviceStateLogList = deviceStateLogService.selectByExample(deviceStateLogExample);
        PageInfo<DeviceStateLog> pageInfo = new PageInfo<>(deviceStateLogList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DeviceStateLogDto> deviceStateLogDtoList = CopyUtil.copyList(deviceStateLogList, DeviceStateLogDto.class);
        pageDto.setList(deviceStateLogDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody DeviceStateLogDto deviceStateLogDto) {
        ResponseDto responseDto = new ResponseDto();
        deviceStateLogService.save(deviceStateLogDto);
        responseDto.setContent(deviceStateLogDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        deviceStateLogService.delete(id);
        return responseDto;
    }

}
