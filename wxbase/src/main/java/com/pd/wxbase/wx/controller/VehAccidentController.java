package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehAccident;
import com.pd.server.main.domain.VehAccidentExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VehAccidentDto;
import com.pd.server.main.service.VehAccidentService;
import com.pd.server.util.CopyUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/vehAccident")
public class VehAccidentController  extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(VehAccidentController.class);
    public static final String BUSINESS_NAME = "事故管理";

    @Resource
    private VehAccidentService vehAccidentService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody VehAccidentDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        VehAccidentExample vehAccidentExample = new VehAccidentExample();
        VehAccidentExample.Criteria ca = vehAccidentExample.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getStime())){
            ca.andSgfssjGreaterThan(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andSgfssjLessThanOrEqualTo(pageDto.getEtime());
        }
        if(!StringUtils.isEmpty(pageDto.getZt())){
            ca.andZtEqualTo(pageDto.getZt());
        }
        if(!StringUtils.isEmpty(pageDto.getSfgj())){
            ca.andSfgjEqualTo(pageDto.getSfgj());
        }
        if(!StringUtils.isEmpty(pageDto.getClfs())){
            ca.andClfsEqualTo(pageDto.getClfs());
        }
        if(!StringUtils.isEmpty(pageDto.getSgbh())){
            ca.andSgbhLike("%"+pageDto.getSgbh()+"%");
        }
        vehAccidentExample.setOrderByClause(" create_time desc");
        List<VehAccident> vehAccidentList = vehAccidentService.list(vehAccidentExample);
        PageInfo<VehAccident> pageInfo = new PageInfo<>(vehAccidentList);
        pageDto.setTotal(pageInfo.getTotal());
        List<VehAccidentDto> vehAccidentDtoList = CopyUtil.copyList(vehAccidentList, VehAccidentDto.class);
        pageDto.setList(vehAccidentDtoList);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/update")
    public ResponseDto update(@RequestBody VehAccidentDto vehAccidentDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto   = getRequestHeader();
        if(!StringUtils.isEmpty(vehAccidentDto.getZt())&&vehAccidentDto.getZt().equals("1")){
            vehAccidentDto.setShrxm(loginUserDto.getLoginName());
        }
        if(!StringUtils.isEmpty(vehAccidentDto.getZt())&&vehAccidentDto.getZt().equals("2")){
            vehAccidentDto.setDzmj(loginUserDto.getLoginName());
            vehAccidentDto.setDzsj(new Date());
        }
        vehAccidentDto.setUpdateBy(loginUserDto.getLoginName());
        vehAccidentDto.setUpdateTime(new Date());
        vehAccidentService.save(vehAccidentDto);
        responseDto.setContent(vehAccidentDto);
        return responseDto;
    }
}
