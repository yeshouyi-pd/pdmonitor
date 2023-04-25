package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.StationsInfo;
import com.pd.server.main.domain.StationsInfoExample;
import com.pd.server.main.dto.StationsInfoDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.StationsInfoService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/stationsInfo")
public class StationsInfoController extends PageInfo{

    private static final Logger LOG = LoggerFactory.getLogger(StationsInfoController.class);
    public static final String BUSINESS_NAME = "基站基本信息";

    @Resource
    private StationsInfoService stationsInfoService;

    @GetMapping("/listAll")
    public ResponseDto listAll(){
        ResponseDto responseDto = new ResponseDto();
        StationsInfoExample stationsInfoExample = new StationsInfoExample();
        stationsInfoExample.setOrderByClause(" xh desc ");
        List<StationsInfo> stationsInfoList = stationsInfoService.selectByExample(stationsInfoExample);
        responseDto.setContent(stationsInfoList);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody StationsInfoDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        StationsInfoExample stationsInfoExample = new StationsInfoExample();
        StationsInfoExample.Criteria ca = stationsInfoExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getId())){
            ca.andIdLike("%"+pageDto.getId()+"%");
        }
        if(!StringUtils.isEmpty(pageDto.getNodeName())){
            ca.andIdLike("%"+pageDto.getNodeName()+"%");
        }
        if(!StringUtils.isEmpty(pageDto.getNodeNum())){
            ca.andIdLike("%"+pageDto.getNodeNum()+"%");
        }
        stationsInfoExample.setOrderByClause(" xh desc ");
        List<StationsInfo> stationsInfoList = stationsInfoService.selectByExample(stationsInfoExample);
        PageInfo<StationsInfo> pageInfo = new PageInfo<>(stationsInfoList);
        pageDto.setTotal(pageInfo.getTotal());
        List<StationsInfoDto> stationsInfoDtoList = CopyUtil.copyList(stationsInfoList, StationsInfoDto.class);
        pageDto.setList(stationsInfoDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody StationsInfoDto stationsInfoDto) {
        // 保存校验
        ValidatorUtil.length(stationsInfoDto.getNodeName(), "基站名称", 1, 255);
        ValidatorUtil.length(stationsInfoDto.getNodeNum(), "基站编号", 1, 255);
        ValidatorUtil.length(stationsInfoDto.getLoraId(), "信标编号", 1, 255);
        ValidatorUtil.length(stationsInfoDto.getXh(), "序号", 1, 500);
        ValidatorUtil.length(stationsInfoDto.getGps(), "坐标点", 1, 500);
        ValidatorUtil.length(stationsInfoDto.getBdGps(), "百度坐标点", 1, 500);
        ValidatorUtil.length(stationsInfoDto.getDh(), "电话", 1, 500);
        ValidatorUtil.length(stationsInfoDto.getDz(), "地址", 1, 500);
        ValidatorUtil.length(stationsInfoDto.getDeptCode(), "部门编号", 1, 500);
        ValidatorUtil.length(stationsInfoDto.getBz(), "备注", 1, 255);
        ValidatorUtil.length(stationsInfoDto.getSm(), "说明", 1, 255);
        ValidatorUtil.length(stationsInfoDto.getRemark(), "标注", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        stationsInfoService.save(stationsInfoDto);
        responseDto.setContent(stationsInfoDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        stationsInfoService.delete(id);
        return responseDto;
    }

}
