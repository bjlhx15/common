package com.github.bjlhx15.netty.demo.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel listenerChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenerChannel = ServerSocketChannel.open();
            listenerChannel.bind(new InetSocketAddress(PORT));
            listenerChannel.configureBlocking(false);
            listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                int count = selector.select(2000);
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = listenerChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + " 上线");
                        }
                        if (key.isReadable()) {
                            readData(key);
                        }
                        //删除key防止重复处理
                        iterator.remove();
                    }
                } else {
                    System.out.println("waiting……");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count > 0) {
                String msg = new String(buffer.array());
                System.out.println("from 客户端 " + msg);
                //向其他客户端转发消息
                sendInfoToOtherClient(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了");
                key.cancel();
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    //    转发消息给其他客户（即通道）
    private void sendInfoToOtherClient(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息");
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());

                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
