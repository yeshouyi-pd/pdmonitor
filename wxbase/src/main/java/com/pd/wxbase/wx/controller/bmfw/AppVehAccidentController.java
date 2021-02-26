package com.pd.wxbase.wx.controller.bmfw;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.pd.server.config.CodeType;
import com.pd.server.main.domain.VehAccident;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VehAccidentDto;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.exception.WxStrException;
import com.pd.server.main.service.VehAccidentService;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.server.util.*;
import com.pd.wxbase.wx.conf.BaseWxController;
import com.pd.wxbase.wx.conf.WxRedisConfig;
import com.pd.wxbase.wx.wxutlis.utils.AccountsServlet;
import com.pd.wxbase.wx.wxutlis.utils.QrCodeCreateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

@RestController
@RequestMapping("/vehAccident")
public class AppVehAccidentController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(AppVehAccidentController.class);
    public static final String BUSINESS_NAME = "快撤易赔";

    @Resource
    private VehAccidentService vehAccidentService;

    @Resource
    private WebchatFileinfoService webchatFileinfoService;

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/wxsave")
    public ResponseDto wxsave(String dsrxm1, String dsrsjhm1, String dsrbxgs1, String sgdd, String openid) {
        ResponseDto responseDto = new ResponseDto();
        VehAccidentDto vehAccidentDto = new VehAccidentDto();
        vehAccidentDto.setDsrxm1(dsrxm1);
        vehAccidentDto.setCreateBy(vehAccidentDto.getDsrxm1());
        vehAccidentDto.setCreateTime(new Date());
        vehAccidentDto.setDsrsjhm1(dsrsjhm1);
        vehAccidentDto.setDsrbxgs1(dsrbxgs1);
        vehAccidentDto.setSgdd(sgdd);
        vehAccidentDto.setOpenid(openid);
        vehAccidentDto.setLsh(OrderUtils.getOrderNo());//流水号
        vehAccidentDto.setSgbh(UuidUtil.getShortUuid() + vehAccidentDto.getLsh());//事故编号
        vehAccidentDto.setSgfssj(DateUtil.getFormatDate(vehAccidentDto.getCreateTime(), DateUtil.yyyy_MM_dd_HH_mm_ss));
        //vehAccidentDto.setZt("0");
        vehAccidentService.save(vehAccidentDto);
        responseDto.setContent(vehAccidentDto);
        return responseDto;
    }

    @PostMapping("/wxsaveAccident")
    public ResponseDto wxsaveAccident(@RequestBody VehAccidentDto vehAccidentDto){
        ResponseDto responseDto = new ResponseDto();
        if(StringUtils.isBlank(vehAccidentDto.getSgbh())){
            throw new WxStrException("请按照流程进行操作");
        }
        VehAccident info = vehAccidentService.findBySgbh(vehAccidentDto.getSgbh());
        CopyUtil.copyPropertiesIgnoreNull(info, vehAccidentDto);
        vehAccidentService.save(vehAccidentDto);
        responseDto.setContent(vehAccidentDto);
        return responseDto;
    }

    /**
     * 根据事故编号查询
     * @param sgbh
     * @return
     */
    @GetMapping("/selectAccident")
    public ResponseDto selectAccident(String sgbh){
        ResponseDto responseDto = new ResponseDto();
        if(StringUtils.isBlank(sgbh)){
            throw new WxStrException("请按照流程进行操作");
        }
        VehAccident info = vehAccidentService.findBySgbh(sgbh);
        responseDto.setContent(info);
        return responseDto;
    }

    /**
     * 根据事故编号查询
     * @param openid
     * @return
     */
    @PostMapping("/selectAccidentOpenid")
    public ResponseDto selectAccidentOpenid(String openid){
        ResponseDto responseDto = new ResponseDto();
        if(StringUtils.isBlank(openid)){
            throw new WxStrException("请按照流程进行操作");
        }
        List<VehAccident> list = vehAccidentService.findByOpenid(openid);
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     * 一键报案
     * @param vehAccidentDto
     * @return
     */
    @PostMapping("/updateAccident")
    public ResponseDto updateAccident(@RequestBody VehAccidentDto vehAccidentDto){
        ResponseDto responseDto = new ResponseDto();
        if(StringUtils.isBlank(vehAccidentDto.getSgbh())){
            throw new WxStrException("请按照流程进行操作");
        }
        VehAccident info = vehAccidentService.findBySgbh(vehAccidentDto.getSgbh());
        if(null == info){
            throw new WxStrException("未查询到相关信息，无法一键报案！");
        }
        if("2".equals(info.getZt())){
            throw new WxStrException("已经报案成功，无需重复操作！");
        }
        info.setZt("2");
        vehAccidentService.update(info);
        responseDto.setContent(info);
        return responseDto;
    }

    /**
     * 放弃一键报案
     * @param vehAccidentDto
     * @return
     */
    @PostMapping("/delAccident")
    public ResponseDto delAccident(@RequestBody VehAccidentDto vehAccidentDto){
        ResponseDto responseDto = new ResponseDto();
        if(StringUtils.isBlank(vehAccidentDto.getSgbh())){
            throw new WxStrException("请按照流程进行操作");
        }
        VehAccident info = vehAccidentService.findBySgbh(vehAccidentDto.getSgbh());
        if(null == info){
            throw new WxStrException("已经放弃报案，无需重复提交！");
        }
        vehAccidentService.delete(info.getId());
        return responseDto;
    }

    /**
     * 二维码
     */
    @GetMapping("/ewm")
    public void ewm(String sgbh, HttpServletResponse response){
        //二维码的图片格式
        String format = "gif";
        Hashtable hints = new Hashtable();
        ErrorCorrectionLevel level = ErrorCorrectionLevel.H;
        //内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, "gbk");
        hints.put(EncodeHintType.ERROR_CORRECTION, level); //容错率
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = null;
        try {
            String url = AccountsServlet.wxqdurl + "/sgjdcxinfo?sgbh="+sgbh;
            bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 250, 250, hints);
            //bitMatrix = deleteWhite(bitMatrix);//删除白边
        }catch (WriterException e) {
            LOG.error("打印参数获取异常", e);
        }
        //生成二维码
        try {
            QrCodeCreateUtil.writeToStream(bitMatrix, format, response.getOutputStream());
        } catch (IOException e) {
            LOG.error("二维码打印打印异常", e);
        }
    }

    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }

}
