package com.pd.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableScheduling
@ComponentScan("com.pd")
@ServletComponentScan    //扫描自定义的WebFilter和WebListener
public class MonitorApplication {

	private  static  final Logger LOG = LoggerFactory.getLogger(MonitorApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MonitorApplication.class);
		Environment env = app.run(args).getEnvironment();
		LOG.info("启动成功！！");
		LOG.info("Wvbase地址：\thttp://127.0.0.1:{}",env.getProperty("server.port"));


	}

}
