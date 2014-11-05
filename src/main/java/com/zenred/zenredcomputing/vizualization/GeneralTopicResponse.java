package com.zenred.zenredcomputing.vizualization;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("TopicResponse")
public class GeneralTopicResponse {

	private List<VisualizationCentricPostsResponse> visualizationCentricPosts;

	public List<VisualizationCentricPostsResponse> getVisualizationCentricPosts() {
		return visualizationCentricPosts;
	}

	public void setVisualizationCentricPosts(
			List<VisualizationCentricPostsResponse> visualizationCentricPosts) {
		this.visualizationCentricPosts = visualizationCentricPosts;
	}

	@Override
	public String toString() {
		return "GeneralTopicResponse [visualizationCentricPosts="
				+ visualizationCentricPosts + "]";
	}
	
	
}
