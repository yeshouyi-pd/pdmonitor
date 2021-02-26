package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehScrew;
import com.pd.server.main.domain.VehScrewExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.VehScrewDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.VehScrewService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/admin/vehScrew")
public class VehScrewController  extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(VehScrewController.class);
public static final String BUSINESS_NAME = "车牌固封螺丝申请表";

@Resource
private VehScrewService vehScrewService;


    /**
     * 线下处理列表查询
     */
    @PostMapping("/vehScrewScene")
    public ResponseDto vehScrewScene(@RequestBody VehScrewDto pageDto) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        try{

            LoginUserDto loginUserDto   = getRequestHeader();
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            VehScrewExample vehScrewExample = new VehScrewExample();
            VehScrewExample.Criteria ca = vehScrewExample.createCriteria();
            if (!StringUtils.isEmpty(pageDto.getHphm())){
                ca.andHphmEqualTo(pageDto.getHphm());
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
            ca.andSysOrgCodeEqualTo(loginUserDto.getDeptcode());
            ca.andSllbEqualTo("2");
            ca.andZtEqualTo("1");

            vehScrewExample.setOrderByClause(" create_time desc");
            List<VehScrew> vehScrewList = vehScrewService.vehScrewScene(vehScrewExample);
            PageInfo<VehScrew> pageInfo = new PageInfo<>(vehScrewList);
            pageDto.setTotal(pageInfo.getTotal());
            List<VehScrewDto> vehScrewDtoList = CopyUtil.copyList(vehScrewList, VehScrewDto.class);
            pageDto.setList(vehScrewDtoList);
            responseDto.setContent(pageDto);

    }catch (Exception e){
        e.printStackTrace();
    }
        return responseDto;
    }

    /**
     * 车牌固封螺丝统计
     */
    @PostMapping("/vehScrewReport")
    public ResponseDto vehScrewReport(@RequestBody VehScrewDto pageDto) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        try{
            LoginUserDto loginUserDto   = getRequestHeader();
            VehScrewExample vehScrewExample = new VehScrewExample();
            VehScrewExample.Criteria ca = vehScrewExample.createCriteria();
            List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
            if (!StringUtils.isEmpty(pageDto.getStime())){
                ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
            }
            if (!StringUtils.isEmpty(pageDto.getEtime())){
                ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
            }
            if(!list.isEmpty()){
                ca.andSysOrgCodeIn(list);
            }
            List<VehScrewDto> vehScrewList = vehScrewService.vehScrewReport(vehScrewExample);
            List<VehScrewDto> vehScrewSllbList = vehScrewService.getSllbReport(vehScrewExample);
            Map map = new HashMap();
            map.put("report1",vehScrewList);
            map.put("report2",vehScrewSllbList);
            responseDto.setContent(map);

        }catch (Exception e){
            e.printStackTrace();
        }
        return responseDto;
    }


    /**
     * 根据号牌查询车辆领取车牌固封螺丝的次数
     */
    @GetMapping("/getScrewByHphm/{hphm}")
    public ResponseDto getScrewByHphm(@PathVariable String hphm) {
        ResponseDto responseDto = new ResponseDto();
        int count = vehScrewService.getScrewByHphm(hphm);
        responseDto.setContent(count);
        return responseDto;
    }
    /**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody VehScrewDto pageDto) throws Exception {
    LoginUserDto loginUserDto   = getRequestHeader();
    List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
    ResponseDto responseDto = new ResponseDto();
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
    VehScrewExample vehScrewExample = new VehScrewExample();
    VehScrewExample.Criteria ca = vehScrewExample.createCriteria();
    if (!StringUtils.isEmpty(pageDto.getHphm())){
        ca.andHphmEqualTo(pageDto.getHphm());
    }

    if(!list.isEmpty()){
        ca.andSysOrgCodeIn(list);
    }
    if (!StringUtils.isEmpty(pageDto.getZt())){
        ca.andZtEqualTo(pageDto.getZt());
    }
    if (!StringUtils.isEmpty(pageDto.getSfzmhm())){
        ca.andSfzmhmEqualTo(DesEncrypt.DesJiaMi(pageDto.getSfzmhm()));
    }
    if (!StringUtils.isEmpty(pageDto.getSllb())){
        ca.andSllbEqualTo(pageDto.getSllb());
    }
    if (!StringUtils.isEmpty(pageDto.getStime())){
        ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
    }
    if (!StringUtils.isEmpty(pageDto.getEtime())){
        ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
    }
    vehScrewExample.or().andSysOrgCodeIsNull();
    vehScrewExample.setOrderByClause(" create_time desc");
    List<VehScrew> vehScrewList = vehScrewService.list(vehScrewExample);
    PageInfo<VehScrew> pageInfo = new PageInfo<>(vehScrewList);
    pageDto.setTotal(pageInfo.getTotal());
    List<VehScrewDto> vehScrewDtoList = CopyUtil.copyList(vehScrewList, VehScrewDto.class);
    pageDto.setList(vehScrewDtoList);

    responseDto.setContent(pageDto);
    return responseDto;
}

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody VehScrewDto vehScrewDto) {
// 保存校验
            ValidatorUtil.length(vehScrewDto.getCreateBy(), "申请人", 1, 50);
            ValidatorUtil.length(vehScrewDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.length(vehScrewDto.getSysOrgCode(), "申领网点", 1, 64);
            ValidatorUtil.length(vehScrewDto.getSfzmhm(), "身份证明号码", 1, 32);
            ValidatorUtil.length(vehScrewDto.getHphm(), "号牌号码", 1, 32);
            ValidatorUtil.length(vehScrewDto.getSqyy(), "申请原因", 1, 32);
            ValidatorUtil.length(vehScrewDto.getSlsl(), "申领数量", 1, 32);
            ValidatorUtil.length(vehScrewDto.getSjr(), "收件人", 1, 32);
            ValidatorUtil.length(vehScrewDto.getLxdh(), "联系电话", 1, 32);
            ValidatorUtil.length(vehScrewDto.getSf(), "省份", 1, 128);
            ValidatorUtil.length(vehScrewDto.getCs(), "城市", 1, 128);
            ValidatorUtil.length(vehScrewDto.getQx(), "区县", 1, 256);
            ValidatorUtil.length(vehScrewDto.getJddz(), "街道地址", 1, 256);
            ValidatorUtil.length(vehScrewDto.getZt(), "状态", 1, 2);
            ValidatorUtil.length(vehScrewDto.getShr(), "审核人", 1, 128);
            ValidatorUtil.length(vehScrewDto.getBz(), "备注", 1, 4000);
            ValidatorUtil.length(vehScrewDto.getOpenid(), "微信用户id", 1, 32);
            ValidatorUtil.length(vehScrewDto.getShyy(), "审核原因说明", 1, 200);
            ValidatorUtil.length(vehScrewDto.getYwmc(), "业务名称", 1, 32);
            ValidatorUtil.length(vehScrewDto.getLsh(), "流水号", 1, 32);
            ValidatorUtil.length(vehScrewDto.getSfbr(), "是否本人车辆1是0否", 1, 1);
            ValidatorUtil.length(vehScrewDto.getSllb(), "申领类别1线上申领2线下申领", 1, 1);

ResponseDto responseDto = new ResponseDto();
    LoginUserDto loginUserDto   = getRequestHeader();
    vehScrewDto.setShr(loginUserDto.getLoginName());
    vehScrewDto.setSysOrgCode(loginUserDto.getDeptcode());
    vehScrewDto.setUpdateBy(loginUserDto.getLoginName());
    vehScrewDto.setUpdateTime(new Date());
    vehScrewDto.setShsj(new Date());
vehScrewService.save(vehScrewDto);
responseDto.setContent(vehScrewDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
vehScrewService.delete(id);
return responseDto;
}



}
