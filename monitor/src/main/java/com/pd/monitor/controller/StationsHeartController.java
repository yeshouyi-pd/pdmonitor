package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.StationsHeart;
import com.pd.server.main.domain.StationsHeartExample;
import com.pd.server.main.dto.StationsHeartDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.StationsHeartService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/stationsHeart")
public class StationsHeartController {

    private static final Logger LOG = LoggerFactory.getLogger(StationsHeartController.class);
    public static final String BUSINESS_NAME = "基站数据";

    @Resource
    private StationsHeartService stationsHeartService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        StationsHeartExample stationsHeartExample = new StationsHeartExample();
        stationsHeartExample.setOrderByClause(" create_time desc ");
        List<StationsHeart> stationsHeartList = stationsHeartService.selectByExample(stationsHeartExample);
        PageInfo<StationsHeart> pageInfo = new PageInfo<>(stationsHeartList);
        pageDto.setTotal(pageInfo.getTotal());
        List<StationsHeartDto> stationsHeartDtoList = CopyUtil.copyList(stationsHeartList, StationsHeartDto.class);
        pageDto.setList(stationsHeartDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody StationsHeartDto stationsHeartDto) {
        // 保存校验
                ValidatorUtil.length(stationsHeartDto.getNodeId(), "基站id", 1, 100);
                ValidatorUtil.length(stationsHeartDto.getNodeName(), "基站名称", 1, 255);
                ValidatorUtil.length(stationsHeartDto.getNodeNum(), "基站编号", 1, 255);
                ValidatorUtil.length(stationsHeartDto.getLoraId(), "信标编号", 1, 255);
                ValidatorUtil.length(stationsHeartDto.getData(), "", 1, 500);
                ValidatorUtil.length(stationsHeartDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(stationsHeartDto.getSm(), "说明", 1, 255);
                ValidatorUtil.length(stationsHeartDto.getRemark(), "标注", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        stationsHeartService.save(stationsHeartDto);
        responseDto.setContent(stationsHeartDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        stationsHeartService.delete(id);
        return responseDto;
    }

}
