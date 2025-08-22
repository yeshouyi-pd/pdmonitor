package com.pd.sonar.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * RabbitMQ配置类
 * 用于管理RabbitMQ连接参数和配置
 */
public class RabbitMQConfig {
    
    private static final String CONFIG_FILE = "rabbitmq.properties";
    
    // 默认配置值
    private static final String DEFAULT_HOST = "8.154.29.39";
    private static final int DEFAULT_PORT = 35672;
    private static final String DEFAULT_USERNAME = "dszx";
    private static final String DEFAULT_PASSWORD = "b!*Vz&^Gs62(@ro";
    private static final String DEFAULT_VHOST = "/";
    private static final String DEFAULT_ENCODING = "UTF-8";
    
    // 配置属性
    private String host;
    private int port;
    private String username;
    private String password;
    private String vhost;
    private String encoding;
    
    // Exchange和Queue配置
    private static final String BIOMASS_EXCHANGE = "neptune.sonar.biomass";
    private static final String BIOMASS_ROUTING_KEY = "sonar_biomass";
    private static final String BIOMASS_QUEUE = "data.sonar.biomass";
    
    private static final String ALARM_EXCHANGE = "neptune.sonar.alarm";
    private static final String ALARM_ROUTING_KEY = "sonar_alarm";
    private static final String ALARM_QUEUE = "data.sonar.alarm";
    
    private static final String ECHO_EXCHANGE = "neptune.sonar.echo";
    private static final String ECHO_ROUTING_KEY = "sonar_echo";
    private static final String ECHO_QUEUE = "data.sonar.echo";
    
    private static final String PARAMETER_EXCHANGE = "neptune.sonar.para";
    private static final String PARAMETER_ROUTING_KEY = "sonar_para";
    private static final String PARAMETER_QUEUE = "data.sonar.para";
    
    private static final String SV_ECHO_EXCHANGE = "neptune.sv.echo";
    private static final String SV_ECHO_ROUTING_KEY = "sv_echo";
    private static final String SV_ECHO_QUEUE = "data.sv.echo";
    
    private static final String SINGLE_TARGET_EXCHANGE = "neptune.single.target";
    private static final String SINGLE_TARGET_ROUTING_KEY = "single_target";
    private static final String SINGLE_TARGET_QUEUE = "data.single.target";
    
    public RabbitMQConfig() {
        loadConfig();
    }
    
    /**
     * 加载配置文件
     */
    private void loadConfig() {
        Properties props = new Properties();
        
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                props.load(input);
                host = props.getProperty("rabbitmq.host", DEFAULT_HOST);
                port = Integer.parseInt(props.getProperty("rabbitmq.port", String.valueOf(DEFAULT_PORT)));
                username = props.getProperty("rabbitmq.username", DEFAULT_USERNAME);
                password = props.getProperty("rabbitmq.password", DEFAULT_PASSWORD);
                vhost = props.getProperty("rabbitmq.vhost", DEFAULT_VHOST);
                encoding = props.getProperty("rabbitmq.encoding", DEFAULT_ENCODING);
            } else {
                // 使用默认配置
                setDefaultConfig();
            }
        } catch (IOException e) {
            System.err.println("无法加载RabbitMQ配置文件，使用默认配置: " + e.getMessage());
            setDefaultConfig();
        } catch (NumberFormatException e) {
            System.err.println("端口配置格式错误，使用默认端口: " + e.getMessage());
            setDefaultConfig();
        }
    }
    
    /**
     * 设置默认配置
     */
    private void setDefaultConfig() {
        host = DEFAULT_HOST;
        port = DEFAULT_PORT;
        username = DEFAULT_USERNAME;
        password = DEFAULT_PASSWORD;
        vhost = DEFAULT_VHOST;
        encoding = DEFAULT_ENCODING;
    }
    
    // Getters
    public String getHost() {
        return host;
    }
    
    public int getPort() {
        return port;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getVhost() {
        return vhost;
    }
    
    public String getEncoding() {
        return encoding;
    }
    
    // Exchange和Queue配置的Getters
    public static String getBiomassExchange() {
        return BIOMASS_EXCHANGE;
    }
    
    public static String getBiomassRoutingKey() {
        return BIOMASS_ROUTING_KEY;
    }
    
    public static String getBiomassQueue() {
        return BIOMASS_QUEUE;
    }
    
    public static String getAlarmExchange() {
        return ALARM_EXCHANGE;
    }
    
    public static String getAlarmRoutingKey() {
        return ALARM_ROUTING_KEY;
    }
    
    public static String getAlarmQueue() {
        return ALARM_QUEUE;
    }
    
    public static String getEchoExchange() {
        return ECHO_EXCHANGE;
    }
    
    public static String getEchoRoutingKey() {
        return ECHO_ROUTING_KEY;
    }
    
    public static String getEchoQueue() {
        return ECHO_QUEUE;
    }
    
    public static String getParameterExchange() {
        return PARAMETER_EXCHANGE;
    }
    
    public static String getParameterRoutingKey() {
        return PARAMETER_ROUTING_KEY;
    }
    
    public static String getParameterQueue() {
        return PARAMETER_QUEUE;
    }
    
    public static String getSvEchoExchange() {
        return SV_ECHO_EXCHANGE;
    }
    
    public static String getSvEchoRoutingKey() {
        return SV_ECHO_ROUTING_KEY;
    }
    
    public static String getSvEchoQueue() {
        return SV_ECHO_QUEUE;
    }
    
    public static String getSingleTargetExchange() {
        return SINGLE_TARGET_EXCHANGE;
    }
    
    public static String getSingleTargetRoutingKey() {
        return SINGLE_TARGET_ROUTING_KEY;
    }
    
    public static String getSingleTargetQueue() {
        return SINGLE_TARGET_QUEUE;
    }
    
    /**
     * 获取连接信息摘要
     */
    public String getConnectionInfo() {
        return String.format("RabbitMQ连接信息: %s:%d, 用户: %s, VHost: %s", host, port, username, vhost);
    }
    
    @Override
    public String toString() {
        return "RabbitMQConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", vhost='" + vhost + '\'' +
                ", encoding='" + encoding + '\'' +
                '}';
    }
} 