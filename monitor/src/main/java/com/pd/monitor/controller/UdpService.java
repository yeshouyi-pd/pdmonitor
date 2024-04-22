package com.pd.monitor.controller;

import org.springframework.stereotype.Service;

@Service("UdpService")
public class UdpService {

    public void handleMessage(byte[] message) {
        // 这里处理接收到的UDP数据
        System.out.println("Received UDP message: " + new String(message));
    }
}
