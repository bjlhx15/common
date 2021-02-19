package com.github.bjlhx15.disruptor.demo.base.other.demo1;

import com.github.bjlhx15.disruptor.demo.base.other.TradeTransaction;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction>, WorkHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(TradeTransaction event) throws Exception {
        System.out.println(Thread.currentThread().getName()+":"+event.getId());
    }


}