package com.pd.wxbase.wx.controller.bmfw;

import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VehLicelostDto;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.service.VehLicelostService;
import com.pd.wxbase.wx.controller.VehLicelostController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/vehLicelost")
public class AppVehLicelostController {

    private static final Logger LOG = LoggerFactory.getLogger(VehLicelostController.class);
    public static final String BUSINESS_NAME = "车牌遗失查询";

    @Resource
    private VehLicelostService vehLicelostService;

    /**
     * 微信前端查询
     * @param vehLicelostDto
     * @return
     */
    @PostMapping("/selectLicelost")
    public ResponseDto selectLicelost(@RequestBody VehLicelostDto vehLicelostDto){
        VehLicelostDto info = vehLicelostService.findByHpzlAndHphm(vehLicelostDto.getHpzl(), vehLicelostDto.getHphm());
        if(null == info){
            throw new WxStrException("未查询到该车牌的相关信息");
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(info);
        return responseDto;
    }

}
