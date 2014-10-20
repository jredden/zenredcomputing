package com.zenred.zenredcomputing.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParseControlTest {
	
	private static String editString = "BlogPost:<p>From: johnredden@aol.com</p>"
			+ "<p>Subject: Computer History</p>"
			+ "<p>Start Entry: &nbsp;Test Test</p>";
	
	private static String editString2 = "<p>From: johnredden@aol.com</p>" +

	"<p>Subject: Inclusive Software Engineering</p>"
			+ "<p>Start Entry: &nbsp;test</p>" +
			"<p>and another line.</p>" +
			"<p>and another.</p>";

	@Test
	public void test() {
		String answer1 = BlogPost.parseControlField(editString, "From:", "</p>");
		System.out.println("Answer1:"+answer1);
		String answer2 = BlogPost.parseControlField(editString, "Subject:", "</p>");
		System.out.println("Answer2:"+answer2);
		String answer3 = BlogPost.parseControlField(editString, "Entry:", editString.length());
		System.out.println("Answer3:"+answer3);
	}

	@Test
	public void test2() {
		String answer1 = BlogPost.parseControlField(editString2, "From:", "</p>");
		System.out.println("Answer1:"+answer1);
		String answer2 = BlogPost.parseControlField(editString2, "Subject:", "</p>");
		System.out.println("Answer2:"+answer2);
		String answer3 = BlogPost.parseControlField(editString2, "Entry:", editString2.length());
		System.out.println("Answer3:"+answer3);
	}
}
