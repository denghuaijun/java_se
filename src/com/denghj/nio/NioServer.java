package com.denghj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * NioServer
 *
 * @author denghuaijun@eversec.cn
 * @date 2021/5/31 18:01
 * @Description 没有选择器的NIO程序
 */
public class NioServer {
    private static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //创建NIOServer
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        //设置ServerSocketChannel 为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("NIO server 启动成功。。。");
        while (true){
            //非阻塞IO的accept方法不会阻塞
            //NIO的非阻塞是由操作系统内部实现的，底层调用了linux内核的accept方法
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel !=null){
                System.out.println("客户都拿连接服务成功。。。");
                socketChannel.configureBlocking(false);//设置socketChannel非阻塞
                //保存客户端连接在list中
                channelList.add(socketChannel);
            }
            //遍历连接进行数据读取
            Iterator<SocketChannel> iterator = channelList.iterator();
            //缺点假如有多个连接，但是只有几个连接在使用，但是这个循环一直在进行全部遍历，性能不好，后面添加选择器，我只将有数据的连接放在集合中，所以衍生出来了selector
            while (iterator.hasNext()){
                SocketChannel channel = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                int read = channel.read(byteBuffer);
                if (read>0){
                    System.out.println("读取到消息："+new String(byteBuffer.array()));
                }else if (read ==-1){
                    iterator.remove();//客户端与服务端断开，移除客户端
                    System.out.println("客户端断开连接");
                }

            }
        }
    }
}
