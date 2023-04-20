package com.pd.monitor.lora;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 客户端
 */
public class ClientDemo {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 7777);

            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);

            // 客户端使用死循环等待用户不断地输入消息
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("发送消息: ");
                String inp = scanner.nextLine();
                // 一旦输入了exit，则关闭客户端程序，并释放资源
                if (inp.equals("exit")) {
                    System.out.println("下线成功");
                    ps.close();
                    break;
                }
                ps.println(inp);
                ps.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

