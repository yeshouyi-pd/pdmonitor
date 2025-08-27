package com.pd.sonar.receiver;

import com.alibaba.fastjson.JSONObject;
import com.pd.sonar.model.*;
import com.pd.sonar.service.SonarMessageService;
import com.rabbitmq.client.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 声呐消息接收器
 * 负责连接RabbitMQ并接收各种类型的声呐数据消息
 */
@Component
public class SonarMessageReceiver {
    
    private static final Logger logger = Logger.getLogger(SonarMessageReceiver.class.getName());
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SonarMessageReceiver.class);
    
    // RabbitMQ连接配置
    private static final String HOST = "127.0.0.1"; // 实际应用时确定
    //private static final String HOST = "8.154.29.39"; // 实际应用时确定
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
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final AtomicBoolean isReconnecting = new AtomicBoolean(false);
    private final ReentrantLock channelLock = new ReentrantLock();
    
    @Autowired
    private SonarMessageService messageService;
    
    public SonarMessageReceiver() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    /**
     * 清理和验证字符串数据
     * 移除或替换可能导致数据库存储问题的特殊字符
     */
    private String cleanStringData(String data) {
        if (data == null) {
            return null;
        }
        
        try {
            // 尝试使用UTF-8编码解码
            byte[] bytes = data.getBytes(StandardCharsets.ISO_8859_1);
            String cleaned = new String(bytes, StandardCharsets.ISO_8859_1);
            
            // 移除控制字符（除了换行符和制表符）
            cleaned = cleaned.replaceAll("[\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]", "");
            
            // 移除其他可能导致问题的字符
            cleaned = cleaned.replaceAll("[\\x80-\\x9F]", "");
            
            // 检查是否包含无法存储的字符
            if (containsInvalidCharacters(cleaned)) {
                LOG.warn("数据包含可能导致数据库存储问题的字符，已进行清理");
                // 进一步清理
                cleaned = cleaned.replaceAll("[^\\x20-\\x7E\\x0A\\x09]", "");
            }
            
            return cleaned;
            
        } catch (Exception e) {
            LOG.error("清理字符串数据时出错", e);
            // 如果清理失败，返回安全的默认值
            return data.replaceAll("[^\\x20-\\x7E\\x0A\\x09]", "");
        }
    }
    
    /**
     * 检查字符串是否包含可能导致数据库存储问题的字符
     */
    private boolean containsInvalidCharacters(String data) {
        if (data == null) {
            return false;
        }
        
        // 检查是否包含非ASCII字符
        for (char c : data.toCharArray()) {
            if (c > 127) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 安全地处理消息数据
     */
    private String processMessageData(byte[] body) {
        try {
            String message = new String(body, StandardCharsets.ISO_8859_1);
            
            // 记录原始数据长度
            LOG.info("收到消息，长度: {} 字节", body.length);
            
            // 检查是否包含特殊字符
            if (containsSpecialCharacters(message)) {
                LOG.warn("消息包含特殊字符，可能影响数据库存储");
                LOG.debug("特殊字符详情: {}", getCharacterAnalysis(message));
            }
            
            // 清理数据
            String cleanedMessage = cleanStringData(message);
            
            if (!cleanedMessage.equals(message)) {
                LOG.info("消息已清理，原始长度: {}, 清理后长度: {}", message.length(), cleanedMessage.length());
            }
            
            return cleanedMessage;
            
        } catch (Exception e) {
            LOG.error("处理消息数据时出错", e);
            // 返回安全的默认值
            return new String(body, StandardCharsets.ISO_8859_1).replaceAll("[^\\x20-\\x7E\\x0A\\x09]", "");
        }
    }
    
    /**
     * 检查字符串是否包含特殊字符
     */
    private boolean containsSpecialCharacters(String str) {
        if (str == null) {
            return false;
        }
        
        // 检查常见的特殊字符
        String specialChars = "$@#%^&*()_+-={}[]|\\:;\"'<>?,./";
        for (char c : specialChars.toCharArray()) {
            if (str.indexOf(c) != -1) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 获取字符分析信息
     */
    private String getCharacterAnalysis(String str) {
        if (str == null) {
            return "字符串为null";
        }
        
        StringBuilder analysis = new StringBuilder();
        analysis.append("字符串长度: ").append(str.length()).append("\n");
        
        // 统计特殊字符
        int specialCharCount = 0;
        int nonAsciiCount = 0;
        
        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '+' && c != '/' && c != '=' && c != ' ' && c != '\n' && c != '\t') {
                specialCharCount++;
                analysis.append("发现特殊字符: '").append(c).append("' (ASCII: ").append((int)c).append(")\n");
            }
            if (c > 127) {
                nonAsciiCount++;
            }
        }
        
        analysis.append("特殊字符总数: ").append(specialCharCount).append("\n");
        analysis.append("非ASCII字符总数: ").append(nonAsciiCount);
        
        return analysis.toString();
    }
    
    /**
     * 初始化RabbitMQ连接
     */
    public void initialize() throws IOException, TimeoutException {
        logger.info("正在初始化RabbitMQ连接...");
        
        try {
            // 创建连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST);
            factory.setPort(PORT);
            factory.setUsername(USERNAME);
            factory.setPassword(PASSWORD);
            factory.setVirtualHost(VHOST);
            
            // 设置连接参数
            factory.setConnectionTimeout(30000);
            factory.setAutomaticRecoveryEnabled(true);
            factory.setNetworkRecoveryInterval(10000);
            
            // 建立连接
            connection = factory.newConnection();
            
            // 添加连接监听器
            connection.addShutdownListener(cause -> {
                LOG.warn("RabbitMQ连接已关闭: {}", cause.getMessage());
                if (isRunning.get() && !isReconnecting.get()) {
                    scheduleReconnection();
                }
            });
            
            // 创建通道
            channel = connection.createChannel();
            
            // 设置通道参数
            channel.basicQos(1);
            
            // 添加通道监听器
            channel.addShutdownListener(cause -> {
                LOG.warn("RabbitMQ通道已关闭: {}", cause.getMessage());
                if (isRunning.get() && !isReconnecting.get()) {
                    scheduleReconnection();
                }
            });
            
            // 声明Exchange和Queue
            declareExchangesAndQueues();
            
            // 绑定消费者
            bindConsumers();
            
            isRunning.set(true);
            logger.info("RabbitMQ连接初始化完成");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "初始化RabbitMQ连接失败", e);
            throw e;
        }
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
                    String message = new String(body, StandardCharsets.ISO_8859_1);
                    LOG.info("收到生物量数据，长度: {} 字符", message.length());
                    
                    SonarBiomassData biomassData = objectMapper.readValue(message, SonarBiomassData.class);
                    messageService.processBiomassData(biomassData);
                    
                    safeAck(envelope);
                } catch (Exception e) {
                    LOG.error("收到生物量数据: {}", new String(body, StandardCharsets.ISO_8859_1));
                    LOG.error("处理生物量数据失败", e.getMessage());
                    safeNack(envelope, true);
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
                    String message = new String(body, StandardCharsets.ISO_8859_1);
                    logger.info("收到报警信息: " + message);
                    
                    SonarAlarmData alarmData = objectMapper.readValue(message, SonarAlarmData.class);
                    messageService.processAlarmData(alarmData);
                    
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    LOG.error("处理报警信息失败", new String(body, StandardCharsets.ISO_8859_1));
                    LOG.error("处理报警信息失败", e.getMessage());
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
                    String message = new String(body, StandardCharsets.ISO_8859_1);
                    LOG.info("收到回波图信息，长度: {} 字符", message.length());
                    
                    //SonarEchoData echoData = objectMapper.readValue(message, SonarEchoData.class);
                    messageService.processEchoData(JSONObject.parseObject(message));
                    
                    safeAck(envelope);
                } catch (Exception e) {
                    //LOG.error("收到回波图信息: ", new String(body, StandardCharsets.ISO_8859_1));
                    LOG.error("处理回波图信息失败", e);
                    safeNack(envelope, true);
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
                    String message = new String(body, StandardCharsets.ISO_8859_1);
                    logger.info("收到声呐参数信息: " + message);
                    
                    SonarParameterData parameterData = objectMapper.readValue(message, SonarParameterData.class);
                    messageService.processParameterData(parameterData);
                    
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    LOG.error("处理声呐参数信息失败", new String(body, StandardCharsets.ISO_8859_1));
                    LOG.error("处理声呐参数信息失败", e.getMessage());
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
                    String message = new String(body, StandardCharsets.ISO_8859_1);
                    LOG.info("收到平均SV回波图信息，长度: {} 字符", message.length());
                    
                    //SonarEchoData svEchoData = objectMapper.readValue(message, SonarEchoData.class);
                    messageService.processSVEchoData(JSONObject.parseObject(message));
                    
                    safeAck(envelope);
                } catch (Exception e) {
                    //LOG.error("收到平均SV回波图信息: {}", new String(body, StandardCharsets.ISO_8859_1));
                    LOG.error("处理平均SV回波图信息失败", e);
                    safeNack(envelope, true);
                }
            }
        });
        
        // 单体目标信息消费者
        channel.basicConsume(SINGLE_TARGET_QUEUE, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, 
                                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, StandardCharsets.ISO_8859_1);
                    LOG.info("收到单体目标信息，长度: {} 字符", message.length());
                    
                    SingleTargetData singleTargetData = objectMapper.readValue(message, SingleTargetData.class);
                    messageService.processSingleTargetData(singleTargetData);
                    
                    safeAck(envelope);
                } catch (Exception e) {
                    LOG.error("收到单体目标信息: {}", new String(body, StandardCharsets.ISO_8859_1));
                    LOG.error("处理单体目标信息失败", e.getMessage());
                    safeNack(envelope, true);
                }
            }
        });
    }
    
    /**
     * 关闭连接
     */
    public void close() {
        isRunning.set(false);
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
        return connection != null && connection.isOpen() && 
               channel != null && channel.isOpen() && 
               isRunning.get();
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

    /**
     * 安全地确认消息
     */
    private void safeAck(Envelope envelope) {
        if (channel != null && channel.isOpen()) {
            try {
                channel.basicAck(envelope.getDeliveryTag(), false);
            } catch (IOException e) {
                LOG.error("确认消息失败: {}", e.getMessage());
                // 如果确认失败，尝试重新创建通道
                scheduleReconnection();
            }
        }
    }
    
    /**
     * 安全地拒绝消息
     */
    private void safeNack(Envelope envelope, boolean requeue) {
        if (channel != null && channel.isOpen()) {
            try {
                channel.basicNack(envelope.getDeliveryTag(), false, requeue);
            } catch (IOException e) {
                LOG.error("拒绝消息失败: {}", e.getMessage());
                // 如果拒绝失败，尝试重新创建通道
                scheduleReconnection();
            }
        }
    }
    
    /**
     * 检查通道状态
     */
    private boolean isChannelHealthy() {
        return channel != null && channel.isOpen() && connection != null && connection.isOpen();
    }
    
    /**
     * 安排重连
     */
    private void scheduleReconnection() {
        if (isReconnecting.compareAndSet(false, true)) {
            LOG.info("安排重连...");
            new Thread(() -> {
                try {
                    Thread.sleep(5000); // 等待5秒后重连
                    if (isRunning.get()) {
                        reconnect();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    isReconnecting.set(false);
                }
            }).start();
        }
    }
    
    /**
     * 重连
     */
    private void reconnect() {
        channelLock.lock();
        try {
            LOG.info("尝试重连...");
            close();
            Thread.sleep(2000); // 等待2秒
            initialize();
            LOG.info("重连成功");
        } catch (Exception e) {
            LOG.error("重连失败: {}", e.getMessage());
            // 继续尝试重连
            if (isRunning.get()) {
                scheduleReconnection();
            }
        } finally {
            channelLock.unlock();
        }
    }
} 