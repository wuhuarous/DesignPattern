package com.jd.test.netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author jd
 * @date 2022/8/31 15:26
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String a = "我很好";
        //将收到的消息写给发送者，
        ByteBuf in = Unpooled.wrappedBuffer(a.getBytes(CharsetUtil.UTF_8));
        ByteBuf msgs = (ByteBuf) msg;
        System.out.println("SERVER RECEIVED:" + msgs.toString(CharsetUtil.UTF_8));
        ctx.write(in);
        System.out.println(2);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
