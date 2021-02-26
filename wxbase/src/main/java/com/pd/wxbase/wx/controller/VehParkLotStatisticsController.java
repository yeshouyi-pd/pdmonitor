package com.pd.wxbase.wx.controller;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonObject;
import com.pd.server.main.domain.VehParkLot;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.VehParkLotService;
import com.pd.server.main.service.VehParkLotStatisticsService;
import com.pd.server.util.DateUtil;
import com.pd.server.util.ValidatorUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/vehParkLotStatistics")
public class VehParkLotStatisticsController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(VehParkLotStatisticsController.class);
    public static final String BUSINESS_NAME = "违法车统计";

    @Resource
    private VehParkLotStatisticsService vehParkLotStatisticsService;
    @Resource
    private VehParkLotService vehParkLotService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        vehParkLotStatisticsService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody VehParkLotStatisticsDto vehParkLotStatisticsDto) {
        // 保存校验
        ValidatorUtil.length(vehParkLotStatisticsDto.getSysOrgCode(), "停车场", 1, 255);
        ValidatorUtil.length(vehParkLotStatisticsDto.getZt(), "状态1入场2出场", 1, 10);

        ResponseDto responseDto = new ResponseDto();
        vehParkLotStatisticsService.save(vehParkLotStatisticsDto);
        responseDto.setContent(vehParkLotStatisticsDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        vehParkLotStatisticsService.delete(id);
        return responseDto;
    }



    /**
     * 统计
     */
    @PostMapping("/getList")
    public  ResponseDto getStatisticsContent(@RequestBody VehParkLotStatisticsDto vehParkLotStatisticsDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto   = getRequestHeader();
        List<String> list = getUpdeptcode(loginUserDto.getDeptcode());//获取部门属性
        Map<String,Object> map = new HashMap<String,Object>();
        //获得今天的日期
        String dateStr = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd");
        if(dateStr.equals(vehParkLotStatisticsDto.getCheckdate())){//统计今天
            VehParkLotDto vehParkLotDto = new VehParkLotDto();
            vehParkLotDto.setZt("1");
            vehParkLotDto.setSysOrgCodeListStr(list);
            Map<String,Object> rlist = vehParkLotService.getStatisticsByToday(vehParkLotDto);
            map.put("rlist",rlist);
            vehParkLotDto.setZt("2");
            Map<String,Object> clist = vehParkLotService.getStatisticsByToday(vehParkLotDto);
            map.put("clist",clist);
        }else{//按类型同统计
            vehParkLotStatisticsDto.setSysOrgCodeListStr(list);
            vehParkLotStatisticsDto.setZt("1");
            if("1".equals(vehParkLotStatisticsDto.getChooseType())){
                vehParkLotStatisticsDto.setChooseType("%Y");//年
            }
            if("2".equals(vehParkLotStatisticsDto.getChooseType())){
                vehParkLotStatisticsDto.setChooseType("%Y-%m");//月
            }
            if("3".equals(vehParkLotStatisticsDto.getChooseType())){
                vehParkLotStatisticsDto.setChooseType("%Y-%m-%d");//日
            }
            Map<String,Object> rlist = vehParkLotStatisticsService.getStatistics(vehParkLotStatisticsDto);
            map.put("rlist",rlist);
            vehParkLotStatisticsDto.setZt("2");
            Map<String,Object> clist = vehParkLotStatisticsService.getStatistics(vehParkLotStatisticsDto);
            map.put("clist",clist);
        }
        responseDto.setContent(map);
        return responseDto;
    }

}
