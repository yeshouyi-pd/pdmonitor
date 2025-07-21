package com.pd.system.controller.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MqttClientLoop {
    private static final Logger LOG = LoggerFactory.getLogger(MqttClientLoop.class);

    //@Scheduled(cron = "0 0 0/1 * * ? ")
    public static void loop() {
        try{
            LOG.info(">>>>>>>>>>>>>>请求数据开始<<<<<<<<<<<<<");
            MqttClientTestXH testXH = MqttClientTestXH.getInstance("tcp://47.244.23.44", "subClient", "benben", "123456");
            testXH.setType("temperature");
            byte[] message = {0x01,0x04,0x00,0x00,0x00,0x01,0x31, (byte) 0xCA};
            LOG.info(">>>>>>>>>>>>>>请求温度数据<<<<<<<<<<<<<");
            testXH.publishMessage(message, 2);
            Thread.sleep(20000);

            testXH.setType("waterDepth");
            byte[] message2 = {0x34,0x03,0x00,0x15,0x00,0x02, (byte) 0xD0, (byte) 0x6A};
            LOG.info(">>>>>>>>>>>>>>请求水深数据<<<<<<<<<<<<<");
            testXH.publishMessage(message2, 2);
            Thread.sleep(20000);

            testXH.setType("waterVelocity");
            byte[] message3 = {0x34,0x03,0x00,0x0F,0x00,0x02, (byte) 0xF1, (byte) 0xAD};
            LOG.info(">>>>>>>>>>>>>>请求流速数据<<<<<<<<<<<<<");
            testXH.publishMessage(message3, 2);
            Thread.sleep(20000);
            testXH.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //closeConnect();
    }
}
