package com.pd.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.server.main.domain.AzimuthAngleRq;
import com.pd.server.main.domain.AzimuthAngleRqExample;
import com.pd.server.main.dto.AzimuthAngleRqDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AzimuthAngleRqService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/azimuthAngleRq")
public class AzimuthAngleRqController {

    private static final Logger LOG = LoggerFactory.getLogger(AzimuthAngleRqController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private AzimuthAngleRqService azimuthAngleRqService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        AzimuthAngleRqExample example = new AzimuthAngleRqExample();
        List<AzimuthAngleRq> azimuthAngleRqList = azimuthAngleRqService.list(example);
        PageInfo<AzimuthAngleRq> pageInfo = new PageInfo<>(azimuthAngleRqList);
        pageDto.setTotal(pageInfo.getTotal());
        List<AzimuthAngleRqDto> azimuthAngleRqDtoList = CopyUtil.copyList(azimuthAngleRqList, AzimuthAngleRqDto.class);
        pageDto.setList(azimuthAngleRqDtoList);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AzimuthAngleRqDto azimuthAngleRqDto) {
        // 保存校验
                ValidatorUtil.length(azimuthAngleRqDto.getSbbh(), "设备编号", 1, 50);
                ValidatorUtil.length(azimuthAngleRqDto.getRq(), "日期", 1, 10);

        ResponseDto responseDto = new ResponseDto();
        azimuthAngleRqService.save(azimuthAngleRqDto);
        responseDto.setContent(azimuthAngleRqDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        azimuthAngleRqService.delete(id);
        return responseDto;
    }

}
