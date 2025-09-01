package com.pd.system.controller.app;

import com.pd.server.main.dto.AppCodeTypeDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AppCodeTypeService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/appCodeType")
public class AppCodeTypeController {

    private static final Logger LOG = LoggerFactory.getLogger(AppCodeTypeController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private AppCodeTypeService appCodeTypeService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        appCodeTypeService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AppCodeTypeDto appCodeTypeDto) {
        // 保存校验
                ValidatorUtil.require(appCodeTypeDto.getTypeName(), "业务类型名称");
                ValidatorUtil.length(appCodeTypeDto.getTypeName(), "业务类型名称", 1, 255);
                ValidatorUtil.length(appCodeTypeDto.getTypeValue(), "业务类型代码", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        appCodeTypeService.save(appCodeTypeDto);
        responseDto.setContent(appCodeTypeDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        appCodeTypeService.delete(id);
        return responseDto;
    }

}
