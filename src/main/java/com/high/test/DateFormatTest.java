package com.high.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.high.formatter.WebDateFormatter;

public class DateFormatTest {

	@Test
	public void dmeo1(){
		//2017-05-31 00:00:00
		WebDateFormatter formatter = new WebDateFormatter("YYYY-MM-DD hh:mm:ss");
		try {
			Date date = formatter.parse("2017-05-31 00:00:00", null);
			System.out.println(date);
		} catch (ParseException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
	
	@Test
	public void deom2(){
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		try {
			Date date = format.parse("2017-05-31 00:00:00");
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
