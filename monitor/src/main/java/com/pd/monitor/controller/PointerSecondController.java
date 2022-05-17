package com.pd.monitor.controller;

import com.pd.server.main.dto.PointerSecondDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.PointerSecondService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/pointerSecond")
public class PointerSecondController {

    private static final Logger LOG = LoggerFactory.getLogger(PointerSecondController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private PointerSecondService pointerSecondService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        pointerSecondService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody PointerSecondDto pointerSecondDto) {
        // 保存校验
                ValidatorUtil.length(pointerSecondDto.getDecibelValue(), "分贝值", 1, 255);
                ValidatorUtil.length(pointerSecondDto.getSm(), "说明", 1, 500);
                ValidatorUtil.length(pointerSecondDto.getBz(), "备注", 1, 500);
                ValidatorUtil.length(pointerSecondDto.getBz1(), "备注字段", 1, 500);
                ValidatorUtil.length(pointerSecondDto.getBz2(), "备注字段", 1, 500);
                ValidatorUtil.length(pointerSecondDto.getBz3(), "备注字段", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        pointerSecondService.save(pointerSecondDto);
        responseDto.setContent(pointerSecondDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        pointerSecondService.delete(id);
        return responseDto;
    }

}
