package com.pd.environment.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TurbidiServerReaderRunnable implements Runnable {

    private Socket socket;
    public TurbidiServerReaderRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String message;
            // 死循环控制服务端收完消息继续等待接收下一个消息
            while ((message = br.readLine()) != null) {
                System.out.println(socket.getRemoteSocketAddress() + "收到消息: " + message);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
