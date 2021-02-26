package com.pd.wxbase.wx.controller;

import com.pd.server.main.domain.WxPage;
import com.pd.server.main.dto.WxPageDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WxPageService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/wxPage")
public class WxPageController {

    private static final Logger LOG = LoggerFactory.getLogger(WxPageController.class);
    public static final String BUSINESS_NAME = "微信页面控制";

    @Resource
    private WxPageService wxPageService;

    /**
    * 列表查询
    */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody WxPageDto wxPageDto) {
        ResponseDto responseDto = new ResponseDto();
        wxPageService.list(wxPageDto);
        responseDto.setContent(wxPageDto);
        return responseDto;
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody WxPageDto wxPageDto) {
        // 保存校验
            ValidatorUtil.length(wxPageDto.getName(), "页面名称", 1, 20);
            ValidatorUtil.length(wxPageDto.getUrl(), "页面路径", 1, 100);
            ValidatorUtil.length(wxPageDto.getPic(), "页面图片", 1, 100);
            ValidatorUtil.length(wxPageDto.getSx(), "页面顺序", 1, 2);
            ValidatorUtil.length(wxPageDto.getLevel(), "页面级别", 1, 2);
            ValidatorUtil.length(wxPageDto.getLinkId(), "页面关联id", 1, 50);
            ValidatorUtil.length(wxPageDto.getIsEnable(), "是否启用", 1, 1);
            ValidatorUtil.length(wxPageDto.getParentName(), "父页面名称", 1, 20);

        ResponseDto responseDto = new ResponseDto();
        wxPageService.save(wxPageDto);
        responseDto.setContent(wxPageDto);
        return responseDto;
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        wxPageService.delete(id);
        return responseDto;
    }

}
