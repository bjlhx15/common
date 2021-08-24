package com.github.bjlhx15.netty.demo.netty.inboundhandleroutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     * decode 会根据接收的数据，被调用多次，直到确定没有新的元素被添加到list
     * 或者是Bytebuf没有更多的可读字节为止
     * 如果list out 不为空，就会将list的内容传递给下一个，ChannelInboundHandler处理，该处理器的方法也会调用多次
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder 被调用");
        // 因为long有8个字节，需要判断8，才能读取一个long
        if(in.readableBytes()>=8){
            out.add(in.readLong());
        }
    }
}
