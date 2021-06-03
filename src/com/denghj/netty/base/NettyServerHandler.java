package com.denghj.netty.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * NettyServerHandler
 *自定义的handler需要集成netty规定好的handlerAdapter
 * @author denghuaijun@eversec.cn
 * @date 2021/6/1 19:05
 * @Description netty 服务端处理器
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当客户端连接服务器端完成时，就会触发这个方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接通道建立完成。。。。");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端连接已断开。。。");
    }

    /**
     * 读取到客户端消息之后，返回给客户端的回应
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello client".getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(byteBuf);
    }

    /**
     * 读取客户端发送的数据
     * @param ctx 上下文对象，含有通道channel和管道pipeline
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();//本质就是双向链接，出站入站
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到客户端的消息："+byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 读取客户端发送数据发生异常触发方法
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端异常。。。。");
        cause.printStackTrace();
        ctx.close();
    }
}
