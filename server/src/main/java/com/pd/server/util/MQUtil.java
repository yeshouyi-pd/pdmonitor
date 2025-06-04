package com.pd.server.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQUtil {

    public final static String HOST="121.229.202.41";//主机
    public final static int PORT=5672;//端口
    public final static String USER_NAME="finless";//用户名
    public final static String PASS_WORD="finless@bxkj";//密码
    public final static String QUEUE_NAME="finless";//队列名称

    public final static String HOST_PYH="120.48.10.75";//主机
    public final static int PORT_PYH=5672;//端口
    public final static String USER_NAME_PYH="admin";//用户名
    public final static String PASS_WORD_PYH="admin";//密码
    public final static String QUEUE_NAME_PYH="TestDirectQueue";//队列名称

    public static Connection getConnection() throws IOException, TimeoutException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USER_NAME);
        factory.setPassword(PASS_WORD);
        Connection connection = factory.newConnection();
        return connection;
    }

    public static Connection getConnectionpYH() throws IOException, TimeoutException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_PYH);
        factory.setPort(PORT_PYH);
        factory.setUsername(USER_NAME_PYH);
        factory.setPassword(PASS_WORD_PYH);
        Connection connection = factory.newConnection();
        return connection;
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(MQUtil.QUEUE_NAME,true,false,false,null);
        String message = "NJA4001&118.73123,32.08157@2023_12_26_09_51_12-0:348.748/2023_12_26_09_51_13-0:188.738,1:351.486/2023_12_26_09_51_17-0:351.052";
        channel.basicPublish("",MQUtil.QUEUE_NAME,null,message.getBytes("UTF-8"));
        channel.close();
        connection.close();
        System.out.println("上传完成");
    }
}
