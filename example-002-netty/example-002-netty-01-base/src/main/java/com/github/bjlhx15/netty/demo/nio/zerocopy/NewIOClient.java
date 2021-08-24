package com.github.bjlhx15.netty.demo.nio.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));
        String fileName = "commons-math3-3.6.1.jar";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();

//        在linux下一个transferTo方法就可以完成传输
        //在windows下一次调用transferTo只能发送8m，就需要分段传输文件，而且要记录传输位置
        //transferTo底层使用到零拷贝
        long total = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总字节数：" + total + "，耗时：" + (System.currentTimeMillis() - startTime));
        fileChannel.close();
    }
}
