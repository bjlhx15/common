package com.github.bjlhx15.netty.demo.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //http解码器
        pipeline.addLast("MyHttpServerCodec",new HttpServerCodec());
        pipeline.addLast("MyTestHttpServerHandler",new TestHttpServerHandler());
    }
}
