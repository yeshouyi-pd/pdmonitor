package com.pd.wxbase.wx.controller.bmfw;

import com.pd.server.main.domain.WebchatFileinfo;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.wxbase.wx.controller.WebchatFileinfoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/webchatFileinfo")
public class AppWebchatFileinfoController {

    private static final Logger LOG = LoggerFactory.getLogger(WebchatFileinfoController.class);
    public static final String BUSINESS_NAME = "微信照片文件存储表";

    @Resource
    private WebchatFileinfoService webchatFileinfoService;

    /**
     * 列表查询
     */
    @PostMapping("/getFileinfoByLsh")
    public ResponseDto getFileinfoByLsh(String lsh) {
        ResponseDto responseDto = new ResponseDto();
        List<WebchatFileinfo> webchatFileinfoList = webchatFileinfoService.getFileinfoByLsh(lsh);
        responseDto.setContent(webchatFileinfoList);
        return responseDto;
    }


}
