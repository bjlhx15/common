package com.github.bjlhx15.disruptor.demo.base.oneponec;

/**
 * 消息事件类
 */
public class MessageEvent{
    /**
     * 原始消息
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
