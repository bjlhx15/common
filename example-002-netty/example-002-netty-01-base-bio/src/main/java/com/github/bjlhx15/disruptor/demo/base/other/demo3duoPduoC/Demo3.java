package com.github.bjlhx15.disruptor.demo.base.other.demo3duoPduoC;

import com.github.bjlhx15.disruptor.demo.base.other.TradeTransaction;
import com.github.bjlhx15.disruptor.demo.base.other.demo1.TradeTransactionInDBHandler;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        long beginTime=System.currentTimeMillis();
        int bufferSize=1024;
        ExecutorService executor= Executors.newFixedThreadPool(8);
        Disruptor<TradeTransaction> disruptor=new Disruptor<TradeTransaction>(() -> new TradeTransaction(), bufferSize, executor, ProducerType.SINGLE, new BusySpinWaitStrategy());
        //使用disruptor创建消费者组C1,C2
        //TradeTransactionVasConsumer实现EventHandler，EventHandler是能够对于event时间处理的过程进行感知
        EventHandlerGroup<TradeTransaction> handlerGroup=disruptor.handleEventsWith(new TradeTransactionVasConsumer(),new TradeTransactionInDBHandler());
        TradeTransactionJMSNotifyHandler jmsConsumer=new TradeTransactionJMSNotifyHandler();
        //声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3
        handlerGroup.then(jmsConsumer);


        disruptor.start();//启动
        CountDownLatch latch=new CountDownLatch(1);
        //生产者准备
        executor.submit(new TradeTransactionPublisher(latch, disruptor));
        latch.await();//等待生产者完事.
        disruptor.shutdown();
        executor.shutdown();

        System.out.println("总耗时:"+(System.currentTimeMillis()-beginTime) / 1000);
    }

    static class TradeTransactionPublisher implements Runnable{
        Disruptor<TradeTransaction> disruptor;
        private CountDownLatch latch;

        private static int LOOP=10000000;//模拟一千万次交易的发生

        public TradeTransactionPublisher(CountDownLatch latch,Disruptor<TradeTransaction> disruptor) {
            this.disruptor=disruptor;
            this.latch=latch;
        }

        @Override
        public void run() {
            TradeTransactionEventTranslator tradeTransloator=new TradeTransactionEventTranslator();
            for(int i=0;i<LOOP;i++){
                disruptor.publishEvent(tradeTransloator);
            }
            latch.countDown();
        }
    }

    static class TradeTransactionEventTranslator implements EventTranslator<TradeTransaction> {
        private Random random=new Random();

        @Override
        public void translateTo(TradeTransaction event, long sequence) {
            this.generateTradeTransaction(event);
        }

        private TradeTransaction generateTradeTransaction(TradeTransaction trade){
            trade.setId(UUID.randomUUID().toString());
            trade.setPrice(random.nextDouble()*9999);
            return trade;
        }

    }
}
