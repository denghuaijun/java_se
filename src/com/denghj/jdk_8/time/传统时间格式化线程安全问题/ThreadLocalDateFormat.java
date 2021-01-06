package com.denghj.jdk_8.time.传统时间格式化线程安全问题;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDateFormat {

    public static final ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    public static Date convert(String s){
        try {
            return threadLocal.get().parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
