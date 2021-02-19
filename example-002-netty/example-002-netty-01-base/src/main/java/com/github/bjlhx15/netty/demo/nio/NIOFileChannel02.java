package com.github.bjlhx15.netty.demo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception {
        //创建输入流
        File file = new File("test.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        //fileInputStream 获取对应的filechannel ,FileChannel 实现是FileChannelImpl
        FileChannel channel = fileInputStream.getChannel();

        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //将fileChannel数据读取到byteBuffer中
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
