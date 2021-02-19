package com.github.bjlhx15.disruptor.demo.base;

import com.lmax.disruptor.RingBuffer;
import io.netty.channel.ChannelHandlerContext;

//生产者
public class MessageProducer {

    private String producerId;

    private RingBuffer<TranslatorDataWapper> ringBuffer;

    public MessageProducer(String producerId, RingBuffer<TranslatorDataWapper> ringBuffer) {
        this.producerId = producerId;
        this.ringBuffer = ringBuffer;
    }
    //发送实际的对象和ctx
    public void onData(TranslatorData data, ChannelHandlerContext ctx) {
        long sequence = ringBuffer.next();
        try {
            TranslatorDataWapper wapper = ringBuffer.get(sequence);
            wapper.setData(data);
            wapper.setCtx(ctx);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
