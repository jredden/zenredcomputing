package com.zenred.zenredcomputing.vizualization;


public class VisualizationCentricPostsResponse {
	
	private boolean ownedByUser;
	private int order;
	private String emailAddress;
	private String Subject;
	private String Title;
	private String Content;
	private String Stamp;
	private int Id;
	
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
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getStamp() {
		return Stamp;
	}
	public void setStamp(String stamp) {
		Stamp = stamp;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	@Override
	public String toString() {
		return "VisualizationCentricPostsResponse [ownedByUser=" + ownedByUser
				+ ", order=" + order + ", emailAddress=" + emailAddress
				+ ", Subject=" + Subject + ", Title=" + Title + ", Content="
				+ Content + ", Stamp=" + Stamp + ", Id=" + Id + "]";
	}


}
