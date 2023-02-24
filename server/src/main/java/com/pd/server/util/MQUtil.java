package com.pd.server.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQUtil {

    public final static String HOST="218.94.155.86";//主机
    public final static int PORT=5672;//端口
    public final static String USER_NAME="finless";//用户名
    public final static String PASS_WORD="finless@bxkj";//密码
    public final static String QUEUE_NAME="finless";//队列名称

    public static Connection getConnection() throws IOException, TimeoutException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USER_NAME);
        factory.setPassword(PASS_WORD);
        Connection connection = factory.newConnection();
        return connection;
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "NJA4001&18.730022,32.260148@022_11_08_07_57_01-0:218.483/2022_11_08_07_57_27-0:224.401/2022_11_08_07_58_09-0:225.254";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));
        channel.close();
        connection.close();
    }
}
