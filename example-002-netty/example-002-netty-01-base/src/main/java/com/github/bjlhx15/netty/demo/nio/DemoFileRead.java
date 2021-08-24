package com.github.bjlhx15.netty.demo.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoFileRead {
    public static void main(String[] args) throws Exception {
        //传统IO读取
        File file = new File("test.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        byte[] bytes = new byte[(int) file.length()];
        raf.read(bytes);

        //  传统网路读取
        Socket socket = new ServerSocket(8080).accept();
        socket.getOutputStream().write(bytes);
    }
}
