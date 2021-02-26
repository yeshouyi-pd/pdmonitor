package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.DrvQxks;
import com.pd.server.main.domain.DrvQxksExample;
import com.pd.server.main.dto.DrvQxksDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.DrvQxksService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/drvQxks")
public class DrvQxksController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(DrvQxksController.class);
public static final String BUSINESS_NAME = "";

@Resource
private DrvQxksService drvQxksService;

    @Resource
    private RedisTemplate redisTemplate;


/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody DrvQxksDto pageDto)  {
    ResponseDto responseDto = new ResponseDto();
    try {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        DrvQxksExample drvQxksExample = new DrvQxksExample();
        DrvQxksExample.Criteria ca = drvQxksExample.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getZt())) {
            ca.andZtEqualTo(pageDto.getZt());
        }
        if (!StringUtils.isEmpty(pageDto.getXm())) {
            ca.andXmEqualTo(pageDto.getXm());
        }
        if (!StringUtils.isEmpty(pageDto.getSfzmhm())) {
            ca.andSfzmhmEqualTo(DesEncrypt.DesJiaMi(pageDto.getSfzmhm()));
        }
        if (!StringUtils.isEmpty(pageDto.getQxkm())) {
            ca.andQxkmEqualTo(pageDto.getQxkm());
        }
        if (!StringUtils.isEmpty(pageDto.getStime())) {
            ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())) {
            ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
        }
        drvQxksExample.setOrderByClause(" create_time desc");
        List<DrvQxks> drvQxksList = drvQxksService.list(drvQxksExample);
        PageInfo<DrvQxks> pageInfo = new PageInfo<>(drvQxksList);
        pageDto.setTotal(pageInfo.getTotal());
        List<DrvQxksDto> drvQxksDtoList = CopyUtil.copyList(drvQxksList, DrvQxksDto.class);
        pageDto.setList(drvQxksDtoList);
        responseDto.setContent(pageDto);
    }catch (Exception e){
        e.printStackTrace();
    }
    return responseDto;
}

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody DrvQxksDto drvQxksDto) {
// 保存校验
            ValidatorUtil.length(drvQxksDto.getCreateBy(), "创建人", 1, 50);
            ValidatorUtil.length(drvQxksDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.length(drvQxksDto.getSysOrgCode(), "所属部门", 1, 64);
            ValidatorUtil.length(drvQxksDto.getXm(), "姓名", 1, 128);
            ValidatorUtil.length(drvQxksDto.getZjlx(), "证件类型", 1, 32);
            ValidatorUtil.length(drvQxksDto.getSfzmhm(), "身份证明号码", 1, 20);
            ValidatorUtil.length(drvQxksDto.getSjhm(), "手机号码", 1, 20);
            ValidatorUtil.length(drvQxksDto.getQxkm(), "取消考试科目", 1, 32);
            ValidatorUtil.length(drvQxksDto.getSqsm(), "申请说明", 1, 2000);
            ValidatorUtil.length(drvQxksDto.getShr(), "审核人", 1, 32);
            ValidatorUtil.length(drvQxksDto.getShsm(), "审核说明", 1, 32);
            ValidatorUtil.length(drvQxksDto.getBz(), "备注", 1, 2000);
            ValidatorUtil.length(drvQxksDto.getOpenid(), "微信用户id", 1, 32);
            ValidatorUtil.length(drvQxksDto.getLsh(), "流水号", 1, 32);
            ValidatorUtil.length(drvQxksDto.getZt(), "状态", 1, 2);

ResponseDto responseDto = new ResponseDto();
    if (!StringUtils.isEmpty(drvQxksDto.getId())) {
        LoginUserDto loginUserDto = getRequestHeader();
        drvQxksDto.setShr(loginUserDto.getLoginName());
        drvQxksDto.setSysOrgCode(loginUserDto.getDeptcode());
        drvQxksDto.setUpdateBy(loginUserDto.getLoginName());
        drvQxksDto.setUpdateTime(new Date());
        drvQxksDto.setShsj(new Date());
    }
drvQxksService.save(drvQxksDto);
responseDto.setContent(drvQxksDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
drvQxksService.delete(id);
return responseDto;
}
}
