package com.pd.wxbase.wx.controller.bmfw;

import com.pd.server.main.dto.ResponseDto;
import com.pd.wxbase.wx.wxutlis.utils.PayToolUtil;
import com.pd.wxbase.wx.wxutlis.utils.SendPostUtil;
import com.pd.wxbase.wx.wxutlis.utils.WXPayUtil;
import com.pd.wxbase.wx.wxutlis.utils.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@RestController
@RequestMapping("/pay")
public class PayController {

    private static final Logger LOG = LoggerFactory.getLogger(PayController.class);

    @PostMapping("/redenvelopes")
    public ResponseDto Redenvelopes() throws Exception {
        ResponseDto responseDto = new ResponseDto();
        String key = "";//商户支付密钥Key
        String nonce_str = String.valueOf(PayToolUtil.buildRandom(10));
        String mch_billno = PayToolUtil.getCurrTime() + PayToolUtil.buildRandom(10);//商户订单号
        String mch_id = "";//商户号
        String wxappid = "";//公众账号appid
        String send_name = "";//商户名称
        String re_openid = "";//用户openid
        String total_amount = "10";//付款金额，单位分
        String total_num = "1";//红包发放总人数
        String wishing = "恭喜发财";
        String client_ip = getIp();//Ip地址
        String act_name = "奖励红包";
        String remark = "备注";//备注
        SortedMap<Object, Object> params = new TreeMap<Object,Object>();
        params.put("nonce_str",nonce_str);
        params.put("mch_billno",mch_billno);
        params.put("mch_id",mch_id);
        params.put("wxappid",wxappid);
        params.put("send_name",send_name);
        params.put("re_openid",re_openid);
        params.put("total_amount",total_amount);
        params.put("total_num",total_num);
        params.put("wishing",wishing);
        params.put("client_ip",client_ip);
        params.put("act_name",act_name);
        params.put("remark",remark);
        String sign = PayToolUtil.createSign(WeixinUtil.DEFAULT_ENCODING, params, key);
        params.put("sign",sign);
        //将请求参数转换为xml格式的string
        String requestXML = PayToolUtil.getRequestXml(params);
        String resXml = SendPostUtil.postData(WeixinUtil.SENDREDPACK_URL,requestXML);
        LOG.info("==============微信发红包返回xml:"+resXml);
        //将微信返回的xml解析为map格式
        Map map = WXPayUtil.doXMLParse(resXml);
        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            String resultCode = (String) map.get("result_code");
            if("SUCCESS".equals(resultCode)){
                LOG.info("==============微信发红包SUCCESS");
            }else if("FAIL".equals(resultCode)){
                String errCode = (String) map.get("err_code");
                String errCodeDes = (String) map.get("err_code_des");
                LOG.info("==============微信发红包FAIL:代码" + errCode + ",内容" + errCodeDes);
            }
        }else if("FAIL".equals(returnCode)){
            String returnMsg = (String) map.get("return_msg");
            LOG.info("==============微信发红包请求FAIL,原因:" + returnMsg);
        }
        return responseDto;
    }

    public static String getIp() {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            return ia.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "500";
        }
    }

    public static void main(String[] args) {
        System.out.println(getIp());
    }

}
