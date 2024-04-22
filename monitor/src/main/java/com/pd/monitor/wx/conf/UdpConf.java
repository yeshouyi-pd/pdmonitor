package com.pd.monitor.wx.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;

@Configuration
public class UdpConf {

    @Bean
    public IntegrationFlow integrationFlow(){
        return IntegrationFlows.from(new UnicastReceivingChannelAdapter(19098))
                .handle("UdpService","handleMessage").get();
    }
}
