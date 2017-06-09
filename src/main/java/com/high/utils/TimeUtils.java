package com.high.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

	public static String formatTimeForSolr(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String time = sdf.format(new Date());
		return time; 
	}
	
	/**
	 * 解析CST格式的时间
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Date paseCSTTime(String dateString) throws ParseException{
		dateString = "Sat Jul 01 08:33:55 CST 2017";
		DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US); 
		return df.parse(dateString);
	}
	
	public static void main(String[] args) throws ParseException {
		Date d = new Date();
		System.out.println(d);
		String date = formatTimeForSolr(d);
		System.out.println(date);
		
		
		String s = "Thu Jul 22 23:58:32 CST 2013";  
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        System.out.println(sdf.format(df.parse(s)));  
		
		
//		Date date2 = new Date("Sat Jun 03 19:53:31 CST 2017");
		try {
			Date time = paseCSTTime("");
			System.out.println(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
//		System.out.println(date2);
	}
}
