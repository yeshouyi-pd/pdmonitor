package com.pd.server.config;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MqttClientSpace implements ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(MqttClientSpace.class);
    private static ApplicationContext applicationContext;
    private static volatile MqttClientSpace instance = null;

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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (MqttClientSpace.applicationContext == null) {
            MqttClientSpace.applicationContext = applicationContext;
        }
    }

    private void init() {
        if (initialized) return;
        mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(userName);
        mqttConnectOptions.setPassword(passWord.toCharArray());
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setConnectionTimeout(0);
        memoryPersistence = new MemoryPersistence();
        try {
            mqttClient = new org.eclipse.paho.client.mqttv3.MqttClient(brokerUrl, clientId, memoryPersistence);
            if (!mqttClient.isConnected()) {
                //LOG.info("创建连接: " + mqttClient.isConnected());
                mqttClient.connect(mqttConnectOptions);
            }
            // 注册回调，确保收到消息时自动调用 messageArrived 方法
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    LOG.warn("connectionLost:" + cause.getMessage());
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
        } catch (MqttException e) {
            LOG.error("MQTT初始化失败", e);
        }
    }

    public void subTopic(String topicName) {
        if (mqttClient != null && mqttClient.isConnected()) {
            try {
                mqttClient.subscribe(topicName, 2);
            } catch (MqttException e) {
                LOG.error("订阅主题失败", e);
            }
        } else {
            LOG.warn("mqttClient is error");
        }
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        LOG.info("topic:" + topic+";message:"+new String(message.getPayload(), "UTF-8"));
//        byte[] payload = message.getPayload();
//        if (payload.length >= 3) { // 至少3字节
//            // 解析设备ID（uint16，假设为大端序）
//            int deviceId = ((payload[0] & 0xFF) << 8) | (payload[1] & 0xFF);
//            // 解析状态位（uint8）
//            int status = payload[2] & 0xFF;
//
//            if (status == 1) {
//                LOG.info("设备ID: " + deviceId + " 命令接收正确");
//            } else {
//                LOG.warn("设备ID: " + deviceId + " 命令接收失败，状态位: " + status);
//            }
//        } else {
//            LOG.error("应答帧长度不足，无法解析");
//        }
        // 你可以在这里加上业务处理逻辑
        //return "topic:" + topic+";message:"+new String(message.getPayload(), "UTF-8");
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
                        mqttClient.connect(mqttConnectOptions);
                    } catch (MqttException e) {
                        LOG.error("重连失败", e);
                    }
                } else {
                    LOG.warn("mqttConnectOptions is null");
                }
            } else {
                LOG.info("mqttClient 已连接");
            }
        } else {
            init();
        }
    }

    public void close() {
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

    // main方法建议移除或仅做简单演示
    public static void main(String[] args) throws InterruptedException {
        try {
            MqttClientSpace testXH = MqttClientSpace.getInstance("tcp://47.244.23.44", "subClient", "benben", "123456");
            testXH.type = "temperature";
            byte[] message = {0x01, 0x04, 0x00, 0x00, 0x00, 0x01, 0x31, (byte) 0xCA};
            testXH.publishMessage("WHPD[meg]", message, 2);
            Thread.sleep(20000);

            testXH.type = "waterDepth";
            byte[] message2 = {0x34, 0x03, 0x00, 0x15, 0x00, 0x02, (byte) 0xD0, (byte) 0x6A};
            testXH.publishMessage("WHPD[meg]", message2, 2);
            Thread.sleep(20000);

            testXH.type = "waterVelocity";
            byte[] message3 = {0x34, 0x03, 0x00, 0x0F, 0x00, 0x02, (byte) 0xF1, (byte) 0xAD};
            testXH.publishMessage("WHPD[meg]", message3, 2);
            Thread.sleep(20000);

            testXH.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

