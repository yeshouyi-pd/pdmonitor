package com.pd.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class TransactionManagementConfig {
	//EnableTransactionManagement 可以写到启动类里面
    //由于server是公告的  只需要写这一个地方就行 其他启动内不需要每个都加
}