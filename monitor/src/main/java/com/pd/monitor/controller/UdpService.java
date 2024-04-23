package com.pd.monitor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("UdpService")
public class UdpService {

    private static final Logger LOG = LoggerFactory.getLogger(UdpService.class);

    public void handleMessage(byte[] message) {
        // 这里处理接收到的UDP数据
        LOG.error("Received UDP message: " + new String(message));
        LOG.error("Received UDP size: " + message.length);
    }
}
