package com.github.bjlhx15.disruptor.demo.base.other.demo1;

import com.github.bjlhx15.disruptor.demo.base.other.TradeTransaction;
import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

import java.util.UUID;
import java.util.concurrent.*;

public class DisruptorDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int BUFFER_SIZE=1024;
        int THREAD_NUMBERS=4;
        //创建RingBuffer
        final RingBuffer<TradeTransaction> ringBuffer = RingBuffer.createSingleProducer(() -> new TradeTransaction(),BUFFER_SIZE,new YieldingWaitStrategy());
        //创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);
        //创建SequenceBarrier
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        //创建消息处理器
        BatchEventProcessor<TradeTransaction> transProcessor = new BatchEventProcessor<>(ringBuffer,sequenceBarrier,new TradeTransactionInDBHandler());
        //处理
        executors.submit(transProcessor);

        //创建一个消费者
        Future<?> future=executors.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                long seq;
                for(int i=0;i<1000;i++){
                    seq=ringBuffer.next();//占个坑 --ringBuffer一个可用区块
                    ringBuffer.get(seq).setPrice(Math.random()*9999);//给这个区块放入 数据  如果此处不理解，想想RingBuffer的结构图
                    ringBuffer.get(seq).setId(UUID.randomUUID().toString()+"===="+i); //
                    ringBuffer.publish(seq);//发布这个区块的数据使handler(consumer)可见
                }
                return null;
            }
        });
        future.get();//等待生产者结束
        Thread.sleep(1000);//等上1秒，等消费都处理完成
        transProcessor.halt();//通知事件(或者说消息)处理器 可以结束了（并不是马上结束!!!）
        executors.shutdown();//终止线程

    }
}
