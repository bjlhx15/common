package com.github.bjlhx15.common.test.other;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ReferenceCountingGC
 * @description TODO
 * @date 2021-03-03 10:17
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;

    //占用点内存，以便在GC中查看是否被回收
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }
}
