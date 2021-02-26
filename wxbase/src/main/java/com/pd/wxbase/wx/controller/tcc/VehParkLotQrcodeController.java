package com.pd.wxbase.wx.controller.tcc;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.WriterException;
import com.pd.wxbase.wx.conf.BaseWxController;
import com.pd.wxbase.wx.wxutlis.utils.QRCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/qrcode/vehParkLot")
public class VehParkLotQrcodeController  extends BaseWxController {
    private static final Logger LOG = LoggerFactory.getLogger(VehParkLotQrcodeController.class);
    public static final String BUSINESS_NAME = "违法车场二维码";

    @PostMapping(value="/getQRImage")
    public ResponseEntity<byte[]> getQRImage(@RequestBody Map<String, Object> map) throws UnsupportedEncodingException {

        //二维码内的信息
        String info = new String(JSONObject.toJSONString(map).getBytes(StandardCharsets.UTF_8), "ISO-8859-1");
        System.out.println("接受参数info："+info);
        byte[] qrcode = null;
        try {
            qrcode = QRCodeGenerator.getQRCodeImage(info, 360, 360);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }

        // Set headers
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]> (qrcode, headers, HttpStatus.CREATED);
    }
}
