package com.pd.wxbase.wx.controller.bmfw;

import com.pd.server.main.domain.WebchatVehicle;
import com.pd.server.main.dto.KvMapDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WebchatVehicleDto;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.service.WebchatVehicleService;
import com.pd.server.secondary.domain.Vehicle3;
import com.pd.server.secondary.service.Drivinglicense3Service;
import com.pd.server.secondary.service.Vehicle3Service;
import com.pd.wxbase.wx.controller.ssp.WebchatIllcodeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/webchatVehicle")
public class AppWebchatVehicleController {

    private static final Logger LOG = LoggerFactory.getLogger(WebchatIllcodeController.class);
    public static final String BUSINESS_NAME = "机动车基本信息";

    @Resource
    private WebchatVehicleService webchatVehicleService;

    @Resource
    private Vehicle3Service vehicle3Service;

    /**
     * 微信前端提交保存
     * @return
     */
    @PostMapping("/wxsave")
    public ResponseDto wxsave(WebchatVehicleDto vehicleDto) throws Exception {
        WebchatVehicle info = webchatVehicleService.findByHphm(vehicleDto.getHphm());
        if(null != info){
            throw new WxStrException("该车辆已被绑定，请勿重复绑定!");
        }
        /*Vehicle3 byHphm = vehicle3Service.findByHphm(vehicleDto.getHphm());
        if(null == byHphm){
            throw new WxStrException("未查询到该车辆的相关信息，无法进行绑定！");
        }
        if(!byHphm.getSfzmhm().equals(vehicleDto.getSfzmhm())){
            throw new WxStrException("只能备案登记在本人名下的车辆！");
        }*/
        ResponseDto responseDto = new ResponseDto();
        vehicleDto.setCjsj(new Date());
        webchatVehicleService.save(vehicleDto);
        responseDto.setContent(vehicleDto);
        return responseDto;
    }

    /**
     * 微信前端获取车辆信息
     * @return
     */
    @PostMapping("/findBySfzmhm")
    public ResponseDto findBySfzmhm(String sfzmhm) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        List<WebchatVehicle> list = webchatVehicleService.findBySfzmhm(sfzmhm);
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     * 微信前端获取车辆信息
     * @return
     */
    @PostMapping("/getVehile")
    public ResponseDto getVehile(String sfzmhm){
        ResponseDto responseDto = new ResponseDto();
        List<KvMapDto> list = new ArrayList<KvMapDto>();
        List<WebchatVehicle> listveh = webchatVehicleService.findBySfzmhm(sfzmhm);
        for (int i = 0; i < listveh.size(); i++) {
            KvMapDto dto = new KvMapDto();
            WebchatVehicle info = listveh.get(i);
            dto.setCode(info.getHpzl());
            dto.setText(info.getHphm());
            list.add(dto);
        }
        responseDto.setContent(list);
        return responseDto;
    }


    /**
     * 微信前端获取车辆信息
     * @return
     */
    @PostMapping("/delVehile")
    public ResponseDto delVehile(String hphm){
        ResponseDto responseDto = new ResponseDto();
        WebchatVehicle byHphm = webchatVehicleService.findByHphm(hphm);
        if(null == byHphm){
            throw new WxStrException("未查询绑定车辆信息，无法解绑！");
        }else{
            webchatVehicleService.delete(byHphm.getId());
        }
        return responseDto;
    }


}
