package com.pd.monitor.netsdk.websocketServer;


import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.module.LoginModule;
import com.pd.monitor.netsdk.module.RealPlayModule;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

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

    public static VideoMonitorService service;

    /**
     * 存放每个客户端对应的WebSocket对象,根据设备realPlayHandler建立session
     */

    public static ConcurrentHashMap< Session ,NetSDKLib.LLong > sessionsALLong = new ConcurrentHashMap<>();


    private static final Logger log = LoggerFactory.getLogger(WebSocketServerDh.class);


    /**
     * 有websocket client连接
     * @param channel 预览句柄
     * @param session
     */
    @OnOpen
    public void OnOpen( @PathParam("channel") Integer channel, Session session)  {
        try{
            // 初始化
            LoginModule.init(LoginModule.disConnect, LoginModule.haveReConnect);
            // 若未登录，先登录。
            if (LoginModule.m_hLoginHandle.longValue() == 0){
                LoginModule.login("49.239.193.148", 43489, "admin", "admin@bht2023");
            }
            RealPlayModule.startRealPlay(LoginModule.m_hLoginHandle,channel, 0, 5,session);

        }catch (Exception e){

        }
    }

    /**
     * 连接关闭调用的方法
     * @param channel 预览句柄
     * @param session websocket连接对象
     */
    @OnClose
    public void onClose(@PathParam("channel") Integer channel, Session session){
        RealPlayModule.stopRealPlay(sessionsALLong.get(session));
        sessionsALLong.remove(session);
        if (session != null) {
            try {
                session.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
     * 转发数据流到指定客户端
     * @param realPlayHandler 预览句柄
     * @param buffer          码流数据
     */
    public void forwardDataFlow(long realPlayHandler, ByteBuffer buffer) {

    }

    /**
     * 发送binary消息给指定客户端
     *
     * @param rePlayHandler 回放句柄
     * @param buffer          码流数据
     */
    public void sendReportMessageToOne(long rePlayHandler, ByteBuffer buffer) {

    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessageToOne(NetSDKLib.LLong lRealHandle, ByteBuffer buffer) throws IOException {

        for (Map.Entry<Session ,NetSDKLib.LLong> entry : sessionsALLong.entrySet()) {

            if(entry.getValue().longValue() == lRealHandle.longValue()){
                try {
                    /**
                     * tomcat的原因,使用session.getAsyncRemote()会报Writing FULL WAITING error
                     * 需要使用session.getBasicRemote()
                     */
                     System.out.println("session:"+entry.getKey());
                     entry.getKey().getBasicRemote().sendBinary(buffer);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }






    }

    public static void sendBuffer(ByteBuffer buffer,NetSDKLib.LLong lRealHandle) {
        WebSocketServerDh wsServerEndpoint = new WebSocketServerDh();
        /**
         * 发送流数据
         * 使用pBuffer.getByteBuffer(0,dwBufSize)得到的是一个指向native pointer的ByteBuffer对象,其数据存储在native,
         * 而webSocket发送的数据需要存储在ByteBuffer的成员变量hb，使用pBuffer的getByteBuffer得到的ByteBuffer其hb为null
         * 所以，需要先得到pBuffer的字节数组,手动创建一个ByteBuffer
         */
        try {
            wsServerEndpoint.sendMessageToOne(lRealHandle, buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static boolean currentSession(String key){
      /*  if(sessions.containsKey(key)){
            return true;
        }*/
        return false;
    }
}
