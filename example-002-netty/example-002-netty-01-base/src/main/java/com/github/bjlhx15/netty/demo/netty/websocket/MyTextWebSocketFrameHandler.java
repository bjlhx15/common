package com.github.bjlhx15.netty.demo.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

//TextWebSocketFrame表示一个文本帧
public class MyTextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("服务端收到消息：" + textWebSocketFrame.text());
        //回复
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("服务器事件："
                + LocalDateTime.now() + " " + textWebSocketFrame.text()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //id 表示唯一至，asLongText是唯一的，asShortText不是唯一的
        System.out.println("handlerAdded被调用：" + ctx.channel().id().asLongText());
        System.out.println("handlerAdded被调用：" + ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved 被调用：" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("handlerRemoved 被调用：" + cause.getMessage());
        ctx.close();
    }
}
