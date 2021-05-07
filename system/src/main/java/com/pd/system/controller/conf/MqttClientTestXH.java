package com.pd.system.controller.conf;

import com.pd.server.config.SpringUtil;
import com.pd.server.main.dto.WaterQualityResultDto;
import com.pd.server.main.service.WaterQualityResultService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Date;

public class MqttClientTestXH implements ApplicationContextAware {
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
            mqttConnectOptions.setConnectionTimeout(30);
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
                System.out.println("memoryPersistence对象为空");
            }
        }else {
            System.out.println("mqttConnectOptions对象为空");
        }
        //设置连接和回调
        if(null != mqttClient) {
            if(!mqttClient.isConnected()) {
                try {
                    System.out.println("创建连接:" + mqttClient.isConnected());
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
            System.out.println("mqttClient为空");
        }
    }

    //	订阅主题
    public static void subTopic(String topic) {
        if(null != mqttClient&& mqttClient.isConnected()) {
            try {
                mqttClient.subscribe(topic, 1);
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            System.out.println("mqttClient is error");
        }
    }

    public static void messageCallBack(){
        // 设置回调函数
        mqttClient.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) {
                System.out.println("connectionLost:"+cause.getMessage());
            }
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("topic:"+topic);
                System.out.println("Qos:"+message.getQos());
                System.out.println("message content:"+encodeToString( message.getPayload(),type));
                System.out.println(bytes2hex02(message.getPayload()));
                saveData(message);
            }
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete---------"+ token.isComplete());
            }
        });
    }

    public static void saveData(MqttMessage message){
        WaterQualityResultDto waterQualityResult = new WaterQualityResultDto();
        waterQualityResult.setIp(bytes2hex02(message.getPayload()).substring(0,4));
        waterQualityResult.setDatacenter("tcp://47.244.23.44");
        waterQualityResult.setJcxm(type);
        waterQualityResult.setDataResult(encodeToString(message.getPayload(),type));
        waterQualityResult.setDataOriginal(bytes2hex02(message.getPayload()));
        waterQualityResult.setCreateTime(new Date());
        System.out.println(waterQualityResult.toString());
        WaterQualityResultService service = SpringUtil.getBean(WaterQualityResultService.class);
        System.out.println(service);
        service.save(waterQualityResult);
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
                        System.out.println("消息发送成功");
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
                    System.out.println("mqttConnectOptions is null");
                }
            }else {
                System.out.println("mqttClient is null or connect");
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
            testXH.publishMessage(message, 1);
            Thread.sleep(20000);

            MqttClientTestXH testXH1 = new MqttClientTestXH();
            testXH1.type="waterDepth";
            byte[] message2 = {0x34,0x03,0x00,0x15,0x00,0x02, (byte) 0xD0, (byte) 0x6A};
            testXH1.publishMessage(message2, 1);
            Thread.sleep(20000);

            MqttClientTestXH testXH2 = new MqttClientTestXH();
            testXH2.type="waterVelocity";
            byte[] message3 = {0x34,0x03,0x00,0x0F,0x00,0x02, (byte) 0xF1, (byte) 0xAD};
            testXH2.publishMessage(message3, 1);
            Thread.sleep(20000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
