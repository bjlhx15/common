package com.github.bjlhx15.netty.demo.netty.heartbeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.HashedWheelTimer;

import java.util.concurrent.TimeUnit;

public class ClientHeartBeat {
    protected final HashedWheelTimer timer = new HashedWheelTimer();
    private Bootstrap boot;
    private final ClientConnectorIdleStateTriggerChannelHandler idleStateTrigger = new ClientConnectorIdleStateTriggerChannelHandler();

    public void connect(int port, String host) {
        EventLoopGroup group = new NioEventLoopGroup();
        boot = new Bootstrap();
        boot.group(group).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO));

        final ConnectionWatchdog watchdog = new ConnectionWatchdog(boot, timer, port, host, true) {
            @Override
            public ChannelHandler[] handlers() {
                return new ChannelHandler[]{
                        this,
                        new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS),
                        idleStateTrigger,
                        new StringDecoder(),
                        new StringEncoder(),
                        new ClientMsgChannelHandler()
                };
            }
        };

        ChannelFuture future = null;
        //进行连接
        try {
            synchronized (boot) {
                boot.handler(new ChannelInitializer<Channel>() {
                    //初始化channel
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(watchdog.handlers());
                    }
                });

                future = boot.connect(host, port);

            }// 以下代码在synchronized同步块外面是安全的
            future.sync();
            if (!future.isSuccess()) {
                System.out.println("---- 连接服务器失败,2秒后重试 ---------port=" + port);
                this.scheduleStart(future.channel(), port, host);
            }

        } catch (Throwable t) {
            System.out.println("connects to  fails." + t.getMessage());
            System.out.println("---- 连接服务器失败,2秒后重试 ---------port=" + port);
            Channel channel = future != null && future.channel() != null ? future.channel() : new LocalChannel();
            this.scheduleStart(channel, port, host);
        }
    }

    public void scheduleStart(Channel channel, int port, String host) {
        channel.eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                connect(port, host);
            }
        }, 2L, TimeUnit.SECONDS);
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new ClientHeartBeat().connect(7000, "127.0.0.1");
    }
}
