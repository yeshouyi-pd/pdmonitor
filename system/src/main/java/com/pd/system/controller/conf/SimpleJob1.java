package com.pd.system.controller.conf;

import com.pd.system.controller.conf.MqttClientTestXH;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

@Component
public class SimpleJob1 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("------SimpleJob1------start------");
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("SimpleJob1-jobKey:"+jobKey);
        MqttClientTestXH testXH = new MqttClientTestXH();
        testXH.type="temperature";
        byte[] message = {0x01,0x04,0x00,0x00,0x00,0x01,0x31, (byte) 0xCA};
        testXH.publishMessage(message, 1);
        System.out.println("------SimpleJob1------end------");
    }
}
