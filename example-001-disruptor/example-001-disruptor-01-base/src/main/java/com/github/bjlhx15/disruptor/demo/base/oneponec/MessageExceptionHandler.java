package com.github.bjlhx15.disruptor.demo.base.oneponec;

import com.lmax.disruptor.ExceptionHandler;

/**
 * 异常处理类
 */
public class MessageExceptionHandler implements ExceptionHandler<MessageEvent> {
    @Override
    public void handleEventException(Throwable ex, long sequence, MessageEvent event) {
        ex.printStackTrace();
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        ex.printStackTrace();

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        ex.printStackTrace();

    }
}

