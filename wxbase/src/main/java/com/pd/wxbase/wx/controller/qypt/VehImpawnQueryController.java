package com.pd.wxbase.wx.controller.qypt;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehImpawnQuery;
import com.pd.server.main.domain.VehImpawnQueryExample;
import com.pd.server.main.dto.VehImpawnQueryDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.VehImpawnQueryService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/vehImpawnQuery")
public class VehImpawnQueryController {

private static final Logger LOG = LoggerFactory.getLogger(VehImpawnQueryController.class);
public static final String BUSINESS_NAME = "";

@Resource
private VehImpawnQueryService vehImpawnQueryService;

/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody VehImpawnQueryDto vehImpawnDto) {
ResponseDto responseDto = new ResponseDto();
    try{
        PageHelper.startPage(vehImpawnDto.getPage(), vehImpawnDto.getSize());
        VehImpawnQueryExample vehImpawnQueryExample = new VehImpawnQueryExample();
        VehImpawnQueryExample.Criteria ca = vehImpawnQueryExample.createCriteria();
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
        if (!StringUtils.isEmpty(vehImpawnDto.getStime())){
            ca.andCreateTimeGreaterThanOrEqualTo(vehImpawnDto.getStime());
        }
        if (!StringUtils.isEmpty(vehImpawnDto.getEtime())){
            ca.andCreateTimeLessThanOrEqualTo(vehImpawnDto.getEtime());
        }


        List<VehImpawnQuery> vehImpawnQueryList = vehImpawnQueryService.list(vehImpawnQueryExample);

        PageInfo<VehImpawnQuery> pageInfo = new PageInfo<>(vehImpawnQueryList);
        vehImpawnDto.setTotal(pageInfo.getTotal());
        List<VehImpawnQueryDto> vehImpawnDtoList = CopyUtil.copyList(vehImpawnQueryList, VehImpawnQueryDto.class);
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
public ResponseDto save(@RequestBody VehImpawnQueryDto vehImpawnQueryDto) {
// 保存校验
            ValidatorUtil.require(vehImpawnQueryDto.getCreateBy(), "创建人");
            ValidatorUtil.length(vehImpawnQueryDto.getCreateBy(), "创建人", 1, 50);
            ValidatorUtil.require(vehImpawnQueryDto.getCreateTime(), "创建日期");
            ValidatorUtil.require(vehImpawnQueryDto.getUpdateBy(), "更新人");
            ValidatorUtil.length(vehImpawnQueryDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.require(vehImpawnQueryDto.getUpdateTime(), "更新日期");
            ValidatorUtil.require(vehImpawnQueryDto.getSysOrgCode(), "所属部门");
            ValidatorUtil.length(vehImpawnQueryDto.getSysOrgCode(), "所属部门", 1, 64);
            ValidatorUtil.require(vehImpawnQueryDto.getXh(), "机动车序号");
            ValidatorUtil.length(vehImpawnQueryDto.getXh(), "机动车序号", 1, 14);
            ValidatorUtil.require(vehImpawnQueryDto.getClsbdh(), "车辆识别代号");
            ValidatorUtil.length(vehImpawnQueryDto.getClsbdh(), "车辆识别代号", 1, 20);
            ValidatorUtil.require(vehImpawnQueryDto.getZhth(), "主合同号");
            ValidatorUtil.length(vehImpawnQueryDto.getZhth(), "主合同号", 1, 64);
            ValidatorUtil.require(vehImpawnQueryDto.getDyhth(), "抵押合同号");
            ValidatorUtil.length(vehImpawnQueryDto.getDyhth(), "抵押合同号", 1, 32);
            ValidatorUtil.require(vehImpawnQueryDto.getDydjrq(), "抵押登记日期");
            ValidatorUtil.require(vehImpawnQueryDto.getJbr(), "经办人");
            ValidatorUtil.length(vehImpawnQueryDto.getJbr(), "经办人", 1, 128);
            ValidatorUtil.require(vehImpawnQueryDto.getLsh(), "流水号");
            ValidatorUtil.length(vehImpawnQueryDto.getLsh(), "流水号", 1, 32);
            ValidatorUtil.require(vehImpawnQueryDto.getSfzmmc(), "身份证明名称");
            ValidatorUtil.length(vehImpawnQueryDto.getSfzmmc(), "身份证明名称", 1, 10);
            ValidatorUtil.require(vehImpawnQueryDto.getSfzmhm(), "身份证明号码");
            ValidatorUtil.length(vehImpawnQueryDto.getSfzmhm(), "身份证明号码", 1, 32);
            ValidatorUtil.require(vehImpawnQueryDto.getXzqh(), "抵押权人行政区划");
            ValidatorUtil.length(vehImpawnQueryDto.getXzqh(), "抵押权人行政区划", 1, 128);
            ValidatorUtil.require(vehImpawnQueryDto.getXxdz(), "详细地址");
            ValidatorUtil.length(vehImpawnQueryDto.getXxdz(), "详细地址", 1, 256);
            ValidatorUtil.length(vehImpawnQueryDto.getYzbm(), "邮政编码", 1, 6);
            ValidatorUtil.require(vehImpawnQueryDto.getLxdh(), "联系电话");
            ValidatorUtil.length(vehImpawnQueryDto.getLxdh(), "联系电话", 1, 20);
            ValidatorUtil.require(vehImpawnQueryDto.getJcrq(), "解除抵押日期");
            ValidatorUtil.require(vehImpawnQueryDto.getGlbm(), "管理部门");
            ValidatorUtil.length(vehImpawnQueryDto.getGlbm(), "管理部门", 1, 12);
            ValidatorUtil.length(vehImpawnQueryDto.getXgzl(), "相关资料", 1, 20);
            ValidatorUtil.require(vehImpawnQueryDto.getShr(), "审核人");
            ValidatorUtil.length(vehImpawnQueryDto.getShr(), "审核人", 1, 128);
            ValidatorUtil.require(vehImpawnQueryDto.getShsj(), "审核时间");
            ValidatorUtil.require(vehImpawnQueryDto.getShsm(), "审核说明");
            ValidatorUtil.length(vehImpawnQueryDto.getShsm(), "审核说明", 1, 2000);
            ValidatorUtil.require(vehImpawnQueryDto.getShyy(), "审核原因");
            ValidatorUtil.length(vehImpawnQueryDto.getShyy(), "审核原因", 1, 1000);
            ValidatorUtil.length(vehImpawnQueryDto.getBz(), "备注", 1, 1000);
            ValidatorUtil.require(vehImpawnQueryDto.getZt(), "状态");
            ValidatorUtil.length(vehImpawnQueryDto.getZt(), "状态", 1, 1);
            ValidatorUtil.length(vehImpawnQueryDto.getDyqrdm(), "抵押权人代码", 1, 128);
            ValidatorUtil.length(vehImpawnQueryDto.getDyqrlx(), "抵押权人类型", 1, 20);
            ValidatorUtil.length(vehImpawnQueryDto.getDyqrsfzmmc(), "抵押权人身份证名称", 1, 20);
            ValidatorUtil.length(vehImpawnQueryDto.getDyqrsfzmhm(), "抵押权人身份证号码", 1, 20);
            ValidatorUtil.length(vehImpawnQueryDto.getSjhm(), "抵押权人手机号", 1, 20);
            ValidatorUtil.length(vehImpawnQueryDto.getHpzl(), "号牌种类", 1, 2);
            ValidatorUtil.length(vehImpawnQueryDto.getHphm(), "号牌号码", 1, 20);
            ValidatorUtil.length(vehImpawnQueryDto.getDyzt(), "抵押状态", 1, 1);

ResponseDto responseDto = new ResponseDto();
vehImpawnQueryService.save(vehImpawnQueryDto);
responseDto.setContent(vehImpawnQueryDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
vehImpawnQueryService.delete(id);
return responseDto;
}
}
