package com.denghj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NioSelectorServer
 *
 * @author denghuaijun@eversec.cn
 * @date 2021/5/31 18:24
 * @Description NIO selector 服务端
 */
public class NioSelectorServer {
    public static void main(String[] args) throws IOException {
        //创建NIOServer
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        //设置ServerSocketChannel 为非阻塞
        serverSocketChannel.configureBlocking(false);
        //打开selector处理channel，即创建epoll
        Selector selector = Selector.open();
        //把服务端ServerSocketChannel注册到selector上，并且设置selector对客户端的accept操作进行感知
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("NIO selector 服务端启动成功。。。。");
        while(true){
            //阻塞等待需要处理的事件发生,这个事件有可能是发生在客户端与服务端之间建立连接，也可能发生在客户端读取消息
            selector.select();
            //获取selector中注册的全部事件的selectionKey 示例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isAcceptable()){//如果是建立连接的事件，获取客户端的channel，同时将客户端也注册在多路复用器上，下次若是客户端有消息产生，那么就可以监听到
                    ServerSocketChannel serverChannel= (ServerSocketChannel)key.channel();
                    //创建连接
                    SocketChannel socketChannel = serverChannel.accept();
                    //打开客户端channel的非阻塞配置
                    socketChannel.configureBlocking(false);
                    //同时将客户端也加入到多路复用的监听上
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println("客户端已经连接成功。。。");
                }else if (key.isReadable()){
                    //如果是客户端的发送消息事件
                    SocketChannel channel= (SocketChannel)key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    int read = channel.read(byteBuffer);
                    if (read>0){
                        System.out.println("读取到消息："+new String(byteBuffer.array()));
                    }else if (read ==-1){
                        iterator.remove();//客户端与服务端断开，移除客户端
                        System.out.println("客户端断开连接");
                        channel.close();
                    }

                }
                //从事件集合里面移除本次处理的事件key，防止重复处理
                iterator.remove();
            }
        }
    }
}
