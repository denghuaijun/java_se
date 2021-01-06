package com.denghj.jdk_8.stream.stream串行流及并行流;

import java.util.concurrent.RecursiveTask;

/**
 * fork/join框架需要继承
 * RecursiveTask 有返回值
 * RecursiveAction 无返回值
 *使用fork/join来计算0-1亿数据和
 */
public class ForkJoinCal extends RecursiveTask<Long> {

    private static final long serialVersionUID= 6462663668821836758L;

    private long start;
    private long end;

    public ForkJoinCal(long start, long end) {
        this.start = start;
        this.end = end;
    }

    //阈值
    private static final long THRESHOLD=10000;


    @Override
    protected Long compute() {
        long len = end - start;
        if (len<=THRESHOLD){
            //若果剩余的长度不到10000则直接累加
            long sum=0;
            for (long i=start;i<=end;i++){
                sum +=i;
            }
            return sum;
        }else {
            long middle=(start+end)/2;
            ForkJoinCal left = new ForkJoinCal(start,middle);
            left.fork();//拆分子任务，同时加入线程队列
            ForkJoinCal right=new ForkJoinCal(middle+1,end);
            right.fork();
            return left.join()+right.join();//汇总子任务结果集ru
        }
    }

    public static void main(String[] args) {

    }
}
