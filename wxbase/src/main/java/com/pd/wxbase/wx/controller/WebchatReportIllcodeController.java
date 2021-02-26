package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.WebchatIllcode;
import com.pd.server.main.domain.WebchatIllcodeExample;
import com.pd.server.main.domain.WebchatReportIllcode;
import com.pd.server.main.domain.WebchatReportIllcodeExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.WebchatReportIllcodeDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WebchatReportIllcodeService;
import com.pd.server.util.CopyUtil;
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
@RequestMapping("/admin/webchatReportIllcode")
public class WebchatReportIllcodeController  extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(WebchatReportIllcodeController.class);
public static final String BUSINESS_NAME = "随手拍奖励设置";

@Resource
private WebchatReportIllcodeService webchatReportIllcodeService;

/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody WebchatReportIllcodeDto pageDto) {
    ResponseDto responseDto = new ResponseDto();

    try {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WebchatReportIllcodeExample webchatReportIllcodeExample = new WebchatReportIllcodeExample();

        WebchatReportIllcodeExample.Criteria ca = webchatReportIllcodeExample.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getWflb())){
            ca.andWflbEqualTo(pageDto.getWflb());
        }
        if (!StringUtils.isEmpty(pageDto.getWfxwms())){
            ca.andWfxwmsLike("%"+pageDto.getWfxwms()+"%");
        }
        if (!StringUtils.isEmpty(pageDto.getWfxwxx())){
            ca.andWfxwxxEqualTo(pageDto.getWfxwxx());
        }
        if (!StringUtils.isEmpty(pageDto.getJljr())){
            ca.andJljrEqualTo(pageDto.getJljr());
        }
        webchatReportIllcodeExample.setOrderByClause(" create_time desc");
        List<WebchatReportIllcode> webchatReportIllcodeList = webchatReportIllcodeService.list(webchatReportIllcodeExample);
        PageInfo<WebchatReportIllcode> pageInfo = new PageInfo<>(webchatReportIllcodeList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WebchatReportIllcodeDto> webchatReportIllcodeDtoList = CopyUtil.copyList(webchatReportIllcodeList, WebchatReportIllcodeDto.class);
        pageDto.setList(webchatReportIllcodeDtoList);
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
public ResponseDto save(@RequestBody WebchatReportIllcodeDto webchatReportIllcodeDto) {
// 保存校验
            ValidatorUtil.length(webchatReportIllcodeDto.getCreateBy(), "创建人", 1, 50);
            ValidatorUtil.length(webchatReportIllcodeDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.length(webchatReportIllcodeDto.getSysOrgCode(), "所属部门", 1, 64);
            ValidatorUtil.length(webchatReportIllcodeDto.getWflb(), "违法类别代码", 1, 32);
            ValidatorUtil.length(webchatReportIllcodeDto.getWfxwxx(), "违法行为详细", 1, 20);
            ValidatorUtil.length(webchatReportIllcodeDto.getWfxwms(), "违法行为", 1, 256);
            ValidatorUtil.length(webchatReportIllcodeDto.getSfsx(), "是否上限", 1, 1);
            ValidatorUtil.length(webchatReportIllcodeDto.getFffs(), "发放方式", 1, 256);
            ValidatorUtil.length(webchatReportIllcodeDto.getBz(), "备注", 1, 4000);
    ResponseDto responseDto = new ResponseDto();
    WebchatReportIllcodeExample webchatReportIllcodeExample = new WebchatReportIllcodeExample();
    WebchatReportIllcodeExample.Criteria ca = webchatReportIllcodeExample.createCriteria();
    if (!StringUtils.isEmpty(webchatReportIllcodeDto.getWfxwxx())){
        ca.andWfxwxxEqualTo(webchatReportIllcodeDto.getWfxwxx());
    }
    List<WebchatReportIllcode> webchatReportIllcodeList = webchatReportIllcodeService.list(webchatReportIllcodeExample);
    if(!((StringUtils.isEmpty(webchatReportIllcodeDto.getId())&&webchatReportIllcodeList.isEmpty())||
            (!StringUtils.isEmpty(webchatReportIllcodeDto.getId())&&(webchatReportIllcodeList.isEmpty()||(!webchatReportIllcodeList.isEmpty()&&webchatReportIllcodeList.size()==1&&webchatReportIllcodeList.get(0).getId().equals(webchatReportIllcodeDto.getId())))))){
        responseDto.setSuccess(false);
        responseDto.setMessage("违法行为明细代码重复");
        return responseDto;
    }
    LoginUserDto loginUserDto   = getRequestHeader();
    if (StringUtils.isEmpty(webchatReportIllcodeDto.getId())) {
        webchatReportIllcodeDto.setSysOrgCode(loginUserDto.getDeptcode());
        webchatReportIllcodeDto.setCreateBy(loginUserDto.getLoginName());
        webchatReportIllcodeDto.setCreateTime(new Date());
    }
    webchatReportIllcodeDto.setUpdateBy(loginUserDto.getLoginName());
    webchatReportIllcodeDto.setUpdateTime(new Date());


webchatReportIllcodeService.save(webchatReportIllcodeDto);
responseDto.setContent(webchatReportIllcodeDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
webchatReportIllcodeService.delete(id);
return responseDto;
}
}
