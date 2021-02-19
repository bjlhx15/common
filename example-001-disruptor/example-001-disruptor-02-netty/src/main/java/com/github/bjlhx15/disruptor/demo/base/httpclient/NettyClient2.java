package com.github.bjlhx15.disruptor.demo.base.httpclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class NettyClient2 {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8081;


    //扩展 完善 池化: ConcurrentHashMap<KEY -> String, Value -> Channel>
    private Channel channel;

    //1. 创建工作线程组: 用于实际处理业务的线程组
    private EventLoopGroup workGroup = new NioEventLoopGroup();

    private ChannelFuture cf;

    public NettyClient2() {
        this.connect(HOST, PORT);
    }

    private void connect(String host, int port) {
        //2 辅助类(注意Client 和 Server 不一样)
        Bootstrap bootstrap = new Bootstrap();
        try {

            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    //表示缓存区动态调配（自适应）
                    .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                    //缓存区 池化操作
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)

                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_TIMEOUT, 8)

                    .handler(new LoggingHandler(LogLevel.INFO))

                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                            // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                            sc.pipeline().addLast(new HttpResponseDecoder());
                            sc.pipeline().addLast(new HttpRequestEncoder());
                            sc.pipeline().addLast(new ClientHandler());
                            System.out.println("===tid2==="+Thread.currentThread().getId());
                        }
                    });
            //绑定端口，同步等等请求连接
            this.cf = bootstrap.connect(host, port).sync();
            System.err.println("Client connected...");

            //接下来就进行数据的发送, 但是首先我们要获取channel:
            this.channel = cf.channel();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //发送数据
    public void sendData(int i) throws Exception {
//        for (int i = 0; i < 10; i++) {
//                TranslatorData request = new TranslatorData();
//                request.setId("" + i);
//                request.setName("请求消息名称 " + i);
//                request.setMessage("请求消息内容 " + i);
//                this.channel.writeAndFlush(request);
        System.out.println("===tid==="+Thread.currentThread().getId());
            URI uri = new URI("http://127.0.0.1:8081/test?q="+i);
//            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "http://127.0.0.1:8081/test?q=1", Unpooled.wrappedBuffer("{ \"test\" : \"test\" }".getBytes()));  //发送请求道服务端
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, uri.toASCIIString(), Unpooled.wrappedBuffer("".getBytes("UTF-8")));  //发送请求道服务端
//            request.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, 1024);  //必须设置Content length否则服务端收不到content
            request.headers().set(HttpHeaders.Names.HOST, "127.0.0.1");

            System.out.println("aaa:" + request.content().toString(CharsetUtil.UTF_8));

            this.channel.writeAndFlush(request).sync();
//        }
    }

    public void close() throws Exception {
        cf.channel().closeFuture().sync();
        //优雅停机
        workGroup.shutdownGracefully();
        System.err.println("Sever ShutDown...");
    }
}
