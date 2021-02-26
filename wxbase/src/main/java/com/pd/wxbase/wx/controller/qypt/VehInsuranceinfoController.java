package com.pd.wxbase.wx.controller.qypt;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehInsuranceinfo;
import com.pd.server.main.domain.VehInsuranceinfoExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.VehInsuranceinfoDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.VehInsuranceinfoService;
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
@RequestMapping("/admin/vehInsuranceinfo")
public class VehInsuranceinfoController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(VehInsuranceinfoController.class);
public static final String BUSINESS_NAME = "";

@Resource
private VehInsuranceinfoService vehInsuranceinfoService;

/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody VehInsuranceinfoDto pageDto) {
    ResponseDto responseDto = new ResponseDto();
    try {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        VehInsuranceinfoExample vehInsuranceinfoExample = new VehInsuranceinfoExample();
        VehInsuranceinfoExample.Criteria ca = vehInsuranceinfoExample.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getBbxrsfzmhm())){
            ca.andBbxrsfzmhmEqualTo(DesEncrypt.DesJiaMi(pageDto.getBbxrsfzmhm()));
        }
        if (!StringUtils.isEmpty(pageDto.getBxpzh())){
            ca.andBxpzhEqualTo(pageDto.getBxpzh());
        }
        if (!StringUtils.isEmpty(pageDto.getHphm())){
            ca.andHphmEqualTo(pageDto.getHphm());
        }
        if (!StringUtils.isEmpty(pageDto.getHpzl())){
            ca.andHpzlEqualTo(pageDto.getHpzl());
        }
        if (!StringUtils.isEmpty(pageDto.getClsbdh())){
            ca.andClsbdhEqualTo(pageDto.getClsbdh());
        }
        if (!StringUtils.isEmpty(pageDto.getSysOrgCode())){
            ca.andSysOrgCodeEqualTo(pageDto.getSysOrgCode());
        }
        if (!StringUtils.isEmpty(pageDto.getJqxtbzt())){
            ca.andJqxtbztEqualTo(pageDto.getJqxtbzt());
        }
        if (!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
        }
        vehInsuranceinfoExample.setOrderByClause(" create_time desc");
        List<VehInsuranceinfo> vehInsuranceinfoList = vehInsuranceinfoService.list(vehInsuranceinfoExample);
        PageInfo<VehInsuranceinfo> pageInfo = new PageInfo<>(vehInsuranceinfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<VehInsuranceinfoDto> vehInsuranceinfoDtoList = CopyUtil.copyList(vehInsuranceinfoList, VehInsuranceinfoDto.class);
        pageDto.setList(vehInsuranceinfoDtoList);
        responseDto.setContent(pageDto);
    }catch (Exception e){

    }
    return responseDto;
}

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody VehInsuranceinfoDto vehInsuranceinfoDto) {
// 保存校验
            ValidatorUtil.length(vehInsuranceinfoDto.getCreateBy(), "创建人", 1, 50);
            ValidatorUtil.length(vehInsuranceinfoDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.length(vehInsuranceinfoDto.getSysOrgCode(), "保险公司代码", 1, 64);
            ValidatorUtil.length(vehInsuranceinfoDto.getHpzl(), "号牌种类", 1, 32);
            ValidatorUtil.length(vehInsuranceinfoDto.getHphm(), "号牌号码", 1, 32);
            ValidatorUtil.require(vehInsuranceinfoDto.getClsbdh(), "车辆识别代号");
            ValidatorUtil.length(vehInsuranceinfoDto.getClsbdh(), "车辆识别代号", 1, 32);
            ValidatorUtil.require(vehInsuranceinfoDto.getBxpzh(), "保险凭证号码");
            ValidatorUtil.length(vehInsuranceinfoDto.getBxpzh(), "保险凭证号码", 1, 32);
            ValidatorUtil.require(vehInsuranceinfoDto.getBxje(), "保险金额");
            ValidatorUtil.length(vehInsuranceinfoDto.getBxje(), "保险金额", 1, 32);
            ValidatorUtil.require(vehInsuranceinfoDto.getBbxr(), "被保险人名称");
            ValidatorUtil.length(vehInsuranceinfoDto.getBbxr(), "被保险人名称", 1, 128);
            ValidatorUtil.require(vehInsuranceinfoDto.getBbxrsfzmhm(), "被保险人身份证明号码");
            ValidatorUtil.length(vehInsuranceinfoDto.getBbxrsfzmhm(), "被保险人身份证明号码", 1, 128);
            ValidatorUtil.require(vehInsuranceinfoDto.getJqxtbzt(), "交强险投标状态");
            ValidatorUtil.length(vehInsuranceinfoDto.getJqxtbzt(), "交强险投标状态", 1, 1);
            ValidatorUtil.length(vehInsuranceinfoDto.getCcsnsqk(), "车船税纳税情况", 1, 1);
            ValidatorUtil.length(vehInsuranceinfoDto.getCcsnsje(), "车船税纳税金额", 1, 6);
            ValidatorUtil.length(vehInsuranceinfoDto.getWspzhm(), "车船税完税/免税凭证号码", 1, 30);
            ValidatorUtil.length(vehInsuranceinfoDto.getNsrsbh(), "车船税纳税人识别号", 1, 18);
            ValidatorUtil.length(vehInsuranceinfoDto.getLsh(), "流水号", 1, 32);
            ValidatorUtil.length(vehInsuranceinfoDto.getBz(), "备注", 1, 32);
            ValidatorUtil.length(vehInsuranceinfoDto.getZt(), "状态", 1, 1);
            ValidatorUtil.length(vehInsuranceinfoDto.getShr(), "审核人", 1, 128);
            ValidatorUtil.length(vehInsuranceinfoDto.getShsm(), "审核说明", 1, 4000);
            ValidatorUtil.length(vehInsuranceinfoDto.getShyy(), "审核原因", 1, 40);

            ResponseDto responseDto = new ResponseDto();
            try {
                LoginUserDto loginUserDto = getRequestHeader();
                vehInsuranceinfoDto.setBbxrsfzmhm(DesEncrypt.DesJiaMi(vehInsuranceinfoDto.getBbxrsfzmhm()));
                vehInsuranceinfoDto.setSysOrgCode(loginUserDto.getDeptcode());
                vehInsuranceinfoDto.setCreateBy(loginUserDto.getLoginName());
                vehInsuranceinfoDto.setCreateTime(new Date());
                vehInsuranceinfoDto.setUpdateBy(loginUserDto.getLoginName());
                vehInsuranceinfoDto.setUpdateTime(new Date());
                vehInsuranceinfoService.save(vehInsuranceinfoDto);
                responseDto.setContent(vehInsuranceinfoDto);
            }catch (Exception e){
                e.printStackTrace();
            }
            return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
vehInsuranceinfoService.delete(id);
return responseDto;
}
}
