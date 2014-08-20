package com.zenred.zenredcomputing.vizualization;


public class VisualizationCentricPostsResponse {
	
	private boolean ownedByUser;
	private int order;
	private String Subject;
	private String Title;
	private String Content;
	public boolean isOwnedByUser() {
		return ownedByUser;
	}
	public void setOwnedByUser(boolean ownedByUser) {
		this.ownedByUser = ownedByUser;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
	@Override
	public String toString() {
		return "VisualizationCentricPosts [ownedByUser=" + ownedByUser
				+ ", order=" + order + ", Subject=" + Subject + ", Title="
				+ Title + ", Content=" + Content + "]";
	}


}
