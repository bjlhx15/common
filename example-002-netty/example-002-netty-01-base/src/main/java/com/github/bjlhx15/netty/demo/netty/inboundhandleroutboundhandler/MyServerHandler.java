package com.github.bjlhx15.netty.demo.netty.inboundhandleroutboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long aLong) throws Exception {
        System.out.println("从客户端读取：" + ctx.channel().remoteAddress() + ";long:" + aLong);
        ctx.writeAndFlush("98765L");
    }
}
