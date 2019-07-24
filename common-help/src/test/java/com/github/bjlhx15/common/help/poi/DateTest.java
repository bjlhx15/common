package com.github.bjlhx15.common.help.poi;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DateTest {

    @Test
    public void testDate() throws Exception {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr="2019-07-23 08:17:01";
        //Date→ String
        String format = sdf.format(date);
        System.out.println(format);
        //String →  Date
        Date date1 = sdf.parse(dateStr);
        System.out.println(date1);
//        2019-07-23 08:25:03
//        Tue Jul 23 08:17:01 CST 2019
    }

    @Test
    public void testParse() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<String> dateStrList = Arrays.asList(
                "2018-04-01 10:00:01",
                "2018-04-02 11:00:02",
                "2018-04-03 12:00:03",
                "2018-04-04 13:00:04",
                "2018-04-05 14:00:05"
        );
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (String str : dateStrList) {
            executorService.execute(() -> {
                try {
                    simpleDateFormat.parse(str);
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }


    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static synchronized Date parse(String s) throws ParseException {
        return sdf.parse(s);
    }



    @Test
    public void testDateAnquan() throws Exception {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr="2019-07-23 08:17:01";
        //Date→ String
        String format = sdf.format(date);
        System.out.println(format);
        //String →  Date
        Date date1 = sdf.parse(dateStr);
        System.out.println(date1);
//        2019-07-23 08:25:03
//        Tue Jul 23 08:17:01 CST 2019
    }
    @Test
    public void testDateThread() throws Exception {
        Date date=new Date();
        String dateStr="2019-07-23 08:17:01";
        //String →  Date
        long start = System.currentTimeMillis();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 500; i++) {
            fixedThreadPool.submit(() -> {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date1 = null;
                try {
                    date1 = sdf.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(date1);
            });
        }
        fixedThreadPool.shutdown();
        Thread.sleep(2000);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
//        Tue Jul 23 08:17:01 CST 2019
//        Wed Jul 24 12:37:01 CST 2019
//        Tue Jul 23 08:17:01 CST 2019
//        Fri Jul 23 08:17:01 CST 1
//        Tue Jul 23 08:17:01 CST 2019
//        Tue Jul 23 08:17:01 CST 2019

    }



}