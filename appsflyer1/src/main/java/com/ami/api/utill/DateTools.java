package com.ami.api.utill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * 
 * Title: 日期转换�?
 * 
 * Description: 进行日期转换的工具类
 * 
 * Copyright: Copyright (c) 2009
 * 
 * @author zhuweiliang
 * @version 1.0
 * @see none
 * @since Safaricom
 */
public final class DateTools
{
    
    /**
     * 日期字符串格式yyyyMMddHHmmss,得到的小时数�?0~23�?
     */
    public static final String DATE_24_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    
    /**
     * 注释内容
     */
    public static final int YEAR = 0;
    
    /**
     * 注释内容
     */
    public static final int MONTH = 1;
    
    /**
     * 注释内容
     */
    public static final int WEEK = 2;
    
    /**
     * 注释内容
     */
    public static final int DAY = 3;
    
    /**
     * 注释内容
     */
    public static final int HOUR = 4;
    
    /**
     * 注释内容
     */
    public static final int MINUTE = 5;
    
    /**
     * 注释内容
     */
    public static final int SECOND = 6;
    
    /**
     * 注释内容
     */
    public static final int MILLISECOND = 7;
    
    /**
     * 注释内容
     */
    public static final int MINUTEOFDAY = 8;
    
    /**
     * 毫秒
     */
    public static final int MSEL = 1000;
    
    /**
     * 小时
     */
    public static final int HOURS = 60;
    
    /**
     * <默认构�?�函�?>
     */
    private DateTools()
    {
    }
    
    /**
     * 获取指定格式的当前日�?,格式描述符的含义参见JDK simpleDateFormat�?
     * 
     * @param pattern String 日期格式，如:yyyyMMddHHmmss
     * @return String
     */
    public static String getCurrentDate(String pattern)
    {
        if (pattern == null)
        {
            throw new IllegalArgumentException("input string parameter is null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date now = new Date();
        return sdf.format(now);
    }
    
    /**
     * 将日期增加一个增量，目前只能是，年，月，周，日，时�?�分、秒、毫�?
     * 
     * @param date long 原始时间
     * @param delta int 增量的大�?
     * @param unit int 增量的单位，YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, MILLISECOND
     * @return long 从格林威治时间：1970�?1�?1�?0点起的毫秒数
     */
    public static long getDateByOffset(long date, int delta, int unit)
    {
        if ((unit < YEAR) || (unit > MILLISECOND))
        {
            throw new IllegalArgumentException("time unit must in [YEAR, MONTH, " + "WEEK, DAY, HOUR, MINUTE, SECOND, MILLISECOND], others not support");
        }
        Date dt = new Date(date);
        Calendar calendar = getLocalCalendar(dt);
        // 增加增量
        switch (unit)
        {
            case YEAR:
                calendar.add(Calendar.YEAR, delta);
                break;
            case MONTH:
                calendar.add(Calendar.MONTH, delta);
                break;
            case WEEK:
                calendar.add(Calendar.DAY_OF_WEEK, delta);
                break;
            case DAY:
                calendar.add(Calendar.DAY_OF_MONTH, delta);
                break;
            case HOUR:
                calendar.add(Calendar.HOUR, delta);
                break;
            case MINUTE:
                calendar.add(Calendar.MINUTE, delta);
                break;
            case SECOND:
                calendar.add(Calendar.SECOND, delta);
                break;
            case MILLISECOND:
                calendar.add(Calendar.MILLISECOND, delta);
                break;
            default:
                break;
        }
        // 获取新的时间，并转换成长整形
        Date ndt = calendar.getTime();
        return ndt.getTime();
    }
    
    /**
     * 获得东八时区的日历，并设置日历的当前日期
     * 
     * @param date Date 日期�?
     * @return Calendar
     */
    public static Calendar getLocalCalendar(Date date)
    {
        // 设置为GMT+08:00时区
        String[] ids = TimeZone.getAvailableIDs(MINUTEOFDAY * HOURS * HOURS * MSEL);
        if (ids.length == 0)
        {
            throw new IllegalArgumentException("get id of GMT+08:00 time zone failed");
        }
        // SimpleTimeZone stz = new SimpleTimeZone(8 * 60 * 60 * 1000, ids[0]);
        // 创建Calendar对象，并设置为指定时�?
        // Calendar calendar = new GregorianCalendar(stz);
        Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
        // 设置成宽容方�?
        if (!calendar.isLenient())
        {
            calendar.setLenient(true);
        }
        // 设置SUNDAY为每周的第一�?
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        // 设置日历的当前时�?
        calendar.setTime(date);
        return calendar;
    }
    
    /**
     * 将日期长整型转换成字符串
     * 
     * @param time long 从格林威治时间：1970�?1�?1�?0点起的毫秒数
     * @param pattern String 转换的目标格�?
     * @return String
     */
    public static String long2TimeStr(long time, String pattern)
    {
        if (pattern == null)
        {
            throw new IllegalArgumentException("pattern parameter can not be null");
        }
        Date dt = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(dt);
    }
    
    /**
     * 
     * 将字符串转换成日期长整型
     * 
     * @param timeString String 要转换的字符�?
     * @param formate String 转换的目标格�?
     * @return long 从格林威治时间：1970�?1�?1�?0点起的毫秒数
     * @throws ParseException ParseException
     */
    public static long timeStr2Long(String timeString, String formate)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        Date myDay = sdf.parse(timeString);
        return myDay.getTime();
    }
    
    /**
     * 按要求转化时间的显示格式
     * 
     * @param time String 时间
     * @param oldpattern String 旧日期格式，�?:yyyyMMddHHmmss 格式描述符的含义参见JDK
     * @param newpattern String 新日期格�?
     * @return String
     */
    public static String timeTransform(String time, String oldpattern, String newpattern)
    {
        if (oldpattern == null)
        {
            throw new IllegalArgumentException("the old pattern is null");
        }
        if (newpattern == null)
        {
            throw new IllegalArgumentException("the new pattern is null");
        }
        if (time == null)
        {
            return null;
        }
        SimpleDateFormat olddatepattern = new SimpleDateFormat(oldpattern);
        Date now = null;
        try
        {
            now = olddatepattern.parse(time);
            // 用原来的pattern解析日期，再和原来的比较，由此来�?查时间是否合�?
            String oldtime = olddatepattern.format(now);
            if (!oldtime.equals(time))
            {
                throw new IllegalArgumentException("using Illegal time");
            }
        }
        catch (ParseException e)
        {
            
        }
        SimpleDateFormat newdatepattern = new SimpleDateFormat(newpattern);
        
        return newdatepattern.format(now);
    }
    
    /**
     * 将时间yyyyMMddhhmmss转为yyyy-MM-dd
     * hh:mm:ss、yyyyMMdd转为yyyy-MM-dd、yyyyMM转为yyyy-MM
     * 
     * @param dateStr
     * @return
     * @see [类�?�类#方法、类#成员]
     */
    public static String toChangeMyDate(String dateStr)
    {
        String str = "";
        
        if (StringTool.isBlank(dateStr))
        {
            return str;
        }
        
        SimpleDateFormat sdf1 = null;
        SimpleDateFormat sdf2 = null;
        dateStr = dateStr.trim();
        if (14 == dateStr.length())
        {
            sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        else if (8 == dateStr.length())
        {
            sdf1 = new SimpleDateFormat("yyyyMMdd");
            sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        }
        else if (6 == dateStr.length())
        {
            sdf1 = new SimpleDateFormat("yyyyMM");
            sdf2 = new SimpleDateFormat("yyyy-MM");
        }
        
        if (!StringTool.isBlank(dateStr) && null != sdf1 && null != sdf2)
        {
            try
            {
                str = sdf2.format(sdf1.parse(dateStr));
            }
            catch (ParseException e)
            {
            }
        }
        
        return str;
    }
    
    /**
     * 将日期型转换成字符串
     * 
     * @param time Date
     * @param pattern String 转换的目标格�?
     * @return String
     */
    public static String date2TimeStr(Date time, String pattern)
    {
        if (pattern == null)
        {
            
            throw new IllegalArgumentException("pattern parameter can not be null");
        }
        if (time == null)
        {
            
            throw new IllegalArgumentException("time parameter can not be null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(time);
    }
    
    /**
     * 将日期增加一个增量，目前只能是，年，月，周，日，时，分，秒，毫秒
     * 
     * @param date long 原始时间
     * @param delta int 增量的大�?
     * @param unit int 增量的单位，YEAR, MONTH, WEEK,DAY, HOUR, MINUTE, SECOND,
     *            MILLISECOND
     * @param pattern String 转换的目标格�?
     * @return String 指定格式的日期字符串
     */
    public static String addDate(long date, int delta, int unit, String pattern)
    {
        if (pattern == null)
        {
            throw new IllegalArgumentException("pattern parameter can not be null");
        }
        return long2TimeStr(getDateByOffset(date, delta, unit), pattern);
    }
    
    /**
     * 比较两个yyyyMMddhhmmss格式的日期之间相差多�?(以豪秒为单位)
     * 
     * @param time1 第一个时�?
     * @param time2 第二个时�?
     * @return 时间�?()
     */
    public static long timeDistinction(String time1, String time2)
    {
        long distinction = 0;
        try
        {
            distinction = timeStr2Long(time1, DATE_24_YYYYMMDDHHMMSS) - timeStr2Long(time2, DATE_24_YYYYMMDDHHMMSS);
        }
        catch (ParseException e)
        {
        }
        
        return distinction;
    }
    
    /**
     * 比较两个yyyyMMddhhmmss格式的日期之间相差多�?(以豪秒为单位)
     * 
     * @param time1 第一个时�?
     * @param time2 第二个时�?
     * @return 时间�?()
     */
    public static long timeDistinction(String time1, String formate1, String time2, String formate2)
    {
        long distinction = 0;
        try
        {
            distinction = timeStr2Long(time1, formate1) - timeStr2Long(time2, formate2);
        }
        catch (ParseException e)
        {
        }
        
        return distinction;
    }
    
    /**
     * �?2010-01-21 00:00:00时间格式转为20100121000000
     * 
     * @param date 源字符串
     * @return String 目标字符�?
     * @see [类�?�类#方法、类#成员]
     */
    public static String chageDate(String date)
    {
        
        date = date.replaceAll("-", "");
        date = date.replaceAll(" ", "");
        date = date.replaceAll(":", "");
        
        return date;
    }
    
    /**
     * 算出两个小时差的所有时间点
     * 
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String[] getAllDateByHour(String start, String end)
    {
        long distinction = DateTools.timeDistinction(end, start);
        
        int hour = (int)(distinction / 1000 / 3600) + 1;
        
        String datatimes[] = new String[hour];
        
        long endtime;
        
        try
        {
            endtime = DateTools.timeStr2Long(end, "yyyyMMddHHmmss");
            
            for (int i = 0; i < hour; i++)
            {
                datatimes[hour - i - 1] = DateTools.addDate(endtime, -1 * i, DateTools.HOUR, "yyyyMMddHHmmss");
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        
        return datatimes;
    }
    
    /**
     * 算出两个时间的天差的所有时间点
     * 
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String[] getAllDateByDay(String start, String end)
    {
        long distinction = DateTools.timeDistinction(end, start);
        
        int day = (int)(distinction / 1000 / (3600 * 24)) + 1;
        
        String datatimes[] = new String[day];
        
        long endtime;
        
        try
        {
            endtime = DateTools.timeStr2Long(end, "yyyyMMddHHmmss");
            
            for (int i = 0; i < day; i++)
            {
                datatimes[day - i - 1] = DateTools.addDate(endtime, -1 * i, DateTools.DAY, "yyyy-MM-dd");
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        
        return datatimes;
    }
    
    /**
     * 算出两个时间的天差的所有时间点
     * 
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static List<String> getAllDateByMonth(String start, String end)
    {
        
        List<String> list = new ArrayList<String>();
        list.add(start);
        try
        {
            
            long temp_end_time;
            temp_end_time = DateTools.timeStr2Long(start, "yyyy年MM月");
            
            while (true)
            {
                
                String temp_end = DateTools.addDate(temp_end_time, 1, DateTools.MONTH, "yyyy年MM月");
                temp_end_time = DateTools.timeStr2Long(temp_end, "yyyy年MM月");
                if (temp_end.compareTo(end) == 0)
                {
                    break;
                }
                
                list.add(temp_end);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        list.add(end);
        return list;
    }
    
    /**
     * 获取两个日期间的所有日期
     * 
     * 日期格式  start, end都是  YYYY-MM-DD 结果也是 YYYY-MM-DD
     * @param start
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static List<String> getAllDatesBetweenDates(String start, String end)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        List<String> result = new ArrayList<String>(); 
        try{  
            Date statdate = sdf.parse(start);  
            Date enddate = sdf.parse(end);  
              
            Calendar calendar = Calendar.getInstance();  
            calendar.setTime(statdate);  
 
            /** 
             * Date.compareTo(Date date) == 0两个时间相等， 
             * 如果此 Date 在 Date 参数之前，则返回小于 0 的值； 
             * 如果此 Date 在 Date 参数之后，则返回大于 0 的值。  
             */  
            while(statdate.before(enddate) || statdate.compareTo(enddate)==0){  
                result.add(sdf.format(calendar.getTime()));  
                calendar.add(Calendar.DAY_OF_MONTH,1);  
                statdate = calendar.getTime();  
            }        
        }catch(Exception e){  
            e.printStackTrace();  
        } 
        return result;
    }
    
    public static void main(String[] args)
        throws ParseException
    {
        
        // Date date = new Date("Thu Jan 17 2013 00:00:00 GMT+0800 (中国标准时间)");
        //
        // String ss =
        // DateTools.addDate(DateTools.timeStr2Long("20120424080000",
        // "yyyyMMddHHmmss"), 280, DateTools.DAY, "yyyyMMddHHmmss");
        //
        // // String d = DateTools.date2TimeStr(date, "yyyymmdd");
        // //
        // // String st2 = DateTools.toChangeMyDate("20120401235908");
        // System.out.println(ss);
        // //
        // // String endtime ="20120401235908";
        // //
        // //
        // // String s = DateTools.addDate(DateTools.timeStr2Long(endtime,
        // // "yyyyMMddHHmmss"), -3600, DateTools.SECOND, "yyyyMMddHHmmss");
        // //
        // // System.out.println(s);
        // //
        // //
        // // String s2 = DateTools.long2TimeStr(new
        // // Long("1334295489000"),"yyyyMMddHHmmss");
        // //
        // // System.out.println(s2);
        //
        // String expired_time = "2013-11-01";
        //
        // long disMillis = DateTools.timeStr2Long(expired_time, "yyyy-MM-dd") -
        // System.currentTimeMillis();
        //
        // float day = (float)(disMillis / (1000 * 3600 * 24.0));
        //
        // System.out.println(day);
        String start="2012-8-9";  
        String end="2012-8-23";  
        List<String> result =getAllDatesBetweenDates(start, end);
        System.out.println(result);
    }                
    
}
