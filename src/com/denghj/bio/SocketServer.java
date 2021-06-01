package com.denghj.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SocketServer
 *
 * @author denghuaijun@eversec.cn
 * @date 2021/5/31 16:51
 * @Description Socket服务端
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true){
            System.out.println("等待连接。。。");
            //阻塞方法
            Socket socketClient = serverSocket.accept();
            System.out.println("有客户端连接。。。。");
           // handler(socketClient); 这样属于同步阻塞消息通讯，也就是如果一个客户端连接及读写操作没有完成，另一个就不能创建连接
            //开个子线程去处理客户端发送的消息 解决了上诉问题，你有没有完成，不影响另一个客户端的船舰连接，但是有很多个客户端的话那么对机器的要求很高，若是使用线程池的话，若是500个线程有499个都创建了连接但是没有消息通讯，所以都会阻塞，线程不得释放，还是会出现阻塞
            new Thread(()->{
                try {
                    handler(socketClient);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void handler(Socket socketClient)throws IOException {
        //定义一个缓冲区来接收数据
        byte[] buffer =new byte[1024];
        System.out.println("read 客户端消息开始。。。。。");
        InputStream inputStream = socketClient.getInputStream();
        //读取客户端的数据，此方法是个阻塞方法，没有数据可接收时就会阻塞
        int read = inputStream.read(buffer);
        System.out.println("read 客户端消息完毕。。。。。");
        if (read !=-1){
            System.out.println("接收到客户端的消息："+new String(buffer,0,buffer.length));
        }

    }
}
