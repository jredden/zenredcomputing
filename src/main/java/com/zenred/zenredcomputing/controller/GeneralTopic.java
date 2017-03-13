package com.zenred.zenredcomputing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.zenredcomputing.controller.json.GeneralTopicView;
import com.zenred.zenredcomputing.domain.PostsDAO;
import com.zenred.zenredcomputing.vizualization.DomainTransferOperation;
import com.zenred.zenredcomputing.vizualization.GeneralTopicResponse;
import com.zenred.zenredcomputing.vizualization.VisualizationCentricPostsResponse;

public class GeneralTopic implements Controller {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String emailAddress = request.getParameter("emailAddress");
		log.info("EMAIL ADDRESS:"+emailAddress+":");
		String subject = request.getParameter("subject");
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
