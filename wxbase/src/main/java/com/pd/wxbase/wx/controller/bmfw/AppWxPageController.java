package com.pd.wxbase.wx.controller.bmfw;

import com.pd.server.main.domain.WxPage;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WxPageDto;
import com.pd.server.main.service.WxPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/wx/wxPage")
public class AppWxPageController {
    private static final Logger LOG = LoggerFactory.getLogger(AppWxPageController.class);
    public static final String BUSINESS_NAME = "微信端页面";

    @Resource
    private WxPageService wxPageService;

    @PostMapping("/findAllByParam")
    public ResponseDto findAllByParam(@RequestBody WxPageDto wxPageDto){
        ResponseDto responseDto = new ResponseDto();
        System.out.println(wxPageDto.toString());
        List<WxPage> wxPageList = wxPageService.findAllByParam(wxPageDto);
        responseDto.setContent(wxPageList);
        return responseDto;
    }
}
