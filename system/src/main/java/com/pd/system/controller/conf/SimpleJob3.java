package com.pd.system.controller.conf;

import com.pd.system.controller.conf.MqttClientTestXH;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

@Component
public class SimpleJob3 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("------SimpleJob3------start------");
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("SimpleJob1-jobKey:"+jobKey);
        MqttClientTestXH testXH2 = new MqttClientTestXH();
        testXH2.type="waterVelocity";
        byte[] message3 = {0x34,0x03,0x00,0x0F,0x00,0x02, (byte) 0xF1, (byte) 0xAD};
        testXH2.publishMessage(message3, 1);
        System.out.println("------SimpleJob3------end------");
    }
}
