package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.PontoonGps;
import com.pd.server.main.domain.PontoonGpsExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PontoonGpsDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.PontoonGpsService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/pontoonGps")
public class PontoonGpsController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(PontoonGpsController.class);
    public static final String BUSINESS_NAME = "趸船gps";

    @Resource
    private PontoonGpsService pontoonGpsService;

    @PostMapping("/selectGpsByDateRange")
    public ResponseDto selectGpsByDateRange(@RequestBody PontoonGpsDto pontoonGpsDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        PontoonGpsExample example = new PontoonGpsExample();
        PontoonGpsExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pontoonGpsDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pontoonGpsDto.getStime());
        }
        if(!StringUtils.isEmpty(pontoonGpsDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pontoonGpsDto.getEtime());
        }
        if(!StringUtils.isEmpty(pontoonGpsDto.getSbbh())){
            ca.andSbbhEqualTo(pontoonGpsDto.getSbbh());
        }
        example.setOrderByClause(" cjsj desc ");
        List<PontoonGps> gpsList = pontoonGpsService.selectByExample(example);
        Map<String, Map<String,List<PontoonGps>>> result = gpsList.stream().collect(Collectors.groupingBy(PontoonGps::getSbbh, Collectors.groupingBy(PontoonGps::getRq)));
        //判断是否是超级管理员
        //超级管理不用进行设备编号的筛选，反之则需要
        if(!"00000000".equals(loginUserDto.getRode())){
            //获取用户所能查看的设备
            List<String> sbbhs = loginUserDto.getXmbhsbsns().get(pontoonGpsDto.getXmbh());
            //循环所有的设备编号
            for(String key: result.keySet()){
                if(!sbbhs.contains(key)){
                    result.remove(key);
                }
            }
        }
        responseDto.setContent(result);
        return responseDto;
    }

    @PostMapping("/selectGps")
    public ResponseDto selectGps(@RequestBody PontoonGpsDto pontoonGpsDto){
        ResponseDto responseDto = new ResponseDto();
        PontoonGpsExample example = new PontoonGpsExample();
        PontoonGpsExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pontoonGpsDto.getSbbh())){
            ca.andSbbhEqualTo(pontoonGpsDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pontoonGpsDto.getRq())){
            ca.andRqEqualTo(pontoonGpsDto.getRq());
        }
        example.setOrderByClause(" cjsj desc ");
        List<String> gpsList = pontoonGpsService.selectGpsByExample(example);
        responseDto.setContent(gpsList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PontoonGpsDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        PontoonGpsExample example = new PontoonGpsExample();
        PontoonGpsExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(userDto.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbbhIn(userDto.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        example.setOrderByClause(" cjsj desc ");
        List<PontoonGps> pontoonGpsList = pontoonGpsService.selectByExample(example);
        PageInfo<PontoonGps> pageInfo = new PageInfo<>(pontoonGpsList);
        pageDto.setTotal(pageInfo.getTotal());
        List<PontoonGpsDto> pontoonGpsDtoList = CopyUtil.copyList(pontoonGpsList, PontoonGpsDto.class);
        pageDto.setList(pontoonGpsDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody PontoonGpsDto pontoonGpsDto) {
        // 保存校验
                ValidatorUtil.length(pontoonGpsDto.getSbbh(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getGps(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getRq(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getBz(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getSm(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getSm1(), "", 1, 255);
                ValidatorUtil.length(pontoonGpsDto.getSm2(), "", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        pontoonGpsService.save(pontoonGpsDto);
        responseDto.setContent(pontoonGpsDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        pontoonGpsService.delete(id);
        return responseDto;
    }

}
