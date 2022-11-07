package com.pd.monitor.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * 获取萤石云信息
 */
public class Ys7Utils {

    private static final Logger LOG = LoggerFactory.getLogger(Ys7Utils.class);

    public static final String YS_GETTOKEN = "https://open.ys7.com/api/lapp/token/get?appKey=APPKEY&appSecret=APPSECRET";

    public static String YS_TOKEN = "";

    public static JSONObject doPostStr(String url, String outStr) throws ParseException, IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        JSONObject jsonObject = null;
        StringEntity entity=new StringEntity(outStr,"UTF-8");
        httpost.setEntity(entity);
        HttpResponse response = client.execute(httpost);
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");
        jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }


    public static void main(String[] args) {
        String appkey = "";
        String appSecret = "";

        String openid_url = YS_GETTOKEN.replace("APPKEY", appkey).replace("APPSECRET", appSecret);
        try {
            JSONObject jsonObj  = doPostStr(openid_url, "");
            String openid = jsonObj.getString("data");
            System.out.println("openid=="+openid);
            String  accessToken =  jsonObj.getJSONObject("data").getString("accessToken");
            String  expireTime =  jsonObj.getJSONObject("data").getString("expireTime");
            System.out.println("accessToken=="+accessToken);

            System.out.println("expireTime=="+expireTime);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
