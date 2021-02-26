package com.pd.wxbase.wx.controller.bmfw;

import com.alibaba.fastjson.JSONArray;
import com.pd.server.main.domain.ReportFacilityfail;
import com.pd.server.main.domain.WebchatFileinfo;
import com.pd.server.main.domain.WebchatUsers;
import com.pd.server.main.dto.ReportFacilityfailDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.main.service.ReportFacilityfailService;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.server.main.service.WebchatUsersService;
import com.pd.server.util.Base64Image;
import com.pd.server.util.OrderUtils;
import com.pd.server.util.ResizeImg;
import com.pd.wxbase.wx.conf.WxRedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/reportFacilityfail")
public class AppReportFacilityfailController {

    private static final Logger LOG = LoggerFactory.getLogger(AppReportFacilityfailController.class);
    public static final String BUSINESS_NAME = "交安故障上报";

    @Resource
    private ReportFacilityfailService reportFacilityfailService;

    @Resource
    private WebchatFileinfoService webchatFileinfoService;

    @Resource
    private WebchatUsersService webchatUsersService;

    /**
     * 微信端提交保存
     * @param imglist
     * @param info
     * @return
     * @throws IOException
     */
    @PostMapping("/wxsave")
    public ResponseDto wxsave(String imglist, ReportFacilityfailDto info) throws IOException {
        JSONArray imgArray = JSONArray.parseArray(imglist);
        String lsh = OrderUtils.getOrderNo();
        if(null != imgArray && imgArray.size() > 0){
            for (int i = 0; i < imgArray.size(); i++) {
                String images = imgArray.getString(i);
                images = images.substring(images.lastIndexOf(",")+1);
                String fileDriectory = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
                String filename = lsh + "_" + new Date().getTime() + ".jpg";
                Base64Image.GenerateImage(ResizeImg.resizeImage(images), fileDriectory + filename);
                WebchatFileinfoDto fileinfo = new WebchatFileinfoDto();
                fileinfo.setLsh(lsh);
                fileinfo.setZplj(WxRedisConfig.fileStr + filename);
                fileinfo.setCjsj(new Date());
                webchatFileinfoService.save(fileinfo);
            }
        }
        info.setLsh(lsh);
        WebchatUsers wxuser = webchatUsersService.findByOpenid(info.getOpenid());
        if(null != wxuser){
            info.setSbr(wxuser.getZjmc());
            info.setSfzmhm(wxuser.getZjhm());
            info.setCreateBy(wxuser.getZjmc());
        }
        info.setZt("1");//1已上报2已处理3虚假上报
        info.setCreateTime(new Date());
        reportFacilityfailService.save(info);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(info);
        return responseDto;
    }

    /**
     * 微信端查询调用
     * @return
     */
    @PostMapping("/selectfail")
    public ResponseDto selectfail(String openid){
        ResponseDto responseDto = new ResponseDto();
        List<ReportFacilityfail> list = reportFacilityfailService.findByOpenid(openid);
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
        ReportFacilityfail info = reportFacilityfailService.findByID(id);
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
