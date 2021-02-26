package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VehPass;
import com.pd.server.main.domain.VehPassExample;
import com.pd.server.main.domain.WebchatFileinfo;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.VehPassDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.VehPassService;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/admin/vehPass")
public class VehPassController  extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(VehPassController.class);
    public static final String BUSINESS_NAME = "通行证表";

    @Resource
    private VehPassService vehPassService;
    @Resource
    private WebchatFileinfoService webchatFileinfoService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody VehPassDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        VehPassExample vehPassExample = new VehPassExample();
        VehPassExample.Criteria ca = vehPassExample.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getHphm())){
            ca.andHphmEqualTo(pageDto.getHphm());
        }
        if (!StringUtils.isEmpty(pageDto.getZt())){
            ca.andZtEqualTo(pageDto.getZt());
        }
        if (!StringUtils.isEmpty(pageDto.getSqr())){
            ca.andSqrEqualTo(pageDto.getSqr());
        }
        if (!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
        }
        vehPassExample.setOrderByClause(" create_time desc");
        List<VehPass> vehPassList = vehPassService.list(vehPassExample);
        PageInfo<VehPass> pageInfo = new PageInfo<>(vehPassList);
        pageDto.setTotal(pageInfo.getTotal());
        List<VehPassDto> vehPassDtoList = CopyUtil.copyList(vehPassList, VehPassDto.class);
        pageDto.setList(vehPassDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/vehPassCheck")
    public ResponseDto vehPassCheck(@RequestBody VehPassDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        VehPassExample vehPassExample = new VehPassExample();
        VehPassExample.Criteria ca = vehPassExample.createCriteria();
        ca.andZtEqualTo("1");
        if (!StringUtils.isEmpty(pageDto.getHphm())){
            ca.andHphmEqualTo(pageDto.getHphm());
        }
        if (!StringUtils.isEmpty(pageDto.getSqr())){
            ca.andSqrEqualTo(pageDto.getSqr());
        }
        if (!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
        }
        vehPassExample.setOrderByClause(" create_time desc");
        List<VehPass> vehPassList = vehPassService.list(vehPassExample);
        PageInfo<VehPass> pageInfo = new PageInfo<>(vehPassList);
        pageDto.setTotal(pageInfo.getTotal());
        List<VehPassDto> vehPassDtoList = CopyUtil.copyList(vehPassList, VehPassDto.class);
        pageDto.setList(vehPassDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody VehPassDto vehPassDto) {
// 保存校验
        ValidatorUtil.length(vehPassDto.getCreateBy(), "创建人", 1, 50);
        ValidatorUtil.length(vehPassDto.getUpdateBy(), "更新人", 1, 50);
        ValidatorUtil.length(vehPassDto.getSysOrgCode(), "处理部门", 1, 64);
        ValidatorUtil.length(vehPassDto.getSqr(), "个人/单位", 1, 256);
        ValidatorUtil.length(vehPassDto.getTxzlx(), "通行证类型", 1, 32);
        ValidatorUtil.length(vehPassDto.getCfd(), "出发地", 1, 128);
        ValidatorUtil.length(vehPassDto.getMdd(), "目的地", 1, 128);
        ValidatorUtil.length(vehPassDto.getHpzl(), "号牌种类", 1, 2);
        ValidatorUtil.length(vehPassDto.getHphm(), "号牌号码", 1, 32);
        ValidatorUtil.length(vehPassDto.getSyr(), "所有人", 1, 128);
        ValidatorUtil.length(vehPassDto.getLxdh(), "联系电话", 1, 20);
        ValidatorUtil.length(vehPassDto.getSqsm(), "申请说明", 1, 500);
        ValidatorUtil.length(vehPassDto.getTxxl(), "通行线路", 1, 4000);
        ValidatorUtil.length(vehPassDto.getZt(), "", 1, 2);
        ValidatorUtil.length(vehPassDto.getShr(), "审核人", 1, 128);
        ValidatorUtil.length(vehPassDto.getShsm(), "审核说明", 1, 256);
        ValidatorUtil.length(vehPassDto.getOpenid(), "微信用户id", 1, 32);
        ValidatorUtil.length(vehPassDto.getLsh(), "流水号", 1, 32);
        ValidatorUtil.length(vehPassDto.getBz(), "备注", 1, 2000);
        ValidatorUtil.length(vehPassDto.getTxzbh(), "", 1, 30);

        ResponseDto responseDto = new ResponseDto();
        StringBuffer s =  new StringBuffer();
        List<String> list = vehPassDto.getChecktxxl();
        if(!list.isEmpty()){
            Iterator it = list.iterator();
            while(it.hasNext()){
                Object o = it.next();
                if(s.length() > 0){
                    s.append("-");
                    s.append(o);
                } else{
                    s.append(o);
                }
            }
        }
        LoginUserDto loginUserDto   = getRequestHeader();
        vehPassDto.setShr(loginUserDto.getLoginName());
        vehPassDto.setSysOrgCode(loginUserDto.getDeptcode());
        vehPassDto.setUpdateBy(loginUserDto.getLoginName());
        vehPassDto.setUpdateTime(new Date());
        vehPassDto.setShsj(new Date());
        vehPassDto.setTxxl(s.toString());
        vehPassService.save(vehPassDto);
        responseDto.setContent(vehPassDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        vehPassService.delete(id);
        return responseDto;
    }

    /**
     * 获取需要拍照的图片
     * @param vehPassDto
     * @return
     */
    @PostMapping("/getpicById")
    public ResponseDto getpicById(@RequestBody VehPassDto vehPassDto){
        ResponseDto responseDto = new ResponseDto();
        if(null != vehPassDto){
            if(!StringUtils.isEmpty(vehPassDto.getId())){
                List<WebchatFileinfo> piclist =webchatFileinfoService.getFileinfoByLsh(vehPassDto.getId());
                if(!CollectionUtils.isEmpty(piclist)){
                    responseDto.setContent(piclist);
                }
            }
        }
        return responseDto;
    }
}
