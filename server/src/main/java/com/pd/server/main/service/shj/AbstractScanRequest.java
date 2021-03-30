package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;

public abstract class AbstractScanRequest {
    public String data = "";

    public String request(JSONObject jsonParam) throws Exception {
        return data;
    }
}
