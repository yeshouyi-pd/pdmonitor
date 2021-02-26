package com.pd.system.controller.admin;

import com.pd.server.main.dto.AttrDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AttrService;
import com.pd.server.util.ValidatorUtil;
import com.pd.system.controller.conf.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/attr")
public class AttrController {

    private static final Logger LOG = LoggerFactory.getLogger(AttrController.class);
    public static final String BUSINESS_NAME = "属性设置";

    @Resource
    private AttrService attrService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody AttrDto attrDto) {
        ResponseDto responseDto = new ResponseDto();
        attrService.list(attrDto);
        responseDto.setContent(attrDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody AttrDto attrDto) {
       // 保存校验
        ValidatorUtil.length(attrDto.getAttrcode(), "属性代码", 1, 100);
        ValidatorUtil.length(attrDto.getAttrkey(), "属性值", 1, 1000);
        ValidatorUtil.length(attrDto.getContent(), "说明", 1, 256);

        ResponseDto responseDto = new ResponseDto();
        attrService.save(attrDto);
        RedisConfig.init_attr();//刷新attr缓存
        responseDto.setContent(attrDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        attrService.delete(id);
        RedisConfig.init_attr();//刷新attr缓存
        return responseDto;
    }
}
