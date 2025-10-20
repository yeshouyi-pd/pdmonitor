package com.pd.monitor.config;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.MqttClientSpace;
import com.pd.server.config.RedisCode;
import com.pd.server.main.mapper.WaterEquiplogMapper;
import com.pd.server.main.service.WaterEquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * MQTT初始化器 - 在monitor.jar中独立初始化MQTT客户端
 * 使用独立的包路径避免与system.jar冲突
 */
@Component
@Order(1) // 确保在RedisConfig之后执行
public class MqttInitializer implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MqttInitializer.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private WaterEquiplogMapper waterEquiplogMapper;

    @Override
    public void run(String... args) throws Exception {
        LOG.info(">>>>>>>>>>>>>>Monitor模块开始初始化MQTT客户端<<<<<<<<<<<<<");
        boolean mqttInitResult = initMqttClient();
        if (mqttInitResult) {
            LOG.info("Monitor模块MQTT客户端初始化成功");
        } else {
            LOG.warn("Monitor模块MQTT客户端初始化失败，但程序继续启动");
        }
        LOG.info(">>>>>>>>>>>>>>>Monitor模块MQTT客户端初始化完成 <<<<<<<<<<<<<");
    }

    /**
     * 初始化MQTT客户端
     */
    public boolean initMqttClient() {
        try {
            // 检查是否已经初始化过
            MqttClientSpace existingClient = MqttClientSpace.getInstance();
            if (existingClient != null) {
                LOG.info("MQTT客户端已经存在，跳过初始化 - 状态: {}", existingClient.getConnectionStatus());
                return true;
            }
            
            // 等待Redis配置加载完成
            Thread.sleep(3000);
            
            @SuppressWarnings("unchecked")
            Map<String, String> mapAttr = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
            if (mapAttr == null || mapAttr.isEmpty()) {
                LOG.warn("Redis中未找到MQTT配置参数，跳过MQTT客户端初始化");
                return false;
            }

            String mqttBrokerUrl = mapAttr.get("mqttBrokerUrl");
            String mqttClientId = mapAttr.get("mqttClientId");
            String mqttUserName = mapAttr.get("mqttUserName");
            String mqttPassWord = mapAttr.get("mqttPassWord");
            
            if (StringUtils.isEmpty(mqttBrokerUrl) || StringUtils.isEmpty(mqttClientId) || 
                StringUtils.isEmpty(mqttUserName) || StringUtils.isEmpty(mqttPassWord)) {
                LOG.warn("MQTT配置参数不完整，跳过MQTT客户端初始化 - brokerUrl: {}, clientId: {}, userName: {}, passWord: {}", 
                         mqttBrokerUrl, mqttClientId, mqttUserName, mqttPassWord != null ? "***" : "null");
                return false;
            }
            
            // 初始化MQTT客户端（带依赖注入）
            MqttClientSpace.initializeMqttClient(mqttBrokerUrl, mqttClientId, mqttUserName, mqttPassWord, 
                                               redisTemplate, waterEquiplogMapper); // WaterEquiplogMapper在server模块中，这里先传null
            
            // 验证初始化结果
            MqttClientSpace client = MqttClientSpace.getInstance();
            if (client != null) {
                LOG.info("MQTT客户端初始化成功 - 状态: {}", client.getConnectionStatus());
                // 订阅主题
                subscribeTopics(client);
                return true;
            } else {
                LOG.error("MQTT客户端初始化失败");
                return false;
            }
            
        } catch (Exception e) {
            LOG.error("初始化MQTT客户端失败", e);
            return false;
        }
    }
    
    /**
     * 订阅主题
     */
    private void subscribeTopics(MqttClientSpace client) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTemplate.opsForValue().get(RedisCode.SBBHTOPICSBMC);
            if (sbbhEquipMap == null || sbbhEquipMap.isEmpty()) {
                LOG.info("Redis中未找到设备配置，跳过订阅主题");
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
                                    LOG.info("开始订阅主题: {}", topicName);
                                    client.subTopic(topicName);
                                    subscribeCount++;
                                } catch (Exception e) {
                                    LOG.error("订阅主题失败: {}", topicName, e);
                                }
                            }
                        }
                    }
                }
            }
            
            LOG.info("主题订阅完成，共订阅了 {} 个主题", subscribeCount);
            
        } catch (Exception e) {
            LOG.error("订阅主题时发生异常", e);
        }
    }
}
