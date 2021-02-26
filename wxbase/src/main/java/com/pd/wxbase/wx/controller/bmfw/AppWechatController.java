package com.pd.wxbase.wx.controller.bmfw;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.WebchatUsers;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.WebchatUsersService;
import com.pd.wxbase.wx.conf.BaseWxController;
import com.pd.wxbase.wx.wxutlis.utils.AccountsServlet;
import com.pd.wxbase.wx.wxutlis.utils.Sign;
import com.pd.wxbase.wx.wxutlis.utils.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
public class AppWechatController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(AppWechatController.class);
    public static final String BUSINESS_NAME = "获取openid";

    @Resource
    public WebchatUsersService wxUsersService;

    /**
     * 获取OPENID
     */
    @RequestMapping("/getOpenid")
    public ResponseDto getOpenid(@RequestBody String data) throws IOException {
        ResponseDto responseDto = new ResponseDto();
        JSONObject jsonObject = JSONObject.parseObject(data);
        String code = jsonObject.getString("code");
        String state = jsonObject.getString("state");
        String openid_url = WeixinUtil.OPENID_URL.replace("APPID", AccountsServlet.appid).replace("SECRET", AccountsServlet.appsecret).replace("CODE", code);
        JSONObject jsonObj = WeixinUtil.doPostStr(openid_url, "");
        String openid = jsonObj.getString("openid");
        String accessToken = jsonObj.getString("access_token");
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("openid", openid);
        map.put("accessToken", accessToken);
        String userinfo_url = WeixinUtil.USER_INFO.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
        JSONObject jsonUser = WeixinUtil.doPostStr(userinfo_url, "");
        LOG.info("头像返回："+jsonUser.toJSONString());
        String nickname = jsonUser.getString("nickname");
        String headimgurl = jsonUser.getString("headimgurl");
        map.put("headimgurl", headimgurl);
        LOG.info("昵称："+nickname);
        LOG.info("头像图片："+headimgurl);
        responseDto.setContent(map);
        return responseDto;
    }

    /**
     * 获取微信用户信息
     * @param data
     * @return
     */
    @RequestMapping("/getWxUser")
    public ResponseDto getWxUser(@RequestBody String data) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        JSONObject jsonObject = JSONObject.parseObject(data);
        String openid = jsonObject.getString("openid");
        WebchatUsers user = wxUsersService.findByOpenid(openid);
        responseDto.setContent(user);
        return responseDto;
    }

    /**
     * 微信页面公共参数
     * @return
     */
    @RequestMapping("/getWxParams")
    public ResponseDto getWxParams(String url){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String, String>();
        Map<String, String> mapp =  Sign.sign(AccountsServlet.jspTicet, url);
        map.put("timestamp", mapp.get("timestamp"));
        map.put("nonceStr", mapp.get("nonceStr"));
        map.put("signature", mapp.get("signature"));
        map.put("appId", AccountsServlet.appid);
        //map.put("redirect_uri", DataInitConfig.sys_attr_map.get("redirect_uri"));
        LOG.info(JSONObject.toJSONString(map));
        responseDto.setContent(map);
        return responseDto;
    }


}
