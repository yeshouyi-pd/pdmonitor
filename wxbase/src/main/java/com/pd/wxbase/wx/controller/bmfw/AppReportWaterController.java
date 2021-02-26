package com.pd.wxbase.wx.controller.bmfw;

import com.alibaba.fastjson.JSONArray;
import com.pd.server.main.domain.ReportWater;
import com.pd.server.main.domain.WebchatFileinfo;
import com.pd.server.main.dto.ReportWaterDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.main.service.ReportWaterService;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.server.util.*;
import com.pd.wxbase.wx.conf.WxRedisConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/reportWater")
public class AppReportWaterController {

    private static final Logger LOG = LoggerFactory.getLogger(AppReportWaterController.class);
    public static final String BUSINESS_NAME = "渍水上报";

    @Resource
    private ReportWaterService reportWaterService;

    @Resource
    private WebchatFileinfoService webchatFileinfoService;

    /**
     * 微信前端保存
     * @param reportWaterDto
     * @return
     */
    @PostMapping("/wxsave")
    public ResponseDto wxsave(String imglist, ReportWaterDto reportWaterDto) throws Exception {
        // 保存校验
        ValidatorUtil.length(reportWaterDto.getSfzmhm(), "上报人身份证号码", 1, 50);
        ValidatorUtil.length(reportWaterDto.getUpdateBy(), "更新人", 1, 50);
        ValidatorUtil.length(reportWaterDto.getSysOrgCode(), "处理部门", 1, 64);
        ValidatorUtil.length(reportWaterDto.getZsdd(), "渍水地点", 1, 512);
        ValidatorUtil.length(reportWaterDto.getGps(), "渍水坐标", 1, 128);
        ValidatorUtil.length(reportWaterDto.getZsddxx(), "渍水详细地址", 1, 512);
        ValidatorUtil.length(reportWaterDto.getSbr(), "上报人", 1, 128);
        ValidatorUtil.length(reportWaterDto.getSbrsjh(), "上报人手机号", 1, 20);
        ValidatorUtil.length(reportWaterDto.getSbsm(), "上报说明", 1, 2000);
        ValidatorUtil.length(reportWaterDto.getShr(), "审核人", 1, 128);
        ValidatorUtil.length(reportWaterDto.getClyj(), "处理反馈", 1, 2000);
        ValidatorUtil.length(reportWaterDto.getZt(), "状态", 1, 2);
        ValidatorUtil.length(reportWaterDto.getBz(), "备注", 1, 4000);
        ValidatorUtil.length(reportWaterDto.getOpenid(), "微信用户openid", 1, 32);
        ValidatorUtil.length(reportWaterDto.getLsh(), "流水号", 1, 32);

        ResponseDto responseDto = new ResponseDto();
        JSONArray imgArray = JSONArray.parseArray(imglist);
        String lsh = OrderUtils.getOrderNo();
        if(null != imgArray && imgArray.size() > 0){
            for (int i = 0; i < imgArray.size(); i++) {
                String images = imgArray.getString(i);
                images = images.substring(images.lastIndexOf(",")+1);
                WebchatFileinfoDto file = new WebchatFileinfoDto();
                String fileDriectory = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
                String filename = lsh + "_" + new Date().getTime() + ".jpg";
                Base64Image.GenerateImage(ResizeImg.resizeImage(images), fileDriectory + filename);
                file.setLsh(lsh);
                file.setZplj(WxRedisConfig.fileStr + filename);
                file.setCjsj(new Date());
                webchatFileinfoService.save(file);
            }
        }
        reportWaterDto.setSbrsjh(DesEncrypt.DesJiaMi(reportWaterDto.getSbrsjh()));
        reportWaterDto.setSfzmhm(DesEncrypt.DesJiaMi(reportWaterDto.getSfzmhm()));
        reportWaterDto.setLsh(lsh);
        reportWaterDto.setZt("1");//1已上报2已处理3虚假上报
        reportWaterDto.setCreateTime(new Date());
        reportWaterService.save(reportWaterDto);
        responseDto.setContent(reportWaterDto);
        return responseDto;
    }

    /**
     * 微信端查询调用
     * @return
     */
    @PostMapping("/selectWater")
    public ResponseDto selectWater(String openid){
        ResponseDto responseDto = new ResponseDto();
        List<ReportWater> list = reportWaterService.findByOpenid(openid);
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     * @param id
     * @return
     */
    @PostMapping("/selectinfo")
    public ResponseDto selectinfo(String id){
        ResponseDto responseDto = new ResponseDto();
        ReportWater info = reportWaterService.findByID(id);
        List<WebchatFileinfo> piclist = new LinkedList<WebchatFileinfo>();
        if(!StringUtils.isEmpty(info.getLsh())){
            piclist = webchatFileinfoService.getFileinfoByLsh(info.getLsh());
        }
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("info",info);
        map.put("piclist", piclist);
        responseDto.setContent(map);
        return responseDto;
    }

}
