package com.github.bjlhx15.netty.demo.netty.inboundhandleroutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        入站的handler进行解码
//        pipeline.addLast(new MyByteToLongDecoder());
        pipeline.addLast(new MyByteToLongDecoder2());
//        出站编码
        pipeline.addLast(new MyLongToByteEncoder());
        pipeline.addLast(new MyServerHandler());
    }
}
