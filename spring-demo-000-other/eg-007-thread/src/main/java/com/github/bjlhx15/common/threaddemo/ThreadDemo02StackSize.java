package com.github.bjlhx15.common.threaddemo;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadDemo01
 * @description TODO
 * @date 2021-02-13 11:00
 */
public class ThreadDemo02StackSize {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("please enter the stack size.");
            System.exit(1);
        }
        ThreadGroup threadGroup = new ThreadGroup("testGroup");
        Runnable runnable = new Runnable() {
            final int MAX = Integer.MAX_VALUE;

            @Override
            public void run() {
                int i = 0;
                recurse(i);
            }

            private void recurse(int i) {
                System.out.println(i);
                if (i < MAX) {
                    recurse(i + 1);
                }
            }
        };
        Thread thread = new Thread(threadGroup, runnable, "Test", Integer.parseInt(args[0]));
        thread.start();
    }
}
