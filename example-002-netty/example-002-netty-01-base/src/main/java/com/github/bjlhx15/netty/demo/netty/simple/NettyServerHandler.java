package com.github.bjlhx15.netty.demo.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * 1、自定义一个handler，继承ChannelInboundHandlerAdapter
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    //读取实际数据
    //ChannelHandlerContext 上下文对象，含有管道pipeline、通道channel,地址
    //Object msg：客户端数据，默认Object
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("thread name:"+Thread.currentThread().getName());

        System.out.println("channel and pipeline 关系");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = channel.pipeline();//pipeline是一个双向链表

        System.out.println("Server ctx=" + ctx);
        //        将msg 转ByteBuffer
        //        ByteBuf 时netty，性能优于nio bytebuffer
        ByteBuf buffer = (ByteBuf) msg;
        System.out.println("客户端发送消息是：" + buffer.toString(CharsetUtil.UTF_8));
        System.out.println("client address:" + channel.remoteAddress());
    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //        writeAndFlush 是 write和 flush
        //        将数据写入到缓存，并刷新
        //        将写入数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client", CharsetUtil.UTF_8));
    }

    //处理异常一般关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
