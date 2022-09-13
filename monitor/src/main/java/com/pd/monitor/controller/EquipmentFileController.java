package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(entityDto.getSbbh())){
            ca.andSbbhEqualTo(entityDto.getSbbh());
        }
        if(!StringUtils.isEmpty(entityDto.getDeptcode())){
            ca.andDeptcodeEqualTo(entityDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(entityDto.getStime())){
            ca.andFzGreaterThanOrEqualTo(entityDto.getStime());
        }
        if(!StringUtils.isEmpty(entityDto.getEtime())){
            ca.andFzLessThanOrEqualTo(entityDto.getEtime());
        }
        ca.andTxtlxEqualTo("1");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNums(example);
        List<String> xAixsData = lists.stream().filter(Objects::nonNull).map(u->u.getFz()).collect(Collectors.toList());
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
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
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
        ca.andTxtlxEqualTo("1");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNumsByHour(example);
        Optional<Integer> op = lists.stream().filter(Objects::nonNull).map(AlarmNumbersDto::getAlarmNum).reduce(Integer::sum);
        List<String> xAixsData = lists.stream().filter(Objects::nonNull).map(u->u.getXs()).collect(Collectors.toList());
        List<String> yAixsData = new ArrayList<>();
        if(op.isPresent()){
            yAixsData = lists.stream().filter(Objects::nonNull).map(u-> calculateResultOfPercent(u.getAlarmNum(),op.get())).collect(Collectors.toList());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xAixsData",xAixsData);
        map.put("yAixsData",yAixsData);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 统计出现次数
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
        if(!StringUtils.isEmpty(alarmNumbersDto.getXmbh())){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(alarmNumbersDto.getXmbh()))){
                ca.andSbbhIn(user.getXmbhsbsns().get(alarmNumbersDto.getXmbh()));
            }
        }
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
        LoginUserDto user = getRequestHeader();
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        ca.andSblbEqualTo("0001");
        if(!StringUtils.isEmpty(equipmentFileDto.getXmbh())){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(equipmentFileDto.getXmbh()))){
                ca.andSbsnIn(user.getXmbhsbsns().get(equipmentFileDto.getXmbh()));
            }
        }
        ca.andDqzlIn(Arrays.asList("A1","A4"));
        List<String> sbbhList = waterEquipmentService.findSbbh(example);
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
        equipmentFileService.list(pageDto,list,user);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/detail/{tpmc}")
    public ResponseDto detail(@PathVariable String tpmc){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileExample example = new EquipmentFileExample();
        example.createCriteria().andTpljLike("%"+tpmc+"%");
        List<EquipmentFile> lists = equipmentFileService.listAll(example);
        responseDto.setContent(lists);
        return responseDto;
    }

    @PostMapping("/lists")
    public ResponseDto lists(@RequestBody EquipmentFileDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = equipmentFileExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());//
        }
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbbhIn(user.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        ca.andTxtlxEqualTo("1");
        equipmentFileExample.setOrderByClause(" cjsj desc ");
        List<EquipmentFile> lists = equipmentFileService.lists(equipmentFileExample);
        PageInfo<EquipmentFile> pageInfo = new PageInfo<>(lists);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(lists);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/listsbbh")
    public ResponseDto listsbbh(@RequestBody EquipmentFileDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria criteria = example.createCriteria();
        criteria.andSbbhEqualTo(pageDto.getSbbh());
        criteria.andCjsjGreaterThan(DateTools.toDate(DateTools.getFormatDate(new Date(),DateTools.yyyy_MM_dd),DateTools.yyyy_MM_dd));
        example.setOrderByClause(" cjsj desc ");
        List<EquipmentFile> equipmentFiles = equipmentFileService.listAll(example);
        responseDto.setContent(equipmentFiles);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody EquipmentFileDto equipmentFileDto) {
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
     * 计算结果百分比，保留1位小数
     *
     * @param v1 除数
     * @param v2 被除数
     * @return
     */
    private static String calculateResultOfPercent(double v1, double v2) {
        if (v2 == 0) {
            return "0%";
        }

        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(v1 / v2);
    }

    public Boolean isOverThreeMinute(String curDateStr, String nextDateStr){
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        if(minute<=2){
            return true;
        }
        return false;
    }

}
