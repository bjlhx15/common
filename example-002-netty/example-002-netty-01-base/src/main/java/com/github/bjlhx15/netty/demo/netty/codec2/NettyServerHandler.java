package com.github.bjlhx15.netty.demo.netty.codec2;

import com.github.bjlhx15.netty.demo.netty.codec.StudentPOJO;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 1、自定义一个handler，继承ChannelInboundHandlerAdapter
 */
//public class NettyServerHandler extends ChannelInboundHandlerAdapter {
public class NettyServerHandler extends SimpleChannelInboundHandler<ProtoDataEntity.MyMessage> {
    //读取实际数据
    //ChannelHandlerContext 上下文对象，含有管道pipeline、通道channel,地址
    //Object msg：客户端数据，默认Object
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        StudentPOJO.Student student = (StudentPOJO.Student) msg;
//        System.out.println("客户端发送的数据" + student.toString());
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ProtoDataEntity.MyMessage msg) throws Exception {
        switch (msg.getDataType()) {
            case StudentType:
                System.out.println("学生："+msg.getStudent().toString());
                break;
            case WorkerType:
                System.out.println("工人："+msg.getWorker().toString());
                break;
            case UNRECOGNIZED:
                System.out.println("类型不对："+msg.toString());
                break;
        }


        System.out.println("客户端发送的数据" + msg.toString());
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
