package com.zenred.zenredcomputing.vizualization;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("createMessageResponse")
public class CreateLoginResponse {
	
	private String theMessage;
	private String emailAddress;

	public String getTheMessage() {
		return theMessage;
	}

	public void setTheMessage(String theMessage) {
		this.theMessage = theMessage;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "CreateLoginResponse [theMessage=" + theMessage
				+ ", emailAddress=" + emailAddress + "]";
	}
	

}
