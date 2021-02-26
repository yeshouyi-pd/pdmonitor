package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehLicelost;
import com.pd.server.main.domain.VehLicelostExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.VehLicelostDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.VehLicelostService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/admin/vehLicelost")
public class VehLicelostController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(VehLicelostController.class);
public static final String BUSINESS_NAME = "";

@Resource
private VehLicelostService vehLicelostService;

    /**
     * 车牌遗失录入查询，查询列表只显示已经录入的车牌，已申领的不显示,即查询状态为1的
     */
    @PostMapping("/prelist")
    public ResponseDto prelist(@RequestBody VehLicelostDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        VehLicelostExample vehLicelostExample = new VehLicelostExample();
        VehLicelostExample.Criteria ca = vehLicelostExample.createCriteria();
        ca.andZtEqualTo("1");

        if (!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
        }

        if (!StringUtils.isEmpty(pageDto.getHphm())){
            ca.andHphmEqualTo(pageDto.getHphm());
        }

        if (!StringUtils.isEmpty(pageDto.getHpzl())){
            ca.andHpzlEqualTo(pageDto.getHpzl());
        }
        /**
         * 查询当前部门录入的车牌信息
         */
        LoginUserDto loginUserDto   = getRequestHeader();
        ca.andSysOrgCodeEqualTo(loginUserDto.getDeptcode());
        vehLicelostExample.setOrderByClause(" create_time desc");
        List<VehLicelost> vehLicelostList = vehLicelostService.list(vehLicelostExample);
        PageInfo<VehLicelost> pageInfo = new PageInfo<>(vehLicelostList);
        pageDto.setTotal(pageInfo.getTotal());
        List<VehLicelostDto> vehLicelostDtoList = CopyUtil.copyList(vehLicelostList, VehLicelostDto.class);
        pageDto.setList(vehLicelostDtoList);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(pageDto);
        return responseDto;
    }

/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody VehLicelostDto pageDto) {
    LoginUserDto loginUserDto   = getRequestHeader();
    List<String> list = getUpdeptcode(loginUserDto.getDeptcode());

    PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
    VehLicelostExample vehLicelostExample = new VehLicelostExample();
    VehLicelostExample.Criteria ca = vehLicelostExample.createCriteria();
    if (!StringUtils.isEmpty(pageDto.getStime())){
        ca.andLqrqGreaterThanOrEqualTo(pageDto.getStime());
    }
    if (!StringUtils.isEmpty(pageDto.getEtime())){
        ca.andLqrqLessThanOrEqualTo(pageDto.getEtime());
    }

    if (!StringUtils.isEmpty(pageDto.getHphm())){
        ca.andHphmEqualTo(pageDto.getHphm());
    }

    if (!StringUtils.isEmpty(pageDto.getHpzl())){
        ca.andHpzlEqualTo(pageDto.getHpzl());
    }

    if (!StringUtils.isEmpty(pageDto.getLqrxm())){
        ca.andLqrxmEqualTo(pageDto.getLqrxm());
    }

    if (!StringUtils.isEmpty(pageDto.getLqrdh())){
        ca.andLqrdhEqualTo(pageDto.getLqrdh());
    }

    if (!StringUtils.isEmpty(pageDto.getSfzmhm())){
        ca.andSfzmhmEqualTo(pageDto.getSfzmhm());
    }

    if (!StringUtils.isEmpty(pageDto.getZt())){
        ca.andZtEqualTo(pageDto.getZt());
    }
    /**
     * 查询当前部门以及下级部门的信息
     */
    if(!list.isEmpty()){
        ca.andSysOrgCodeIn(list);
    }
    vehLicelostExample.setOrderByClause(" create_time desc");
    List<VehLicelost> vehLicelostList = vehLicelostService.list(vehLicelostExample);
    PageInfo<VehLicelost> pageInfo = new PageInfo<>(vehLicelostList);
    pageDto.setTotal(pageInfo.getTotal());
    List<VehLicelostDto> vehLicelostDtoList = CopyUtil.copyList(vehLicelostList, VehLicelostDto.class);
    pageDto.setList(vehLicelostDtoList);


    ResponseDto responseDto = new ResponseDto();
    responseDto.setContent(pageDto);
    return responseDto;
}

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody VehLicelostDto vehLicelostDto) {
// 保存校验
            ValidatorUtil.length(vehLicelostDto.getCreateBy(), "创建人", 1, 128);
            ValidatorUtil.length(vehLicelostDto.getUpdateBy(), "更新人", 1, 128);
            ValidatorUtil.length(vehLicelostDto.getSysOrgCode(), "所属部门", 1, 64);
            ValidatorUtil.length(vehLicelostDto.getHpzl(), "号牌种类", 1, 32);
            ValidatorUtil.length(vehLicelostDto.getHphm(), "号牌号码", 1, 32);
            ValidatorUtil.length(vehLicelostDto.getLqdz(), "领取地址", 1, 512);
            ValidatorUtil.length(vehLicelostDto.getLqdh(), "领取电话", 1, 32);
            ValidatorUtil.length(vehLicelostDto.getLqrxm(), "领取人姓名", 1, 32);
            ValidatorUtil.length(vehLicelostDto.getLqrdh(), "领取人电话", 1, 32);
            ValidatorUtil.length(vehLicelostDto.getSfzmhm(), "身份证明号码", 1, 32);
            ValidatorUtil.length(vehLicelostDto.getLqsm(), "领取说明", 1, 4000);
            ValidatorUtil.length(vehLicelostDto.getZt(), "状态", 1, 2);
            ValidatorUtil.length(vehLicelostDto.getOpenid(), "微信用户openid", 1, 32);
            ValidatorUtil.length(vehLicelostDto.getBz(), "备注", 1, 4000);
            ValidatorUtil.length(vehLicelostDto.getLsh(), "流水号", 1, 45);

            ResponseDto responseDto = new ResponseDto();
            LoginUserDto loginUserDto   = getRequestHeader();
            if (StringUtils.isEmpty(vehLicelostDto.getId())) {
                vehLicelostDto.setSysOrgCode(loginUserDto.getDeptcode());
                vehLicelostDto.setCreateBy(loginUserDto.getLoginName());
                vehLicelostDto.setCreateTime(new Date());
                vehLicelostDto.setZt("1");              //车牌状态1录入未申领2已领取
            }
            if("2".equals(vehLicelostDto.getZt())){
                vehLicelostDto.setLqrq(new Date());
            }
            vehLicelostDto.setUpdateBy(loginUserDto.getLoginName());
            vehLicelostDto.setUpdateTime(new Date());
            vehLicelostService.save(vehLicelostDto);
            responseDto.setContent(vehLicelostDto);
            return responseDto;
}

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/saveAll")
    public ResponseDto saveAll(@RequestBody VehLicelostDto vehLicelostDto) {
// 保存校验
        ValidatorUtil.length(vehLicelostDto.getCreateBy(), "创建人", 1, 128);
        ValidatorUtil.length(vehLicelostDto.getUpdateBy(), "更新人", 1, 128);
        ValidatorUtil.length(vehLicelostDto.getSysOrgCode(), "所属部门", 1, 64);
        ValidatorUtil.length(vehLicelostDto.getHpzl(), "号牌种类", 1, 32);
        ValidatorUtil.length(vehLicelostDto.getHphm(), "号牌号码", 1, 32);
        ValidatorUtil.length(vehLicelostDto.getLqdz(), "领取地址", 1, 512);
        ValidatorUtil.length(vehLicelostDto.getLqdh(), "领取电话", 1, 32);
        ValidatorUtil.length(vehLicelostDto.getLqrxm(), "领取人姓名", 1, 32);
        ValidatorUtil.length(vehLicelostDto.getLqrdh(), "领取人电话", 1, 32);
        ValidatorUtil.length(vehLicelostDto.getSfzmhm(), "身份证明号码", 1, 32);
        ValidatorUtil.length(vehLicelostDto.getLqsm(), "领取说明", 1, 4000);
        ValidatorUtil.length(vehLicelostDto.getZt(), "状态", 1, 2);
        ValidatorUtil.length(vehLicelostDto.getOpenid(), "微信用户openid", 1, 32);
        ValidatorUtil.length(vehLicelostDto.getBz(), "备注", 1, 4000);
        ValidatorUtil.length(vehLicelostDto.getLsh(), "流水号", 1, 45);

        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto   = getRequestHeader();
        List<Map<String,Object>> cpInfos = vehLicelostDto.getCpInfos();
        if(!StringUtils.isEmpty(cpInfos)&&cpInfos.size()>0){
            vehLicelostDto.setSysOrgCode(loginUserDto.getDeptcode());
            vehLicelostDto.setCreateBy(loginUserDto.getLoginName());
            vehLicelostDto.setCreateTime(new Date());
            vehLicelostDto.setZt("1");
            vehLicelostDto.setUpdateBy(loginUserDto.getLoginName());
            vehLicelostDto.setUpdateTime(new Date());
            for(int i=0;i<cpInfos.size();i++){
                vehLicelostDto.setHpzl(cpInfos.get(i).get("cplx").toString());
                vehLicelostDto.setHphm(cpInfos.get(i).get("hphm").toString().toUpperCase(Locale.ROOT));
                vehLicelostService.save(vehLicelostDto);
            }
        }
        responseDto.setContent(vehLicelostDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        vehLicelostService.delete(id);
        return responseDto;
    }

}
