package com.denghj.bio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * SocketClient
 *
 * @author denghuaijun@eversec.cn
 * @date 2021/5/31 17:17
 * @Description Socket客户端
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket =new Socket("localhost",9999);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer =new PrintWriter(outputStream,true);
        Scanner scanner=new Scanner(System.in);
        String next = scanner.next();
        while (!next.equals("bye")){
            writer.write(next);
            writer.flush();
        }


    }
}
