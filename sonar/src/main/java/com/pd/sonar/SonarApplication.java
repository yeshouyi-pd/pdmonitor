package com.pd.sonar;

import com.pd.sonar.config.RabbitMQConfig;
import com.pd.sonar.receiver.SonarMessageReceiver;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * 声呐应用主类
 * 用于启动声呐消息接收器
 */
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableScheduling
@ComponentScan("com.pd")
@ServletComponentScan    //扫描自定义的WebFilter和WebListener
public class SonarApplication {

    private  static  final org.slf4j.Logger LOG = LoggerFactory.getLogger(SonarApplication.class);
    private static SonarMessageReceiver messageReceiver;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SonarApplication.class);
        ApplicationContext context = app.run(args);
        Environment env = context.getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("Wvbase地址：\thttp://127.0.0.1:{}",env.getProperty("server.port"));

        try {
            LOG.info("正在启动声呐消息接收器...");

            // 显示配置信息
            RabbitMQConfig config = new RabbitMQConfig();
            LOG.info(config.getConnectionInfo());

            // 从Spring容器获取消息接收器
            messageReceiver = context.getBean(SonarMessageReceiver.class);
            messageReceiver.start();

            // 添加关闭钩子
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                LOG.info("正在关闭声呐消息接收器...");
                if (messageReceiver != null) {
                    messageReceiver.close();
                }
                LOG.info("声呐消息接收器已关闭");
            }));

            LOG.info("声呐消息接收器启动成功，正在监听消息...");

            // 保持程序运行
            while (true) {
                try {
                    Thread.sleep(5000); // 每5秒检查一次连接状态

                    if (!messageReceiver.isConnected()) {
                        LOG.info("RabbitMQ连接已断开，尝试重新连接...");
                        messageReceiver.start();
                    }
                } catch (InterruptedException e) {
                    LOG.info("程序被中断");
                    break;
                } catch (Exception e) {
                    LOG.info( "重新连接失败", e);
                }
            }

        } catch (Exception e) {
            LOG.info( "启动声呐消息接收器失败", e);
            System.exit(1);
        }

    }
    
    /**
     * 获取消息接收器实例
     */
    public static SonarMessageReceiver getMessageReceiver() {
        return messageReceiver;
    }
    
    /**
     * 检查应用状态
     */
    public static boolean isRunning() {
        return messageReceiver != null && messageReceiver.isConnected();
    }
    
    /**
     * 停止应用
     */
    public static void stop() {
        if (messageReceiver != null) {
            messageReceiver.close();
        }
    }
} 