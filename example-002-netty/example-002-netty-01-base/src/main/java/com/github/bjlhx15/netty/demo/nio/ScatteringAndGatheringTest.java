package com.github.bjlhx15.netty.demo.nio;

import net.minidev.json.JSONUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws Exception {
        //Scattering:将数据写入Buffer时，可以采用Buffer数组，依次写
        //Gathering:从buffer读取数据时，可以采用buffer数组，依次读
//        使用ServerSocketChannel 和SocketChannel 网络
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

//        绑定端口到socket，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
//        等客户端链接（telnet）
        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLength = 8;
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long read = socketChannel.read(byteBuffers);
                byteRead += read;//累计读取到的字节数
                System.out.println("byteRead=" + byteRead);
//                使用流打印，看看当前的这个Bufferde postion和limit
                Arrays.asList(byteBuffers).stream()
                        .map(p -> "position=" + p.position() + ",limit=" + p.limit())
                        .forEach(p -> System.out.println(p));
            }
            Arrays.asList(byteBuffers).forEach(p -> p.flip());
            //将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }
//            close
            Arrays.asList(byteBuffers).forEach(p -> p.clear());
            System.out.println("byteRead=" + byteRead + ",byteWrite=" + byteWrite + ",messageLength=" + messageLength);
        }
    }
}
