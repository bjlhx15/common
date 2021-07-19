package com.github.bjlhx15.netty.demo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception {
//        创建相关的流
        FileInputStream fileInputStream = new FileInputStream("testIn.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("testIn.txt.copy");
//获取各个流对应的fileChanel
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();
//        使用transferFrom
        destCh.transferFrom(sourceCh, 0, sourceCh.size());
        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();

    }
}
