package com.pd.system.controller.conf;

import com.pd.system.controller.conf.MqttClientTestXH;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

@Component
public class SimpleJob2 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("------SimpleJob2------start------");
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("SimpleJob1-jobKey:"+jobKey);
        MqttClientTestXH testXH1 = new MqttClientTestXH();
        testXH1.type="waterDepth";
        byte[] message2 = {0x34,0x03,0x00,0x15,0x00,0x02, (byte) 0xD0, (byte) 0x6A};
        testXH1.publishMessage(message2, 1);
        System.out.println("------SimpleJob2------end------");
    }
}
