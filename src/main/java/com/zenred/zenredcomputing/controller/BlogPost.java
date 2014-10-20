package com.zenred.zenredcomputing.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class BlogPost implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		Enumeration<String> enumeration =  request.getParameterNames();
		while(enumeration.hasMoreElements()){
			System.out.println("ParamName:"+enumeration.nextElement());
		}
		
		String editString = request.getParameter("editor1");
		System.out.println("BlogPost:"+editString);
		String emailAddress = BlogPost.parseControlField(editString, "From:", "</p>");
		String subject = BlogPost.parseControlField(editString, "Subject:", "</p>");
		String content = BlogPost.parseControlField(editString, "Entry:", editString.length());
		
		return null;
	}
	
	protected static String parseControlField(String entirePost, String startString, String endString){
		String result = "";
		
		int istart = entirePost.indexOf(startString) + startString.length();
		int iend = entirePost.indexOf(endString, istart);
		result = entirePost.substring(istart, iend);
		return result;
	}

	protected static String parseControlField(String entirePost, String startString, int endPos){
		String result = "";
		
		int istart = entirePost.indexOf(startString) + startString.length();
		result = entirePost.substring(istart, endPos);
		return result;
	}
}
