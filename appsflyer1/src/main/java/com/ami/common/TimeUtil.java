package com.ami.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeUtil {

	/** 年月日 时分, 格式如: 2011-01-11 01:10 */
	private static final DateFormat ymdhmFormat = new SimpleDateFormat("yyyy_MM_dd");
	private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) throws Exception {
		
		long tiem1 = getYMDHMTime("2016_07_01");
		System.out.println(tiem1);
		
		String time = formatYMDHMTime(tiem1-24*60*60*1000);
		System.out.println(time);

	}
	
	/**
	 * 以格式{@link TimeUtils#ymdhmFormat}解析数据，返回其表示的毫秒数
	 * 
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static long getYMDHMTime(String source) {
		try{
			Date date = ymdhmFormat.parse(source);
			return date.getTime();
		}catch(Exception e){
			e.printStackTrace();
			return 0l;
		}
	}
	
	/**
	 * 返回 <b>年份-月份-日期 小时:分钟</b> 格式的时间. 例如: 2012-12-24 15:01
	 * 
	 * @param time
	 * @return
	 */
	public static String formatYMDHMTime(long time) {
		return ymdhmFormat.format(time);
	}
	
	public static String formatLongTOStr(long time){
		return format.format(time);
	}
	public static String format(Date date){
		return format.format(date);
	}
	public static String formatDate(Date date){
		return formatDate.format(date);
	}
	public static Date formatStrTODate(String date) {
		try {
			return ymdhmFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date formatStrTODateff(String date) {
		try {
			return formatDate.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date formatAddDayReDate(Date date,int day){
		Calendar calendar=Calendar.getInstance();  
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,day);//天加减
		return calendar.getTime();
	}
	public static String formatAddDay(Date date,int day){
		Calendar calendar=Calendar.getInstance();  
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,day);//天加减
		return formatDate.format(calendar.getTime());
	}
	public static String formatChangeYmdhmDay(Date date,int day){
		Calendar calendar=Calendar.getInstance();  
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,day);//天加减
		return ymdhmFormat.format(calendar.getTime());
	}
	public static String formatAdd(Date date,int minute){
		   Calendar calendar=Calendar.getInstance();  
		   calendar.setTime(date);
		   calendar.add(Calendar.MINUTE,minute);//让分钟加减 
		   return format.format(calendar.getTime());
	}
	
}
