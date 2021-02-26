package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.WebchatNewinfo;
import com.pd.server.main.domain.WebchatNewinfoExample;
import com.pd.server.main.domain.WebchatUsers;
import com.pd.server.main.domain.WebchatUsersExample;
import com.pd.server.main.dto.WebchatNewinfoDto;
import com.pd.server.main.dto.WebchatUsersDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WebchatUsersService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/wxUsers")
public class WxUsersController {

private static final Logger LOG = LoggerFactory.getLogger(WxUsersController.class);
public static final String BUSINESS_NAME = "微信认证用户信息";

@Resource
private WebchatUsersService wxUsersService;

/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody WebchatUsersDto pageDto) throws Exception{
    ResponseDto responseDto = new ResponseDto();
    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
    WebchatUsersExample example = new WebchatUsersExample();
    WebchatUsersExample.Criteria ca = example.createCriteria();
    if (!StringUtils.isEmpty(pageDto.getZjmc())){
        ca.andZjmcEqualTo(pageDto.getZjmc());
    }
    if (!StringUtils.isEmpty(pageDto.getZjhm())){
        ca.andZjhmEqualTo(DesEncrypt.DesJiaMi(pageDto.getZjhm()));
    }
    if (!StringUtils.isEmpty(pageDto.getSfrz())){
        ca.andSfrzEqualTo(pageDto.getSfrz());
    }
    if (!StringUtils.isEmpty(pageDto.getStime())){
        ca.andCjsjGreaterThanOrEqualTo(pageDto.getStime());
    }
    if (!StringUtils.isEmpty(pageDto.getEtime())){
        ca.andCjsjLessThanOrEqualTo(pageDto.getEtime());
    }
    example.setOrderByClause(" cjsj desc");
    List<WebchatUsers> listinfo = wxUsersService.list(example);
    PageInfo<WebchatUsers> pageInfo = new PageInfo<WebchatUsers>(listinfo);
    pageDto.setTotal(pageInfo.getTotal());
    List<WebchatUsersDto> dtoList = CopyUtil.copyList(listinfo, WebchatUsersDto.class);
    pageDto.setList(dtoList);
    responseDto.setContent(pageDto);
    return responseDto;
}

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody WebchatUsersDto wxUsersDto) {
// 保存校验
            ValidatorUtil.length(wxUsersDto.getOpenid(), "openid", 1, 32);
            ValidatorUtil.length(wxUsersDto.getNickname(), "昵称", 1, 50);
            ValidatorUtil.length(wxUsersDto.getRemark(), "备注名", 1, 50);
            ValidatorUtil.length(wxUsersDto.getSex(), "性别", 1, 4);
            ValidatorUtil.length(wxUsersDto.getCity(), "市", 1, 30);
            ValidatorUtil.length(wxUsersDto.getProvince(), "省", 1, 30);
            ValidatorUtil.length(wxUsersDto.getCountry(), "国家", 1, 30);
            ValidatorUtil.length(wxUsersDto.getZjmc(), "证件名称", 1, 50);
            ValidatorUtil.length(wxUsersDto.getZjhm(), "证件号", 1, 30);
            ValidatorUtil.length(wxUsersDto.getZjlx(), "证件类型", 1, 4);
            ValidatorUtil.length(wxUsersDto.getZjdz(), "证件地址", 1, 60);
            ValidatorUtil.length(wxUsersDto.getSfrz(), "是否认证", 1, 1);
            ValidatorUtil.length(wxUsersDto.getZjcx(), "准驾车型", 1, 4);
            ValidatorUtil.length(wxUsersDto.getXtbh(), "系统编号", 1, 36);
            ValidatorUtil.length(wxUsersDto.getBz(), "备注", 1, 1000);
            ValidatorUtil.length(wxUsersDto.getSimilarity(), "两个人脸的相似度", 1, 3);
            ValidatorUtil.length(wxUsersDto.getFailflag(), "标志失败图片，0-没有失败，1-第一张，2-第二张", 1, 1);
            ValidatorUtil.length(wxUsersDto.getJszh(), "驾驶证号", 1, 24);
            ValidatorUtil.length(wxUsersDto.getLxdh(), "联系电话", 1, 12);
            ValidatorUtil.length(wxUsersDto.getYhdj(), "用户等级", 1, 1);
            ValidatorUtil.length(wxUsersDto.getGzcl(), "关注车辆", 1, 200);

ResponseDto responseDto = new ResponseDto();
wxUsersService.save(wxUsersDto);
responseDto.setContent(wxUsersDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
wxUsersService.delete(id);
return responseDto;
}
}
