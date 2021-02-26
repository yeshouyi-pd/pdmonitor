package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.ReportWater;
import com.pd.server.main.domain.ReportWaterExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ReportWaterDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.ReportWaterService;
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
@RequestMapping("/admin/reportWater")
public class ReportWaterController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(ReportWaterController.class);
public static final String BUSINESS_NAME = "渍水上报";

@Resource
private ReportWaterService reportWaterService;

/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody ReportWaterDto pageDto) throws Exception {

    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
    ReportWaterExample reportWaterExample = new ReportWaterExample();

    ReportWaterExample.Criteria ca = reportWaterExample.createCriteria();

    if (!StringUtils.isEmpty(pageDto.getZt())){
        ca.andZtEqualTo(pageDto.getZt());
    }

    if (!StringUtils.isEmpty(pageDto.getSbr())){
        ca.andSbrEqualTo(pageDto.getSbr());
    }

    if (!StringUtils.isEmpty(pageDto.getSfzmhm())){
        ca.andSfzmhmEqualTo(DesEncrypt.DesJiaMi(pageDto.getSfzmhm()));
    }

    if (!StringUtils.isEmpty(pageDto.getSbrsjh())){
        ca.andSbrsjhEqualTo(pageDto.getSbrsjh());
    }

    if (!StringUtils.isEmpty(pageDto.getStime())){
        ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
    }
    if (!StringUtils.isEmpty(pageDto.getEtime())){
        ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
    }

    reportWaterExample.setOrderByClause(" create_time desc");

    List<ReportWater> reportWaterList = reportWaterService.list(reportWaterExample);
    PageInfo<ReportWater> pageInfo = new PageInfo<>(reportWaterList);
    pageDto.setTotal(pageInfo.getTotal());
    List<ReportWaterDto> reportWaterDtoList = CopyUtil.copyList(reportWaterList, ReportWaterDto.class);
    pageDto.setList(reportWaterDtoList);

    ResponseDto responseDto = new ResponseDto();
    responseDto.setContent(pageDto);
    return responseDto;
}

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody ReportWaterDto reportWaterDto) throws Exception {
// 保存校验
            ValidatorUtil.length(reportWaterDto.getSfzmhm(), "上报人身份证号码", 1, 50);
            ValidatorUtil.length(reportWaterDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.length(reportWaterDto.getSysOrgCode(), "处理部门", 1, 64);
            ValidatorUtil.length(reportWaterDto.getZsdd(), "渍水地点", 1, 512);
            ValidatorUtil.length(reportWaterDto.getGps(), "渍水坐标", 1, 128);
            ValidatorUtil.length(reportWaterDto.getZsddxx(), "渍水详细地址", 1, 512);
            ValidatorUtil.length(reportWaterDto.getSbr(), "上报人", 1, 128);
            ValidatorUtil.length(reportWaterDto.getSbrsjh(), "上报人手机号", 1, 20);
            ValidatorUtil.length(reportWaterDto.getSbsm(), "上报说明", 1, 2000);
            ValidatorUtil.length(reportWaterDto.getShr(), "审核人", 1, 128);
            ValidatorUtil.length(reportWaterDto.getClyj(), "处理反馈", 1, 2000);
            ValidatorUtil.length(reportWaterDto.getZt(), "状态", 1, 2);
            ValidatorUtil.length(reportWaterDto.getBz(), "备注", 1, 4000);
            ValidatorUtil.length(reportWaterDto.getOpenid(), "微信用户openid", 1, 32);
            ValidatorUtil.length(reportWaterDto.getLsh(), "流水号", 1, 32);

            ResponseDto responseDto = new ResponseDto();
            if (!StringUtils.isEmpty(reportWaterDto.getId())) {
                LoginUserDto loginUserDto = getRequestHeader();
                reportWaterDto.setShr(loginUserDto.getLoginName());
                reportWaterDto.setSysOrgCode(loginUserDto.getDeptcode());
                reportWaterDto.setUpdateBy(loginUserDto.getLoginName());
                reportWaterDto.setUpdateTime(new Date());
                reportWaterDto.setShsj(new Date());
            }
            reportWaterDto.setSbrsjh(DesEncrypt.DesJiaMi(reportWaterDto.getSbrsjh()));
            reportWaterDto.setSfzmhm(DesEncrypt.DesJiaMi(reportWaterDto.getSfzmhm()));
            reportWaterService.save(reportWaterDto);
            responseDto.setContent(reportWaterDto);
            return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
reportWaterService.delete(id);
return responseDto;
}
}
