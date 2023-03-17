package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.EquipmentFileTyExample;
import com.pd.server.main.domain.EquipmentFileTyToday;
import com.pd.server.main.domain.EquipmentFileTyTodayExample;
import com.pd.server.main.dto.EquipmentFileTyTodayDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.basewx.my.GpsKVDto;
import com.pd.server.main.dto.basewx.my.TyDataDto;
import com.pd.server.main.service.EquipmentFileTyService;
import com.pd.server.main.service.EquipmentFileTyTodayService;
import com.pd.server.util.DateUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.axis.utils.StringUtils;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/equipmentFileTyToday")
public class EquipmentFileTyTodayController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileTyTodayController.class);
    public static final String BUSINESS_NAME = "拖曳设备当日声谱图";

    @Resource
    private EquipmentFileTyTodayService equipmentFileTyTodayService;
    @Resource
    private EquipmentFileTyService equipmentFileTyService;

    @GetMapping("/getDataStatistics/{sbbh}")
    public ResponseDto getDataStatistics(@PathVariable String sbbh){
        ResponseDto responseDto = new ResponseDto();
//        LoginUserDto userDto = getRequestHeader();
//        List<String> depts = getUpdeptcode(userDto.getDeptcode());
        EquipmentFileTyTodayExample example = new EquipmentFileTyTodayExample();
        EquipmentFileTyTodayExample.Criteria ca = example.createCriteria();
//        if(!CollectionUtils.isEmpty(depts)){
//            ca.andDeptcodeIn(depts);
//        }
        ca.andSbbhEqualTo(sbbh);
        ca.andWjlxEqualTo("3");
        TyDataDto dto = equipmentFileTyTodayService.selectTyData(example);
        responseDto.setContent(dto);
        return responseDto;
    }

    @PostMapping("/selectGpsByDateRange")
    public ResponseDto selectGpsByDateRange(@RequestBody EquipmentFileTyTodayDto todayDto){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileTyExample example = new EquipmentFileTyExample();
        EquipmentFileTyExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(todayDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(todayDto.getStime());
        }
        if(!StringUtils.isEmpty(todayDto.getEtime())){
            ca.andRqLessThanOrEqualTo(todayDto.getEtime());
        }
        ca.andTxtlxEqualTo("3");
        List<GpsKVDto> gpsList = equipmentFileTyService.selectGpsByExample(example);
        responseDto.setContent(gpsList);
        return responseDto;
    }

    @PostMapping("/selectTodayGps")
    public ResponseDto selectTodayGps(@RequestBody EquipmentFileTyTodayDto todayDto){
        ResponseDto responseDto = new ResponseDto();
        String todayStr = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd");
        List<GpsKVDto> gpsList = new ArrayList<>();
        if(!StringUtils.isEmpty(todayDto.getRq())){
            if(todayStr.equals(todayDto.getRq())){
                gpsList = equipmentFileTyTodayService.selectTodayGps(todayDto.getRq(),todayDto.getSbbh());
            }else{
                gpsList = equipmentFileTyService.selectTodayGps(todayDto.getRq(),todayDto.getSbbh());
            }
        }
        responseDto.setContent(gpsList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTyTodayService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileTyTodayDto equipmentFileTyTodayDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileTyTodayDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileTyTodayDto.getTplj(), "图片路径", 1, 500);
                ValidatorUtil.length(equipmentFileTyTodayDto.getNf(), "采集年份", 1, 20);
                ValidatorUtil.length(equipmentFileTyTodayDto.getYf(), "采集月份", 1, 20);
                ValidatorUtil.length(equipmentFileTyTodayDto.getRq(), "采集日期", 1, 20);
                ValidatorUtil.length(equipmentFileTyTodayDto.getXs(), "采集小时", 1, 20);
                ValidatorUtil.length(equipmentFileTyTodayDto.getFz(), "采集分钟", 1, 20);
                ValidatorUtil.length(equipmentFileTyTodayDto.getDeptcode(), "部门", 1, 36);
                ValidatorUtil.length(equipmentFileTyTodayDto.getXmbh(), "项目编号", 1, 36);
                ValidatorUtil.length(equipmentFileTyTodayDto.getWjlx(), "报警文件类型1图片2音频3文件", 1, 128);
                ValidatorUtil.length(equipmentFileTyTodayDto.getJczl(), "监测种类1江豚2白海豚", 1, 1);
                ValidatorUtil.length(equipmentFileTyTodayDto.getLy(), "来源1实时数据采集2非实时数据分析", 1, 1);
                ValidatorUtil.length(equipmentFileTyTodayDto.getSm1(), "坐标原数据", 1, 500);
                ValidatorUtil.length(equipmentFileTyTodayDto.getSm2(), "坐标信息", 1, 255);
                ValidatorUtil.length(equipmentFileTyTodayDto.getSm3(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTyTodayDto.getSm4(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTyTodayDto.getSm5(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTyTodayDto.getSm6(), "", 1, 255);
                ValidatorUtil.length(equipmentFileTyTodayDto.getType(), "type，codeset表中的文件种类", 1, 50);
                ValidatorUtil.length(equipmentFileTyTodayDto.getTs(), "头数", 1, 10);
                ValidatorUtil.length(equipmentFileTyTodayDto.getTxtlx(), "txt文件类型", 1, 5);
                ValidatorUtil.length(equipmentFileTyTodayDto.getWjmc(), "文件名称截取，为了查询出对应的音频，文件，图片等数据", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileTyTodayService.save(equipmentFileTyTodayDto);
        responseDto.setContent(equipmentFileTyTodayDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileTyTodayService.delete(id);
        return responseDto;
    }

}
