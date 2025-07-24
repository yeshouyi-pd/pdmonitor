package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.AngleFile;
import com.pd.server.main.domain.AngleFileExample;
import com.pd.server.main.dto.AngleFileDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AngleFileService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/angleFile")
public class AngleFileController {

    private static final Logger LOG = LoggerFactory.getLogger(AngleFileController.class);
    public static final String BUSINESS_NAME = "仪器姿态报警管理";

    @Resource
    private AngleFileService angleFileService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody AngleFileDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AngleFileExample angleFileExample = new AngleFileExample();
        AngleFileExample.Criteria ca = angleFileExample.createCriteria();
        if(!StringUtils.isEmpty(pageDto.getSbbh())){
            ca.andSbbhEqualTo(pageDto.getSbbh());
        }
        if(!StringUtils.isEmpty(pageDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(pageDto.getStime());
        }
        if(!StringUtils.isEmpty(pageDto.getEtime())){
            ca.andRqLessThanOrEqualTo(pageDto.getEtime());
        }
        angleFileExample.setOrderByClause(" cjsj desc ");
        List<AngleFile> angleFileList = angleFileService.selectByExample(angleFileExample);
        PageInfo<AngleFile> pageInfo = new PageInfo<>(angleFileList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AngleFileDto> angleFileDtoList = CopyUtil.copyList(angleFileList, AngleFileDto.class);
        pageDto.setList(angleFileDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AngleFileDto angleFileDto) {
        // 保存校验
                ValidatorUtil.length(angleFileDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(angleFileDto.getWjlj(), "文件路径", 1, 255);
                ValidatorUtil.length(angleFileDto.getDeptcode(), "部门编号", 1, 50);
                ValidatorUtil.length(angleFileDto.getRq(), "日期", 1, 10);
                ValidatorUtil.length(angleFileDto.getBz(), "备注", 1, 255);
                ValidatorUtil.length(angleFileDto.getSm(), "说明", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        angleFileService.save(angleFileDto);
        responseDto.setContent(angleFileDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        angleFileService.delete(id);
        return responseDto;
    }

}
