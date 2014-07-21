package com.zenred.zenredcomputing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.zenredcomputing.domain.Posts;
import com.zenred.zenredcomputing.domain.PostsDAO;

public class GeneralTopic implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String emailAddress = request.getParameter("email");
		String subject = request.getParameter("subject");
		PostsDAO postsDAO = new PostsDAO();
		List<Posts> notUsersPosts = postsDAO.readNonUserPostsWithinSubject(emailAddress, subject);
		List<Posts> usersPosts = postsDAO.readUserPostsWithinSubject(emailAddress, subject);
		
		return null;
	}

}
