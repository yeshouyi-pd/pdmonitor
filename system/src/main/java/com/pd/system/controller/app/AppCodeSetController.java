package com.pd.system.controller.app;

import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.AppCodeSetDto;
import com.pd.server.main.dto.PageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AppCodeSetService;
import com.pd.server.util.ValidatorUtil;
import com.pd.system.controller.conf.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/admin/appCodeSet")
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
        try {
            appCodeSetService.list(pageDto);
            responseDto.setContent(pageDto);
        } catch (Exception e) {
            LOG.error("查询代码集列表失败", e);
            responseDto.setSuccess(false);
            responseDto.setMessage("查询失败");
        }
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AppCodeSetDto appCodeSetDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            // 保存校验
            ValidatorUtil.require(appCodeSetDto.getCodeName(), "代码名称");
            ValidatorUtil.require(appCodeSetDto.getCodeValue(), "代码值");
            ValidatorUtil.require(appCodeSetDto.getTypeValue(), "代码类型");
            ValidatorUtil.length(appCodeSetDto.getCodeName(), "代码名称", 1, 255);
            ValidatorUtil.length(appCodeSetDto.getCodeValue(), "代码值", 1, 100);
            ValidatorUtil.length(appCodeSetDto.getTypeValue(), "代码类型", 1, 100);

            appCodeSetService.save(appCodeSetDto);
            RedisConfig.init_app_code_set();
            responseDto.setContent(appCodeSetDto);
        } catch (Exception e) {
            LOG.error("保存代码集失败", e);
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
            appCodeSetService.delete(id);
            RedisConfig.init_app_code_set();
        } catch (Exception e) {
            LOG.error("删除代码集失败", e);
            responseDto.setSuccess(false);
            responseDto.setMessage("删除失败");
        }
        return responseDto;
    }

    /**
     * 获取所有代码类型
     */
    @GetMapping("/getAllType")
    public ResponseDto getAllType() {
        ResponseDto responseDto = new ResponseDto();
        try {
            Map<String, String> typeMap = (Map<String, String>) RedisConfig.redisTstaticemplate.opsForValue().get(RedisCode.APPCODETYPE);
            responseDto.setContent(typeMap);
        } catch (Exception e) {
            LOG.error("获取代码类型失败", e);
            responseDto.setSuccess(false);
            responseDto.setMessage("获取代码类型失败");
        }
        return responseDto;
    }

}
