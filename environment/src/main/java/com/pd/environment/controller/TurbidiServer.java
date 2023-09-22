package com.pd.environment.controller;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

@Component
public class TurbidiServer implements ApplicationRunner {

    // 使用静态变量记录一个线程池对象
    private static ExecutorService pool = new ThreadPoolExecutor(3, 5, 6,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
//            ServerSocket serverSocket = new ServerSocket(7777);
//            Socket socket = serverSocket.accept();
//            System.out.println(socket.getRemoteSocketAddress() + "上线了");
//            // 创建Runnable任务交给线程池处理
//            pool.execute(new TurbidiServerReaderRunnable(socket));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
