package com.springboot.rest.exception;

import java.util.Date;

public class ErrorDetails {
	private Date date;
	private String message;
	private String errorDetails;
	private String errorCode;
	
	
	
	public ErrorDetails() {
		super();
	}
	
	public ErrorDetails(Date date, String message, String errorDetails, String errorCode) {
		this.date = date;
		this.message = message;
		this.errorDetails = errorDetails;
		this.errorCode = errorCode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
