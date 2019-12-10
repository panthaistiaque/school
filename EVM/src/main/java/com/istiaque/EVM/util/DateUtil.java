package com.istiaque.EVM.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 08/Nov/2019.
 */
public class DateUtil {
    public static String currentDate() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(date);
        return currentDate;
    }

    public static String currentDateTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = df.format(date);
        return currentDate;
    }

    public static long uniqueNo() {
        long currentDate = new Date().getTime();
        Random random = new Random();
        int num = random.nextInt(90) + 10;
        currentDate = Long.parseLong(String.valueOf(currentDate) + String.valueOf(num));
        return currentDate;
    }
}
