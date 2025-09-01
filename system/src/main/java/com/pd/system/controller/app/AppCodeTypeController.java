package com.pd.system.controller.app;

import com.pd.server.main.dto.AppCodeTypeDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AppCodeTypeService;
import com.pd.server.util.ValidatorUtil;
import com.pd.system.controller.conf.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/appCodeType")
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
        try {
            appCodeTypeService.list(pageDto);
            responseDto.setContent(pageDto);
        } catch (Exception e) {
            LOG.error("查询业务类型列表失败", e);
            responseDto.setSuccess(false);
            responseDto.setMessage("查询失败");
        }
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AppCodeTypeDto appCodeTypeDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            // 保存校验
            ValidatorUtil.require(appCodeTypeDto.getTypeName(), "业务类型名称");
            ValidatorUtil.length(appCodeTypeDto.getTypeName(), "业务类型名称", 1, 255);
            ValidatorUtil.length(appCodeTypeDto.getTypeValue(), "业务类型代码", 1, 20);

            appCodeTypeService.save(appCodeTypeDto);
            RedisConfig.init_app_code_type();
            responseDto.setContent(appCodeTypeDto);
        } catch (Exception e) {
            LOG.error("保存业务类型失败", e);
            responseDto.setSuccess(false);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            appCodeTypeService.delete(id);
            RedisConfig.init_app_code_type();
        } catch (Exception e) {
            LOG.error("删除业务类型失败", e);
            responseDto.setSuccess(false);
            responseDto.setMessage("删除失败");
        }
        return responseDto;
    }

}
