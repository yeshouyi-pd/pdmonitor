package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.dto.EquipmentFileDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/equipmentFile")
public class EquipmentFileController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileController.class);
    public static final String BUSINESS_NAME = "水噪声图片";

    @Resource
    private EquipmentFileService equipmentFileService;
    @Resource
    private WaterEquipmentService waterEquipmentService;
    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/statisticsAlarmNumsByMinute")
    public ResponseDto statisticsAlarmNumsByMinute(@RequestBody AlarmNumbersDto entityDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(entityDto.getSbbh())){
            ca.andSbbhEqualTo(entityDto.getSbbh());
        }
        if(!StringUtils.isEmpty(entityDto.getDeptcode())){
            ca.andDeptcodeEqualTo(entityDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(entityDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(entityDto.getStime());
        }
        if(!StringUtils.isEmpty(entityDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(entityDto.getEtime());
        }
        ca.andTpljLike("%png");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNums(example);
        List<String> xAixsData = lists.stream().filter(Objects::nonNull).map(u->u.getBjsj()+" "+u.getXs()+":"+u.getFz()).collect(Collectors.toList());
        List<Integer> yAixsData = lists.stream().filter(Objects::nonNull).map(u-> u.getAlarmNum()).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("xAixsData",xAixsData);
        map.put("yAixsData",yAixsData);
        responseDto.setContent(map);
        return responseDto;
    }

    @PostMapping("/statisticsAlarmNumsByHour")
    public ResponseDto statisticsAlarmNumsByHour(@RequestBody AlarmNumbersDto entityDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(entityDto.getSbbh())){
            ca.andSbbhEqualTo(entityDto.getSbbh());
        }
        if(!StringUtils.isEmpty(entityDto.getDeptcode())){
            ca.andDeptcodeEqualTo(entityDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(entityDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(entityDto.getStime());
        }
        if(!StringUtils.isEmpty(entityDto.getEtime())){
            ca.andRqLessThanOrEqualTo(entityDto.getEtime());
        }
        ca.andTpljLike("%png");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNumsByHour(example);
        Optional<Integer> op = lists.stream().filter(Objects::nonNull).map(AlarmNumbersDto::getAlarmNum).reduce(Integer::sum);
        List<String> xAixsData = lists.stream().filter(Objects::nonNull).map(u->u.getBjsj()+" "+u.getXs()).collect(Collectors.toList());
        List<Double> yAixsData = new ArrayList<>();
        if(op.isPresent()){
            yAixsData = lists.stream().filter(Objects::nonNull).map(u-> div(u.getAlarmNum(),op.get(),4)*100).collect(Collectors.toList());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xAixsData",xAixsData);
        map.put("yAixsData",yAixsData);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 统计报警次数
     * @param alarmNumbersDto
     * @return
     */
    @PostMapping("/statisticsAlarmNums")
    public ResponseDto statisticsAlarmNums(@RequestBody AlarmNumbersDto alarmNumbersDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        PageHelper.startPage(alarmNumbersDto.getPage(), alarmNumbersDto.getSize());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getSbbh())){
            ca.andSbbhEqualTo(alarmNumbersDto.getSbbh());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getDeptcode())){
            ca.andDeptcodeEqualTo(alarmNumbersDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(alarmNumbersDto.getStime());
        }
        if(!StringUtils.isEmpty(alarmNumbersDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(alarmNumbersDto.getEtime());
        }
        ca.andTpljLike("%png");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNumsByPage(example);
        PageInfo<AlarmNumbersDto> pageInfo = new PageInfo<>(lists);
        alarmNumbersDto.setTotal(pageInfo.getTotal());
        alarmNumbersDto.setList(lists);
        responseDto.setContent(alarmNumbersDto);
        return responseDto;
    }

    @PostMapping("/findSbbh")
    public ResponseDto findSbbh(@RequestBody EquipmentFileDto equipmentFileDto){
        ResponseDto responseDto = new ResponseDto();
        List<String> sbbhList = equipmentFileService.findSbbh(equipmentFileDto);
        responseDto.setContent(sbbhList);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody EquipmentFileDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        equipmentFileService.list(pageDto,list);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileDto equipmentFileDto) {
        // 保存校验
                ValidatorUtil.length(equipmentFileDto.getSbbh(), "设备唯一标识", 1, 50);
                ValidatorUtil.length(equipmentFileDto.getTplj(), "图片路径", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        equipmentFileService.save(equipmentFileDto);
        responseDto.setContent(equipmentFileDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        equipmentFileService.delete(id);
        return responseDto;
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1            被除数
     * @param v2            除数
     * @param scale         表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(int v1, int v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Integer.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
