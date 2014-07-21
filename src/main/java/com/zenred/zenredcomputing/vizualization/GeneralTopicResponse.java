package com.zenred.zenredcomputing.vizualization;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("TopicRespone")
public class GeneralTopicResponse {

	private List<VisualizationCentricPosts> visualizationCentricPosts;

	public List<VisualizationCentricPosts> getVisualizationCentricPosts() {
		return visualizationCentricPosts;
	}

	public void setVisualizationCentricPosts(
			List<VisualizationCentricPosts> visualizationCentricPosts) {
		this.visualizationCentricPosts = visualizationCentricPosts;
	}

	@Override
	public String toString() {
		return "GeneralTopicResponse [visualizationCentricPosts="
				+ visualizationCentricPosts + "]";
	}
	
	
}
