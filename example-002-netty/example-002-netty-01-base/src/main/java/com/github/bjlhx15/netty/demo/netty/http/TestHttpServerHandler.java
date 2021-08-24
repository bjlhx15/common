package com.github.bjlhx15.netty.demo.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

//SimpleChannelInboundHandler  是 ChannelInboundHandlerAdapter
//HttpObject 客户端和服务器端相互通讯的数据被封装成HttpObject
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {

            System.out.println("msg 类型是：" + msg.getClass());
            System.out.println("客户端地址：" + channelHandlerContext.channel().remoteAddress());

            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了 /favicon.ico ，不处理");
                return;
            }

            ByteBuf byteBuf = Unpooled.copiedBuffer("helleo 你好", CharsetUtil.UTF_8);

            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
            channelHandlerContext.writeAndFlush(response);
        }
    }
}
