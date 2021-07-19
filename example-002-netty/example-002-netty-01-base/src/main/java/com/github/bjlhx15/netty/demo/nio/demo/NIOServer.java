package com.github.bjlhx15.netty.demo.nio.demo;

import org.apache.catalina.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws Exception {
//        创建ServerSocketChannel-》ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//        得到一个Selector
        Selector selector = Selector.open();
//        绑定一个6666端口，在服务端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
//        设置为非阻塞
        serverSocketChannel.configureBlocking(false);
//        把serversocketchannel注册到Selector上。关系事件为OP
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            if (selector.select(1000) == 0) {
//                没有事件发生，
                System.out.println("服务器等待了1s.无连接");
                continue;
            }
//            有时间发生的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成，生产了一个socketChannel=" + socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    channel.read(buffer);
                    System.out.println("from客户端" + new String(buffer.array()));
                }
                keyIterator.remove();
            }
        }
    }
}
