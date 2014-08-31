package com.zenred.zenredcomputing.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.SizeSequence;

import com.zenredcomputing.util.ListOperations;

public class  DateOperation <Obj>{
	
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
	
	/**
	 * Obj generic must implement DateStampIF
	 * listOne and listTwo are already assumed to be in sorted order
	 * from newest to oldest
	 * 
	 * @param listOne
	 j @return
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public
	List<Obj> combineLists(List<Obj> listOne, List<Obj> listTwo){
		List<Obj> combinedList = new ArrayList<Obj>();
		
		int sizeOne = listOne.size();
		int sizeTwo = listTwo.size();
		int indexOne = 0;
		int indexTwo = 0;
		boolean done = true;
		
		if(0 == listOne.size() && 0 == listTwo.size()){
			return combinedList;  // initial state, nothing there
		}
		if(0 == listOne.size()){
			Obj obj2 = listTwo.get(indexTwo);
			DateStampIF dateStampIF2 = (DateStampIF)obj2;
			StateIF stateIF2 = (StateIF)obj2;
			for(; indexTwo < sizeTwo; indexTwo++){
				((StateIF)listTwo.get(indexTwo)).setState("true");
				combinedList.add(listTwo.get(indexTwo));
			}
			return combinedList;	// first degenerate case
		}
		if(0 == listTwo.size()){
			Obj obj1 = listOne.get(indexOne);
			DateStampIF dateStampIF1 = (DateStampIF)obj1;
			StateIF stateIF1 = (StateIF)obj1;
			for(; indexOne < sizeOne; indexOne++){
				((StateIF)listOne.get(indexOne)).setState("false");
				combinedList.add(listOne.get(indexOne));
			}
			return combinedList;	// second degenerate case
		}

		Obj obj1 = listOne.get(indexOne);
		Obj obj2 = listTwo.get(indexTwo);
		DateStampIF dateStampIF1 = (DateStampIF)obj1;
		DateStampIF dateStampIF2 = (DateStampIF)obj2;
		StateIF stateIF1 = (StateIF)obj1;
		StateIF stateIF2 = (StateIF)obj2;

		do{
			if(isDateOneLessThanDateTwo(dateStampIF1.getDatestamp(), dateStampIF2.getDatestamp())){
				stateIF1.setState("false");
				combinedList.add(obj1);
				++indexOne;
				if(indexOne >= sizeOne){
					for(; indexTwo < sizeTwo; indexTwo++){
						((StateIF)listTwo.get(indexTwo)).setState("true");
						combinedList.add(listTwo.get(indexTwo));
					}
					done = false;
					break;
				}
				obj1 = listOne.get(indexOne);
				dateStampIF1 = (DateStampIF)obj1;
				stateIF1 = (StateIF)obj1;
			}
			if(isDateOneLessThanDateTwo(dateStampIF2.getDatestamp(), dateStampIF1.getDatestamp())){
				stateIF2.setState("true");
				combinedList.add(obj2);
				++indexTwo;
				if(indexTwo >= sizeTwo){
					for(; indexOne < sizeOne; indexOne++){
						((StateIF)listOne.get(indexOne)).setState("false");
						combinedList.add(listOne.get(indexOne));
					}
					done = false;
					break;
				}
				obj2 = listTwo.get(indexTwo);
				dateStampIF2 = (DateStampIF)obj2;
				stateIF2 = (StateIF)obj2;
			}
		}while(done);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List rawList = new ArrayList(combinedList.size());
		for (Obj object: combinedList){
			rawList.add(object);
		}
		return ListOperations.reverseList(rawList);
	}

}
