package com.github.bjlhx15.netty.demo.netty.codec2;

import com.github.bjlhx15.netty.demo.netty.codec.StudentPOJO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    //    当通道就绪就会触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int random = new Random().nextInt(3);
        ProtoDataEntity.MyMessage myMessage=null;

        if(0==random){
            myMessage=ProtoDataEntity.MyMessage.newBuilder()
                    .setDataType(ProtoDataEntity.MyMessage.DataType.StudentType)
                    .setStudent(ProtoDataEntity.Student.newBuilder().setId(3).build())
                    .build();
        }else{

            myMessage=ProtoDataEntity.MyMessage.newBuilder()
                    .setDataType(ProtoDataEntity.MyMessage.DataType.WorkerType)
                    .setWorker(ProtoDataEntity.Worker.newBuilder().setId(30).build())
                    .build();
        }

    }

    //当通道有读取事件时，会触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("server response:" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("server address:" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
