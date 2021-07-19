package com.github.bjlhx15.netty.demo.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 说明
 * MappedByteBuffer 可让文件直接在内存（堆外内存）中修改，操作系统不需要拷贝一次
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("testIn.txt", "rw");
//        获取对应通道
        FileChannel channel = randomAccessFile.getChannel();
        //第三个参数：映射到内存的大小，即将文件的多少个字节映射到内存
        //可直接修改的范围就是0-5
//        MappedByteBuffer 可让文件直接在内存（堆外内存）中修改，操作系统不需要拷贝一次
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');
        randomAccessFile.close();
        System.out.println("修改成功");
    }
}
