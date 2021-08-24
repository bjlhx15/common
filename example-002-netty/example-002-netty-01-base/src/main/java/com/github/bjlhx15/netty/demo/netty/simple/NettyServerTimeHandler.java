package com.github.bjlhx15.netty.demo.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 1、自定义一个handler，继承ChannelInboundHandlerAdapter
 */
public class NettyServerTimeHandler extends ChannelInboundHandlerAdapter {
    //读取实际数据
    //ChannelHandlerContext 上下文对象，含有管道pipeline、通道channel,地址
    //Object msg：客户端数据，默认Object
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //解决方案1 用户程序自定义普通任务
        ctx.channel().eventLoop().execute(() -> {
            //耗时任务
            try {
                Thread.sleep(10 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello client 2", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                System.out.println("发生异常");
            }
        });

        ctx.channel().eventLoop().execute(() -> {
            //耗时任务
            try {
                Thread.sleep(20 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello client 3", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                System.out.println("发生异常");
            }
        });


        ctx.channel().eventLoop().schedule(() -> {
            //耗时任务
            try {
                Thread.sleep(5 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello client 4", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                System.out.println("发生异常");
            }
        },5, TimeUnit.SECONDS);
        ctx.channel().eventLoop().schedule(() -> {
            //耗时任务
            try {
                Thread.sleep(7 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello client 5", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                System.out.println("发生异常");
            }
        },7, TimeUnit.SECONDS);
        System.out.println(System.currentTimeMillis() + ":go on ……");
    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //        writeAndFlush 是 write和 flush
        //        将数据写入到缓存，并刷新
        //        将写入数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer(System.currentTimeMillis() + "hello client 1", CharsetUtil.UTF_8));
    }

    //处理异常一般关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
