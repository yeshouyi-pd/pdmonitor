package com.pd.system.controller.conf;

import com.pd.server.config.RedisCode;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.dto.WaterQualityResultDto;
import com.pd.server.main.service.WaterQualityResultService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.Map;

public class MqttClientTestXH implements ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(MqttClientTestXH.class);

    private static ApplicationContext applicationContext;
    public static org.eclipse.paho.client.mqttv3.MqttClient mqttClient = null;
    private static MemoryPersistence memoryPersistence = null;
    private static MqttConnectOptions mqttConnectOptions = null;

    private static MqttClientTestXH instance = null;

    private static MqttTopic topic = null;

    public static String type="";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(MqttClientTestXH.applicationContext == null) {
            MqttClientTestXH.applicationContext = applicationContext;
        }
    }

    public static MqttClientTestXH getInstance() throws Exception {
        if (instance == null) {
            synchronized (MqttClientTestXH.class) {
                if (instance == null) {
                    instance = new MqttClientTestXH();
                }
            }
        }
        return instance;
    }

    public MqttClientTestXH(){
        init("subClient","benben","123456");
    }

    public static void init(String clientId,String userName,String passWord) {
        //初始化连接设置对象
        mqttConnectOptions = new MqttConnectOptions();
        // 设置连接的用户名
        mqttConnectOptions.setUserName(userName);
        // 设置连接的密码
        mqttConnectOptions.setPassword(passWord.toCharArray());
        //初始化MqttClient
        if(null != mqttConnectOptions) {
//			true可以安全地使用内存持久性作为客户端断开连接时清除的所有状态
            mqttConnectOptions.setCleanSession(true);
//			设置连接超时
            mqttConnectOptions.setConnectionTimeout(0);
//            mqttConnectOptions.setKeepAliveInterval(20);
//            //设置断开后重新连接
//            mqttConnectOptions.setAutomaticReconnect(true);
//			设置持久化方式
            memoryPersistence = new MemoryPersistence();
            if(null != memoryPersistence && null != clientId) {
                try {
                    mqttClient = new org.eclipse.paho.client.mqttv3.MqttClient("tcp://47.244.23.44", clientId,memoryPersistence);
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else {
                LOG.info("memoryPersistence对象为空");
            }
        }else {
            LOG.info("mqttConnectOptions对象为空");
        }
        //设置连接和回调
        if(null != mqttClient) {
            if(!mqttClient.isConnected()) {
                try {
                    LOG.info("创建连接:" + mqttClient.isConnected());
                    mqttClient.connect(mqttConnectOptions);
                    //订阅消息
                    subTopic("test2");
                    messageCallBack();
                    topic = mqttClient.getTopic("test1");
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }else {
            LOG.info("mqttClient为空");
        }
    }

    //	订阅主题
    public static void subTopic(String topic) {
        if(null != mqttClient&& mqttClient.isConnected()) {
            try {
                mqttClient.subscribe(topic, 2);
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            LOG.info("mqttClient is error");
        }
    }

    public static void messageCallBack(){
        // 设置回调函数
        mqttClient.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) {
                LOG.info("connectionLost:"+cause.getMessage());
            }
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                LOG.info("topic:"+topic);
                LOG.info("Qos:"+message.getQos());
                byte[] payload = message.getPayload();
                String ip = bytes2hex02(payload).substring(0,4);
                String dataResult = encodeToString(payload,type);
                String dataOriginal = bytes2hex02(payload);
                LOG.info("message content:"+dataResult);
                LOG.info(dataOriginal);
                saveData(ip,dataResult,dataOriginal);
            }
            public void deliveryComplete(IMqttDeliveryToken token) {
                LOG.info("deliveryComplete---------"+ token.isComplete());
            }
        });
    }

    public static void saveData(String ip,String dataResult,String dataOriginal){
        try{
            LOG.info("进来了");
            Map<String,String> map = (Map<String, String>) RedisConfig.redisTstaticemplate.opsForValue().get(RedisCode.SBSNCENTERCODE);
            WaterQualityResultDto waterQualityResult = new WaterQualityResultDto();
            waterQualityResult.setIp(ip);
            waterQualityResult.setDatacenter("tcp://47.244.23.44");
            waterQualityResult.setJcxm(type);
            waterQualityResult.setDataResult(dataResult);
            waterQualityResult.setDataOriginal(dataOriginal);
            waterQualityResult.setCreateTime(new Date());
            waterQualityResult.setSm1(map.get(waterQualityResult.getIp()));
            LOG.info(waterQualityResult.toString());
            WaterQualityResultService service = SpringUtil.getBean(WaterQualityResultService.class);
            service.save(waterQualityResult);
        }catch (Exception e){
            LOG.info(e.getMessage());
        }
    }

    //	发布消息
    public static void publishMessage(byte[] message,int qos) {
        if(null != mqttClient&& mqttClient.isConnected()) {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(qos);
            mqttMessage.setPayload(message);
            if(null != topic) {
                try {
                    MqttDeliveryToken publish = topic.publish(mqttMessage);
                    if(!publish.isComplete()){
                        publish.waitForCompletion();
                        LOG.info("消息发送成功");
                    }
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }else {
            reConnect();
        }
    }
    public static String encodeToString(byte[] bytes, String type) {
        if(type.equals("temperature")){
            byte[] bytes1 = new byte[2];
            bytes1[0] = bytes[3];
            bytes1[1] = bytes[4];
            int wd = bytesToInt(bytes1);
            return wd/10+"."+wd%10;
        }else {
            String orignData = bytes2hex02(bytes);
            String byteLeng = orignData.substring(4,6);
            int arrLeng = 0;
            if(byteLeng.startsWith("0")){
                arrLeng = Integer.parseInt(byteLeng.substring(1));
            }else{
                arrLeng = Integer.parseInt(byteLeng);
            }
            byte[] bytes1 = new byte[arrLeng];
            for(int i=0;i<arrLeng;i++){
                bytes1[i] = bytes[3+i];
            }
            return bytesToFloat(bytes1)+"";
        }
    }

    public static int bytesToInt(byte[] value){
        int ret = 0;
        for(int i=0;i<value.length;i++){
            ret += (value[value.length-i-1] & 0xFF) << (i*8);
        }
        return ret;
    }

    public static float bytesToFloat(byte[] bytes){
        int ret = (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
        return Float.intBitsToFloat(ret);
    }

    public static String bytes2hex02(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        String tmp = null;
        for (byte b : bytes)
        {
            // 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制
            tmp = Integer.toHexString(0xFF & b);
            if (tmp.length() == 1)// 每个字节8为，转为16进制标志，2个16进制位
            {
                tmp = "0" + tmp;
            }
            sb.append(tmp);
        }
        return sb.toString();
    }

    public static void reConnect() {
        if(null != mqttClient) {
            if(!mqttClient.isConnected()) {
                if(null != mqttConnectOptions) {
                    try {
                        mqttClient.connect(mqttConnectOptions);
                    } catch (MqttException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else {
                    LOG.info("mqttConnectOptions is null");
                }
            }else {
                LOG.info("mqttClient is null or connect");
            }
        }else {
            init("subClient","benben","123456");
        }

    }

    public static void main(String [] args) throws InterruptedException {
        try{
            MqttClientTestXH testXH = new MqttClientTestXH();
            testXH.type="temperature";
            byte[] message = {0x01,0x04,0x00,0x00,0x00,0x01,0x31, (byte) 0xCA};
            testXH.publishMessage(message, 2);
            Thread.sleep(20000);

            MqttClientTestXH testXH1 = new MqttClientTestXH();
            testXH1.type="waterDepth";
            byte[] message2 = {0x34,0x03,0x00,0x15,0x00,0x02, (byte) 0xD0, (byte) 0x6A};
            testXH1.publishMessage(message2, 2);
            Thread.sleep(20000);

            MqttClientTestXH testXH2 = new MqttClientTestXH();
            testXH2.type="waterVelocity";
            byte[] message3 = {0x34,0x03,0x00,0x0F,0x00,0x02, (byte) 0xF1, (byte) 0xAD};
            testXH2.publishMessage(message3, 2);
            Thread.sleep(20000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
