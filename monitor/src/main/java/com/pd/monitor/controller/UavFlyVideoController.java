package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.UavFlyVideo;
import com.pd.server.main.domain.UavFlyVideoExample;
import com.pd.server.main.dto.UavFlyVideoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.UavFlyVideoService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/uavFlyVideo")
public class UavFlyVideoController {

    private static final Logger LOG = LoggerFactory.getLogger(UavFlyVideoController.class);
    public static final String BUSINESS_NAME = "无人机视频";

    @Resource
    private UavFlyVideoService uavFlyVideoService;

    @PostMapping("/update")
    public ResponseDto update(@RequestBody UavFlyVideoDto uavFlyVideoDto){
        ResponseDto responseDto = new ResponseDto();
        uavFlyVideoService.save(uavFlyVideoDto);
        return responseDto;
    }

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody UavFlyVideoDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        UavFlyVideoExample uavFlyVideoExample = new UavFlyVideoExample();
        UavFlyVideoExample.Criteria ca = uavFlyVideoExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andCjsjGreaterThanOrEqualTo(pageDto.getStime()+" 00:00:00");
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andCjsjLessThanOrEqualTo(pageDto.getEtime()+" 23:59:59");
        }
        uavFlyVideoExample.setOrderByClause(" cjsj desc ");
        List<UavFlyVideo> uavFlyVideoList = uavFlyVideoService.selectByExample(uavFlyVideoExample);
        PageInfo<UavFlyVideo> pageInfo = new PageInfo<>(uavFlyVideoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<UavFlyVideoDto> uavFlyVideoDtoList = CopyUtil.copyList(uavFlyVideoList, UavFlyVideoDto.class);
        pageDto.setList(uavFlyVideoDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UavFlyVideoDto uavFlyVideoDto) {
        // 保存校验
                ValidatorUtil.length(uavFlyVideoDto.getSbbh(), "设备编号(无法获取)", 1, 15);
                ValidatorUtil.length(uavFlyVideoDto.getVideoUrl(), "视频路径", 1, 255);
                ValidatorUtil.length(uavFlyVideoDto.getDeptcode(), "部门编号(无法获取)", 1, 100);
                ValidatorUtil.length(uavFlyVideoDto.getCjsj(), "采集时间", 1, 30);
                ValidatorUtil.length(uavFlyVideoDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(uavFlyVideoDto.getSm(), "说明", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        uavFlyVideoService.save(uavFlyVideoDto);
        responseDto.setContent(uavFlyVideoDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        uavFlyVideoService.delete(id);
        return responseDto;
    }

}
