package com.github.bjlhx15.netty.demo.nio;

import java.nio.Buffer;
import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        //示例 Buffer
        // 创建一个Buffer 大小为5
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        //读取buffer数据
        //将buffer转换，读写切换
//        public final Buffer flip() {
//            limit = position;
//            position = 0;
//            mark = -1;
//            return this;
//        }
        intBuffer.flip();
//        读
        while (intBuffer.hasRemaining()) {
            //，每读取一次 指针向下走一下
            System.out.println(intBuffer.get());
        }
    }
}
