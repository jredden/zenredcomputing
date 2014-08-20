package com.zenred.zenredcomputing.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MysqlDateTest {

	// 2014-07-12 17:55:28

	/*
	 * java.util.Date dt = new java.util.Date(); java.text.SimpleDate Posts
	 * posts_0_0 = new Posts(); posts_0_0.setContent("Test 0 0");
	 * posts_0_0.setDatestamp("2012-02-03 06:30:00");
	 * posts_0_0.setPosts_id(1000); posts_0_0.setTitle("test 0 0");
	 * postListOne.add(posts_0_0); Format sdf = new
	 * java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String currentTime =
	 * sdf.format(dt);
	 * 
	 * 
	 * 
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); String
	 * aDateString = "2012-02-02"; Date date = sdf.parse(aDateString);
	 * System.out.println("reference date:"+date);
	 * 
	 * Calendar cal = Calendar.getInstance(); cal.setTime(date);
	 * cal.add(Calendar.HOUR, 36);
	 * System.out.println("added one and half days to reference date: "
	 * +cal.getTime());
	 * 
	 * java.util.Date dt = new java.util.Date();
	 * 
	 * java.text.SimpleDateFormat sdf = new
	 * java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * 
	 * String newDateString = "2012-02-03 06:30:00.0"; sdf = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); Date newDate =
	 * sdf.parse(newDateString);
	 * System.out.println("new date to compare with reference date : "+newDate);
	 * 
	 * Calendar newCal = Calendar.getInstance(); newCal.setTime(newDate);
	 * 
	 * if(cal.after(newCal)){
	 * System.out.println("date is greater than reference that."); }else
	 * if(cal.before(newCal)){
	 * System.out.println("date is lesser than reference that."); }else{
	 * System.out.println("date is equal to reference that."); }
	 */
	static List<Posts> postListOne = new ArrayList<Posts>();
	static List<Posts> postListTwo = new ArrayList<Posts>();
	static {
		
		Posts posts_0_0 = new Posts();
		posts_0_0.setContent("Test 0 0");
		posts_0_0.setDatestamp("2012-02-03 06:30:00");
		posts_0_0.setPosts_id(1000);
		posts_0_0.setTitle("test 0 0");
		postListOne.add(posts_0_0);
		Posts posts_0_1 = new Posts();
		posts_0_1.setContent("Test 0 1");
		posts_0_1.setDatestamp("2012-02-05 06:30:00");
		posts_0_1.setPosts_id(1010);
		posts_0_1.setTitle("test 0 1");
		postListOne.add(posts_0_1);
		Posts posts_0_2 = new Posts();
		posts_0_2.setContent("Test 0 2");
		posts_0_2.setDatestamp("2014-02-03 18:30:00");
		posts_0_2.setPosts_id(1020);
		posts_0_2.setTitle("test 0 2");
		postListOne.add(posts_0_2);

		Posts posts_1_0 = new Posts();
		posts_1_0.setContent("Test 1 0");
		posts_1_0.setDatestamp("2012-02-03 06:30:00");
		posts_1_0.setPosts_id(1000);
		posts_1_0.setTitle("test 1 0");
		postListTwo.add(posts_1_0);
		Posts posts_1_1 = new Posts();
		posts_1_1.setContent("Test 0 1");
		posts_1_1.setDatestamp("2012-02-05 06:30:00");
		posts_1_1.setPosts_id(1010);
		posts_1_1.setTitle("test 1 1");
		postListTwo.add(posts_1_1);
		Posts posts_1_2 = new Posts();
		posts_1_2.setContent("Test 1 2");
		posts_1_2.setDatestamp("2014-02-03 18:30:00");
		posts_1_2.setPosts_id(1020);
		posts_1_2.setTitle("test 1 2");
		postListTwo.add(posts_1_2);
		Posts posts_1_3 = new Posts();
		posts_1_3.setContent("Test 1 3");
		posts_1_3.setDatestamp("2014-06-03 18:30:00");
		posts_1_3.setPosts_id(1030);
		posts_1_3.setTitle("test 0 3");
		postListTwo.add(posts_1_3);
	}

	@Test
	public void test() {
		DateOperation<Posts> dateOperation = new DateOperation<Posts>();
		List<Posts> combinedList = dateOperation.combineLists(postListOne,
				postListTwo);
		System.out.println(combinedList);
	}

}
