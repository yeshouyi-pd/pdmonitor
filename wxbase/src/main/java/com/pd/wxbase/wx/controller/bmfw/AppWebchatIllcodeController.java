package com.pd.wxbase.wx.controller.bmfw;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WebchatIllcodeDto;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.service.WebchatIllcodeService;
import com.pd.wxbase.wx.controller.ssp.WebchatIllcodeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/webchatIllcode")
public class AppWebchatIllcodeController {

    private static final Logger LOG = LoggerFactory.getLogger(WebchatIllcodeController.class);
    public static final String BUSINESS_NAME = "违法代码";

    @Resource
    private WebchatIllcodeService webchatIllcodeService;

    /**
     * 违法代码查询
     * @param data
     * @return
     */
    @PostMapping("/selectwfdm")
    public ResponseDto selectwfdm(@RequestBody String data) {
        ResponseDto responseDto = new ResponseDto();
        JSONObject jsonObject = JSONObject.parseObject(data);
        String wfdm = jsonObject.getString("wfdm");
        String lb = jsonObject.getString("lb");
        WebchatIllcodeDto dto = webchatIllcodeService.findByWfdmandLb(wfdm, lb);
        if(null == dto){
            throw new WxStrException("未查询到违法代码的信息");
        }
        responseDto.setContent(dto);
        return responseDto;
    }

}
