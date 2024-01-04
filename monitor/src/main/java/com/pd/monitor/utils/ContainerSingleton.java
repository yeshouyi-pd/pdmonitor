package com.pd.monitor.utils;

import com.pd.server.main.service.shj.AbstractScanRequest;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by geely
 */
public class ContainerSingleton {


    public static Map<String, AbstractScanRequest> singletonMap = new HashMap<String,AbstractScanRequest>();

    public static void putInstance(String key,AbstractScanRequest instance){
        if(StringUtils.isNotBlank(key) && instance != null){
            if(!singletonMap.containsKey(key)){
                singletonMap.put(key,instance);
            }
        }
    }




}
