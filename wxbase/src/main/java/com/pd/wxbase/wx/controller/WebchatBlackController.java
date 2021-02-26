package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.WebchatBlack;
import com.pd.server.main.domain.WebchatBlackExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.WebchatBlackDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WebchatBlackService;
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
@RequestMapping("/admin/webchatBlack")
public class WebchatBlackController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(WebchatBlackController.class);
public static final String BUSINESS_NAME = "黑名单管理";

@Resource
private WebchatBlackService webchatBlackService;


/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody WebchatBlackDto pageDto) {
ResponseDto responseDto = new ResponseDto();
    try {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WebchatBlackExample webchatBlackExample = new WebchatBlackExample();
        WebchatBlackExample.Criteria ca = webchatBlackExample.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getSfzmhm())) {
            ca.andSfzmhmEqualTo(DesEncrypt.DesJiaMi(pageDto.getSfzmhm()));
        }
        if (!StringUtils.isEmpty(pageDto.getXm())) {
            ca.andXmEqualTo(pageDto.getXm());
        }
        if (!StringUtils.isEmpty(pageDto.getZt())) {
            ca.andZtEqualTo(pageDto.getZt());
        }
        if (!StringUtils.isEmpty(pageDto.getStime())) {
            ca.andKssjGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())) {
            ca.andJssjGreaterThanOrEqualTo(pageDto.getEtime());
        }
        webchatBlackExample.setOrderByClause(" create_time desc");
        List<WebchatBlack> webchatBlackList = webchatBlackService.list(webchatBlackExample);
        PageInfo<WebchatBlack> pageInfo = new PageInfo<>(webchatBlackList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WebchatBlackDto> webchatBlackDtoList = CopyUtil.copyList(webchatBlackList, WebchatBlackDto.class);
        pageDto.setList(webchatBlackDtoList);


        responseDto.setContent(pageDto);
    } catch (Exception e) {
        e.printStackTrace();
    }
return responseDto;
}

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody WebchatBlackDto webchatBlackDto) {
// 保存校验
            ValidatorUtil.length(webchatBlackDto.getCreateBy(), "创建人", 1, 50);
            ValidatorUtil.length(webchatBlackDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.length(webchatBlackDto.getSysOrgCode(), "所属部门", 1, 64);
            ValidatorUtil.length(webchatBlackDto.getXm(), "姓名", 1, 32);
            ValidatorUtil.length(webchatBlackDto.getSfzmhm(), "身份证明号码", 1, 32);
            ValidatorUtil.length(webchatBlackDto.getYy(), "原因", 1, 32);
            ValidatorUtil.length(webchatBlackDto.getYysm(), "原因说明", 1, 400);
            ValidatorUtil.length(webchatBlackDto.getZt(), "状态", 1, 2);
            ValidatorUtil.length(webchatBlackDto.getOpenid(), "微信用户id", 1, 32);
            ValidatorUtil.length(webchatBlackDto.getBz(), "备注", 1, 400);

ResponseDto responseDto = new ResponseDto();

    LoginUserDto loginUserDto   = getRequestHeader();
    webchatBlackDto.setSysOrgCode(loginUserDto.getDeptcode());
    webchatBlackDto.setUpdateBy(loginUserDto.getLoginName());
    webchatBlackDto.setUpdateTime(new Date());
webchatBlackService.save(webchatBlackDto);
responseDto.setContent(webchatBlackDto);
return responseDto;
}


    /**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
webchatBlackService.delete(id);
return responseDto;
}
}
