package com.pd.system.controller.conf;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MqttClientLoop {
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public static void loop() {
        try{
            MqttClientTestXH testXH = new MqttClientTestXH();
            testXH.type="temperature";
            byte[] message = {0x01,0x04,0x00,0x00,0x00,0x01,0x31, (byte) 0xCA};
            testXH.publishMessage(message, 1);
            Thread.sleep(10000);

//            MqttClientTestXH testXH1 = new MqttClientTestXH();
//            testXH1.type="waterDepth";
//            byte[] message2 = {0x34,0x03,0x00,0x15,0x00,0x02, (byte) 0xD0, (byte) 0x6A};
//            testXH1.publishMessage(message2, 1);
//            Thread.sleep(10000);
//
//            MqttClientTestXH testXH2 = new MqttClientTestXH();
//            testXH2.type="waterVelocity";
//            byte[] message3 = {0x34,0x03,0x00,0x0F,0x00,0x02, (byte) 0xF1, (byte) 0xAD};
//            testXH2.publishMessage(message3, 1);
//            Thread.sleep(10000);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //closeConnect();
    }
}
