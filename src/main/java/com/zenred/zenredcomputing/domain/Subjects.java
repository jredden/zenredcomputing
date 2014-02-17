package com.zenred.zenredcomputing.domain;

public class Subjects {

	private Integer Subjects_id;
	private Integer Subjects_code;
	private String Subjects_name;
	
	public Integer getSubjects_id() {
		return Subjects_id;
	}
	public void setSubjects_id(Integer subjects_id) {
		Subjects_id = subjects_id;
	}
	public Integer getSubjects_code() {
		return Subjects_code;
	}
	public void setSubjects_code(Integer subjects_code) {
		Subjects_code = subjects_code;
	}
	public String getSubjects_name() {
		return Subjects_name;
	}
	public void setSubjects_name(String subjects_name) {
		Subjects_name = subjects_name;
	}
	@Override
	public String toString() {
		return "Subjects [Subjects_id=" + Subjects_id + ", Subjects_code="
				+ Subjects_code + ", Subjects_name=" + Subjects_name + "]";
	}
	
	
}
