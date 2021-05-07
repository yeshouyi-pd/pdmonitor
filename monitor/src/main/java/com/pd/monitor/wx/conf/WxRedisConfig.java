package com.pd.monitor.wx.conf;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.AddrInfo;
import com.pd.server.main.domain.AddrInfoExample;
import com.pd.server.main.mapper.AddrInfoMapper;
import com.pd.server.util.DateTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * rddis 缓存工具
 */
@Component
@Order(Integer.MAX_VALUE)
public class WxRedisConfig implements CommandLineRunner {


    private static final Logger LOG = LoggerFactory.getLogger(WxRedisConfig.class);

    public static final String fileStr = "/system/f/" + DateTools.getYM() + "/";

    //当前操作系统
    private static String macos = "";

    //当前存储路径
    private static String picStorePath = "";

    public static String getMacos() {
        return macos;
    }

    public static RedisTemplate  redisTstaticemplate;

    public  static AddrInfoMapper  addrInfostaticMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AddrInfoMapper addrInfoMapper;

    @PostConstruct
    protected void init() {
        redisTstaticemplate = redisTemplate;
        addrInfostaticMapper = addrInfoMapper;
    }


    @Override
    public void run(String... args) throws Exception {
        LOG.info(">>>>>>>>>>>>>>开始加载数据库参数<<<<<<<<<<<<<");
        macos = System.getProperties().getProperty("os.name");// 获取系统操作类型
        reload();
        LOG.info(">>>>>>>>>>>>>>>服务启动，基础数据加载完成 <<<<<<<<<<<<<");
    }

    /**
     * 获取当前OS下的存储路径
     */
    public static String getPicStorePath() {
        Map<String,String> map = (Map<String, String>) redisTstaticemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
        if (getMacos().toLowerCase().contains("windows")) {
            picStorePath= map.get("pathwindows");
        } else {
            picStorePath= map.get("pathliunx");
        }
        return picStorePath;
    }

    /**
     * 创建目录
     * @param sysstorePath
     * @return
     */
    public static String createFileDriectory(String sysstorePath){
        String picDirectory = "";
        File storePath = new File(sysstorePath);
        if (!storePath.exists() && !storePath.isDirectory()) {
            LOG.info("创建目录-----------" + storePath.getAbsoluteFile());
            storePath.mkdir();
            if (!getMacos().toLowerCase().contains("windows")) {//当前系统不是windows 则授权
                try {
                    Runtime.getRuntime().exec("chmod 775 -R " + storePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        picDirectory = sysstorePath  + DateTools.getYM() + "/";
        File file = new File(picDirectory);
        if (!file.exists() && !file.isDirectory()) {
            LOG.info("创建目录-----------" + file.getAbsoluteFile());
            file.mkdir();
            if (!getMacos().toLowerCase().contains("windows")) {//当前系统不是windows 则授权
                try {
                    Runtime.getRuntime().exec("chmod 775 -R " + file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return picDirectory;
    }

    /*
     * 重载参数，刷新缓存调用
     */
    public static synchronized void reload() {
        init_city();//加载省市县
    }

    public synchronized static boolean init_city(){
        try {
            Map<String, String> provincemap = selectPcode("0");
            Map<String, Map<String, String>> map = new LinkedHashMap<String, Map<String, String>>();
            Map<String, String> listcity = new LinkedHashMap<String, String>();
            Map<String, String> listcounty = new LinkedHashMap<String, String>();
            for (String key1:provincemap.keySet()) {
                Map<String, String> mapcity = selectPcode(key1);
                listcity.putAll(mapcity);
                for(String key2:mapcity.keySet()){
                    Map<String, String> mapcounty = selectPcode(key2);
                    listcounty.putAll(mapcounty);
                }
            }
            map.put("province_list", provincemap);
            map.put("city_list", listcity);
            map.put("county_list", listcounty);
            String s = JSONObject.toJSONString(map);
            JSONObject jsonObj = JSONObject.parseObject(s);
            redisTstaticemplate.opsForValue().set(RedisCode.ADDRINFO, jsonObj);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Map<String,String> selectPcode(String pcode){
        AddrInfoExample example = new AddrInfoExample();
        example.createCriteria().andPcodeEqualTo(pcode);
        List<AddrInfo> addrInfos = addrInfostaticMapper.selectByExample(example);
        Map<String,String> map = new LinkedHashMap<String, String>();
        for (int i = 0; i < addrInfos.size(); i++) {
            AddrInfo addrInfo = addrInfos.get(i);
            map.put(addrInfo.getCode(),addrInfo.getName());
        }
        return map;
    }

}
