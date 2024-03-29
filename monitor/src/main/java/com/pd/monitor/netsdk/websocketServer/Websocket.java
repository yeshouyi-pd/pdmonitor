package com.pd.monitor.netsdk.websocketServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/ws/monitor/{device}/{channel}")
@Controller
public class Websocket {

    private static ConfigurableApplicationContext applicationContext;

    public static void setApplicationContext(ConfigurableApplicationContext context) {
        applicationContext = context;
    }

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    public static Session session;

    private static Websocket instance;
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private static ConcurrentHashMap<Long, Session> sessions = new ConcurrentHashMap<>();

    private static final Logger log = LoggerFactory.getLogger(Websocket.class);
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;


    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session; // 保存客户端连接的Session对象

    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {


    }

    /**
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public String onMsg(String text) throws IOException {
        System.out.println("连接成功");
        return null;
    }



    /**
     * 实现服务器主动推送
     * @param realPlayHandler 播放拉流的回调句柄
     * @param buffer  发送的数据
     * @throws IOException
     */
    public void sendMessageToOne(long realPlayHandler, ByteBuffer buffer) throws IOException {
        session.getBasicRemote().sendBinary(buffer);
    }

    public static void sendBuffer(byte[] bytes, long realPlayHandler) {
        Websocket wsServerEndpoint = new Websocket();
        /**
         * 发送流数据
         * 使用pBuffer.getByteBuffer(0,dwBufSize)得到的是一个指向native pointer的ByteBuffer对象,其数据存储在native,
         * 而webSocket发送的数据需要存储在ByteBuffer的成员变量hb，使用pBuffer的getByteBuffer得到的ByteBuffer其hb为null
         * 所以，需要先得到pBuffer的字节数组,手动创建一个ByteBuffer
         */
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        try {
            wsServerEndpoint.sendMessageToOne(realPlayHandler, buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}



