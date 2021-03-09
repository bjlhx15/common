package com.github.bjlhx15.common.threaddemo.eg04Lock;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author lihongxu6
 * @version 1.0
 * @className BooleanLockTest
 * @description TODO
 * @date 2021-02-18 16:03
 */
public class BooleanLockTest {
    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        try {
            lock.lock();
            int randomInt = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread() + " get the lock");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws DecoderException, UnsupportedEncodingException {
//        final BooleanLockTest booleanLockTest = new BooleanLockTest();
//        IntStream.range(1, 10)
//                .mapToObj(p -> new Thread(() -> {
//                    booleanLockTest.syncMethod();
//                }))
//                .forEach(Thread::start);

        Integer a=3;
        Integer b=3;

        //System.identityHashCode(a) 该值是内存地址通过算法换算的一个整数值
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));

//a.equals()
        Integer a1=300;
        Integer b1=300;

        System.out.println(System.identityHashCode(a1));
        System.out.println(System.identityHashCode(b1));

        String pin="xtl_ZFVTUXj";
        String s = Hex.encodeHexString(pin.getBytes("UTF-8"));
        System.out.println(s);
//        byte[] bytes = ;
        System.out.println(new String(Hex.decodeHex(s), Charset.forName("UTF8")));
    }
}