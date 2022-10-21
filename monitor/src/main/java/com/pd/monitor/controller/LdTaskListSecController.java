package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.service.AttrService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.secondary.domain.LdTaskListSec;
import com.pd.server.secondary.domain.LdTaskListSecExample;
import com.pd.server.secondary.dto.LdTaskListSecDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.secondary.service.LdTaskListSecService;
import com.pd.server.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/ldTaskListSec")
public class LdTaskListSecController {

    private static final Logger LOG = LoggerFactory.getLogger(LdTaskListSecController.class);
    public static final String BUSINESS_NAME = "下发指令";

    @Resource
    private LdTaskListSecService ldTaskListSecService;
    @Resource
    private AttrService attrService;
    @Resource
    private WaterEquipmentService waterEquipmentService;

    @PostMapping("/startAll")
    public ResponseDto startAll(){
        ResponseDto responseDto = new ResponseDto();
        WaterEquipmentExample example = new WaterEquipmentExample();
        example.createCriteria().andSbcjIsNotNull().andSbcjNotEqualTo("");
        List<WaterEquipment> list = waterEquipmentService.list(example);
        for(WaterEquipment entity : list){
            LdTaskListSecDto dto1 = new LdTaskListSecDto();
            dto1.setIccid(entity.getSbcj());
            dto1.setTask("cmd:202");
            dto1.setFsdate(new Date());
            ldTaskListSecService.save(dto1);
        }
        return responseDto;
    }

    @PostMapping("/closedAll")
    public ResponseDto closedAll(){
        ResponseDto responseDto = new ResponseDto();
        WaterEquipmentExample example = new WaterEquipmentExample();
        example.createCriteria().andSbcjIsNotNull().andSbcjNotEqualTo("");
        List<WaterEquipment> list = waterEquipmentService.list(example);
        for(WaterEquipment entity : list){
            LdTaskListSecDto dto1 = new LdTaskListSecDto();
            dto1.setIccid(entity.getSbcj());
            dto1.setTask("cmd:203");
            dto1.setFsdate(new Date());
            ldTaskListSecService.save(dto1);
        }
        return responseDto;
    }

    @PostMapping("/startEquip/{sbcj}")
    public ResponseDto startEquip(@PathVariable String sbcj){
        ResponseDto responseDto = new ResponseDto();
        LdTaskListSecDto dto1 = new LdTaskListSecDto();
        dto1.setIccid(sbcj);
        dto1.setTask("cmd:202");
        dto1.setFsdate(new Date());
        ldTaskListSecService.save(dto1);
        return responseDto;
    }

    @PostMapping("/closedEquip/{sbcj}")
    public ResponseDto closedEquip(@PathVariable String sbcj){
        ResponseDto responseDto = new ResponseDto();
        LdTaskListSecDto dto1 = new LdTaskListSecDto();
        dto1.setIccid(sbcj);
        dto1.setTask("cmd:203");
        dto1.setFsdate(new Date());
        ldTaskListSecService.save(dto1);
        return responseDto;
    }

    @PostMapping("/restart/{sbcj}")
    public ResponseDto restart(@PathVariable String sbcj){
        ResponseDto responseDto = new ResponseDto();
        String restartinterval = attrService.findByAttrKey("restartinterval");
        try {
            LdTaskListSecDto dto = new LdTaskListSecDto();
            dto.setIccid(sbcj);
            dto.setTask("cmd:203");
            dto.setFsdate(new Date());
            ldTaskListSecService.save(dto);
            Thread.sleep(Long.parseLong(restartinterval));
            LdTaskListSecDto dto1 = new LdTaskListSecDto();
            dto1.setIccid(sbcj);
            dto1.setTask("cmd:202");
            dto1.setFsdate(new Date());
            ldTaskListSecService.save(dto1);
            return responseDto;
        }catch (Exception e){
            responseDto.setSuccess(false);
            return responseDto;
        }
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody LdTaskListSecDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        LdTaskListSecExample ldTaskListSecExample = new LdTaskListSecExample();
        LdTaskListSecExample.Criteria ca = ldTaskListSecExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getState())){
            ca.andStateEqualTo(pageDto.getState());
        }
        if(!StringUtils.isEmpty(pageDto.getIccid())){
            ca.andIccidEqualTo(pageDto.getIccid());
        }
        ldTaskListSecExample.setOrderByClause(" fsdate desc ");
        List<LdTaskListSec> ldTaskListSecList = ldTaskListSecService.list(ldTaskListSecExample);
        PageInfo<LdTaskListSec> pageInfo = new PageInfo<>(ldTaskListSecList);
        pageDto.setTotal(pageInfo.getTotal());
        List<LdTaskListSecDto> ldTaskListSecDtoList = CopyUtil.copyList(ldTaskListSecList, LdTaskListSecDto.class);
        pageDto.setList(ldTaskListSecDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody LdTaskListSecDto ldTaskListSecDto) {
        ResponseDto responseDto = new ResponseDto();
        ldTaskListSecDto.setFsdate(new Date());
        ldTaskListSecService.save(ldTaskListSecDto);
        responseDto.setContent(ldTaskListSecDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Integer id) {
        ResponseDto responseDto = new ResponseDto();
        ldTaskListSecService.delete(id);
        return responseDto;
    }

}
