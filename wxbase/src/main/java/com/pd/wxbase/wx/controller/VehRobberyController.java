package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehRobbery;
import com.pd.server.main.domain.VehRobberyExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.VehRobberyDto;

import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.VehRobberyService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/vehRobbery")
public class VehRobberyController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(VehRobberyController.class);
public static final String BUSINESS_NAME = "套牌车信息上报";

@Resource
private VehRobberyService vehRobberyService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody VehRobberyDto pageDto) throws Exception {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        VehRobberyExample vehRobberyExample = new VehRobberyExample();


        VehRobberyExample.Criteria ca = vehRobberyExample.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getHpzl())){
            ca.andHpzlEqualTo(pageDto.getHpzl());
        }
        if (!StringUtils.isEmpty(pageDto.getLb())){
            ca.andLbEqualTo(pageDto.getLb());
        }

        if (!StringUtils.isEmpty(pageDto.getHphm())){
            ca.andHphmEqualTo(pageDto.getHphm());
        }
        if (!StringUtils.isEmpty(pageDto.getZt())){
            ca.andZtEqualTo(pageDto.getZt());
        }
        if (!StringUtils.isEmpty(pageDto.getXm())){
            ca.andXmEqualTo(pageDto.getXm());
        }
        if (!StringUtils.isEmpty(pageDto.getSfzmhm())){
            ca.andSfzmhmEqualTo(DesEncrypt.DesJiaMi(pageDto.getSfzmhm()));
        }
        if (!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
        }
        vehRobberyExample.setOrderByClause(" create_time desc");
        List<VehRobbery> vehRobberyList = vehRobberyService.list(vehRobberyExample);
        PageInfo<VehRobbery> pageInfo = new PageInfo<>(vehRobberyList);
        pageDto.setTotal(pageInfo.getTotal());
        List<VehRobberyDto> vehRobberyDtoList = CopyUtil.copyList(vehRobberyList, VehRobberyDto.class);
        pageDto.setList(vehRobberyDtoList);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(pageDto);
        return responseDto;
    }

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody VehRobberyDto vehRobberyDto) {
// 保存校验
            ValidatorUtil.length(vehRobberyDto.getCreateBy(), "创建人", 1, 50);
            ValidatorUtil.length(vehRobberyDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.length(vehRobberyDto.getSysOrgCode(), "所属部门", 1, 64);
            ValidatorUtil.length(vehRobberyDto.getXm(), "报案人", 1, 128);
            ValidatorUtil.length(vehRobberyDto.getSfzmhm(), "身份证明号码", 1, 20);
            ValidatorUtil.length(vehRobberyDto.getLxdh(), "联系电话", 1, 20);
            ValidatorUtil.length(vehRobberyDto.getJtzz(), "家庭住址", 1, 512);
            ValidatorUtil.length(vehRobberyDto.getHphm(), "被套车牌", 1, 20);
            ValidatorUtil.length(vehRobberyDto.getClpp(), "被套车品牌", 1, 256);
            ValidatorUtil.length(vehRobberyDto.getZj(), "车辆被套牌证据", 1, 256);
            ValidatorUtil.length(vehRobberyDto.getQksm(), "情况说明", 1, 3000);
            ValidatorUtil.length(vehRobberyDto.getBz(), "备注", 1, 4000);
            ValidatorUtil.length(vehRobberyDto.getZt(), "状态", 1, 2);
            ValidatorUtil.length(vehRobberyDto.getOpenid(), "微信用户id", 1, 128);
            ValidatorUtil.length(vehRobberyDto.getLsh(), "流水号", 1, 45);
            ValidatorUtil.length(vehRobberyDto.getShr(), "审核人", 1, 128);
            ValidatorUtil.length(vehRobberyDto.getShsm(), "审核说明", 1, 500);
            ValidatorUtil.length(vehRobberyDto.getLb(), "车辆类别1个人车辆2单位车辆", 1, 1);
            ValidatorUtil.length(vehRobberyDto.getHpzl(), "号牌种类", 1, 2);

ResponseDto responseDto = new ResponseDto();
    LoginUserDto loginUserDto   = getRequestHeader();
    if (!StringUtils.isEmpty(vehRobberyDto.getId())) {
        vehRobberyDto.setShr(loginUserDto.getLoginName());
        vehRobberyDto.setSysOrgCode(loginUserDto.getDeptcode());
        vehRobberyDto.setUpdateBy(loginUserDto.getLoginName());
        vehRobberyDto.setUpdateTime(new Date());
        vehRobberyDto.setShsj(new Date());
    }
vehRobberyService.save(vehRobberyDto);
responseDto.setContent(vehRobberyDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
vehRobberyService.delete(id);
return responseDto;
}
}
