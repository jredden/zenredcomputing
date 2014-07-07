package com.zenred.zenredcomputing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.zenredcomputing.controller.json.SubjectsView;
import com.zenred.zenredcomputing.domain.SubjectsDAO;
import com.zenred.zenredcomputing.vizualization.SubjectsResponse;

public class Category implements Controller {
	
	private SubjectsDAO subjectsDAO;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		subjectsDAO = new SubjectsDAO();
		String [] subjects = subjectsDAO.readSubjects().toArray(new String []{});
		SubjectsResponse subjectsResponse = new SubjectsResponse();
		subjectsResponse.setSubjects(subjects);
		ModelAndView modelAndView = new ModelAndView(new SubjectsView());
		modelAndView.addObject(SubjectsView.JSON_ROOT, subjectsResponse);
		return modelAndView;
	}

}
