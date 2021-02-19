package com.github.bjlhx15.disruptor.demo.base.httpclient;


import com.github.bjlhx15.disruptor.demo.base.MessageConsumer;
import com.github.bjlhx15.disruptor.demo.base.RingBufferWorkerPoolFactory;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Constructor;

@SpringBootApplication
public class NettyClientApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NettyClientApplication.class, args);

        MessageConsumer[] conusmers = new MessageConsumer[4];
        for(int i =0; i < conusmers.length; i++) {
            MessageConsumer messageConsumer = new MessageConsumerImpl4Client("code:clientId:" + i);
            conusmers[i] = messageConsumer;
        }
        RingBufferWorkerPoolFactory.getInstance().initAndStart(ProducerType.MULTI,
                1024*2,
                //new YieldingWaitStrategy(),
                new BlockingWaitStrategy(),
                conusmers);

//        NettyClient2 nettyClient = new NettyClient2();
        NettyClient nettyClient = new NettyClient();
        for (int i = 0; i < 1000; i++) {
            //建立连接 并发送消息
//            new NettyClient2().sendData(i);
            nettyClient.sendData(i);

        }
    }
}
