package com.github.bjlhx15.disruptor.demo.base.httpclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpNettyClient {
    public static void main(String[] args) throws InterruptedException, URISyntaxException, UnsupportedEncodingException {
        EventLoopGroup group = new NioEventLoopGroup(4);
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_TIMEOUT, 8)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new HttpClientCodec());
                            // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                            // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                            ch.pipeline().addLast(new HttpResponseDecoder());
                            ch.pipeline().addLast(new HttpRequestEncoder());
                            ch.pipeline().addLast(new ClientHandler());
//                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            ChannelFuture f = b.connect("127.0.0.1", 8081).sync();
            Channel ch = f.channel();
            URI uri = new URI("http://127.0.0.1:8081/test?q=1");
//            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "http://127.0.0.1:8081/test?q=1", Unpooled.wrappedBuffer("{ \"test\" : \"test\" }".getBytes()));  //发送请求道服务端
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString(), Unpooled.wrappedBuffer("".getBytes("UTF-8")));  //发送请求道服务端
//            request.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, 1024);  //必须设置Content length否则服务端收不到content
            request.headers().set(HttpHeaders.Names.HOST, "127.0.0.1");

            System.out.println("aaa:" + request.content().toString(CharsetUtil.UTF_8));

            ch.writeAndFlush(request).sync();
            //wait until server to close the connection
            ch.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

}
