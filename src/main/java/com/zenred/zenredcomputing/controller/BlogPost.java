package com.zenred.zenredcomputing.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.zenredcomputing.controller.json.GeneralTopicView;
import com.zenred.zenredcomputing.domain.PostsDAO;
import com.zenred.zenredcomputing.domain.SubjectsDAO;
import com.zenred.zenredcomputing.vizualization.DomainTransferOperation;
import com.zenred.zenredcomputing.vizualization.GeneralTopicResponse;
import com.zenred.zenredcomputing.vizualization.VisualizationCentricPostsResponse;

public class BlogPost implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		System.out.println("BlogPost_Entry:");
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			System.out.println("ParamName:" + enumeration.nextElement());
		}

		String editString = request.getParameter("editor1");
		String emailAddress = request.getParameter("emailAddress");
		String subject = request.getParameter("category");
		
		String title = BlogPost.parseControlField(editString, "Title:", "</p>");
		System.out.println("BlogPost_title:" + title);
		
		SubjectsDAO subjectsDAO = new SubjectsDAO();
		if(!subjectsDAO.isUserAssociatedToSubject(subject, emailAddress)){
			subjectsDAO.associateUserToSubject(subject, emailAddress);
		}
		PostsDAO postsDAO = new PostsDAO();
		System.out.println("Here_000:"+editString+"::"+emailAddress+"::"+title+"::"+subject+"::");
		postsDAO.addPost(editString, title, emailAddress, subject);
		System.out.println("Here_00");
		List<VisualizationCentricPostsResponse> listVisualizationCentricPostsResponses = DomainTransferOperation
				.operation(postsDAO, emailAddress, subject);
		GeneralTopicResponse generalTopicResponse = new GeneralTopicResponse();
		System.out.println("Here_0");
		generalTopicResponse
				.setVisualizationCentricPosts(listVisualizationCentricPostsResponses);
		System.out.println("Here_1");
		ModelAndView modelAndView = new ModelAndView(new GeneralTopicView());
		modelAndView
				.addObject(GeneralTopicView.JSON_ROOT, generalTopicResponse);
		System.out.println("Here_2");
		return modelAndView;
	}

	protected static String parseControlField(String entirePost,
			String startString, String endString) {
		String result = "";

		int istart = entirePost.indexOf(startString) + startString.length();
		int iend = entirePost.indexOf(endString, istart);
		result = entirePost.substring(istart, iend);
		return result;
	}

	protected static String parseControlField(String entirePost,
			String startString, int endPos) {
		String result = "";

		int istart = entirePost.indexOf(startString) + startString.length();
		result = entirePost.substring(istart, endPos);
		return result;
	}
}
