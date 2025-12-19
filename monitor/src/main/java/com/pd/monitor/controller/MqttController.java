package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.MqttClientSpace;
import com.pd.server.main.domain.VoicePowerDevice;
import com.pd.server.main.dto.ResponseDto;
import com.pd.monitor.config.MqttInitializer;
import com.pd.server.main.service.VoicePowerDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Timer;
import java.util.TimerTask;

/**
 * MQTT控制器 - 在monitor.jar中提供MQTT管理功能
 * 使用独立的包路径避免与system.jar冲突
 */
@RestController
@RequestMapping("/monitor/mqtt")
public class MqttController {

    private static final Logger LOG = LoggerFactory.getLogger(MqttController.class);

    @Resource
    private MqttInitializer mqttInitializer;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private VoicePowerDeviceService voicePowerDeviceService;

    /**
     * 发送播放指令
     */
    @PostMapping("/play")
    public ResponseDto<String> play(@RequestBody JSONObject jsonObject) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        try {
            String[] topicArr = jsonObject.getString("jdfw").split(";");//WHPD[meg],WHPD[updata];RPCD[meg],RPCD[updata]
            for(int i=0;i<topicArr.length;i++) {
                String oneitem = topicArr[i];
                String[] oneArr = oneitem.split(",");
                if (oneArr.length < 2) {
                    LOG.error("主题配置格式错误: {}", oneitem);
                    continue;
                }
                MqttClientSpace client = MqttClientSpace.getInstance();
                byte[] messageStart = hexStringToByteArray(jsonObject.getString("hex"));
                String topicName = oneArr[0].substring(0, oneArr[0].indexOf("["));
                VoicePowerDevice voicePowerDevice = voicePowerDeviceService.saveData(topicName);
                redisTemplate.opsForValue().set(topicName+"QLWJ", JSONObject.toJSONString(voicePowerDevice));
                client.publishMessage(oneArr[0], messageStart, 2);
                sendStop(oneArr[0]);
            }
            responseDto.setContent("播放指令已发送");
            responseDto.setSuccess(true);
        } catch (Exception e) {
            LOG.error("检查MQTT状态失败", e);
            responseDto.setContent("检查MQTT状态失败: " + e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }

    public void sendStop(String topic) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                MqttClientSpace client = MqttClientSpace.getInstance();
                byte[] messageStop = new byte[] { 0x43, 0x52, 0x44, 0x02, 0x00, 0x07, 0x10, 0x02, 0x4C };
                client.publishMessage(topic, messageStop, 2);
                String topicName = topic.substring(0,topic.indexOf("["));
                voicePowerDeviceService.updateStopTime(topicName);
            }
        };
        int delay = 600000; // 延迟时间，单位为毫秒
        timer.schedule(task, delay);
    }

    /**
     * 发送停止指令
     */
    @PostMapping("/stop")
    public ResponseDto<String> stop(@RequestBody JSONObject jsonObject) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        try {
            String[] topicArr = jsonObject.getString("jdfw").split(";");//WHPD[meg],WHPD[updata];RPCD[meg],RPCD[updata]
            for(int i=0;i<topicArr.length;i++) {
                String oneitem = topicArr[i];
                String[] oneArr = oneitem.split(",");
                if (oneArr.length < 2) {
                    LOG.error("主题配置格式错误: {}", oneitem);
                    continue;
                }
                MqttClientSpace client = MqttClientSpace.getInstance();
                byte[] messageStop = new byte[] { 0x43, 0x52, 0x44, 0x02, 0x00, 0x07, 0x10, 0x02, 0x4C };
                client.publishMessage(oneArr[0], messageStop, 2);
                String topicName = oneArr[0].substring(0,oneArr[0].indexOf("["));
                voicePowerDeviceService.updateStopTime(topicName);
            }
            responseDto.setContent("停止指令已发送");
            responseDto.setSuccess(true);
        } catch (Exception e) {
            LOG.error("检查MQTT状态失败", e);
            responseDto.setContent("检查MQTT状态失败: " + e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }

    public static byte[] hexStringToByteArray(String hex) {
        hex = hex.replaceAll("\\s+", ""); // 去掉空格
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }

    /**
     * 获取MQTT客户端状态
     */
    @GetMapping("/status")
    public ResponseDto<String> getMqttStatus() {
        ResponseDto<String> responseDto = new ResponseDto<>();
        try {
            MqttClientSpace client = MqttClientSpace.getInstance();
            if (client != null) {
                responseDto.setContent(client.getConnectionStatus());
                responseDto.setSuccess(true);
            } else {
                responseDto.setContent("MQTT客户端未初始化");
                responseDto.setSuccess(false);
            }
        } catch (Exception e) {
            LOG.error("检查MQTT状态失败", e);
            responseDto.setContent("检查MQTT状态失败: " + e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }

    /**
     * 重新初始化MQTT客户端
     */
    @PostMapping("/reinit")
    public ResponseDto<String> reinitMqtt() {
        ResponseDto<String> responseDto = new ResponseDto<>();
        try {
            // 先重置现有实例
            MqttClientSpace.resetInstance();
            
            // 然后重新初始化
            boolean success = mqttInitializer.initMqttClient();
            if (success) {
                MqttClientSpace client = MqttClientSpace.getInstance();
                String status = client != null ? client.getConnectionStatus() : "未知";
                responseDto.setContent("MQTT客户端重新初始化成功 - 状态: " + status);
                responseDto.setSuccess(true);
                LOG.info("MQTT客户端重新初始化成功，当前状态: {}", status);
            } else {
                responseDto.setContent("MQTT客户端重新初始化失败");
                responseDto.setSuccess(false);
                LOG.error("MQTT客户端重新初始化失败");
            }
        } catch (Exception e) {
            LOG.error("重新初始化MQTT客户端失败", e);
            responseDto.setContent("重新初始化MQTT客户端失败: " + e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }
    
    /**
     * 获取MQTT重连状态
     */
    @GetMapping("/reconnect-status")
    public ResponseDto<String> getReconnectStatus() {
        ResponseDto<String> responseDto = new ResponseDto<>();
        try {
            MqttClientSpace client = MqttClientSpace.getInstance();
            if (client != null) {
                String reconnectStatus = client.getReconnectStatus();
                boolean isReconnecting = client.isReconnecting();
                String content = String.format("重连状态: %s, 是否正在重连: %s", reconnectStatus, isReconnecting);
                responseDto.setContent(content);
                responseDto.setSuccess(true);
            } else {
                responseDto.setContent("MQTT客户端未初始化");
                responseDto.setSuccess(false);
            }
        } catch (Exception e) {
            LOG.error("获取MQTT重连状态失败", e);
            responseDto.setContent("获取MQTT重连状态失败: " + e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }
    
    /**
     * 手动触发重连
     */
    @PostMapping("/reconnect")
    public ResponseDto<String> manualReconnect() {
        ResponseDto<String> responseDto = new ResponseDto<>();
        try {
            MqttClientSpace client = MqttClientSpace.getInstance();
            if (client != null) {
                // 停止当前重连进程
                client.stopReconnectProcess();
                
                // 启动新的重连进程
                client.reConnect();
                
                responseDto.setContent("手动重连已触发 - 状态: " + client.getConnectionStatus());
                responseDto.setSuccess(true);
                LOG.info("手动触发MQTT重连");
            } else {
                responseDto.setContent("MQTT客户端未初始化，无法重连");
                responseDto.setSuccess(false);
            }
        } catch (Exception e) {
            LOG.error("手动触发重连失败", e);
            responseDto.setContent("手动触发重连失败: " + e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }
    
    /**
     * 手动重新订阅所有主题
     */
    @PostMapping("/resubscribe")
    public ResponseDto<String> manualResubscribe() {
        ResponseDto<String> responseDto = new ResponseDto<>();
        try {
            MqttClientSpace client = MqttClientSpace.getInstance();
            if (client != null) {
                if (client.isHealthy()) {
                    client.resubscribeAllTopics();
                    responseDto.setContent("手动重新订阅主题完成 - 状态: " + client.getConnectionStatus());
                    responseDto.setSuccess(true);
                    LOG.info("手动重新订阅主题完成");
                } else {
                    responseDto.setContent("MQTT客户端未连接，无法重新订阅主题 - 状态: " + client.getConnectionStatus());
                    responseDto.setSuccess(false);
                }
            } else {
                responseDto.setContent("MQTT客户端未初始化，无法重新订阅主题");
                responseDto.setSuccess(false);
            }
        } catch (Exception e) {
            LOG.error("手动重新订阅主题失败", e);
            responseDto.setContent("手动重新订阅主题失败: " + e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }
    
    /**
     * 检查静态变量初始化状态
     */
    @GetMapping("/static-status")
    public ResponseDto<String> getStaticStatus() {
        ResponseDto<String> responseDto = new ResponseDto<>();
        try {
            String status = MqttClientSpace.getStaticInitStatus();
            responseDto.setContent("静态变量状态: " + status);
            responseDto.setSuccess(true);
            LOG.info("静态变量状态检查: {}", status);
        } catch (Exception e) {
            LOG.error("检查静态变量状态失败", e);
            responseDto.setContent("检查静态变量状态失败: " + e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }
    
    /**
     * 手动初始化静态变量
     */
    @PostMapping("/init-static")
    public ResponseDto<String> initStaticVars() {
        ResponseDto<String> responseDto = new ResponseDto<>();
        try {
            // 这里需要从Spring容器中获取Bean
            // 由于MqttController在monitor模块，而MqttClientSpace在server模块
            // 我们需要通过其他方式获取这些Bean
            responseDto.setContent("手动初始化静态变量功能需要进一步实现");
            responseDto.setSuccess(false);
        } catch (Exception e) {
            LOG.error("手动初始化静态变量失败", e);
            responseDto.setContent("手动初始化静态变量失败: " + e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }

}
