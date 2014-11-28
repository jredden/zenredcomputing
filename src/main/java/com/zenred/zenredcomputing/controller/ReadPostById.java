package com.zenred.zenredcomputing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.zenredcomputing.controller.json.PostView;
import com.zenred.zenredcomputing.domain.DomainTransfer;
import com.zenred.zenredcomputing.domain.Posts;
import com.zenred.zenredcomputing.domain.PostsDAO;
import com.zenred.zenredcomputing.vizualization.PostResponse;
import com.zenred.zenredcomputing.vizualization.VisualizationCentricPostsResponse;

public class ReadPostById implements Controller {

	/**
	 * 
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		String id = request.getParameter("PostId");
		System.out.println("POST_ID:"+id);
		if(null == id){
			PostResponse postResponse = new PostResponse();
			postResponse.setStatus("fail");
			ModelAndView modelAndView = new ModelAndView(new PostView());
			modelAndView.addObject(PostView.JSON_ROOT, postResponse);
			return(modelAndView);
		}
		PostsDAO postsDAO = new PostsDAO();
		Posts post = postsDAO.readPostById(id);
		VisualizationCentricPostsResponse visualizationCentricPostsResponse = DomainTransfer.postToPostResponse(post);
		PostResponse postResponse = new PostResponse();
		postResponse.setStatus("success");
		postResponse.setVisualizationCentricPostsResponse(visualizationCentricPostsResponse);
		ModelAndView modelAndView = new ModelAndView(new PostView());
		modelAndView.addObject(PostView.JSON_ROOT, postResponse);
		return(modelAndView);
	}

}
