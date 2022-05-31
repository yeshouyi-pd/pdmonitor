package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.GroupEvent;
import com.pd.server.main.domain.GroupEventExample;
import com.pd.server.main.dto.GroupEventDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.GroupEventService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/groupEvent")
public class GroupEventController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(GroupEventController.class);
    public static final String BUSINESS_NAME = "群次管理";

    @Resource
    private GroupEventService groupEventService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody GroupEventDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        GroupEventExample groupEventExample = new GroupEventExample();
        GroupEventExample.Criteria ca = groupEventExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(pageDto.getXmbh()))){
                ca.andSbbhIn(user.getXmbhsbsns().get(pageDto.getXmbh()));
            }
        }
        List<GroupEvent> groupEventList = groupEventService.list(groupEventExample);
        PageInfo<GroupEvent> pageInfo = new PageInfo<>(groupEventList);
        pageDto.setTotal(pageInfo.getTotal());
        List<GroupEventDto> groupEventDtoList = CopyUtil.copyList(groupEventList, GroupEventDto.class);
        for(GroupEventDto entityDto : groupEventDtoList){
            if(!StringUtils.isEmpty(entityDto.getZcfwj())){
                entityDto.setZcts(entityDto.getZcfwj().split(",").length+"");
                entityDto.setZcList(Arrays.asList(entityDto.getZcfwj().split(",")));
            }
        }
        pageDto.setList(groupEventDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody GroupEventDto groupEventDto) {
        // 保存校验
                ValidatorUtil.length(groupEventDto.getSbbh(), "设备编号", 1, 255);
                ValidatorUtil.length(groupEventDto.getSm(), "说明", 1, 500);
                ValidatorUtil.length(groupEventDto.getBz(), "备注", 1, 500);
                ValidatorUtil.length(groupEventDto.getBz1(), "备注字段", 1, 500);
                ValidatorUtil.length(groupEventDto.getBz2(), "备注字段", 1, 500);
                ValidatorUtil.length(groupEventDto.getBz3(), "备注字段", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        groupEventService.save(groupEventDto);
        responseDto.setContent(groupEventDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        groupEventService.delete(id);
        return responseDto;
    }

}
