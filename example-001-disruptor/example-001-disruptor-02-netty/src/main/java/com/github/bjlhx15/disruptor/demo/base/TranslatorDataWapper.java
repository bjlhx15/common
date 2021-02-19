package com.github.bjlhx15.disruptor.demo.base;

import io.netty.channel.ChannelHandlerContext;

//dis内部需要传输的对象
public class TranslatorDataWapper {
    //实际的数据
    private TranslatorData data;
    //ctx对象
    private ChannelHandlerContext ctx;

    public TranslatorData getData() {
        return data;
    }

    public void setData(TranslatorData data) {
        this.data = data;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }
}
