package com.pd.monitor.netsdk.websocket;

import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.module.OperationModule;
import com.pd.monitor.netsdk.module.DevicesModule;
import com.pd.monitor.netsdk.po.RealPlayInfo;
import com.pd.monitor.netsdk.service.VideoMonitorService;
import com.pd.monitor.netsdk.utils.DeviceListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket控制器
 * @author xutongxue
 * @date 2023/8/23 13:36
 */
@ServerEndpoint("/device/monitor/{device}/{channel}")
@Component
@Slf4j
public class WebSocketServerDh {
    public static VideoMonitorService service;
    /**
     * 静态变量,用来记录当前在线连接数。应该把它设计成线程安全
     */
    private final AtomicInteger onlineCount = new AtomicInteger(0);
    /**
     * 存放每个客户端对应的WebSocket对象,根据设备realPlayHandler建立session
     */
    public static ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();
    /**
     * 保存覆盖播放标识
     */
    public static CopyOnWriteArrayList<String> sessionList = new CopyOnWriteArrayList<>();


    /**
     * 有websocket client连接
     * @param device 设备ID
     * @param channel 预览句柄
     * @param session
     */
    @OnOpen
    public void OnOpen(@PathParam("device") String device, @PathParam("channel") String channel, Session session)  throws InterruptedException {
        log.info("{}{}：websocket连接进入",device,channel);
        //设备ID+通道组成唯一性标识
        String uuid = device + channel;
        if (sessions.containsKey(uuid)) {
            sessions.put(uuid, session);
        } else {
            sessions.put(uuid, session);
            addOnlineCount();
        }
        log.info("websocket connect.session: " + session);
    }

    /**
     * 连接关闭调用的方法
     * @param device 设备ID
     * @param channel 预览句柄
     * @param session websocket连接对象
     */
    @OnClose
    public void onClose(@PathParam("device") String device, @PathParam("channel") String channel, Session session){
        System.out.println("websocket 断开" + device);
        String uuid = device + channel;
        DevicesModule devicesModule = DeviceListUtil.getDeviceModuleByDeviceId(device);
        if (sessions.containsKey(uuid)) {
            try{
                    long handle = OperationModule.findRealPlayInfoByDeviceIdAndChannelNum(uuid);
                    RealPlayInfo realPlayInfo = OperationModule.findRealPlayInfo(handle);
                    if(realPlayInfo != null){
                        System.out.println(uuid + ":websocket 断开连接！");
                        boolean b = false;
                        if("1".equals(realPlayInfo.getType())){
                            b = devicesModule.stopPlay(new NetSDKLib.LLong(handle));
                        }else if("2".equals(realPlayInfo.getType())){
                            b = service.stopReport(new NetSDKLib.LLong(handle),realPlayInfo.getDeviceId());
                        }
                        if(b){
                            sessions.remove(uuid);
                            subOnlineCount();
                            OperationModule.removeRealPlayInfo(handle);
                        }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误
     *
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
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
        for (Map.Entry<String, Session> session : sessions.entrySet()) {
            session.getValue().getAsyncRemote().sendText(message);
        }
    }

    /**
     * 发送binary消息
     *
     * @param buffer
     */
    public void sendMessage(ByteBuffer buffer) {
        for (Map.Entry<String, Session> session : sessions.entrySet()) {
            session.getValue().getAsyncRemote().sendBinary(buffer);
        }
    }

    /**
     * 转发数据流到指定客户端
     * @param realPlayHandler 预览句柄
     * @param buffer          码流数据
     */
    public void forwardDataFlow(long realPlayHandler, ByteBuffer buffer) {
        //播放句柄无效
        if (realPlayHandler == 0) {
            log.error("loginHandler is invalid.please check.", this);
            return;
        }
        RealPlayInfo realPlayInfo = OperationModule.findRealPlayInfo(realPlayHandler);
        if(realPlayInfo == null){
            System.out.println("连接已断开");
        }
        String key = realPlayInfo.getDeviceId()+realPlayInfo.getChannel();
        Session session = sessions.get(key);
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
        }
    }

    /**
     * 发送binary消息给指定客户端
     *
     * @param rePlayHandler 回放句柄
     * @param buffer          码流数据
     */
    public void sendReportMessageToOne(long rePlayHandler, ByteBuffer buffer) {
        //登录句柄无效
        if (rePlayHandler == 0) {
            log.error("loginHandler is invalid.please check.", this);
            return;
        }
        RealPlayInfo realPlayInfo = OperationModule.findRealPlayInfo(rePlayHandler);
        if(realPlayInfo == null){
            //连接已断开
        }
        String key = realPlayInfo.getDeviceId()+realPlayInfo.getChannel()+realPlayInfo.getOdate();
        Session session = sessions.get(key);
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

        }
    }

    public void sendMessageToAll(ByteBuffer buffer) {

        for (Session session : sessions.values()) {
            synchronized (session) {
                try {
                    /**
                     * tomcat的原因,使用session.getAsyncRemote()会报Writing FULL WAITING error
                     * 需要使用session.getBasicRemote()
                     */
                    session.getBasicRemote().sendBinary(buffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 主动关闭websocket连接
     *
     * @param realPlayHandler 预览句柄
     */
    public void closeSession(long realPlayHandler) {
        try {
            Session session = sessions.get(realPlayHandler);
            if (session != null) {
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
