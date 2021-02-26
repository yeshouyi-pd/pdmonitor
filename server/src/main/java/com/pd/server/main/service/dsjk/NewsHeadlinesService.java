package com.pd.server.main.service.dsjk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.WebchatNewinfo;
import com.pd.server.main.domain.WebchatNewinfoExample;
import com.pd.server.main.mapper.WebchatNewinfoMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsHeadlinesService extends AbstractScanRequest{

    private WebchatNewinfoMapper webchatNewinfoMapper;

    public NewsHeadlinesService(){
        webchatNewinfoMapper = SpringUtil.getBean(WebchatNewinfoMapper.class);
    }

    /**
     * 新闻头条
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public String request(String jsonParam) throws Exception {
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        WebchatNewinfoExample example = new WebchatNewinfoExample();
        WebchatNewinfoExample.Criteria ca = example.createCriteria();
        List<WebchatNewinfo> webchatNewinfos = webchatNewinfoMapper.selectByExampleNewsHead();
        if(null != webchatNewinfos && webchatNewinfos.size() > 0){
            for (int i = 0; i < webchatNewinfos.size(); i++) {
                Map<String,String> map = new HashMap<String, String>();
                WebchatNewinfo webchatNewinfo = webchatNewinfos.get(i);
                map.put("title", webchatNewinfo.getTitle());
                map.put("content", webchatNewinfo.getContent());
                list.add(map);
            }
        }
        if(list.size() > 0){
            data = JSON.toJSONString(list);
        }
        return data;
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        Map<String,String> map = new HashMap<String, String>();
        map.put("title", "123123");
        map.put("content", "123124124214");
        Map<String,String> map1 = new HashMap<String, String>();
        map1.put("title", "123123");
        map1.put("content", "123124124214");
        list.add(map);
        list.add(map1);
        String s = JSON.toJSONString(list);
        System.out.println(s);
    }
}
