package com.github.bjlhx15.common.threaddemo;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadDemo01
 * @description TODO
 * @date 2021-02-13 11:00
 */
public class ThreadDemo06ThreadExitFlag {
    static class MyTask extends Thread{
        private volatile boolean closed=false;
        @Override
        public void run() {
            System.out.println("started");
            while (!closed&&!isInterrupted()){
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                }
                System.out.println("do something");
            }
            System.out.println("exit");
        }
        public void close(){
            this.closed=true;
            this.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask myTask=new MyTask();
        myTask.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("system will be shutdown");
        myTask.close();
    }
}
