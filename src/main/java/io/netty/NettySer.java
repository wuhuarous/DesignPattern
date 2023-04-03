/*
package io.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

*/
/**
 * @author jd
 * @date 2023/3/7 16:16
 *//*

public class NettySer {


    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                // BossGroup the Thread group is responsible for connection events.
                // WorkGroup the thread group is responsible for read and write events.
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ObjectEncoder());
                        pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE,
                                ClassResolvers.cacheDisabled(null)));
                        pipeline.addLast(new ServerHandler(hisRunDataService));
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind(Integer.parseInt(serverBootstrapProperties.getNettyServerPort())).sync();
        channelFuture.channel().closeFuture().sync();
    }
}
*/
