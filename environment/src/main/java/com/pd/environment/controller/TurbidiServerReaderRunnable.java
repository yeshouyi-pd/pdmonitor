package com.pd.environment.controller;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TurbidiServerReaderRunnable implements Runnable {

    private Socket socket;
    public TurbidiServerReaderRunnable(Socket socket) {
        this.socket = socket;
    }

    @Resource
    private TurbidiServer turbidiServer;

    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String message;
            // 死循环控制服务端收完消息继续等待接收下一个消息
            while ((message = br.readLine()) != null) {
                System.out.println(socket.getRemoteSocketAddress() + "收到消息: " + message);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is!=null){
                    is.close();
                }
                if(isr!=null){
                    isr.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
