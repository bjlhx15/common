package com.github.bjlhx15.netty.demo.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ServerHeartBeat {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))//在bossGroup增加一个日志处理器
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //加入一个netty提供的IdleStateHandler，空闲状态处理器
                            // long readerIdleTime 表示多长时间没有读，就会发送一个心跳检测包检测是否连接
                            // long writerIdleTim 表示多长时间没有写，就会发送一个心跳检测包检测是否连接
                            // long allIdleTime 表示多长时间没有读写，就会发送一个心跳检测包检测是否连接
                            // 触发一个 IdleStateEvent事件
//                            当IdleStateEvent事件触发后，就会传递给管道下一个handler去处理。
//                            通过调用（触发）下一handler的userEventTiggered。在该方法中去处理（读、写等）
                            pipeline.addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
//                            加入对IdleStateEvent检测进一步处理的handler
                            pipeline.addLast(new ServerHeartBeatIdleStateTriggerChannelHandler());
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast(new ServerMsgChannelHandler());//业务处理的Handler
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();

            System.out.println("Server start listen at " + 7000);
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
