package com.istiaque.EVM.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Mohammad Istiaque Hossain (DS00688) on 08/Nov/2019.
 */
public class DateUtil {
    private static final int FIVE_DAY =  86400000*5;
    private static final int TWO_DAY =  86400000*2;
    private static final int ONE_DAY =  86400000*1;
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

    public static String nominationCloseDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(date.getTime()- FIVE_DAY );
        return currentDate;
    }
    public static String electionDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(date.getTime()- TWO_DAY );
        return currentDate;
    }
    public static String electionResultDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(date.getTime()- ONE_DAY );
        return currentDate;
    }

}
