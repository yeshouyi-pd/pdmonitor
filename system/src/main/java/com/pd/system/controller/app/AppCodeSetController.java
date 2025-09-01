package com.pd.system.controller.app;

import com.pd.server.main.dto.AppCodeSetDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AppCodeSetService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/appCodeSet")
public class AppCodeSetController {

    private static final Logger LOG = LoggerFactory.getLogger(AppCodeSetController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private AppCodeSetService appCodeSetService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        appCodeSetService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AppCodeSetDto appCodeSetDto) {
        // 保存校验
                ValidatorUtil.length(appCodeSetDto.getCodeName(), "code 名", 1, 255);
                ValidatorUtil.length(appCodeSetDto.getCodeValue(), "code 值", 1, 100);
                ValidatorUtil.length(appCodeSetDto.getTypeValue(), "type 的值", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        appCodeSetService.save(appCodeSetDto);
        responseDto.setContent(appCodeSetDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        appCodeSetService.delete(id);
        return responseDto;
    }

}
