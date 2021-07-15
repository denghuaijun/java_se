package com.denghj.netty.directbuffer;

import org.junit.rules.Stopwatch;

import java.nio.ByteBuffer;

/**
 * DirectMemoryTest
 *
 * @author denghuaijun@eversec.cn
 * @date 2021/6/2 15:16
 * @Description 测试直接内存与堆内存的区别
 */
public class DirectMemoryTest {
    public static void heapAccess(){
        long startTime = System.currentTimeMillis();
        //分配堆内存
        ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
        for (int i = 0; i < 100000; i++) {
            for (int i1 = 0; i1 < 200; i1++) {
                byteBuffer.putInt(i1);
            }
            byteBuffer.flip();
            for (int i1 = 0; i1 < 200; i1++) {
                byteBuffer.getInt(i1);
            }
            byteBuffer.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("堆内存访问"+(endTime-startTime)+"ms");
    }
    public static void directAccess(){
        long startTime = System.currentTimeMillis();
        //分配直接内存
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1000);
        for (int i = 0; i < 100000; i++) {
            for (int i1 = 0; i1 < 200; i1++) {
                byteBuffer.putInt(i1);
            }
            byteBuffer.flip();
            for (int i1 = 0; i1 < 200; i1++) {
                byteBuffer.getInt(i1);
            }
            byteBuffer.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("直接内存访问"+(endTime-startTime)+"ms");
    }

    public static void main(String[] args) {
        heapAccess();
        directAccess();
    }
}
