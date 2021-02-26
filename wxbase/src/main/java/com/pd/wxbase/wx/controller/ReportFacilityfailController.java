package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.ReportFacilityfail;
import com.pd.server.main.domain.ReportFacilityfailExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ReportFacilityfailDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.ReportFacilityfailService;
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
@RequestMapping("/admin/reportFacilityfail")
public class ReportFacilityfailController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(ReportFacilityfailController.class);
public static final String BUSINESS_NAME = "交安故障上报";

@Resource
private ReportFacilityfailService reportFacilityfailService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody ReportFacilityfailDto pageDto) throws Exception {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ReportFacilityfailExample reportFacilityfailExample = new ReportFacilityfailExample();
        ReportFacilityfailExample.Criteria ca = reportFacilityfailExample.createCriteria();

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

        if (!StringUtils.isEmpty(pageDto.getGzlx())){
            ca.andGzlxEqualTo(pageDto.getGzlx());
        }

        if (!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
        }

        reportFacilityfailExample.setOrderByClause(" create_time desc");
        List<ReportFacilityfail> reportFacilityfailList = reportFacilityfailService.list(reportFacilityfailExample);
        PageInfo<ReportFacilityfail> pageInfo = new PageInfo<>(reportFacilityfailList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ReportFacilityfailDto> reportFacilityfailDtoList = CopyUtil.copyList(reportFacilityfailList, ReportFacilityfailDto.class);
        pageDto.setList(reportFacilityfailDtoList);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(pageDto);
        return responseDto;
    }

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody ReportFacilityfailDto reportFacilityfailDto) {
// 保存校验
            ValidatorUtil.length(reportFacilityfailDto.getCreateBy(), "创建人", 1, 50);
            ValidatorUtil.length(reportFacilityfailDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.length(reportFacilityfailDto.getSysOrgCode(), "所属部门", 1, 64);
            ValidatorUtil.length(reportFacilityfailDto.getGzdd(), "故障地点", 1, 128);
            ValidatorUtil.length(reportFacilityfailDto.getGzlx(), "故障类型", 1, 32);
            ValidatorUtil.length(reportFacilityfailDto.getGzgps(), "故障坐标", 1, 128);
            ValidatorUtil.length(reportFacilityfailDto.getGzxxdz(), "详细地址", 1, 2000);
            ValidatorUtil.length(reportFacilityfailDto.getGzms(), "故障描述", 1, 2000);
            ValidatorUtil.length(reportFacilityfailDto.getSbr(), "上报人", 1, 128);
            ValidatorUtil.length(reportFacilityfailDto.getSbrsjh(), "手机号", 1, 20);
            ValidatorUtil.length(reportFacilityfailDto.getSfzmhm(), "身份证号码", 1, 32);
            ValidatorUtil.length(reportFacilityfailDto.getShr(), "审核人", 1, 32);
            ValidatorUtil.length(reportFacilityfailDto.getZt(), "状态", 1, 2);
            ValidatorUtil.length(reportFacilityfailDto.getBz(), "备注", 1, 4000);
            ValidatorUtil.length(reportFacilityfailDto.getClyj(), "处理意见", 1, 4000);
            ValidatorUtil.length(reportFacilityfailDto.getLsh(), "流水号", 1, 32);
            ValidatorUtil.length(reportFacilityfailDto.getOpenid(), "微信用户id", 1, 32);

ResponseDto responseDto = new ResponseDto();
    LoginUserDto loginUserDto   = getRequestHeader();
    reportFacilityfailDto.setShr(loginUserDto.getLoginName());
    reportFacilityfailDto.setSysOrgCode(loginUserDto.getDeptcode());
    reportFacilityfailDto.setUpdateBy(loginUserDto.getLoginName());
    reportFacilityfailDto.setUpdateTime(new Date());
    reportFacilityfailDto.setShsj(new Date());
reportFacilityfailService.save(reportFacilityfailDto);
responseDto.setContent(reportFacilityfailDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
reportFacilityfailService.delete(id);
return responseDto;
}

}
