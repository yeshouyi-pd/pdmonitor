package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.AzimuthAngle;
import com.pd.server.main.domain.AzimuthAngleExample;
import com.pd.server.main.domain.AzimuthAngleUnique;
import com.pd.server.main.domain.AzimuthAngleUniqueExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.AzimuthAngleUniqueService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/azimuthAngleUnique")
public class AzimuthAngleUniqueController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(AzimuthAngleUniqueController.class);
    public static final String BUSINESS_NAME = "方位角统计查询(历史)";

    @Resource
    private AzimuthAngleUniqueService azimuthAngleUniqueService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody AzimuthAngleUniqueDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto userDto = getRequestHeader();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AzimuthAngleUniqueExample example = new AzimuthAngleUniqueExample();
        AzimuthAngleUniqueExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(userDto.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbbhIn(userDto.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        example.setOrderByClause(" fz desc ");
        List<AzimuthAngleUnique> azimuthAngleUniqueList = azimuthAngleUniqueService.list(example);
        PageInfo<AzimuthAngleUnique> pageInfo = new PageInfo<>(azimuthAngleUniqueList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AzimuthAngleUniqueDto> azimuthAngleDtoList = CopyUtil.copyList(azimuthAngleUniqueList, AzimuthAngleUniqueDto.class);
        pageDto.setList(azimuthAngleDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AzimuthAngleUniqueDto azimuthAngleUniqueDto) {
        // 保存校验
                ValidatorUtil.require(azimuthAngleUniqueDto.getSbbh(), "设备编号");
                ValidatorUtil.length(azimuthAngleUniqueDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(azimuthAngleUniqueDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(azimuthAngleUniqueDto.getXs(), "小时", 1, 13);
                ValidatorUtil.require(azimuthAngleUniqueDto.getFz(), "分钟");
                ValidatorUtil.length(azimuthAngleUniqueDto.getFz(), "分钟", 1, 16);
                ValidatorUtil.length(azimuthAngleUniqueDto.getDeptcode(), "部门编号", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        azimuthAngleUniqueService.save(azimuthAngleUniqueDto);
        responseDto.setContent(azimuthAngleUniqueDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        azimuthAngleUniqueService.delete(id);
        return responseDto;
    }

}
