package com.jd.test.netty;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author jd
 * @date 2022/8/31 16:21
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        //记录已接收的信息
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
        System.out.println(3);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送一条信息
        ctx.writeAndFlush(Unpooled.copiedBuffer("netty rocks: 你好吗？", CharsetUtil.UTF_8));
        System.out.println(1);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
