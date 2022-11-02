package com.github.bjlhx15.common.other.fullgc;

import java.util.ArrayList;
import java.util.List;

public class SystemGcTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            createBigObj(21);
            System.gc();
        }
    }

    private static void createBigObj(int n) {
        List<byte[]> bytes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            bytes.add(new byte[1024 * 1024 * 10]);
        }
    }
}
