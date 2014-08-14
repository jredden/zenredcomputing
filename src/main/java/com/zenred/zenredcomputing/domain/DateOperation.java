package com.zenred.zenredcomputing.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateOperation {
	
	/**
	 * mysql date format
	 * 
	 * @param firstOne
	 * @param secondOne
	 * @return
	 */
	public static Boolean isDateOneLessThanDateTwo(String firstOne, String secondOne){
		Boolean answer = false;
		Date newDateFirst = null;
		Date newDateSecond = null;
		SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			newDateFirst = sdf.parse(firstOne);
		} catch (ParseException pe) {
			pe.printStackTrace();
			throw new RuntimeException(pe.getMessage());
		}
		try {
			newDateSecond = sdf.parse(secondOne);
		} catch (ParseException pe) {
			pe.printStackTrace();
			throw new RuntimeException(pe.getMessage());
		}
		
		Calendar calendarFirst = Calendar.getInstance();
		calendarFirst.setTime(newDateFirst);
		Calendar calendarSecond = Calendar.getInstance();
		calendarFirst.setTime(newDateSecond);
		
		if(calendarFirst.before(calendarSecond)){
			answer = true;
		}
		return answer;
	}

}
