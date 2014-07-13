package com.zenred.zenredcomputing.vizualization;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("basicMesage")
public class BasicMessageResponse {
	
	private String theMessage;
	private List<String> questionList;
	private Integer questionNumber;
	private String secondQuestion;
	private String emailAddress;


	public String getTheMessage() {
		return theMessage;
	}

	public void setTheMessage(String theMessage) {
		this.theMessage = theMessage;
	}

	public List<String> getQuestionList() {
		return questionList;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public String getSecondQuestion() {
		return secondQuestion;
	}

	public void setQuestionList(List<String> questionList) {
		this.questionList = questionList;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	public void setSecondQuestion(String secondQuestion) {
		this.secondQuestion = secondQuestion;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "BasicMessageResponse [theMessage=" + theMessage
				+ ", questionList=" + questionList + ", questionNumber="
				+ questionNumber + ", secondQuestion=" + secondQuestion
				+ ", emailAddress=" + emailAddress + "]";
	}
	
	

}
