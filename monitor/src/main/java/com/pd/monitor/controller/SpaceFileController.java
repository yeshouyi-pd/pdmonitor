package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.EquipmentFileEvent;
import com.pd.server.main.domain.SpaceFile;
import com.pd.server.main.domain.SpaceFileExample;
import com.pd.server.main.dto.SpaceFileDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SpaceFileService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/spaceFile")
public class SpaceFileController {

    private static final Logger LOG = LoggerFactory.getLogger(SpaceFileController.class);
    public static final String BUSINESS_NAME = "驱离文件管理";

    @Resource
    private SpaceFileService spaceFileService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody SpaceFileDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SpaceFileExample spaceFileExample = new SpaceFileExample();
        SpaceFileExample.Criteria ca = spaceFileExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        spaceFileExample.setOrderByClause(" cjsj desc ");
        List<SpaceFile> spaceFileList = new ArrayList<>();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            spaceFileList = spaceFileService.selectByExampleSpecial(pageDto);
        }else{
            spaceFileList = spaceFileService.selectByExample(spaceFileExample);
        }
        PageInfo<SpaceFile> pageInfo = new PageInfo<>(spaceFileList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SpaceFileDto> spaceFileDtoList = CopyUtil.copyList(spaceFileList, SpaceFileDto.class);
        pageDto.setList(spaceFileDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SpaceFileDto spaceFileDto) {
        // 保存校验
                ValidatorUtil.length(spaceFileDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(spaceFileDto.getWjlj(), "文件路径", 1, 255);
                ValidatorUtil.length(spaceFileDto.getYjz(), "预警值", 1, 50);
                ValidatorUtil.length(spaceFileDto.getDeptcode(), "部门编号", 1, 50);
                ValidatorUtil.length(spaceFileDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(spaceFileDto.getBz(), "备注", 1, 100);
                ValidatorUtil.length(spaceFileDto.getSm(), "说明", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        spaceFileService.save(spaceFileDto);
        responseDto.setContent(spaceFileDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        spaceFileService.delete(id);
        return responseDto;
    }

}
