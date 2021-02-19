package com.github.bjlhx15.disruptor.demo.base.other.demo3duoPduoC;

import com.github.bjlhx15.disruptor.demo.base.other.TradeTransaction;
import com.lmax.disruptor.EventHandler;

public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        //do Something
        //System.out.println("TradeTransactionVasConsumer:"+event.getId()+":"+event.getPrice()+" sequence:"+sequence+" endOfBatch:"+endOfBatch);
    }
}
