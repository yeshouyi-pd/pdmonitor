package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.dto.EquipmentFileDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/equipmentFile")
public class EquipmentFileController {

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

    /**
     * 统计报警次数
     * @param alarmNumbersDto
     * @return
     */
    @PostMapping("/statisticsAlarmNums")
    public ResponseDto statisticsAlarmNums(@RequestBody AlarmNumbersDto alarmNumbersDto){
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(alarmNumbersDto.getPage(), alarmNumbersDto.getSize());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
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
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNums(example);
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
        equipmentFileService.list(pageDto);
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

}
