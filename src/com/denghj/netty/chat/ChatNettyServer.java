package com.denghj.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * ChatNettyServer
 *
 * @author denghuaijun@eversec.cn
 * @date 2021/6/2 10:49
 * @Description 基于netty的聊天室服务端
 */
public class ChatNettyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup =new NioEventLoopGroup(8);
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("_".getBytes()));
                            //向管道中添加解码器
                            pipeline.addLast("decoder",new StringDecoder());
                            //向管道中添加编码器
                            pipeline.addLast("encoder",new StringEncoder());
                            //向管道中添加自己业务逻辑处理的handler
                            pipeline.addLast(new ChatServerHandler());
                        }
                    });
            System.out.println("聊天室服务端已启动。。。");
            ChannelFuture future = serverBootstrap.bind(8888).sync();
            System.out.println("=======服务端地址============="+future.channel().localAddress()+"=============");
            future.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
