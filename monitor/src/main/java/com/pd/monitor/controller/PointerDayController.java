package com.pd.monitor.controller;

import com.pd.server.main.dto.PointerDayDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.PointerDayService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/pointerDay")
public class PointerDayController {

    private static final Logger LOG = LoggerFactory.getLogger(PointerDayController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private PointerDayService pointerDayService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        pointerDayService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody PointerDayDto pointerDayDto) {
        // 保存校验
                ValidatorUtil.length(pointerDayDto.getDecibelValue(), "分贝值", 1, 255);
                ValidatorUtil.length(pointerDayDto.getSm(), "说明", 1, 500);
                ValidatorUtil.length(pointerDayDto.getBz(), "备注", 1, 500);
                ValidatorUtil.length(pointerDayDto.getBz1(), "备注字段", 1, 500);
                ValidatorUtil.length(pointerDayDto.getBz2(), "备注字段", 1, 500);
                ValidatorUtil.length(pointerDayDto.getBz3(), "备注字段", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        pointerDayService.save(pointerDayDto);
        responseDto.setContent(pointerDayDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        pointerDayService.delete(id);
        return responseDto;
    }

}
