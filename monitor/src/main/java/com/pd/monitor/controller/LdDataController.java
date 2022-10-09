package com.pd.monitor.controller;

import com.pd.server.main.dto.LdDataDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.LdDataService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/ldData")
public class LdDataController {

    private static final Logger LOG = LoggerFactory.getLogger(LdDataController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private LdDataService ldDataService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        ldDataService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody LdDataDto ldDataDto) {
        ResponseDto responseDto = new ResponseDto();
        ldDataService.save(ldDataDto);
        responseDto.setContent(ldDataDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Integer id) {
        ResponseDto responseDto = new ResponseDto();
        ldDataService.delete(id);
        return responseDto;
    }

}
