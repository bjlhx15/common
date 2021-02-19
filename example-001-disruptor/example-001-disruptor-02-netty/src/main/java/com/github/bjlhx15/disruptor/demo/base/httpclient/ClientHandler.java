package com.github.bjlhx15.disruptor.demo.base.httpclient;

import com.alibaba.fastjson.JSON;
import com.github.bjlhx15.disruptor.demo.base.MessageProducer;
import com.github.bjlhx15.disruptor.demo.base.RingBufferWorkerPoolFactory;
import com.github.bjlhx15.disruptor.demo.base.TranslatorData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.CharsetUtil;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HttpContent content;
        HttpResponse response;
        if (msg instanceof HttpResponse) {
            response = (HttpResponse) msg;
            System.out.println("bbb:" + response.status().toString());
        }

        if (msg instanceof HttpContent) {
            content = (HttpContent) msg;
            ByteBuf buf = content.content();
            String responseContent = buf.toString(CharsetUtil.UTF_8);
            System.out.println("ccc:" + responseContent);
//            buf.release();

            TranslatorData response2 = JSON.parseObject(responseContent, TranslatorData.class);
            String producerId = "code:seesionId:002";
            MessageProducer messageProducer = RingBufferWorkerPoolFactory.getInstance().getMessageProducer(producerId);
            messageProducer.onData(response2, ctx);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}