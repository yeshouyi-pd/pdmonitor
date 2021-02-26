package com.pd.wxbase.wx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.WebchatNewinfoService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DesEncrypt;
import com.pd.server.util.OrderUtils;
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
@RequestMapping("/admin/wxNewinfo")
public class WxNewinfoController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(WxNewinfoController.class);
public static final String BUSINESS_NAME = "信息发布";

@Resource
private WebchatNewinfoService wxNewinfoService;

    @Resource

    private RedisTemplate redisTemplate;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WebchatNewinfoDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WebchatNewinfoExample example = new WebchatNewinfoExample();
        WebchatNewinfoExample.Criteria ca = example.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getXxlb())){
            ca.andXxlbEqualTo(pageDto.getXxlb());
        }
        if (!StringUtils.isEmpty(pageDto.getZt())){
            ca.andZtEqualTo(pageDto.getZt());
        }
        if (!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCreateTimeGreaterThanOrEqualTo(pageDto.getStime());
        }
        if (!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCreateTimeLessThanOrEqualTo(pageDto.getEtime());
        }
        example.setOrderByClause(" create_time desc");
        List<WebchatNewinfo> listinfo = wxNewinfoService.list(example);
        PageInfo<WebchatNewinfo> pageInfo = new PageInfo<WebchatNewinfo>(listinfo);
        pageDto.setTotal(pageInfo.getTotal());
        List<WebchatNewinfoDto> dtoList = CopyUtil.copyList(listinfo, WebchatNewinfoDto.class);
        pageDto.setList(dtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }


    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WebchatNewinfoDto wxNewinfoDto) {
            // 保存校验
            ValidatorUtil.length(wxNewinfoDto.getCreateBy(), "创建人", 1, 50);
            ValidatorUtil.length(wxNewinfoDto.getUpdateBy(), "更新人", 1, 50);
            ValidatorUtil.length(wxNewinfoDto.getSysOrgCode(), "所属部门", 1, 64);
            ValidatorUtil.length(wxNewinfoDto.getTitle(), "标题", 1, 32);
            ValidatorUtil.length(wxNewinfoDto.getGjz(), "关键字", 1, 32);
            ValidatorUtil.length(wxNewinfoDto.getXxlb(), "信息类别", 1, 32);
            ValidatorUtil.length(wxNewinfoDto.getZt(), "状态", 1, 32);
            ValidatorUtil.length(wxNewinfoDto.getShr(), "审核人", 1, 32);
            ValidatorUtil.length(wxNewinfoDto.getLsh(), "流水号", 1, 32);
            ValidatorUtil.length(wxNewinfoDto.getLsh(), "审核说明", 1, 200);
            ValidatorUtil.length(wxNewinfoDto.getLsh(), "备注", 1, 200);

            ResponseDto responseDto = new ResponseDto();
            LoginUserDto userDto = getRequestHeader();
            if (StringUtils.isEmpty(wxNewinfoDto.getId())) {
                wxNewinfoDto.setLsh(OrderUtils.getOrderNo());
                wxNewinfoDto.setCreateBy(userDto.getLoginName());
                wxNewinfoDto.setCreateTime(new Date());
                wxNewinfoDto.setSysOrgCode(userDto.getDeptcode());
                wxNewinfoDto.setZt("0");
            } else {
                WebchatNewinfoDto info = wxNewinfoService.findByID(wxNewinfoDto.getId());
                if("1".equals(info.getZt())){//状态1表示已经审核通过,不能在修改
                    throw new WxStrException("该信息已经审核通过,无法继续操作");
                }
                if(!"0".equals(wxNewinfoDto.getZt())){
                    wxNewinfoDto.setShr(userDto.getLoginName());
                    wxNewinfoDto.setShsj(new Date());
                }else{
                    wxNewinfoDto.setUpdateBy(userDto.getLoginName());
                    wxNewinfoDto.setUpdateTime(new Date());
                }
            }
            wxNewinfoService.save(wxNewinfoDto);
            responseDto.setContent(wxNewinfoDto);
            return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        wxNewinfoService.delete(id);
        return responseDto;
        }

}


