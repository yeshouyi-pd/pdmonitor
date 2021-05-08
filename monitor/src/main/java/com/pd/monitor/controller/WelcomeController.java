package com.pd.monitor.controller;


import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.main.service.WaterQualityResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/welcome")
public class WelcomeController  extends BaseWxController{

    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);


    /**
     * 水值监测数据
     */
    @Resource
    private WaterQualityResultService waterQualityResultService;
    /**
     * 水质告警
     */
    @Resource
    private EquipmentFileService equipmentFileService;
    /**
     * 设备信息
     */
    @Resource
    private WaterEquipmentService waterEquipmentService;





    /**
     *  welcome 水质实时数据
     */
    @GetMapping("/getLatestDate")
    public ResponseDto getLatestDate() {
        ResponseDto responseDto = new ResponseDto();
        List<WaterQualityResultDto>  list  =  waterQualityResultService.getLatestDate();
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     *  welcome 实时越限警告
     */
    @GetMapping("/getWarningDate")
    public ResponseDto getWarningDate() {
        ResponseDto responseDto = new ResponseDto();
        List<WelcomeKvDto>  list  =  equipmentFileService.getWarningDate();
        responseDto.setContent(list);
        return responseDto;
    }


    /**
     *  welcome 在线率饼状图
     *  设备状态1正常2离线3设备故障
     */
    @GetMapping("/getPieChart")
    public ResponseDto getPieChart() {
        ResponseDto responseDto = new ResponseDto();
        List<PieChartDto>  list  =  waterEquipmentService.getPieChart();
        List<PieChartDto> newlit = new ArrayList<PieChartDto>();
        if(!CollectionUtils.isEmpty(list)){
            for(PieChartDto  vo :list){
                PieChartDto newvo = new PieChartDto();
                if("1".equals(vo.getLabel())){//成功
                    newvo.setData(vo.getData());
                    newvo.setLabel("正常");
                    newvo.setColor("#68BC31");
                }else if ("2".equals(vo.getLabel())){//离线
                    newvo.setData(vo.getData());
                    newvo.setLabel("离线");
                    newvo.setColor("#DA5430");
                }else if ("3".equals(vo.getLabel())){//故障
                    newvo.setData(vo.getData());
                    newvo.setLabel("故障");
                    newvo.setColor("#FEE074");
                }
                newlit.add(newvo);
            }
        }
        responseDto.setContent(newlit);
        return responseDto;
    }







}
