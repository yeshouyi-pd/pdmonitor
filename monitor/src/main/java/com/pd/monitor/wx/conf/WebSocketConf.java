package com.pd.monitor.wx.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
public class WebSocketConf {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    /**
     * WebSocket 配置信息
     *
     * @return servletServerContainerFactoryBean
     */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean bean = new ServletServerContainerFactoryBean();

        // 文本缓冲区大小
        bean.setMaxTextMessageBufferSize(1024 * 1024 * 64);
        // 字节缓冲区大小
        bean.setMaxBinaryMessageBufferSize(1024 * 1024 * 512);

        return bean;
    }
}
