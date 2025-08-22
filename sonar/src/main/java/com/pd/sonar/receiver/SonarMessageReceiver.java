package com.pd.sonar.receiver;

import com.pd.sonar.model.*;
import com.pd.sonar.service.SonarMessageService;
import com.rabbitmq.client.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 声呐消息接收器
 * 负责连接RabbitMQ并接收各种类型的声呐数据消息
 */
@Component
public class SonarMessageReceiver {
    
    private static final Logger logger = Logger.getLogger(SonarMessageReceiver.class.getName());
    
    // RabbitMQ连接配置
    private static final String HOST = "8.154.29.39"; // 实际应用时确定
    private static final int PORT = 35672;
    private static final String USERNAME = "dszx"; // 实际应用时确定
    private static final String PASSWORD = "b!*Vz&^Gs62(@ro"; // 实际应用时确定
    private static final String VHOST = "/";
    private static final String ENCODING = "UTF-8";
    
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
    
    private Connection connection;
    private Channel channel;
    private ObjectMapper objectMapper;
    
    @Autowired
    private SonarMessageService messageService;
    
    public SonarMessageReceiver() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    /**
     * 初始化RabbitMQ连接
     */
    public void initialize() throws IOException, TimeoutException {
        logger.info("正在初始化RabbitMQ连接...");
        
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VHOST);
        
        // 建立连接
        connection = factory.newConnection();
        channel = connection.createChannel();
        
        // 设置字符编码
        channel.basicQos(1);
        
        // 声明Exchange和Queue
        declareExchangesAndQueues();
        
        // 绑定消费者
        bindConsumers();
        
        logger.info("RabbitMQ连接初始化完成");
    }
    
    /**
     * 声明Exchange和Queue
     */
    private void declareExchangesAndQueues() throws IOException {
        // 声明Exchange
        channel.exchangeDeclare(BIOMASS_EXCHANGE, "topic", true);
        channel.exchangeDeclare(ALARM_EXCHANGE, "topic", true);
        channel.exchangeDeclare(ECHO_EXCHANGE, "topic", true);
        channel.exchangeDeclare(PARAMETER_EXCHANGE, "topic", true);
        channel.exchangeDeclare(SV_ECHO_EXCHANGE, "topic", true);
        channel.exchangeDeclare(SINGLE_TARGET_EXCHANGE, "topic", true);
        
        // 声明Queue
        channel.queueDeclare(BIOMASS_QUEUE, true, false, false, null);
        channel.queueDeclare(ALARM_QUEUE, true, false, false, null);
        channel.queueDeclare(ECHO_QUEUE, true, false, false, null);
        channel.queueDeclare(PARAMETER_QUEUE, true, false, false, null);
        channel.queueDeclare(SV_ECHO_QUEUE, true, false, false, null);
        channel.queueDeclare(SINGLE_TARGET_QUEUE, true, false, false, null);
        
        // 绑定Queue和Exchange
        channel.queueBind(BIOMASS_QUEUE, BIOMASS_EXCHANGE, BIOMASS_ROUTING_KEY);
        channel.queueBind(ALARM_QUEUE, ALARM_EXCHANGE, ALARM_ROUTING_KEY);
        channel.queueBind(ECHO_QUEUE, ECHO_EXCHANGE, ECHO_ROUTING_KEY);
        channel.queueBind(PARAMETER_QUEUE, PARAMETER_EXCHANGE, PARAMETER_ROUTING_KEY);
        channel.queueBind(SV_ECHO_QUEUE, SV_ECHO_EXCHANGE, SV_ECHO_ROUTING_KEY);
        channel.queueBind(SINGLE_TARGET_QUEUE, SINGLE_TARGET_EXCHANGE, SINGLE_TARGET_ROUTING_KEY);
    }
    
    /**
     * 绑定消费者
     */
    private void bindConsumers() throws IOException {
        // 注意：报警信息消费者和声呐参数信息消费者已暂时屏蔽
        // 如需启用，请取消注释相应代码块
        
        // 生物量数据消费者
        channel.basicConsume(BIOMASS_QUEUE, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, 
                                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, StandardCharsets.UTF_8);
                    logger.info("收到生物量数据: " + message);
                    
                    SonarBiomassData biomassData = objectMapper.readValue(message, SonarBiomassData.class);
                    messageService.processBiomassData(biomassData);
                    
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "处理生物量数据失败", e);
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                }
            }
        });
        
        // 报警信息消费者 - 暂时屏蔽
        /*
        channel.basicConsume(ALARM_QUEUE, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, 
                                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, StandardCharsets.UTF_8);
                    logger.info("收到报警信息: " + message);
                    
                    SonarAlarmData alarmData = objectMapper.readValue(message, SonarAlarmData.class);
                    messageService.processAlarmData(alarmData);
                    
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "处理报警信息失败", e);
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                }
            }
        });
        */
        
        // 回波图信息消费者
        channel.basicConsume(ECHO_QUEUE, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, 
                                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, StandardCharsets.UTF_8);
                    logger.info("收到回波图信息: " + message);
                    
                    SonarEchoData echoData = objectMapper.readValue(message, SonarEchoData.class);
                    messageService.processEchoData(echoData);
                    
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "处理回波图信息失败", e);
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                }
            }
        });
        
        // 声呐参数信息消费者 - 暂时屏蔽
        /*
        channel.basicConsume(PARAMETER_QUEUE, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, 
                                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, StandardCharsets.UTF_8);
                    logger.info("收到声呐参数信息: " + message);
                    
                    SonarParameterData parameterData = objectMapper.readValue(message, SonarParameterData.class);
                    messageService.processParameterData(parameterData);
                    
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "处理声呐参数信息失败", e);
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                }
            }
        });
        */
        
        // 平均SV回波图信息消费者
        channel.basicConsume(SV_ECHO_QUEUE, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, 
                                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, StandardCharsets.UTF_8);
                    logger.info("收到平均SV回波图信息: " + message);
                    
                    SonarEchoData svEchoData = objectMapper.readValue(message, SonarEchoData.class);
                    messageService.processSVEchoData(svEchoData);
                    
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "处理平均SV回波图信息失败", e);
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                }
            }
        });
        
        // 单体目标信息消费者
        channel.basicConsume(SINGLE_TARGET_QUEUE, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, 
                                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, StandardCharsets.UTF_8);
                    logger.info("收到单体目标信息: " + message);
                    
                    SingleTargetData singleTargetData = objectMapper.readValue(message, SingleTargetData.class);
                    messageService.processSingleTargetData(singleTargetData);
                    
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "处理单体目标信息失败", e);
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                }
            }
        });
    }
    
    /**
     * 关闭连接
     */
    public void close() {
        try {
            if (channel != null && channel.isOpen()) {
                channel.close();
            }
            if (connection != null && connection.isOpen()) {
                connection.close();
            }
            logger.info("RabbitMQ连接已关闭");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "关闭RabbitMQ连接失败", e);
        }
    }
    
    /**
     * 检查连接状态
     */
    public boolean isConnected() {
        return connection != null && connection.isOpen() && channel != null && channel.isOpen();
    }
    
    /**
     * 启动消息接收器
     */
    public void start() {
        try {
            initialize();
            logger.info("声呐消息接收器启动成功");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "启动声呐消息接收器失败", e);
        }
    }
} 