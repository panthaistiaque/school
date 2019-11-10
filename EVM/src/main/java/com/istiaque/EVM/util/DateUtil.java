package com.istiaque.EVM.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
