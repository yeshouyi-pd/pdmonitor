package com.pd.wxbase.wx.controller.bmfw;

import com.pd.server.exception.WxStrException;
import com.pd.server.main.domain.VehParkLot;
import com.pd.server.main.domain.VehParkLotExample;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VehParkLotDto;
import com.pd.server.main.service.VehParkLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/vehParkLot")
public class AppVehParkLotController {

    private static final Logger LOG = LoggerFactory.getLogger(AppVehParkLotController.class);
    public static final String BUSINESS_NAME = "拖车查询";

    @Resource
    private VehParkLotService vehParkLotService;

    /**
     * 微信前端查询
     * @param vehParkLotDto
     * @return
     */
    @PostMapping("/selectParklot")
    public ResponseDto selectParklot(@RequestBody VehParkLotDto vehParkLotDto){
        VehParkLotExample vehParkLotExample = new VehParkLotExample();
        VehParkLotExample.Criteria ca = vehParkLotExample.createCriteria();
        if(!StringUtils.isEmpty(vehParkLotDto.getCllb())){
            ca.andCllbEqualTo(vehParkLotDto.getCllb());
        }
        if(!StringUtils.isEmpty(vehParkLotDto.getHphm())){
            ca.andHphmEqualTo(vehParkLotDto.getHphm());
        }
        if(!StringUtils.isEmpty(vehParkLotDto.getClsbdh())){
            ca.andClsbdhEqualTo(vehParkLotDto.getClsbdh());
        }
        List<VehParkLot> vehParkLotList = vehParkLotService.list(vehParkLotExample);
        if(StringUtils.isEmpty(vehParkLotList)||vehParkLotList.size()<=0){
            throw new WxStrException("未查询到该车牌的相关信息");
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(vehParkLotList.get(0));
        return responseDto;
    }

}
