package com.pd.environment.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.TurbidityDto;
import com.pd.server.main.service.TurbidityService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.DateUtil;
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
@RequestMapping("/turbidi")
public class TurbidiHttpController {

    private static final Logger LOG = LoggerFactory.getLogger(TurbidiHttpController.class);
    public static final String BUSINESS_NAME = "接收第三方浊度仪数据";

    @Resource
    private TurbidityService turbidityService;
    @Resource
    private WaterEquipmentService waterEquipmentService;

    /**
     * 保存数据
     */
    @PostMapping("/saveData")
    public ResponseDto saveData(@RequestBody JSONObject jsonObject) {
        ResponseDto responseDto = new ResponseDto();
        try {
            if(StringUtils.isEmpty(jsonObject.get("depth")) || StringUtils.isEmpty(jsonObject.get("temperature"))
                    || StringUtils.isEmpty(jsonObject.get("salinity")) || StringUtils.isEmpty(jsonObject.get("cjsj"))
                    || StringUtils.isEmpty(jsonObject.get("sbbh")) ){
                responseDto.setSuccess(false);
                responseDto.setMessage("参数错误");
                return responseDto;
            }
            String cjsj = jsonObject.getString("cjsj");
            String isFlag = "^(\\d{4})-(\\d{2})-(\\d{2})\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";//判断时间
            if(!cjsj.matches(isFlag)){
                responseDto.setSuccess(false);
                responseDto.setMessage("时间格式错误");
                return responseDto;
            }
            WaterEquipmentExample example = new WaterEquipmentExample();
            List<String> sbbhs= waterEquipmentService.findSbbh(example);
            if(!sbbhs.contains(jsonObject.getString("sbbh"))){
                responseDto.setSuccess(false);
                responseDto.setMessage("设备编号错误");
                return responseDto;
            }
            TurbidityDto dto = new TurbidityDto();
            dto.setDateTime(jsonObject.getDate("cjsj"));
            dto.setBz(jsonObject.getString("sbbh"));
            dto.setDepth(jsonObject.getString("depth"));
            dto.setTemperature(jsonObject.getString("temperature"));
            dto.setSalinity(jsonObject.getString("salinity"));
            if(!StringUtils.isEmpty(jsonObject.get("turbidityH"))){
                dto.setTurbidityH(jsonObject.getString("turbidityH"));
            }
            if(!StringUtils.isEmpty(jsonObject.get("turibidityL"))){
                dto.setTuribidityL(jsonObject.getString("turibidityL"));
            }
            if(!StringUtils.isEmpty(jsonObject.get("conductivity"))){
                dto.setConductivity(jsonObject.getString("conductivity"));
            }
            if(!StringUtils.isEmpty(jsonObject.get("batVolt"))){
                dto.setBatVolt(jsonObject.getString("batVolt"));
            }
            turbidityService.save(dto);
            responseDto.setSuccess(true);
            responseDto.setMessage("保存成功");
        }catch (Exception e){
            LOG.error("接收到的数据："+ JSONObject.toJSONString(jsonObject)+"====错误原因："+e.getMessage());
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }

    /**
     * 保存数据
     */
    @PostMapping("/sendData21001")
    public ResponseDto sendData21001(@RequestBody String str) {
        ResponseDto responseDto = new ResponseDto();
        try {
            TurbidityDto turbidityDto = new TurbidityDto();
            String[] arr = str.trim().split(",");
            if(arr.length==9&&"$OBS".equals(arr[0])){
                turbidityDto.setDateTime(DateUtil.toDate(arr[1].substring(0,arr[1].lastIndexOf(".")),"yyyy-MM-dd HH:mm:ss"));
                turbidityDto.setTurbidityH(arr[2]);
                turbidityDto.setTuribidityL(arr[3]);
                turbidityDto.setDepth(arr[4]);
                turbidityDto.setTemperature(arr[5]);
                turbidityDto.setConductivity(arr[6]);
                turbidityDto.setSalinity(arr[7]);
                turbidityDto.setBatVolt(arr[8]);
                turbidityDto.setBz("ZDY21001");
                turbidityService.save(turbidityDto);
            }else {
                LOG.error("接收到的错误数据："+str);
            }
        }catch (Exception e){
            LOG.error("接收到的数据："+str+"====错误原因："+e.getMessage());
        }
        responseDto.setCode("0000");
        responseDto.setSuccess(true);
        responseDto.setMessage("保存成功");
        return responseDto;
    }

    /**
     * 保存数据
     */
    @PostMapping("/sendData21002")
    public ResponseDto sendData21002(@RequestBody String str) {
        ResponseDto responseDto = new ResponseDto();
        try {
            TurbidityDto turbidityDto = new TurbidityDto();
            String[] arr = str.trim().split(",");
            if(arr.length==9&&"$OBS".equals(arr[0])){
                turbidityDto.setDateTime(DateUtil.toDate(arr[1].substring(0,arr[1].lastIndexOf(".")),"yyyy-MM-dd HH:mm:ss"));
                turbidityDto.setTurbidityH(arr[2]);
                turbidityDto.setTuribidityL(arr[3]);
                turbidityDto.setDepth(arr[4]);
                turbidityDto.setTemperature(arr[5]);
                turbidityDto.setConductivity(arr[6]);
                turbidityDto.setSalinity(arr[7]);
                turbidityDto.setBatVolt(arr[8]);
                turbidityDto.setBz("ZDY21002");
                turbidityService.save(turbidityDto);
            }else {
                LOG.error("接收到的错误数据："+str);
            }
        }catch (Exception e){
            LOG.error("接收到的数据："+str+"====错误原因："+e.getMessage());
        }
        responseDto.setCode("0000");
        responseDto.setSuccess(true);
        responseDto.setMessage("保存成功");
        return responseDto;
    }
}
