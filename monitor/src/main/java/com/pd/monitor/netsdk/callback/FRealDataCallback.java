package com.pd.monitor.netsdk.callback;

import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.module.OperationModule;
import com.pd.monitor.netsdk.po.RealPlayInfo;
import com.pd.monitor.netsdk.websocketServer.WebSocketServerDh;
import com.sun.jna.Pointer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

/**
 * 实时预览回调
 * @author xutongxue
 * @date 2023/8/23 16:53
 */
@Component
public class FRealDataCallback implements NetSDKLib.fRealDataCallBackEx {
    @Autowired
    private WebSocketServerDh server;

    @Override
    public void invoke(NetSDKLib.LLong lRealHandle, int dwDataType, Pointer pBuffer, int dwBufSize, int param, Pointer dwUser) {
        RealPlayInfo info = OperationModule.findRealPlayInfo(lRealHandle.longValue());
        if (info != null && info.getLoginHandler() != 0) {
            //过滤码流
            byte[] buffer = pBuffer.getByteArray(0, dwBufSize);
            if (info.getEmDataType() == 0 || info.getEmDataType() == 3) {
                //选择私有码流或mp4码流,拉流出的码流都是私有码流
                if (dwDataType == 0) {
//                    sendBuffer(buffer, lRealHandle.longValue());
                }
            } else if ((dwDataType - 1000) == info.getEmDataType()) {
                sendBuffer(buffer, lRealHandle.longValue());
            }
        }
    }

    private void sendBuffer(byte[] bytes, long realPlayHandler) {
        /**
         * 发送流数据
         * 使用pBuffer.getByteBuffer(0,dwBufSize)得到的是一个指向native pointer的ByteBuffer对象,其数据存储在native,
         * 而webSocket发送的数据需要存储在ByteBuffer的成员变量hb，使用pBuffer的getByteBuffer得到的ByteBuffer其hb为null
         * 所以，需要先得到pBuffer的字节数组,手动创建一个ByteBuffer
         */
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        server.forwardDataFlow(realPlayHandler, buffer);
    }

}
