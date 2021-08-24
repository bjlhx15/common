package com.github.bjlhx15.netty.demo.netty.inboundhandleroutboundhandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("服务器端的IP="+ctx.channel().remoteAddress());
        System.out.println("服务器端的消息="+msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 发送数据");
        //发送一个long，会调用编码器
        ctx.writeAndFlush(123456L);

        //分析：
        // 1、abcsabcdabcdabcd 是16个字节
        // 2、没有调用编码器，
        // 该处理器的前一个处理器是 MyLongToByteEncoder
        // MyLongToByteEncoder 父类是 MessageToByteEncoder ，中有个 write 方法
        // write方法中有个判断 acceptOutboundMessage 是否进入编码器
        // 编写encoder 类型与传入数据类型一致，才能使用自定义编码器
        //ctx.writeAndFlush(Unpooled.copiedBuffer("abcsabcdabcdabcd", CharsetUtil.UTF_8));

    }
}
