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
			visualizationCentricPostsResponse.setEmailAddress(posts.getState().equals("true")? emailAddress : posts.getEmailAddress());
			visualizationCentricPostsResponse.setStamp(posts.getDatestamp());
			visualizationCentricPostsResponses.add(visualizationCentricPostsResponse);
		}
		return visualizationCentricPostsResponses;
	}
	
	/**
	 * 
	 * @param post
	 * @return visualized post
	 */
	public static VisualizationCentricPostsResponse postToPostResponse(Posts post, String subject){
		VisualizationCentricPostsResponse visualizationCentricPostsResponse = new VisualizationCentricPostsResponse();
		visualizationCentricPostsResponse.setContent(post.getContent());
		visualizationCentricPostsResponse.setOrder(post.getPosts_id());
		visualizationCentricPostsResponse.setOwnedByUser(true);
		visualizationCentricPostsResponse.setSubject(subject);
		visualizationCentricPostsResponse.setTitle(post.getTitle());
		visualizationCentricPostsResponse.setId(post.getPosts_id());
		return visualizationCentricPostsResponse;
	}
}
