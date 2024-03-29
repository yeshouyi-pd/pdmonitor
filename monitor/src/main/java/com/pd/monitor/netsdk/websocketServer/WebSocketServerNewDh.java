package com.pd.monitor.netsdk.websocketServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket控制器
 * @author xutongxue
 * @date 2023/8/23 13:36
 */
@ServerEndpoint("/webSocketDh/{device}/{channel}")
@Component
public class WebSocketServerNewDh {

    private static ConfigurableApplicationContext applicationContext;

    public static void setApplicationContext(ConfigurableApplicationContext context) {
        applicationContext = context;
    }

    private static WebSocketServerNewDh instance;

    /**
     * 静态变量,用来记录当前在线连接数。应该把它设计成线程安全
     */
    private final AtomicInteger onlineCount = new AtomicInteger(0);
    //concurrent包的线程安全Set,用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketServerNewDh> webSocketSet = new CopyOnWriteArraySet<>();
    //与某个客户端的链接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private String sid="";

    public static final Logger log = LoggerFactory.getLogger(WebSocketServerNewDh.class);

    /**
     * 有websocket client连接
     * @param device 设备ID
     * @param channel 预览句柄
     * @param session
     */
    @OnOpen
    public void OnOpen(@PathParam("device") String device, @PathParam("channel") String channel, Session session)  throws InterruptedException {
        log.info("{}{}：websocket连接进入",device,channel);
        this.session = session;
        webSocketSet.add(this);//加入set中
        addOnlineCount();//在线数加1
        log.error("有新窗口开始监听："+sid+",当前在线人数为"+getOnlineCount());
        log.error("当前websocketSet:"+webSocketSet);
        this.sid = device+channel;
        try {
            sendMessage(StandardCharsets.UTF_8.encode("连接成功"));
        }catch (IOException e){
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("device") String device, @PathParam("channel") String channel, Session session){
        webSocketSet.remove(this);//从set中删除
        subOnlineCount();//在线人数减1
        log.error("有一连接关闭！当前在线人数为"+getOnlineCount());
    }

    public void sendBuffer(byte[] bytes, long realPlayHandler) {
        /**
         * 发送流数据
         * 使用pBuffer.getByteBuffer(0,dwBufSize)得到的是一个指向native pointer的ByteBuffer对象,其数据存储在native,
         * 而webSocket发送的数据需要存储在ByteBuffer的成员变量hb，使用pBuffer的getByteBuffer得到的ByteBuffer其hb为null
         * 所以，需要先得到pBuffer的字节数组,手动创建一个ByteBuffer
         */
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        sendMessageToAll(buffer,"");
    }

    public void sendMessageToAll(ByteBuffer buffer,String sid) {
        for (WebSocketServerNewDh item : webSocketSet){
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid==null || sid ==""){
                    item.sendMessage(buffer);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(buffer);
                }
            }catch (IOException e){
                continue;
            }
        }
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(ByteBuffer buffer) throws IOException{
        this.session.getBasicRemote().sendBinary(buffer);
    }

    @OnError
    public void onError(Session session, Throwable error){
        log.error("发送错误");
        error.printStackTrace();
    }

    /**
     * 获取当前连接数
     *
     * @return
     */
    public int getOnlineCount() {
        return onlineCount.get();
    }

    /**
     * 增加当前连接数
     *
     * @return
     */
    public int addOnlineCount() {
        return onlineCount.getAndIncrement();
    }

    /**
     * 减少当前连接数
     *
     * @return
     */
    public int subOnlineCount() {
        return onlineCount.getAndDecrement();
    }

}
