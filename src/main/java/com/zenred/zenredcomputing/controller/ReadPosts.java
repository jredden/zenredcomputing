package com.zenred.zenredcomputing.controller;

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

public class ReadPosts implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");

		String emailAddress = request.getParameter("emailAddress");
		String subject = request.getParameter("category");
		SubjectsDAO subjectsDAO = new SubjectsDAO();
		if (!subjectsDAO.isUserAssociatedToSubject(subject, emailAddress)) {
			subjectsDAO.associateUserToSubject(subject, emailAddress);
		}
		PostsDAO postsDAO = new PostsDAO();
		List<VisualizationCentricPostsResponse> listVisualizationCentricPostsResponses = DomainTransferOperation
				.operation(postsDAO, emailAddress, subject);
		GeneralTopicResponse generalTopicResponse = new GeneralTopicResponse();
		generalTopicResponse
				.setVisualizationCentricPosts(listVisualizationCentricPostsResponses);
		ModelAndView modelAndView = new ModelAndView(new GeneralTopicView());
		modelAndView
				.addObject(GeneralTopicView.JSON_ROOT, generalTopicResponse);

		return modelAndView;
	}

}
