package com.github.bjlhx15.netty.demo.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ServerHeartBeatIdleStateTriggerChannelHandler extends ChannelInboundHandlerAdapter {
    /**
     * @param ctx 上下文
     * @param evt 事件
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if(event.state()== IdleState.READER_IDLE){
                System.out.println(ctx.channel().remoteAddress() + "--读超时时间--" + event.state());
                System.out.println("服务器做对应处理即可");
            }
        }
    }
}
