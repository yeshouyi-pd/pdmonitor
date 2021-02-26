package com.pd.wxbase.wx.controller.qypt;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehImpawnCheck;
import com.pd.server.main.domain.VehImpawnCheckExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VehImpawnCheckDto;
import com.pd.server.main.service.VehImpawnCheckService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/vehImpawnCheck")
public class VehImpawnCheckController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(VehImpawnCheckController.class);
public static final String BUSINESS_NAME = "";

@Resource
private VehImpawnCheckService vehImpawnCheckService;

/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody VehImpawnCheckDto vehImpawnDto) {
    LoginUserDto loginUserDto   = getRequestHeader();

ResponseDto responseDto = new ResponseDto();
    try{
        PageHelper.startPage(vehImpawnDto.getPage(), vehImpawnDto.getSize());
        VehImpawnCheckExample vehImpawnCheckExample = new VehImpawnCheckExample();
        VehImpawnCheckExample.Criteria ca = vehImpawnCheckExample.createCriteria();
        if (!StringUtils.isEmpty(vehImpawnDto.getXh())){
            ca.andXhEqualTo(vehImpawnDto.getXh());
        }

        if (!StringUtils.isEmpty(vehImpawnDto.getClsbdh())){
            ca.andClsbdhEqualTo(vehImpawnDto.getClsbdh());
        }
        if (!StringUtils.isEmpty(vehImpawnDto.getZhth())){
            ca.andZhthEqualTo(vehImpawnDto.getZhth());
        }
        if (!StringUtils.isEmpty(vehImpawnDto.getDyhth())){
            ca.andDyhthEqualTo(vehImpawnDto.getDyhth());
        }
        if (!StringUtils.isEmpty(vehImpawnDto.getSfzmhm())){
            ca.andSfzmhmEqualTo(vehImpawnDto.getSfzmhm());
        }
        if (!StringUtils.isEmpty(vehImpawnDto.getZt())){
            ca.andZtEqualTo(vehImpawnDto.getZt());
        }
        if (!StringUtils.isEmpty(vehImpawnDto.getStime())){
            ca.andCreateTimeGreaterThanOrEqualTo(vehImpawnDto.getStime());
        }
        if (!StringUtils.isEmpty(vehImpawnDto.getEtime())){
            ca.andCreateTimeLessThanOrEqualTo(vehImpawnDto.getEtime());
        }
        ca.andSysOrgCodeEqualTo(loginUserDto.getDeptcode());


        List<VehImpawnCheck> vehImpawnCheckList = vehImpawnCheckService.list(vehImpawnCheckExample);

        PageInfo<VehImpawnCheck> pageInfo = new PageInfo<>(vehImpawnCheckList);
        vehImpawnDto.setTotal(pageInfo.getTotal());
        List<VehImpawnCheckDto> vehImpawnDtoList = CopyUtil.copyList(vehImpawnCheckList, VehImpawnCheckDto.class);
        vehImpawnDto.setList(vehImpawnDtoList);

        responseDto.setContent(vehImpawnDto);
    }catch (Exception e){
        e.printStackTrace();
    }
return responseDto;
}

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody VehImpawnCheckDto vehImpawnCheckDto) throws Exception {
// 保存校验
            ValidatorUtil.require(vehImpawnCheckDto.getShsm(), "审核说明");
            ValidatorUtil.length(vehImpawnCheckDto.getShsm(), "审核说明", 1, 2000);
            ValidatorUtil.length(vehImpawnCheckDto.getShyy(), "审核原因", 1, 1000);
            ValidatorUtil.require(vehImpawnCheckDto.getZt(), "审核结果");
            ValidatorUtil.length(vehImpawnCheckDto.getZt(), "审核结果", 1, 1);

ResponseDto responseDto = new ResponseDto();
    LoginUserDto requestHeader = this.getRequestHeader();
vehImpawnCheckService.save(vehImpawnCheckDto,requestHeader);
responseDto.setContent(vehImpawnCheckDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
vehImpawnCheckService.delete(id);
return responseDto;
}
}
