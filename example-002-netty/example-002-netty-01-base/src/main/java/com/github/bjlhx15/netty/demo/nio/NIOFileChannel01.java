package com.github.bjlhx15.netty.demo.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception {
        String str = "hello lihongxu";
        //创建一个输出流 -》channel
        FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
        //通过fileOutputStream 获取对应的filechannel ,FileChannel 实现是FileChannelImpl
        FileChannel channel = fileOutputStream.getChannel();

        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将Str放入到buffer
        byteBuffer.put(str.getBytes());
        //反转buffer
        byteBuffer.flip();
        //将byteBuffer数据写入到fileChannel中
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
