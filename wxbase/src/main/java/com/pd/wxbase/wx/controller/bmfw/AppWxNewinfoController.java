package com.pd.wxbase.wx.controller.bmfw;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.WebchatNewinfo;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WebchatNewinfoDto;
import com.pd.server.main.service.WebchatNewinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/wxNewinfo")
public class AppWxNewinfoController {

    private static final Logger LOG = LoggerFactory.getLogger(AppWxNewinfoController.class);
    public static final String BUSINESS_NAME = "信息发布";

    @Resource
    private WebchatNewinfoService wxNewinfoService;

    /**
     * 微信列表查询
     */
    @PostMapping("/wxlist")
    public ResponseDto list(@RequestBody String data) {
        ResponseDto responseDto = new ResponseDto();
        JSONObject jsonObject = JSONObject.parseObject(data);
        String xxlb = jsonObject.getString("xxlb");
        List<WebchatNewinfo> wxlist = wxNewinfoService.wxlist(xxlb);
        responseDto.setContent(wxlist);
        return responseDto;
    }

    /**
     * 根据id查询
     */
    @PostMapping("/findByID")
    public ResponseDto findByID(@RequestBody String data) {
        ResponseDto responseDto = new ResponseDto();
        JSONObject jsonObject = JSONObject.parseObject(data);
        String id = jsonObject.getString("id");
        WebchatNewinfoDto wxNewinfoDto = wxNewinfoService.findByID(id);
        responseDto.setContent(wxNewinfoDto);
        return responseDto;
    }

}
