package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.WaterEquiplog;
import com.pd.server.main.domain.WaterEquiplogExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.WaterEquiplogService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/waterEquiplog")
public class WaterEquiplogController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterEquiplogController.class);
    public static final String BUSINESS_NAME = "设备心跳包";

    @Resource
    private WaterEquiplogService waterEquiplogService;

    @Resource
    private WaterEquipmentService waterEquipmentService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WaterEquiplogDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = getRequestHeader();
        WaterEquiplogExample example = new WaterEquiplogExample();
        WaterEquiplogExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(loginUserDto.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbbhIn(loginUserDto.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getCode())){
            ca.andCodeEqualTo(pageDto.getCode());
        }
        example.setOrderByClause(" cjsj desc ");
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        List<WaterEquiplog> waterlist = waterEquiplogService.list(example);
        PageInfo<WaterEquiplog> pageInfo = new PageInfo<WaterEquiplog>(waterlist);
        pageDto.setTotal(pageInfo.getTotal());
        List<WaterEquiplogDto> waterEquiplogList = CopyUtil.copyList(waterlist, WaterEquiplogDto.class);
        pageDto.setList(waterEquiplogList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WaterEquiplogDto waterEquiplogDto) {
        // 保存校验
                ValidatorUtil.length(waterEquiplogDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(waterEquiplogDto.getCode(), "返回code值1设备正常2设备离线3设备故障", 1, 2);
                ValidatorUtil.length(waterEquiplogDto.getMsg(), "回传说明", 1, 200);
                ValidatorUtil.length(waterEquiplogDto.getReqmsg(), "接口请求参数", 1, 2000);
                ValidatorUtil.length(waterEquiplogDto.getRespmsg(), "接口返回参数", 1, 2000);
                ValidatorUtil.length(waterEquiplogDto.getSm1(), "", 1, 1000);
                ValidatorUtil.length(waterEquiplogDto.getSm2(), "", 1, 1000);
                ValidatorUtil.length(waterEquiplogDto.getSm3(), "", 1, 1000);
                ValidatorUtil.length(waterEquiplogDto.getSm4(), "", 1, 1000);
                ValidatorUtil.length(waterEquiplogDto.getSm5(), "", 1, 1000);

        ResponseDto responseDto = new ResponseDto();
        waterEquiplogService.save(waterEquiplogDto);
        responseDto.setContent(waterEquiplogDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        waterEquiplogService.delete(id);
        return responseDto;
    }

}
