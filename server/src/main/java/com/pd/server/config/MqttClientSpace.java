package com.pd.server.config;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.WaterEquiplog;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.mapper.WaterEquiplogMapper;
import com.pd.server.main.service.VoicePowerDeviceService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.Date;
import java.util.Map;

public class MqttClientSpace implements ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(MqttClientSpace.class);
    private static ApplicationContext applicationContext;
    private static volatile MqttClientSpace instance = null;
    private static volatile boolean initializationAttempted = false;

    private org.eclipse.paho.client.mqttv3.MqttClient mqttClient = null;
    private MemoryPersistence memoryPersistence = null;
    private MqttConnectOptions mqttConnectOptions = null;
    private MqttTopic topic = null;
    private String type = "";
    private String brokerUrl;
    private String clientId;
    private String userName;
    private String passWord;
    private boolean initialized = false;
    private volatile boolean reconnecting = false;
    private volatile long lastReconnectTime = 0;
    private static final long RECONNECT_INTERVAL = 5000; // 重连间隔5秒
    private static final int MAX_RECONNECT_ATTEMPTS = 10; // 最大重连次数
    private volatile int reconnectAttempts = 0;
    private volatile boolean needResubscribe = false; // 标记是否需要重新订阅

    public static RedisTemplate redisTstaticemplate;
    public static WaterEquiplogMapper waterEquiplogMapperStatic;
    public static VoicePowerDeviceService voicePowerDeviceServiceStatic;

    private MqttClientSpace(String brokerUrl, String clientId, String userName, String passWord) {
        this.brokerUrl = brokerUrl;
        this.clientId = clientId;
        this.userName = userName;
        this.passWord = passWord;
        init();
    }

    public static MqttClientSpace getInstance(String brokerUrl, String clientId, String userName, String passWord) {
        if (instance == null) {
            synchronized (MqttClientSpace.class) {
                if (instance == null) {
                    instance = new MqttClientSpace(brokerUrl, clientId, userName, passWord);
                }
            }
        }
        return instance;
    }
    
    /**
     * 获取已初始化的实例（如果未初始化则返回null）
     */
    public static MqttClientSpace getInstance() {
        return instance;
    }
    
    /**
     * 初始化MQTT客户端（带依赖注入）
     */
    public static void initializeMqttClient(String brokerUrl, String clientId, String userName, String passWord,
                                            RedisTemplate redisTemplate, WaterEquiplogMapper waterEquiplogMapper,
                                            VoicePowerDeviceService voicePowerDeviceService) {
        if (initializationAttempted) {
            LOG.info("MQTT客户端初始化已经尝试过，跳过重复初始化");
            return;
        }
        
        synchronized (MqttClientSpace.class) {
            if (initializationAttempted) {
                LOG.info("MQTT客户端初始化已经尝试过，跳过重复初始化");
                return;
            }
            
            if (instance == null) {
                LOG.info("正在初始化MQTT客户端（带依赖注入）...");
                instance = new MqttClientSpace(brokerUrl, clientId, userName, passWord);
                
                // 手动设置静态变量
                redisTstaticemplate = redisTemplate;
                waterEquiplogMapperStatic = waterEquiplogMapper;
                voicePowerDeviceServiceStatic = voicePowerDeviceService;
                LOG.info("静态变量手动设置完成: {}", getStaticInitStatus());
                
                LOG.info("MQTT客户端初始化完成");
            } else {
                LOG.info("MQTT客户端已经初始化");
            }
            initializationAttempted = true;
        }
    }
    
    /**
     * 重置MQTT客户端实例（用于重新初始化）
     */
    public static void resetInstance() {
        synchronized (MqttClientSpace.class) {
            LOG.info("正在重置MQTT客户端实例...");
            
            // 关闭现有连接
            if (instance != null) {
                try {
                    instance.close();
                } catch (Exception e) {
                    LOG.warn("关闭现有MQTT连接时发生错误: " + e.getMessage());
                }
            }
            
            // 重置状态
            instance = null;
            initializationAttempted = false;
            
            LOG.info("MQTT客户端实例重置完成");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (MqttClientSpace.applicationContext == null) {
            MqttClientSpace.applicationContext = applicationContext;
        }
    }

    private void init() {
        if (initialized) return;
        
        // 验证配置参数
        if (StringUtils.isEmpty(brokerUrl) || StringUtils.isEmpty(clientId) || 
            StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
            LOG.error("MQTT配置参数不完整 - brokerUrl: {}, clientId: {}, userName: {}, passWord: {}", 
                     brokerUrl, clientId, userName, passWord != null ? "***" : "null");
            return;
        }
        
        // 为避免客户端ID冲突，为clientId添加唯一后缀
        // 使用时间戳和主机名确保唯一性，避免多个实例冲突
        String originalClientId = clientId;
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            // 使用主机名和时间戳生成唯一标识
            // 格式: 原clientId_主机名_时间戳
            String uniqueSuffix = "_" + hostname + "_" + System.currentTimeMillis();
            this.clientId = clientId + uniqueSuffix;
            LOG.info("为客户端ID添加唯一后缀以避免冲突 - 原始ID: {}, 新ID: {}", originalClientId, this.clientId);
        } catch (Exception e) {
            // 如果获取主机名失败，仅使用时间戳
            String uniqueSuffix = "_" + System.currentTimeMillis();
            this.clientId = clientId + uniqueSuffix;
            LOG.warn("为客户端ID添加唯一后缀时发生异常，使用时间戳: {}", e.getMessage());
            LOG.info("为客户端ID添加唯一后缀以避免冲突 - 原始ID: {}, 新ID: {} (使用时间戳)", originalClientId, this.clientId);
        }
        
        mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(userName);
        mqttConnectOptions.setPassword(passWord.toCharArray());
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setConnectionTimeout(30); // 设置连接超时时间
        
        // 将心跳间隔从60秒调整为30秒，确保连接保持活跃
        // 如果服务器端有更短的超时设置，频繁的心跳可以避免连接被断开
        mqttConnectOptions.setKeepAliveInterval(30); // 设置心跳间隔为30秒（原来是60秒）
        
        // 设置MQTT版本，使用MQTT 3.1.1版本以获得更好的兼容性
        mqttConnectOptions.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
        
        // 启用自动重连
        mqttConnectOptions.setAutomaticReconnect(true);
        
        // 注意：setMaxReconnectDelay方法在某些MQTT客户端版本中可能不可用
        // 如果需要控制重连延迟，可以在重连逻辑中实现
        
        // 设置遗嘱消息（可选，但在连接异常断开时可以通知服务器）
        // 注意：这需要根据实际业务需求来决定是否启用
        // mqttConnectOptions.setWill("client/disconnected", ("客户端 " + clientId + " 异常断开").getBytes(), 0, false);
        
        // 设置是否允许服务器自动重连时恢复之前的会话
        // 由于我们使用的是CleanSession=true，这个设置影响不大，但保持默认值
        mqttConnectOptions.setCleanSession(true);
        
        LOG.info("MQTT连接选项配置 - KeepAlive: {}秒, 连接超时: {}秒, 自动重连: {}, MQTT版本: {}", 
                mqttConnectOptions.getKeepAliveInterval(), 
                mqttConnectOptions.getConnectionTimeout(),
                mqttConnectOptions.isAutomaticReconnect(),
                mqttConnectOptions.getMqttVersion() == MqttConnectOptions.MQTT_VERSION_3_1_1 ? "3.1.1" : "3.1");
        memoryPersistence = new MemoryPersistence();
        
        try {
            LOG.info("正在初始化MQTT客户端 - brokerUrl: {}, clientId: {}", brokerUrl, clientId);
            
            // 如果存在旧连接，先尝试关闭（防止客户端ID冲突）
            if (mqttClient != null) {
                try {
                    if (mqttClient.isConnected()) {
                        LOG.warn("检测到已存在的MQTT连接，先关闭旧连接以避免冲突");
                        mqttClient.disconnect();
                    }
                    mqttClient.close();
                } catch (Exception e) {
                    LOG.warn("关闭旧MQTT连接时发生异常: {}", e.getMessage());
                }
            }
            
            mqttClient = new org.eclipse.paho.client.mqttv3.MqttClient(brokerUrl, clientId, memoryPersistence);
            
            if (!mqttClient.isConnected()) {
                LOG.info("正在连接MQTT服务器... - brokerUrl: {}, clientId: {}", brokerUrl, clientId);
                mqttClient.connect(mqttConnectOptions);
                
                // 连接成功后立即验证连接状态
                if (mqttClient.isConnected()) {
                    LOG.info("MQTT连接成功 - clientId: {}, 服务器URI: {}", clientId, mqttClient.getServerURI());
                    
                    // 等待一小段时间后再次验证，确保连接稳定
                    try {
                        Thread.sleep(500); // 等待500ms
                        if (!mqttClient.isConnected()) {
                            LOG.error("连接后立即断开！clientId: {}, 可能是客户端ID冲突或服务器端主动断开", clientId);
                        } else {
                            LOG.info("连接验证通过，连接稳定 - clientId: {}", clientId);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        LOG.warn("连接验证等待被中断", e);
                    }
                } else {
                    LOG.error("连接后立即断开！clientId: {}", clientId);
                }
            }
            
            // 注册回调，确保收到消息时自动调用 messageArrived 方法
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    // 增强错误日志记录，便于诊断问题
                    if (cause != null) {
                        // 检查是否是EOFException（通常表示服务器端主动断开）
                        Throwable rootCause = cause;
                        while (rootCause.getCause() != null) {
                            rootCause = rootCause.getCause();
                        }
                        
                        if (rootCause instanceof java.io.EOFException) {
                            LOG.error("MQTT连接被服务器端突然关闭 (EOFException) - 这可能是因为:");
                            LOG.error("  1. 服务器端心跳超时设置太短");
                            LOG.error("  2. 服务器端检测到连接异常或协议错误");
                            LOG.error("  3. 网络中间设备（防火墙/NAT）主动断开连接");
                            LOG.error("  4. 服务器端连接数限制或资源不足");
                            LOG.error("建议检查: MQTT服务器配置、网络防火墙设置、心跳间隔配置");
                        }
                        
                        LOG.warn("MQTT连接丢失 - 错误类型: {}, 错误消息: {}, 根本原因: {}, 完整堆栈: ", 
                                cause.getClass().getName(), 
                                cause.getMessage(),
                                rootCause.getClass().getName() + ": " + rootCause.getMessage(),
                                cause);
                    } else {
                        LOG.warn("MQTT连接丢失: 未知原因 (cause为null)");
                    }
                    LOG.warn("连接丢失时的客户端状态 - brokerUrl: {}, clientId: {}, isConnected: {}, initialized: {}", 
                            brokerUrl, clientId, 
                            mqttClient != null ? mqttClient.isConnected() : false, 
                            initialized);
                    initialized = false; // 重置初始化状态，允许重新连接
                    needResubscribe = true; // 标记需要重新订阅主题
                    
                    // 启动重连机制
                    startReconnectProcess();
                }
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // 调用你自己的处理方法
                    MqttClientSpace.this.messageArrived(topic, message);
                }
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //LOG.info("deliveryComplete---------" + token.isComplete());
                }
            });
            initialized = true;
            LOG.info("MQTT客户端初始化完成");
        } catch (MqttException e) {
            LOG.error("MQTT初始化失败 - brokerUrl: {}, clientId: {}, 错误: {}", 
                     brokerUrl, clientId, e.getMessage(), e);
            initialized = false;
        }
    }

    public void subTopic(String topicName) {
        if (mqttClient != null && mqttClient.isConnected()) {
            try {
                mqttClient.subscribe(topicName, 2);
                LOG.info("成功订阅主题: " + topicName);
            } catch (MqttException e) {
                LOG.error("订阅主题失败: " + topicName, e);
            }
        } else {
            LOG.warn("mqttClient is error - 客户端未初始化或未连接，尝试重新连接");
            // 尝试重新连接
            reConnect();
            // 重新连接后再次尝试订阅
            if (mqttClient != null && mqttClient.isConnected()) {
                try {
                    mqttClient.subscribe(topicName, 2);
                    LOG.info("重新连接后成功订阅主题: " + topicName);
                } catch (MqttException e) {
                    LOG.error("重新连接后订阅主题仍然失败: " + topicName, e);
                }
            } else {
                LOG.error("重新连接失败，无法订阅主题: " + topicName);
            }
        }
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        try {
            String messageContent = new String(message.getPayload(), "UTF-8");
            LOG.info("topic:" + topic + ";message:" + messageContent);
            
            // 检查是否包含$GNRMC和,A,字符串
            if (messageContent.contains("$GNRMC") || messageContent.contains("PLAY")) {
                LOG.info("检测到GPS NMEA数据，开始解析...");
                if(messageContent.contains("$GNRMC")){
                    parseGNRMCData(topic, messageContent);
                }
                if(messageContent.contains("PLAY")){
                    savePlayData(topic);
                }
            } else {
                LOG.debug("消息不包含GPS NMEA数据，跳过解析");
            }
        } catch (Exception e) {
            LOG.error("处理MQTT消息时发生异常，topic: " + topic, e);
            // 不重新抛出异常，避免导致MQTT连接丢失
        }
    }

    
    public void publishMessage(String topicName, byte[] message, int qos) {
        if (mqttClient != null && mqttClient.isConnected()) {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(qos);
            mqttMessage.setPayload(message);
            try {
                MqttTopic targetTopic = mqttClient.getTopic(topicName);
                MqttDeliveryToken publish = targetTopic.publish(mqttMessage);
                if (!publish.isComplete()) {
                    publish.waitForCompletion();
                    //LOG.info("消息发送成功");
                }
            } catch (MqttException e) {
                LOG.error("消息发布失败", e);
            }
        } else {
            reConnect();
        }
    }

    public void reConnect() {
        if (mqttClient != null) {
            if (!mqttClient.isConnected()) {
                if (mqttConnectOptions != null) {
                    try {
                        LOG.info("正在尝试重新连接MQTT服务器... - brokerUrl: {}, clientId: {}", brokerUrl, clientId);
                        mqttClient.connect(mqttConnectOptions);
                        
                        // 重连成功后立即验证连接状态
                        if (mqttClient.isConnected()) {
                            LOG.info("MQTT重连成功 - clientId: {}, 服务器URI: {}", clientId, mqttClient.getServerURI());
                            
                            // 等待一小段时间后再次验证，确保连接稳定
                            try {
                                Thread.sleep(500); // 等待500ms
                                if (!mqttClient.isConnected()) {
                                    LOG.error("重连后立即断开！clientId: {}, 可能是客户端ID冲突或服务器端主动断开", clientId);
                                    initialized = false;
                                    return;
                                } else {
                                    LOG.info("重连验证通过，连接稳定 - clientId: {}", clientId);
                                }
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                LOG.warn("重连验证等待被中断", e);
                            }
                            
                            initialized = true;
                            reconnecting = false;
                            reconnectAttempts = 0; // 重置重连次数
                            
                            // 重连成功后重新订阅主题
                            if (needResubscribe) {
                                LOG.info("重连成功，开始重新订阅主题...");
                                resubscribeAllTopics();
                            }
                        } else {
                            LOG.error("重连后立即断开！clientId: {}", clientId);
                            initialized = false;
                        }
                    } catch (MqttException e) {
                        LOG.error("MQTT重连失败 - clientId: {}, 错误代码: {}, 错误消息: {}", 
                                clientId, e.getReasonCode(), e.getMessage(), e);
                        // 记录详细的异常信息，帮助诊断问题
                        if (e.getReasonCode() == MqttException.REASON_CODE_CLIENT_EXCEPTION) {
                            LOG.error("重连失败原因: 客户端异常，可能是客户端ID冲突或其他客户端配置问题");
                        } else if (e.getReasonCode() == MqttException.REASON_CODE_NOT_AUTHORIZED) {
                            LOG.error("重连失败原因: 认证失败，请检查用户名和密码");
                        } else if (e.getReasonCode() == MqttException.REASON_CODE_CONNECTION_LOST) {
                            LOG.error("重连失败原因: 连接丢失，可能是网络问题或服务器主动断开");
                        }
                        initialized = false;
                    }
                } else {
                    LOG.warn("mqttConnectOptions is null，重新初始化");
                    initialized = false;
                    init();
                }
            } else {
                LOG.info("mqttClient 已连接");
            }
        } else {
            LOG.info("mqttClient为null，重新初始化");
            initialized = false;
            init();
        }
    }
    
    /**
     * 启动重连进程
     */
    private void startReconnectProcess() {
        if (reconnecting) {
            LOG.debug("重连进程已在运行，跳过重复启动");
            return;
        }
        
        reconnecting = true;
        reconnectAttempts = 0;
        
        // 在单独线程中执行重连
        Thread reconnectThread = new Thread(() -> {
            LOG.info("开始MQTT重连进程...");
            
            while (reconnecting && reconnectAttempts < MAX_RECONNECT_ATTEMPTS) {
                try {
                    // 检查是否已经连接
                    if (mqttClient != null && mqttClient.isConnected()) {
                        LOG.info("MQTT连接已恢复，停止重连进程");
                        reconnecting = false;
                        reconnectAttempts = 0;
                        return;
                    }
                    
                    // 等待重连间隔
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastReconnectTime < RECONNECT_INTERVAL) {
                        Thread.sleep(RECONNECT_INTERVAL - (currentTime - lastReconnectTime));
                    }
                    
                    reconnectAttempts++;
                    lastReconnectTime = System.currentTimeMillis();
                    
                    LOG.info("第 {} 次重连尝试...", reconnectAttempts);
                    
                    // 尝试重连
                    reConnect();
                    
                    // 检查重连是否成功
                    if (mqttClient != null && mqttClient.isConnected()) {
                        LOG.info("MQTT重连成功，停止重连进程");
                        reconnecting = false;
                        reconnectAttempts = 0;
                        
                        // 重连成功后重新订阅主题
                        if (needResubscribe) {
                            LOG.info("重连成功，开始重新订阅主题...");
                            resubscribeAllTopics();
                        }
                        return;
                    }
                    
                } catch (InterruptedException e) {
                    LOG.warn("重连线程被中断: " + e.getMessage());
                    reconnecting = false;
                    break;
                } catch (Exception e) {
                    LOG.error("重连过程中发生异常: " + e.getMessage(), e);
                }
            }
            
            if (reconnectAttempts >= MAX_RECONNECT_ATTEMPTS) {
                LOG.error("达到最大重连次数 ({}), 停止重连进程", MAX_RECONNECT_ATTEMPTS);
                reconnecting = false;
            }
        });
        
        reconnectThread.setName("MQTT-Reconnect-Thread");
        reconnectThread.setDaemon(true);
        reconnectThread.start();
    }
    
    /**
     * 停止重连进程
     */
    public void stopReconnectProcess() {
        LOG.info("停止MQTT重连进程");
        reconnecting = false;
        reconnectAttempts = 0;
    }
    
    /**
     * 重新订阅所有主题
     */
    public void resubscribeAllTopics() {
        if (redisTstaticemplate == null) {
            LOG.warn("Redis模板未初始化，无法重新订阅主题");
            return;
        }
        
        try {
            LOG.info("开始重新订阅所有主题...");
            
            @SuppressWarnings("unchecked")
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTstaticemplate.opsForValue().get(RedisCode.SBBHTOPICSBMC);
            if (sbbhEquipMap == null || sbbhEquipMap.isEmpty()) {
                LOG.info("Redis中未找到设备配置，跳过重新订阅主题");
                return;
            }
            
            int subscribeCount = 0;
            for (String key : sbbhEquipMap.keySet()) {
                JSONObject item = sbbhEquipMap.get(key);
                String jdfw = item.getString("jdfw");
                if (jdfw != null && !jdfw.isEmpty()) {
                    String[] topicArr = jdfw.split(";");
                    for (String topicInfo : topicArr) {
                        if (topicInfo.contains(",")) {
                            String[] parts = topicInfo.split(",");
                            if (parts.length >= 2) {
                                String topicName = parts[1];
                                try {
                                    subTopic(topicName);
                                    subscribeCount++;
                                    LOG.info("重新订阅主题成功: {}", topicName);
                                } catch (Exception e) {
                                    LOG.error("重新订阅主题失败: {}", topicName, e);
                                }
                            }
                        }
                    }
                }
            }
            
            LOG.info("重新订阅完成，共订阅了 {} 个主题", subscribeCount);
            needResubscribe = false;
            
        } catch (Exception e) {
            LOG.error("重新订阅主题时发生异常", e);
        }
    }

    public void close() {
        // 停止重连进程
        stopReconnectProcess();
        
        if (mqttClient != null) {
            try {
                mqttClient.disconnect();
                mqttClient.close();
                LOG.info("MQTT连接已关闭");
            } catch (MqttException e) {
                LOG.error("关闭MQTT连接失败", e);
            }
        }
        initialized = false;
    }

    /**
     * savePlayData记录播放
     */
    private void savePlayData(String topic){
        try {
            voicePowerDeviceServiceStatic.updateIsPlay(topic.substring(0,topic.indexOf("[")));
            LOG.error("当前发声设备播放-主题：{}",topic);
        } catch (Exception e) {
            LOG.error("记录PLAY数据时发生错误: " + e.getMessage(), e);
        }
    }

    /**
     * 解析GNRMC GPS数据
     * GNRMC格式: $GNRMC,时间,状态,纬度,纬度方向,经度,经度方向,速度,航向,日期,磁偏角,磁偏角方向,模式*校验和
     * 例如: $GNRMC,123519.00,A,4807.038,N,01131.000,E,022.4,,230394,,W*6A
     *      $GNRMC,081805.00,A,3027.380,N,11418.938,E,0.032,,161025,,,A,V*19
     */
    private void parseGNRMCData(String topic,String messageContent) {
        try {
            // 查找$GNRMC行
            String[] lines = messageContent.split("\n");
            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("$GNRMC") && line.contains(",A,")) {
                    LOG.info("找到有效的GNRMC数据: " + line);
                    
                    // 解析GNRMC数据
                    String[] fields = line.split(",");
                    if (fields.length >= 12) {
                        String time = fields[1];           // UTC时间
                        String status = fields[2];         // 状态 (A=有效, V=无效)
                        String latitude = fields[3];       // 纬度
                        String latDirection = fields[4];   // 纬度方向 (N/S)
                        String longitude = fields[5];      // 经度
                        String lonDirection = fields[6];   // 经度方向 (E/W)
                        String speed = fields[7];          // 速度 (节)
                        String course = fields[8];         // 航向
                        String date = fields[9];           // 日期
                        
                        // 转换坐标格式
                        double latDecimal = convertToDecimalDegrees(latitude, latDirection);
                        double lonDecimal = convertToDecimalDegrees(longitude, lonDirection);
                        
                        // 转换速度 (节转公里/小时)
                        double speedKmh = Double.parseDouble(speed) * 1.852;
                        
                        LOG.info("GPS数据解析结果:");
                        LOG.info("  时间: " + time);
                        LOG.info("  状态: " + status);
                        LOG.info("  纬度: " + latDecimal + "°");
                        LOG.info("  经度: " + lonDecimal + "°");
                        LOG.info("  速度: " + String.format("%.2f", speedKmh) + " km/h");
                        LOG.info("  航向: " + course + "°");
                        LOG.info("  日期: " + date);
                        
                        // 这里可以添加业务逻辑，比如保存到数据库或发送到其他服务
                        processGPSData(topic, time, status, latDecimal, lonDecimal, speedKmh, course, date);
                    } else {
                        LOG.warn("GNRMC数据字段不完整，字段数量: " + fields.length);
                    }
                    break; // 找到第一个有效的GNRMC数据就退出
                }
            }
        } catch (Exception e) {
            LOG.error("解析GNRMC数据时发生错误: " + e.getMessage(), e);
        }
    }
    
    /**
     * 将度分秒格式转换为十进制度格式
     * GPS NMEA格式说明：
     * - 纬度格式: DDMM.SSSS (度+分.秒)
     * - 经度格式: DDDMM.SSSS (度+分.秒)
     * 例如: 3027.380 -> 30度27分380秒 -> 30 + 27/60 + 380/3600 = 30.4611度
     *      11418.938 -> 114度18分938秒 -> 114 + 18/60 + 938/3600 = 114.3161度
     */
    private double convertToDecimalDegrees(String coordinate, String direction) {
        try {
            double coord = Double.parseDouble(coordinate);
            
            // 分离度、分、秒
            int degrees = (int) (coord / 100);
            double minutesAndSeconds = coord - (degrees * 100);
            
            // 分离分和秒
            int minutes = (int) minutesAndSeconds;
            double seconds = (minutesAndSeconds - minutes) * 100; // 将小数部分转换为秒
            
            // 转换为十进制度: 度 + 分/60 + 秒/3600
            double decimal = degrees + (minutes / 60.0) + (seconds / 3600.0);
            
            // 南纬和西经为负值
            if ("S".equals(direction) || "W".equals(direction)) {
                decimal = -decimal;
            }
            
            // 保留6位小数
            decimal = Math.round(decimal * 1000000.0) / 1000000.0;
            
            LOG.debug("坐标转换: {} {} -> {}度 (度: {}, 分: {}, 秒: {})", 
                     coordinate, direction, decimal, degrees, minutes, seconds);
            return decimal;
        } catch (NumberFormatException e) {
            LOG.error("坐标格式转换失败: " + coordinate, e);
            return 0.0;
        }
    }
    
    /**
     * 处理解析后的GPS数据
     * 可以在这里添加具体的业务逻辑
     */
    private void processGPSData(String topic, String time, String status, double latitude, double longitude,
                               double speed, String course, String date) {
        try {
            LOG.info("开始处理GPS数据...");
            
            // 检查Redis模板是否可用
            if (redisTstaticemplate == null) {
                LOG.warn("Redis模板未初始化，跳过GPS数据处理");
                return;
            }

            // 检查数据库映射器是否可用
            if (waterEquiplogMapperStatic == null) {
                LOG.warn("数据库映射器未初始化，跳过GPS数据处理");
                return;
            }

            String sbbh = topicToSbbh(topic);
            // 获取设备信息
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTstaticemplate.opsForValue().get(RedisCode.SBBHEQUIPMAP);
            if (sbbhEquipMap == null || sbbhEquipMap.isEmpty()) {
                LOG.warn("Redis中未找到设备配置信息，跳过GPS数据处理");
                return;
            }
            
            String equipKey = "equip-" + sbbh;
            JSONObject equipJson = sbbhEquipMap.get(equipKey);
            if (equipJson == null) {
                LOG.warn("未找到设备 {} 的配置信息，跳过GPS数据处理", sbbh);
                return;
            }
            
            // 安全地转换设备信息
            WaterEquipment waterEquipment = JSONObject.toJavaObject(equipJson, WaterEquipment.class);
            if (waterEquipment == null) {
                LOG.warn("设备 {} 的配置信息转换失败，跳过GPS数据处理", sbbh);
                return;
            }
            
            // 创建日志记录
            WaterEquiplog record = new WaterEquiplog();
            record.setSbbh(sbbh);
            record.setCode("1");
            record.setMsg(longitude + "," + latitude);
            record.setReqmsg("{\"sbbh\":" + sbbh + ",\"msg\":" + record.getMsg() + ",\"code\":\"1\"}");
            record.setCjsj(new Date());
            record.setRespmsg("保存成功");
            record.setSm1(waterEquipment.getSbcj());
            record.setSm2(waterEquipment.getSbmc());
            
            // 保存到数据库
            waterEquiplogMapperStatic.updateBySbbhSelective(record);
            LOG.info("GPS数据处理完成，设备编号: {}, 坐标: {},{}", sbbh, latitude, longitude);
            
        } catch (Exception e) {
            LOG.error("处理GPS数据时发生异常，主题: " + topic, e);
            // 不重新抛出异常，避免影响MQTT连接
        }
    }

    public String topicToSbbh(String topic){
        Map<String,String> topicSbbhMap = (Map<String, String>) redisTstaticemplate.opsForValue().get(RedisCode.TOPICSBBH);
        if (topicSbbhMap != null) {
            for(String key : topicSbbhMap.keySet()){
                if(key.contains(topic)){
                    return topicSbbhMap.get(key);
                }
            }
        } else {
            LOG.warn("未找到主题 {} 对应的设备编号", topic);
        }
        return "";
    }
    
    /**
     * 检查MQTT客户端状态
     */
    public boolean isHealthy() {
        return mqttClient != null && mqttClient.isConnected() && initialized;
    }
    
    /**
     * 检查是否正在重连
     */
    public boolean isReconnecting() {
        return reconnecting;
    }
    
    /**
     * 获取重连状态信息
     */
    public String getReconnectStatus() {
        if (reconnecting) {
            return String.format("正在重连中 (第%d/%d次尝试)", reconnectAttempts, MAX_RECONNECT_ATTEMPTS);
        } else if (reconnectAttempts >= MAX_RECONNECT_ATTEMPTS) {
            return "重连失败，已达到最大重连次数";
        } else {
            return "未在重连";
        }
    }
    
    /**
     * 检查静态变量初始化状态
     */
    public static String getStaticInitStatus() {
        return String.format("Redis模板: %s, 数据库映射器: %s", 
                redisTstaticemplate != null ? "已初始化" : "未初始化",
                waterEquiplogMapperStatic != null ? "已初始化" : "未初始化");
    }

    /**
     * 获取连接状态信息
     */
    public String getConnectionStatus() {
        if (mqttClient == null) {
            return "MQTT客户端未初始化";
        }
        if (!mqttClient.isConnected()) {
            String status = "MQTT客户端未连接";
            if (reconnecting) {
                status += " - " + getReconnectStatus();
            }
            return status;
        }
        if (!initialized) {
            return "MQTT客户端未完成初始化";
        }
        return "MQTT客户端正常连接";
    }

    // 保留静态工具方法
    public static String encodeToString(byte[] bytes, String type) {
        if (type.equals("temperature")) {
            byte[] bytes1 = new byte[2];
            bytes1[0] = bytes[3];
            bytes1[1] = bytes[4];
            int wd = bytesToInt(bytes1);
            return wd / 10 + "." + wd % 10;
        } else {
            String orignData = bytes2hex02(bytes);
            String byteLeng = orignData.substring(4, 6);
            int arrLeng = 0;
            if (byteLeng.startsWith("0")) {
                arrLeng = Integer.parseInt(byteLeng.substring(1));
            } else {
                arrLeng = Integer.parseInt(byteLeng);
            }
            byte[] bytes1 = new byte[arrLeng];
            for (int i = 0; i < arrLeng; i++) {
                bytes1[i] = bytes[3 + i];
            }
            return bytesToFloat(bytes1) + "";
        }
    }
    public static int bytesToInt(byte[] value) {
        int ret = 0;
        for (int i = 0; i < value.length; i++) {
            ret += (value[value.length - i - 1] & 0xFF) << (i * 8);
        }
        return ret;
    }
    public static float bytesToFloat(byte[] bytes) {
        int ret = (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
        return Float.intBitsToFloat(ret);
    }
    public static String bytes2hex02(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String tmp = null;
        for (byte b : bytes) {
            tmp = Integer.toHexString(0xFF & b);
            if (tmp.length() == 1) {
                tmp = "0" + tmp;
            }
            sb.append(tmp);
        }
        return sb.toString();
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}

