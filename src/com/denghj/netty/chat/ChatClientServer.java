package com.denghj.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * ChatNettyServer
 *
 * @author denghuaijun@eversec.cn
 * @date 2021/6/2 10:49
 * @Description 基于netty的聊天室客户端
 */
public class ChatClientServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    //.option(ChannelOption.SO_BACKLOG,1024)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("_".getBytes()));
                            //向管道中添加解码器
                            pipeline.addLast("decoder",new StringDecoder());
                            //向管道中添加编码器
                            pipeline.addLast("encoder",new StringEncoder());
                            //向管道中添加自己业务逻辑处理的handler
                            pipeline.addLast(new ChatClientHandler());
                        }
                    });
            System.out.println("聊天室客户端已启动。。。");
            ChannelFuture future = bootstrap.connect("127.0.0.1",8888).sync();
            System.out.println("=======客户端地址============="+future.channel().localAddress()+"=============");
            //客户端需要循环输入信息，创建一个扫描器
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                //通过channel发送到服务端
                future.channel().writeAndFlush(line);
            }
           // future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }
}
