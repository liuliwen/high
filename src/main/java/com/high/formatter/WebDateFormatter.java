package com.high.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class WebDateFormatter implements Formatter<Date> {

	
	private String datePattern="YYYY-MM-DD hh:mm:ss";
	private SimpleDateFormat dateFormat;
	
	public WebDateFormatter(String datePattern){
		this.datePattern = datePattern;
		dateFormat = new SimpleDateFormat(datePattern);
	}
	
	@Override
	public String print(Date date, Locale arg1) {
		return dateFormat.format(date);
	}

	@Override
	public Date parse(String arg0, Locale arg1) throws ParseException {
		try {
			return dateFormat.parse(arg0);
		} catch (Exception e) {
			System.out.println("error!!!!!!!!!!!!!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
