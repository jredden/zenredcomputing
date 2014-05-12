package com.zenred.zenredcomputing.domain;

public class Posts {

	private Integer Posts_id;
	private String Title;
	private String Content;
	private String Datestamp;
	
	public Integer getPosts_id() {
		return Posts_id;
	}
	public void setPosts_id(Integer posts_id) {
		Posts_id = posts_id;
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
	
	public String getDatestamp() {
		return Datestamp;
	}
	public void setDatestamp(String datestamp) {
		Datestamp = datestamp;
	}
	@Override
	public String toString() {
		return "Posts [Posts_id=" + Posts_id + ", Title=" + Title
				+ ", Content=" + Content + ", Datestamp=" + Datestamp + "]";
	}
	
	
}
