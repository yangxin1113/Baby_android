package com.zlzkj.app.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zyx on 2017/1/28.
 */

public class TimeUtils {

    private TimeUtils() {
        throw new AssertionError();
    }

    public static String getDateString(int year, int day, int monthOfYear,String format){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = day + "/" + monthOfYear + "/" + year;
            Date date = formatter.parse(dateInString);
            formatter = new SimpleDateFormat(format);
            String dateString = formatter.format(date).toString();
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)* 1000L));
    }

    /**
     * 日期格式字符串转换成时间戳
     * @param date 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str = sdf.format(date);
        return str;
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }

}
