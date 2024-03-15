package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.VideoEvent;
import com.pd.server.main.domain.VideoEventExample;
import com.pd.server.main.dto.VideoEventDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.VideoEventService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/videoEvent")
public class VideoEventController {

    private static final Logger LOG = LoggerFactory.getLogger(VideoEventController.class);
    public static final String BUSINESS_NAME = "视频分析管理";

    @Resource
    private VideoEventService videoEventService;

    @PostMapping("/checkSave")
    public ResponseDto checkSave(@RequestBody VideoEventDto videoEventDto){
        ResponseDto responseDto = new ResponseDto();
        if(!StringUtils.isEmpty(videoEventDto.getSm())&&"1".equals(videoEventDto.getSm())){
            //核查通过
            VideoEvent videoEvent = videoEventService.selectById(videoEventDto.getId());
            videoEvent.setSm("1");
            videoEventService.updateItem(videoEvent);
        }else if(!StringUtils.isEmpty(videoEventDto.getSm())&&"2".equals(videoEventDto.getSm())){
            //核查不通过
            videoEventService.delete(videoEventDto.getId());
        }
        return responseDto;
    }

    @PostMapping("/videoList")
    public ResponseDto videoList(@RequestBody VideoEventDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        VideoEventExample example = new VideoEventExample();
        VideoEventExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getWjmc())){
            ca.andWjmcEqualTo(pageDto.getWjmc());
        }
        if(!StringUtils.isEmpty(pageDto.getSfysp())){
            ca.andSfyspEqualTo(pageDto.getSfysp());
        }
        if(!StringUtils.isEmpty(pageDto.getSxtip())){
            ca.andSxtipEqualTo(pageDto.getSxtip());
        }
        List<VideoEvent> lists = videoEventService.selectByExample(example);
        responseDto.setContent(lists);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody VideoEventDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        List<VideoEvent> videoEventList = videoEventService.selectByPage(pageDto);
        PageInfo<VideoEvent> pageInfo = new PageInfo<>(videoEventList);
        pageDto.setTotal(pageInfo.getTotal());
        List<VideoEventDto> videoEventDtoList = CopyUtil.copyList(videoEventList, VideoEventDto.class);
        pageDto.setList(videoEventDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/listSs")
    public ResponseDto listSs(@RequestBody VideoEventDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        VideoEventExample example = new VideoEventExample();
        VideoEventExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getSfysp())){
            ca.andSfyspEqualTo(pageDto.getSfysp());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqLessThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        example.setOrderByClause(" kssj desc ");
        List<VideoEvent> videoEventList = videoEventService.selectByExample(example);
        PageInfo<VideoEvent> pageInfo = new PageInfo<>(videoEventList);
        pageDto.setTotal(pageInfo.getTotal());
        List<VideoEventDto> videoEventDtoList = CopyUtil.copyList(videoEventList, VideoEventDto.class);
        pageDto.setList(videoEventDtoList);
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
                ValidatorUtil.length(videoEventDto.getWjmc(), "文件名称", 1, 50);
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
