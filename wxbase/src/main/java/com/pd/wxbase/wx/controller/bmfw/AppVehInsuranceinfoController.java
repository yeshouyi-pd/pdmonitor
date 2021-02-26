package com.pd.wxbase.wx.controller.bmfw;

import com.pd.server.exception.WxException;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VehInsuranceinfoDto;
import com.pd.server.main.service.VehInsuranceinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/vehInsuranceinfo")
public class AppVehInsuranceinfoController {

    private static final Logger LOG = LoggerFactory.getLogger(AppVehInsuranceinfoController.class);
    public static final String BUSINESS_NAME = "电子保单";

    @Resource
    private VehInsuranceinfoService vehInsuranceinfoService;

    /**
     * 根据号牌号码查询车辆信息
     */
    @PostMapping("/findByHphm")
    public ResponseDto findByHphm(String hphm) {
        ResponseDto responseDto = new ResponseDto();
        VehInsuranceinfoDto info = vehInsuranceinfoService.findByHphm(hphm);
        if(null == info){
            throw new WxStrException("未查询到该车的相关电子保单信息");
        }
        responseDto.setContent(info);
        return responseDto;
    }

}
