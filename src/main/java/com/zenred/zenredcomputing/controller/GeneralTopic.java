package com.zenred.zenredcomputing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.zenredcomputing.controller.json.GeneralTopicView;
import com.zenred.zenredcomputing.domain.DateOperation;
import com.zenred.zenredcomputing.domain.DomainTransfer;
import com.zenred.zenredcomputing.domain.Posts;
import com.zenred.zenredcomputing.domain.PostsDAO;
import com.zenred.zenredcomputing.vizualization.GeneralTopicResponse;
import com.zenred.zenredcomputing.vizualization.VisualizationCentricPostsResponse;

public class GeneralTopic implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String emailAddress = request.getParameter("emailAddress");
		String subject = request.getParameter("subject");
		PostsDAO postsDAO = new PostsDAO();
		List<Posts> notUsersPosts = postsDAO.readNonUserPostsWithinSubject(emailAddress, subject);
		List<Posts> usersPosts = postsDAO.readUserPostsWithinSubject(emailAddress, subject);
		DateOperation<Posts> dateOperation = new DateOperation<Posts>();
		List<Posts> combinedList = dateOperation.combineLists(notUsersPosts,
				usersPosts);
		GeneralTopicResponse generalTopicResponse = new GeneralTopicResponse();
		generalTopicResponse.setVisualizationCentricPosts(DomainTransfer.postsToPostsResponse(combinedList, subject));
		ModelAndView modelAndView = new ModelAndView(new GeneralTopicView());
		modelAndView.addObject(GeneralTopicView.JSON_ROOT, generalTopicResponse);
		return modelAndView;
	}

}
