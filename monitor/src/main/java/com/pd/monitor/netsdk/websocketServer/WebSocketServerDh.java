package com.pd.monitor.netsdk.websocketServer;

import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.module.LoginModule;
import com.pd.monitor.netsdk.module.OperationModule;
import com.pd.monitor.netsdk.module.RealPlayModule;
import com.pd.monitor.netsdk.po.RealPlayInfo;
import com.pd.monitor.netsdk.service.VideoMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket控制器
 * @author xutongxue
 * @date 2023/8/23 13:36
 */
@ServerEndpoint("/device/monitor/{channel}")
@Component
public class WebSocketServerDh {
    private static ConfigurableApplicationContext applicationContext;

    public static void setApplicationContext(ConfigurableApplicationContext context) {
        applicationContext = context;
    }

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */

    private static WebSocketServerDh instance;

    public static VideoMonitorService service;
    /**
     * 静态变量,用来记录当前在线连接数。应该把它设计成线程安全
     */
    private final AtomicInteger onlineCount = new AtomicInteger(0);
    /**
     * 存放每个客户端对应的WebSocket对象,根据设备realPlayHandler建立session
     */
    public static ConcurrentHashMap< Session ,Integer > sessions = new ConcurrentHashMap<>();

    /**
     * 关联通道号和播放句柄
     */
    public static HashMap<Integer, NetSDKLib.LLong> channlAndlRealHandles = new HashMap<>();
    /**
     * 保存覆盖播放标识
     */
    public static CopyOnWriteArrayList<String> sessionList = new CopyOnWriteArrayList<>();

    private static final Logger log = LoggerFactory.getLogger(WebSocketServerDh.class);


    /**
     * 有websocket client连接
     * @param channel 预览句柄
     * @param session
     */
    @OnOpen
    public void OnOpen( @PathParam("channel") Integer channel, Session session)  throws InterruptedException {

        sessions.put( session  ,channel);
        addOnlineCount();
        log.info("websocket connect.session: " + sessions.size());
    }

    /**
     * 连接关闭调用的方法
     * @param channel 预览句柄
     * @param session websocket连接对象
     */
    @OnClose
    public void onClose(@PathParam("channel") Integer channel, Session session){
        sessions.remove(session); //移除当前的sessions
        log.error("有一连接关闭！当前在线人数为"+sessions.size());

        if(sessions.size()==0){
            channlAndlRealHandles.forEach((key, value)->{
                RealPlayModule.stopRealPlay(value);
            });
        }else{
            //所有通道村存的对饮的播放句柄
            channlAndlRealHandles.forEach((key, value)->{
                //当所有session里面的通道不在channlAndlRealHandles 说明已经退出
                //直接断掉推流
                if(!sessions.containsValue(key)){
                    RealPlayModule.stopRealPlay(value);
                }
            });

        }

        subOnlineCount();


    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        // 处理错误，可以记录日志或者其他操作
        error.printStackTrace();
    }

    /**
     * 收到客户端发来消息
     *
     * @param message 消息对象
     */
    @OnMessage
    public void onMessage(ByteBuffer message) {
        log.info("服务端收到客户端发来的消息: {}", message);
    }

    /**
     * 收到客户端发来消息
     *
     * @param message 字符串类型消息
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("服务端收到客户端发来的消息: {}", message);
    }

    /**
     * 发送消息
     *
     * @param message 字符串类型的消息
     */
    public void sendAll(String message) {
       /* for (Map.Entry<String, Session> session : sessions.entrySet()) {
            session.getValue().getAsyncRemote().sendText(message);
        }*/
    }

    /**
     * 发送binary消息
     *
     * @param buffer
     */
    public void sendMessage(ByteBuffer buffer) {
      /*  for (Map.Entry<String, Session> session : sessions.entrySet()) {
            session.getValue().getAsyncRemote().sendBinary(buffer);
        }*/
    }

    /**
     * 转发数据流到指定客户端
     * @param realPlayHandler 预览句柄
     * @param buffer          码流数据
     */
    public void forwardDataFlow(long realPlayHandler, ByteBuffer buffer) {
        //播放句柄无效
/*        if (realPlayHandler == 0) {
            log.error("loginHandler is invalid.please check.", this);
            return;
        }
        RealPlayInfo realPlayInfo = OperationModule.findRealPlayInfo(realPlayHandler);
        if(realPlayInfo == null){
            System.out.println("连接已断开");
        }
        String key = realPlayInfo.getDeviceId()+realPlayInfo.getChannel();
        Session session = sessions.get(this.sid);
        if (session != null) {
            synchronized (session) {
                try {
                    session.getBasicRemote().sendBinary(buffer);
                    byte[] bytes=new byte[buffer.limit()];
                    buffer.get(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            log.error("session is null.please check.");
        }*/
    }

    /**
     * 发送binary消息给指定客户端
     *
     * @param rePlayHandler 回放句柄
     * @param buffer          码流数据
     */
    public void sendReportMessageToOne(long rePlayHandler, ByteBuffer buffer) {
        //登录句柄无效
/*        if (rePlayHandler == 0) {
            log.error("loginHandler is invalid.please check.", this);
            return;
        }
        RealPlayInfo realPlayInfo = OperationModule.findRealPlayInfo(rePlayHandler);
        if(realPlayInfo == null){
            //连接已断开
        }
        String key = realPlayInfo.getDeviceId()+realPlayInfo.getChannel()+realPlayInfo.getOdate();
        Session session = sessions.get(this.sid);
        if (session != null) {
            synchronized (session) {
                try {
                    session.getBasicRemote().sendBinary(buffer);
                    byte[] bytes=new byte[buffer.limit()];
                    buffer.get(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {

        }*/
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessageToOne(int nChannelID, ByteBuffer buffer) throws IOException {
        for (Map.Entry<Session ,Integer> entry : sessions.entrySet()) {

            if(entry.getValue() == nChannelID){
                try {
                    /**
                     * tomcat的原因,使用session.getAsyncRemote()会报Writing FULL WAITING error
                     * 需要使用session.getBasicRemote()
                     */

                    System.out.println("发送数据"+buffer);
                     entry.getKey().getBasicRemote().sendBinary(buffer);
/*                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                entry.getKey().getBasicRemote().sendBinary(buffers);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }).start();*/

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }






    }

    public static void sendBuffer(ByteBuffer buffer, int channel,NetSDKLib.LLong lRealHandle) {
        WebSocketServerDh wsServerEndpoint = new WebSocketServerDh();
        WebSocketServerDh.channlAndlRealHandles.put(channel, lRealHandle);
        /**
         * 发送流数据
         * 使用pBuffer.getByteBuffer(0,dwBufSize)得到的是一个指向native pointer的ByteBuffer对象,其数据存储在native,
         * 而webSocket发送的数据需要存储在ByteBuffer的成员变量hb，使用pBuffer的getByteBuffer得到的ByteBuffer其hb为null
         * 所以，需要先得到pBuffer的字节数组,手动创建一个ByteBuffer
         */
        // ByteBuffer buffer = ByteBuffer.wrap(bytes);
        try {
            wsServerEndpoint.sendMessageToOne(channel, buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToAll(ByteBuffer buffer) {

/*        for (Session session : sessions.values()) {
            synchronized (session) {
                try {
                    *//**
                     * tomcat的原因,使用session.getAsyncRemote()会报Writing FULL WAITING error
                     * 需要使用session.getBasicRemote()
                     *//*
                    if (session != null && session.isOpen()) {
                        session.getBasicRemote().sendBinary(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

    /**
     * 主动关闭websocket连接
     *
     * @param realPlayHandler 预览句柄
     */
    public void closeSession(String realPlayHandler) {
  /*      try {
            Session session = sessions.get(realPlayHandler);
            if (session != null) {
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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

    public static boolean currentSession(String key){
        if(sessions.containsKey(key)){
            return true;
        }
        return false;
    }
}
