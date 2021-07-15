import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

public class Main {

  @Test
    public void write() throws IOException {
      Map<String,Object> map1 = new HashMap<>();
      map1.put("host","localhost");
      map1.put("dip","10.10.10.10");
      map1.put("dport","3000");
      map1.put("dcount","21385583");
      map1.put("host","localhost");
      map1.put("dip","10.10.10.10");
      Map<String,Object> map2 = new HashMap<>();
      map2.put("02","dhj22");
      map2.put("warnlevel","1");
      List<Map<String,Object>> list = Arrays.asList(map1,map2);
      File file = new File("D:\\test0607.txt");
      OutputStream outputStream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(outputStream);
      BufferedWriter out = new BufferedWriter(writer);
      for (Map<String, Object> map : list) {
          StringBuffer stringBuffer = new StringBuffer();
          int len=0;
          for (Map.Entry<String,Object> entry:map.entrySet()){
              ++len;
              if (len==map.size()){
                  stringBuffer.append(entry.getValue());
              }else {
                  stringBuffer.append(entry.getValue()).append(" | ");
              }
          }
          try {
              out.write(stringBuffer.toString());
              out.newLine();
              out.flush();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      out.close();


  }


    public String formatStr(String str, int length) {
        str = "  "+str;
        int strLen = str.getBytes().length;
        if (strLen < length) {
            int temp = length - strLen;
            for (int i = 0; i < temp; i++) {
                str += ",| ";
            }
        }
        return str ;
    }


    @Test
    public void test1(){
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    }

    @Test
    public void test2(){
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<String> testList =new ArrayList<>();
        testList.add("cf1");
        testList.add("cf2");
        long start = System.currentTimeMillis();
        CompletableFuture<Void> all = null;
        for (String str : testList) {
            // 定义任务
            CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return str;
            }, executor);

            cf.whenComplete(new BiConsumer<String, Throwable>() {
                @Override
                public void accept(String t, Throwable u) {
                    System.out.println("hello " + t);
                }
            });
            all = CompletableFuture.allOf(cf);
        }
        System.out.println("start block");
        // 开始等待所有任务执行完成
        all.join();
        System.out.println("block finish, consume time:" + (System.currentTimeMillis() - start));
        executor.shutdown();
    }
}
