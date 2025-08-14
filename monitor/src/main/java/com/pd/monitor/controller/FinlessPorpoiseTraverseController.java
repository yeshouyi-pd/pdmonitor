package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.FinlessPorpoiseTraverse;
import com.pd.server.main.domain.FinlessPorpoiseTraverseExample;
import com.pd.server.main.dto.FinlessPorpoiseTraverseDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.FinlessPorpoiseTraverseService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/finlessPorpoiseTraverse")
public class FinlessPorpoiseTraverseController {

    private static final Logger LOG = LoggerFactory.getLogger(FinlessPorpoiseTraverseController.class);
    public static final String BUSINESS_NAME = "江豚穿越行为";

    @Resource
    private FinlessPorpoiseTraverseService finlessPorpoiseTraverseService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody FinlessPorpoiseTraverseDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        FinlessPorpoiseTraverseExample finlessPorpoiseTraverseExample = new FinlessPorpoiseTraverseExample();
        FinlessPorpoiseTraverseExample.Criteria ca = finlessPorpoiseTraverseExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getXmbh())){
            ca.andXmbhEqualTo(pageDto.getXmbh());
        }
        if(!StringUtils.isEmpty(pageDto.getXmbhmc())){
            ca.andXmbhmcLike("%"+pageDto.getXmbhmc()+"%");
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        finlessPorpoiseTraverseExample.setOrderByClause(" cjsj desc ");
        List<FinlessPorpoiseTraverse> finlessPorpoiseTraverseList = finlessPorpoiseTraverseService.selectByExample(finlessPorpoiseTraverseExample);
        PageInfo<FinlessPorpoiseTraverse> pageInfo = new PageInfo<>(finlessPorpoiseTraverseList);
        pageDto.setTotal(pageInfo.getTotal());
        List<FinlessPorpoiseTraverseDto> finlessPorpoiseTraverseDtoList = CopyUtil.copyList(finlessPorpoiseTraverseList, FinlessPorpoiseTraverseDto.class);
        pageDto.setList(finlessPorpoiseTraverseDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody FinlessPorpoiseTraverseDto finlessPorpoiseTraverseDto) {
        // 保存校验
                ValidatorUtil.length(finlessPorpoiseTraverseDto.getXmbh(), "项目编号", 1, 20);
                ValidatorUtil.length(finlessPorpoiseTraverseDto.getXmbhmc(), "项目编号名称", 1, 20);
                ValidatorUtil.length(finlessPorpoiseTraverseDto.getCjsj(), "创建时间", 1, 20);
                ValidatorUtil.length(finlessPorpoiseTraverseDto.getTplj(), "图片路径", 1, 255);
                ValidatorUtil.length(finlessPorpoiseTraverseDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(finlessPorpoiseTraverseDto.getBz(), "备注", 1, 50);
                ValidatorUtil.length(finlessPorpoiseTraverseDto.getSm(), "说明", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        finlessPorpoiseTraverseService.save(finlessPorpoiseTraverseDto);
        responseDto.setContent(finlessPorpoiseTraverseDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        finlessPorpoiseTraverseService.delete(id);
        return responseDto;
    }

}
