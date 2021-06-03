package com.denghj.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ChatServerHandler
 *
 * @author denghuaijun@eversec.cn
 * @date 2021/6/2 10:58
 * @Description 服务端业务处理handler
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    //GlobalEventExecutor.INSTANCE 是一个全局事件执行器，是单例的
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 当客户端连接到服务端就会触发此方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将当前连接的客户端加入聊天室的信息推送给其他已经在线的客户端
        //该方法会将通道组中所有的通道进行遍历，并推送该客户端上线的消息
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"已上线"+sdf.format(new Date())+"\n");
        //将当前客户端加入的组中
        channelGroup.add(channel);
        System.out.println("客户端："+channel.remoteAddress()+"已上线。。。。"+"\n");
    }

    /**
     *
     * 表示channel处于不活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.remove(channel);
        //将当前连接的客户端离开聊天室的信息推送给其他已经在线的客户端
        //该方法会将通道组中所有的通道进行遍历，并推送该客户端上线的消息
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"已下线"+sdf.format(new Date())+"\n");
        //将当前客户端移除组中
        channelGroup.remove(channel);
        System.out.println("客户端："+channel.remoteAddress()+"已下线。。。。"+"\n");
    }


    /**
     * 读取数据
      * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取当前连接的clientChannel
        Channel currentChannel = ctx.channel();
        //遍历组中所有的channel，根据不同的情况推送不同的消息
        channelGroup.forEach(channel -> {
            if (channel !=currentChannel){
                //如果当前连接的客户端不是这个的话，就进行转发给channel
                channel.writeAndFlush("收到了客户端【"+currentChannel.remoteAddress()+"】发送的消息："+msg+"\n");
            }else {
                //如果是就回显
                channel.writeAndFlush("【自己】发送的消息："+msg+"\n");
            }
        });
    }

    /**
     * 服务端读取完消息给客户端的回信
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    /**
     * 客户端连接服务端异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("chatServerHandler。。。。exception");
        cause.printStackTrace();
        ctx.close();
    }
}
