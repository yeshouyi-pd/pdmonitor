package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.StationsHeartbeat;
import com.pd.server.main.domain.StationsHeartbeatExample;
import com.pd.server.main.dto.StationsHeartbeatDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.StationsHeartbeatService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/stationsHeartbeat")
public class StationsHeartbeatController {

    private static final Logger LOG = LoggerFactory.getLogger(StationsHeartbeatController.class);
    public static final String BUSINESS_NAME = "基站心跳";

    @Resource
    private StationsHeartbeatService stationsHeartbeatService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        StationsHeartbeatExample stationsHeartbeatExample = new StationsHeartbeatExample();
        stationsHeartbeatExample.setOrderByClause(" create_time desc ");
        List<StationsHeartbeat> stationsHeartbeatList = stationsHeartbeatService.selectByExample(stationsHeartbeatExample);
        PageInfo<StationsHeartbeat> pageInfo = new PageInfo<>(stationsHeartbeatList);
        pageDto.setTotal(pageInfo.getTotal());
        List<StationsHeartbeatDto> stationsHeartbeatDtoList = CopyUtil.copyList(stationsHeartbeatList, StationsHeartbeatDto.class);
        pageDto.setList(stationsHeartbeatDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody StationsHeartbeatDto stationsHeartbeatDto) {
        // 保存校验
                ValidatorUtil.length(stationsHeartbeatDto.getNodeId(), "网关id即基站id", 1, 255);
                ValidatorUtil.length(stationsHeartbeatDto.getStatus(), "状态", 1, 255);
                ValidatorUtil.length(stationsHeartbeatDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(stationsHeartbeatDto.getSm(), "说明", 1, 255);
                ValidatorUtil.length(stationsHeartbeatDto.getRemark(), "标注", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        stationsHeartbeatService.save(stationsHeartbeatDto);
        responseDto.setContent(stationsHeartbeatDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        stationsHeartbeatService.delete(id);
        return responseDto;
    }

}
