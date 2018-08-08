package cn.com.gree.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTransform {

    // 接口日期转换器
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");

    // 前端日期转换器
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @author Abin
     * @date 2018/8/7 17:34
     * 增加hour小时
     */
    public static Date convert (Date date, int hour){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        return cal.getTime();
    }

    /**
     * @author Abin
     * @date 2018/8/8 8:24
     * 转换成Date
     */
    public static Date toDate(String dateStr){
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("转换失败。" + e.getMessage());
        }
        return date;
    }

    /**
     * @author Abin
     * @date 2018/8/8 8:25
     * 转换成String
     */
    public static String toDateStr(Date date){
        return sdf2.format(date);
    }
}
