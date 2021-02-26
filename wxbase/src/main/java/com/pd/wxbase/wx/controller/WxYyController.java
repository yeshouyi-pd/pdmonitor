package com.pd.wxbase.wx.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.WxYy;
import com.pd.server.main.domain.WxYyExample;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WxYyDto;
import com.pd.server.main.dto.basewx.my.WxyyGuoupDto;
import com.pd.server.main.service.WxYyService;
import com.pd.server.util.*;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/admin/wxYy")
public class WxYyController extends BaseWxController {

private static final Logger LOG = LoggerFactory.getLogger(WxYyController.class);
    public static final String BUSINESS_NAME = "预约";

    @Resource
    private WxYyService wxYyService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WxYyDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto   = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        WxYyExample wxYyExample = new WxYyExample();
        WxYyExample.Criteria ca = wxYyExample.createCriteria();
        if(!list.isEmpty()){
           ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(pageDto.getYwfl())){
            ca.andYwflEqualTo(pageDto.getYwfl());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andYysjGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andYysjLessThanOrEqualTo(pageDto.getEtime());
        }
        if(!StringUtils.isEmpty(pageDto.getYytype())){
            ca.andYytypeEqualTo(pageDto.getYytype());
        }
        if(!StringUtils.isEmpty(pageDto.getZt())){
            ca.andZtEqualTo(pageDto.getZt());
        }
        if(!StringUtils.isEmpty(pageDto.getName())){
            ca.andNameEqualTo(pageDto.getName());
        }
        if(!StringUtils.isEmpty(pageDto.getSjhm())){
            ca.andSjhmEqualTo(pageDto.getSjhm());
        }
        if(!StringUtils.isEmpty(pageDto.getZjhm())){
            ca.andZjhmEqualTo(pageDto.getZjhm());
        }
        wxYyExample.setOrderByClause(" cjsj desc");
        List<WxYy> wxYyList = wxYyService.list(wxYyExample);
        PageInfo<WxYy> pageInfo = new PageInfo<>(wxYyList);
        pageDto.setTotal(pageInfo.getTotal());
        List<WxYyDto> wxYyDtoList = CopyUtil.copyList(wxYyList, WxYyDto.class);
        pageDto.setList(wxYyDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/findByZjhm")
    public List<WxYy> findByZjhm(@RequestBody String zjhm){
        WxYyExample wxYyExample = new WxYyExample();
        WxYyExample.Criteria ca = wxYyExample.createCriteria();
        ca.andZjhmEqualTo(zjhm);
        ca.andCjsjGreaterThanOrEqualTo(DateUtil.nowTime());
        ca.andCjsjLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getMonthBeforeOrLater(3),"yyyy-MM-dd HH:mm:ss"));
        List<WxYy> wxYyList = wxYyService.list(wxYyExample);
        return wxYyList;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WxYyDto wxYyDto) {
        // 保存校验
        ValidatorUtil.length(wxYyDto.getDeptcode(), "预约部门", 1, 36);
        ValidatorUtil.length(wxYyDto.getYysd(), "预约时段", 1, 35);
        ValidatorUtil.length(wxYyDto.getName(), "预约人姓名", 1, 100);
        ValidatorUtil.length(wxYyDto.getSjhm(), "联系电话", 1, 20);
        ValidatorUtil.length(wxYyDto.getZjlx(), "证件类型", 1, 10);
        ValidatorUtil.length(wxYyDto.getZjhm(), "证件号码", 1, 50);
        ValidatorUtil.length(wxYyDto.getKclx(), "客车类型", 1, 20);
        ValidatorUtil.length(wxYyDto.getSfxny(), "是否新能源", 1, 10);
        ValidatorUtil.length(wxYyDto.getVin(), "车架号后六位", 1, 10);
        ValidatorUtil.length(wxYyDto.getDwmc(), "单位名称", 1, 200);
        ValidatorUtil.length(wxYyDto.getXydm(), "社会信用代码", 1, 100);
        ValidatorUtil.length(wxYyDto.getCjsj(), "创建时间", 1, 100);
        ValidatorUtil.length(wxYyDto.getYysj(), "预约时间", 1, 100);
        ValidatorUtil.length(wxYyDto.getZt(), "状态", 1, 2);
        ValidatorUtil.length(wxYyDto.getSlsj(), "受理时间", 1, 100);
        ValidatorUtil.length(wxYyDto.getYyrq(), "预约日段（段）", 1, 100);
        ValidatorUtil.length(wxYyDto.getYwfl(), "业务分类", 1, 10);
        ValidatorUtil.length(wxYyDto.getYwlx(), "业务类型", 1, 10);
        ValidatorUtil.length(wxYyDto.getOpenid(), "openid", 1, 200);
        ValidatorUtil.length(wxYyDto.getYytype(), "预约类型", 1, 2);

        ResponseDto responseDto = new ResponseDto();
        wxYyService.save(wxYyDto);
        responseDto.setContent(wxYyDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        wxYyService.delete(id);
        return responseDto;
    }

    /**
     * 获取今天,本月,本年预约数
     */
    @GetMapping("/getYyslByDay")
    public ResponseDto getYyslByDay(){
        ResponseDto responseDto = new ResponseDto();
        Map<String ,Object> resultMap = new HashMap<>();
        LoginUserDto loginUserDto   = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
        //获取今天的预约数量
        String todayStr = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd");
        WxYyExample wxYyExample = new WxYyExample();
        WxYyExample.Criteria ca = wxYyExample.createCriteria();
        if(!list.isEmpty()){
            ca.andDeptcodeIn(list);
        }
        ca.andYysjLike(todayStr+"%");
        Map<String ,Object> todayMap = wxYyService.getYyslByDay(wxYyExample);
        //获取本月的预约数量
        String yfStr = DateUtil.getFormatDate(new Date(),"yyyy-MM");
        WxYyExample yfExample = new WxYyExample();
        WxYyExample.Criteria yfCa = yfExample.createCriteria();
        if(!list.isEmpty()){
            yfCa.andDeptcodeIn(list);
        }
        yfCa.andYysjLike(yfStr+"%");
        Map<String ,Object> yfMap = wxYyService.getYyslByDay(yfExample);
        //获取本年的预约数量
        String nfStr = DateUtil.getFormatDate(new Date(),"yyyy");
        WxYyExample nfYyExample = new WxYyExample();
        WxYyExample.Criteria nfCa = nfYyExample.createCriteria();
        if(!list.isEmpty()){
            nfCa.andDeptcodeIn(list);
        }
        nfCa.andYysjLike(nfStr+"%");
        Map<String ,Object> nfMap = wxYyService.getYyslByDay(nfYyExample);
        resultMap.put("today",todayMap);
        resultMap.put("nf",nfMap);
        resultMap.put("yf",yfMap);
        responseDto.setContent(resultMap);
        return responseDto;
    }

    /**
     * 获取未来7天的预约量
     */
    @GetMapping("/getWlqtYysl")
    public ResponseDto getWlqtYysl(){
        ResponseDto responseDto = new ResponseDto();
        String todayStr = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd");
        String afterStr = DateUtil.getFormatDate(DateUtil.getNextNumDay(new Date(),15),"yyyy-MM-dd");
        LoginUserDto loginUserDto   = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
        WxYyExample wxYyExample = new WxYyExample();
        WxYyExample.Criteria ca = wxYyExample.createCriteria();
        if(!list.isEmpty()){
            ca.andDeptcodeIn(list);
        }
        ca.andYysjGreaterThan(todayStr);
        ca.andYysjLessThan(afterStr);
        List<WxyyGuoupDto> wxyyGuoupDtoList = wxYyService.getYyslByExample(wxYyExample);
        responseDto.setContent(!StringUtils.isEmpty(wxyyGuoupDtoList)?wxyyGuoupDtoList:new ArrayList<>());
        return responseDto;
    }

    /**
     * 获取本年或者本月的前10的预约数量
     */
    @GetMapping("getYyslByBeforeTen")
    public ResponseDto getYyslByBeforeTen(){
        ResponseDto responseDto = new ResponseDto();
        Map<String, Object> map = new HashMap<>();
        LoginUserDto loginUserDto   = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());
        //获取本月个人的预约数量
        String yfStr = DateUtil.getFormatDate(new Date(),"yyyy-MM");
        WxYyExample yfGrExample = new WxYyExample();
        WxYyExample.Criteria yfGrCa = yfGrExample.createCriteria();
        if(!list.isEmpty()){
            yfGrCa.andDeptcodeIn(list);
        }
        yfGrCa.andYysjLike(yfStr+"%");
        yfGrCa.andYytypeEqualTo("1");
        List<WxyyGuoupDto> yfGrList = wxYyService.getYyslByBeforeTen(yfGrExample);
        //获取本月单位的预约数量
        WxYyExample yfDwExample = new WxYyExample();
        WxYyExample.Criteria yfDwCa = yfDwExample.createCriteria();
        if(!list.isEmpty()){
            yfDwCa.andDeptcodeIn(list);
        }
        yfDwCa.andYysjLike(yfStr+"%");
        yfDwCa.andYytypeEqualTo("2");
        List<WxyyGuoupDto> yfDwList = wxYyService.getYyslByBeforeTen(yfDwExample);
        //获取本年个人的预约数量
        String nfStr = DateUtil.getFormatDate(new Date(),"yyyy");
        WxYyExample nfGrExample = new WxYyExample();
        WxYyExample.Criteria nfGrCa = nfGrExample.createCriteria();
        if(!list.isEmpty()){
            nfGrCa.andDeptcodeIn(list);
        }
        nfGrCa.andYysjLike(nfStr+"%");
        nfGrCa.andYytypeEqualTo("1");
        List<WxyyGuoupDto> nfGrList = wxYyService.getYyslByBeforeTen(nfGrExample);
        //获取本年单位的预约数量
        WxYyExample nfDwExample = new WxYyExample();
        WxYyExample.Criteria nfDwCa = nfDwExample.createCriteria();
        if(!list.isEmpty()){
            nfDwCa.andDeptcodeIn(list);
        }
        nfDwCa.andYysjLike(nfStr+"%");
        nfDwCa.andYytypeEqualTo("2");
        List<WxyyGuoupDto> nfDwList = wxYyService.getYyslByBeforeTen(nfDwExample);
        map.put("yfGrList",yfGrList);
        map.put("yfDwList",yfDwList);
        map.put("nfGrList",nfGrList);
        map.put("nfDwList",nfDwList);
        responseDto.setContent(map);
        return responseDto;
    }

}
