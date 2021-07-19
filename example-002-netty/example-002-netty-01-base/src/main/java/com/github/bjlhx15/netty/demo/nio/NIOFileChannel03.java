package com.github.bjlhx15.netty.demo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception {
        //创建输入流
        // FileInputStream fileInputStream = new FileInputStream(file);
        FileInputStream fileInputStream = new FileInputStream("testIn.txt");
        FileChannel channelIn = fileInputStream.getChannel();


        FileOutputStream fileOutputStream = new FileOutputStream("testOut.txt");
        FileChannel channelOut = fileOutputStream.getChannel();

        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);

        while (true) {
            //需要每次重置
            /**
             *         position = 0;
             *         limit = capacity;
             *         mark = -1;
             */
            byteBuffer.clear(); //复位
            //将fileChannel数据读取到byteBuffer中
            int read = channelIn.read(byteBuffer);
            System.out.println("read=" + read);
            if (read == -1) {
                break;
            }
//            将buffer中的数据写入
            byteBuffer.flip();
            channelOut.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
