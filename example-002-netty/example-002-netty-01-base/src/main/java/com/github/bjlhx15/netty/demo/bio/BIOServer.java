package com.github.bjlhx15.netty.demo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        //1、创建一个线程池
        //2、客户端连接，启动一个线程

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务启动了:6666端口,telnet 127.0.0.1 6666");
        while (true) {
            System.out.println("等待连接……");
            final Socket socket = serverSocket.accept();

            System.out.println("一个客户端被连接");
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket) {
        try {
            System.out.println("Thread id:" + Thread.currentThread().getId() +";name:"+ Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            //循环读取客户端数据
            while (true) {

                System.out.println("等待读取……");
                int read = inputStream.read(bytes);//将inputStream 读取到 bytes
                if (read != -1) {
                    System.out.println("Thread id:" + Thread.currentThread().getId() +";name:"+ Thread.currentThread().getName());

                    System.out.println("输出客户端发送的数据");
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
