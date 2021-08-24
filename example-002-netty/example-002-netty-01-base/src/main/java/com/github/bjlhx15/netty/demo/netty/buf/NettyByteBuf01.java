package com.github.bjlhx15.netty.demo.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf01 {
    public static void main(String[] args) {
        //创建一个ByteBuf，包含一个数据 是一个byte[10]
        //capacity 长度
        //在netty的buffer中，不需要使用flip进行反转
        //底层维护了readerIndex和writerIndex
        // 通过readerIndex和writerIndex和 capacity,将buffer分成三个区域
        //0 -- readerIndex 已读取的区域
        //readerIndex -- writerIndex 可读取区域
        //writerIndex -- capacity 可写区域
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }
        // 输出，仅仅是索引读取，不会引起 readerIndex变化
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.getByte(i));
        }
        // 输出，readerIndex会变化
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readerIndex());
        }
    }
}
