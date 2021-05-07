package com.pd.system.controller.conf;

import com.pd.server.config.SpringUtil;
import com.pd.server.main.dto.WaterQualityResultDto;
import com.pd.server.main.service.WaterQualityResultService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.eclipse.paho.client.mqttv3.MqttClient;
import java.util.Date;

@Component
public class SubscribeSampleController implements ApplicationContextAware {

//    @Scheduled(cron = "0 0/1 * * * ? ")
//    public static void loop() {
//        System.out.println("------------test-------------");
//    }
private static ApplicationContext applicationContext;
    private static boolean flag=false;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SubscribeSampleController.applicationContext == null) {
            SubscribeSampleController.applicationContext = applicationContext;
        }
    }

    public static int encodeToString(byte[] bytes) {
        byte[] bytes1 = new byte[2];
        bytes1[0] = bytes[3];
        bytes1[1] = bytes[4];
        return bytesToInt(bytes1);
    }

    public static int bytesToInt(byte[] value){
        int ret = 0;
        for(int i=0;i<value.length;i++){
            ret += (value[value.length-i-1] & 0xFF) << (i*8);
        }
        return ret;
    }

    public static void subscribeSample(){
        String HOST = "tcp://47.244.23.44";
        String TOPIC = "test2";
        int qos = 2;
        String clientid = "subClient";
        String userName = "benben";
        String passWord = "123456";
        try {
            // host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            MqttClient client = new MqttClient(HOST, clientid, new MemoryPersistence());
            // MQTT的连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(userName);
            // 设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调函数
            client.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost:"+cause.getMessage());
                }

                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("topic:"+topic);
                    System.out.println("Qos:"+message.getQos());
                    System.out.println("message content:"+encodeToString( message.getPayload()));
                    System.out.println(bytes2hex02(message.getPayload()));
                    int wd = encodeToString(message.getPayload());
                    WaterQualityResultDto waterQualityResult = new WaterQualityResultDto();
                    waterQualityResult.setIp(TOPIC);
                    waterQualityResult.setDatacenter(HOST);
                    waterQualityResult.setJcxm("temperature");
                    waterQualityResult.setDataResult(wd/10+"."+wd%10);
                    waterQualityResult.setDataOriginal(bytes2hex02(message.getPayload()));
                    waterQualityResult.setCreateTime(new Date());
                    if(flag){
                        System.out.println(waterQualityResult.toString());
                        WaterQualityResultService service = SpringUtil.getBean(WaterQualityResultService.class);
                        service.save(waterQualityResult);
                    }
                    flag = true;
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------"+ token.isComplete());
                }

            });
            client.connect(options);
            //订阅消息
            client.subscribe(TOPIC, qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MqttException {
        subscribeSample();
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
}
