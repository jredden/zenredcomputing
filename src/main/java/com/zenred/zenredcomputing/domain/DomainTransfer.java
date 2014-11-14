package com.zenred.zenredcomputing.domain;

import java.util.ArrayList;
import java.util.List;

import com.zenred.zenredcomputing.vizualization.VisualizationCentricPostsResponse;

public class DomainTransfer {
	
	/**
	 * transfer from domain object to visualization centric object
	 * 
	 * @param postsList
	 * @return
	 */
	public static List<VisualizationCentricPostsResponse> postsToPostsResponse(List<Posts> postsList, String subject, String emailAddress){
		List<VisualizationCentricPostsResponse> visualizationCentricPostsResponses = new ArrayList<VisualizationCentricPostsResponse>();
		for(Posts posts: postsList){
			VisualizationCentricPostsResponse visualizationCentricPostsResponse = new VisualizationCentricPostsResponse();
			visualizationCentricPostsResponse.setContent(posts.getContent());
			visualizationCentricPostsResponse.setOrder(posts.getPosts_id());
			visualizationCentricPostsResponse.setOwnedByUser(posts.getState().equals("true")? true: false);
			visualizationCentricPostsResponse.setSubject(subject);
			visualizationCentricPostsResponse.setTitle(posts.getTitle());
			visualizationCentricPostsResponse.setId(posts.getPosts_id());
			visualizationCentricPostsResponse.setEmailAddress(emailAddress);
			visualizationCentricPostsResponses.add(visualizationCentricPostsResponse);
		}
		return visualizationCentricPostsResponses;
	}
}
