package com.pd.monitor.controller;

import com.pd.server.main.dto.VideoEventDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.VideoEventService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/videoEvent")
public class VideoEventController {

    private static final Logger LOG = LoggerFactory.getLogger(VideoEventController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private VideoEventService videoEventService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        videoEventService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody VideoEventDto videoEventDto) {
        // 保存校验
                ValidatorUtil.length(videoEventDto.getSbbh(), "设备编号", 1, 15);
                ValidatorUtil.length(videoEventDto.getKssj(), "开始时间", 1, 25);
                ValidatorUtil.length(videoEventDto.getJssj(), "结束时间", 1, 25);
                ValidatorUtil.length(videoEventDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(videoEventDto.getWjlj(), "文件路径", 1, 255);
                ValidatorUtil.length(videoEventDto.getBz(), "备注", 1, 25);
                ValidatorUtil.length(videoEventDto.getSm(), "说明", 1, 25);

        ResponseDto responseDto = new ResponseDto();
        videoEventService.save(videoEventDto);
        responseDto.setContent(videoEventDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        videoEventService.delete(id);
        return responseDto;
    }

}
