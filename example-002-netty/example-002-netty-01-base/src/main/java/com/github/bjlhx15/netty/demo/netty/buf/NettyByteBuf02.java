package com.github.bjlhx15.netty.demo.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class NettyByteBuf02 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello world!", CharsetUtil.UTF_8);
        if (byteBuf.hasArray()) {
            byte[] content = byteBuf.array();
            //将content转换为字符串
            System.out.println(new String(content, Charset.forName("utf-8")));

            System.out.println("buteBuf=" + byteBuf);
            System.out.println(byteBuf.arrayOffset());//0
            System.out.println(byteBuf.readerIndex());//0
            System.out.println(byteBuf.writerIndex());//12
            System.out.println(byteBuf.capacity());//36

            int len = byteBuf.readableBytes();//12
            System.out.println("len="+len);

            for (int i = 0; i < len; i++) {
                System.out.println((char)byteBuf.getByte(i));
            }

            System.out.println(byteBuf.getCharSequence(0,4,CharsetUtil.UTF_8));//Hell
            System.out.println(byteBuf.getCharSequence(4,6,CharsetUtil.UTF_8));//o worl
        }
    }
}
