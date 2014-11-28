package com.zenred.zenredcomputing.vizualization;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PostResponse")
public class PostResponse {

	private VisualizationCentricPostsResponse visualizationCentricPostsResponse;
	
	private String status;

	public VisualizationCentricPostsResponse getVisualizationCentricPostsResponse() {
		return visualizationCentricPostsResponse;
	}

	public void setVisualizationCentricPostsResponse(
			VisualizationCentricPostsResponse visualizationCentricPostsResponse) {
		this.visualizationCentricPostsResponse = visualizationCentricPostsResponse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PostResponse [visualizationCentricPostsResponse="
				+ visualizationCentricPostsResponse + ", status=" + status
				+ "]";
	}
	
}
