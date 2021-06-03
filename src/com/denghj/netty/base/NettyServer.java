package com.denghj.netty.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * NettyServer
 *
 * @author denghuaijun@eversec.cn
 * @date 2021/6/1 18:10
 * @Description netty基础入门服务端
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建2个线程组BossGroup和WorkGroup，含有的子线程个数NioEventLoop的个数默认是cpu核数的2倍
        //BossGroup主要处理连接请i去，而WorkGroup主要处理连接之后的业务请求
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(8);
        try{
            //创建服务器的启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            //使用链式编程为服务启动脚本配置参数
            bootstrap.group(bossGroup,workGroup)
                    //使用NioServerSocketChannel作为服务器通道的实现
                    .channel(NioServerSocketChannel.class)
                    //初始化服务器连接队列大小，服务器处理客户端连接请求是顺序处理的，所以同一时间只能处理同一个客户端
                    //多个客户端同时来的时候，服务端将不能处理的客户端请求都放在队列中等待
                    .option(ChannelOption.SO_BACKLOG,1024)
                    //对workGroup的SocketChannel设置处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("netty server start......");
            //绑定一个端口，并且同步生成一个ChannelFuture的一部对象，通过isDone()等方法可以判断异步事件执行的情况
            //启动服务器并绑定端口，bind是异步操作，sync方法是等待异步操作完成
            ChannelFuture channelFuture = bootstrap.bind(9000).sync();
            //给channelFuture绑定监听器，监听我们关心的事件
//            channelFuture.addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture future) throws Exception {
//                    if (future.isSuccess()){
//                        System.out.println("监听端口9000成功。。。");
//                    }else {
//                        System.out.println("监听端口9000失败。。。");
//                    }
//                }
//            });
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
