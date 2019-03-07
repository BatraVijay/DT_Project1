package com.backend.model;

public class MyError {
	private String message;
	
	public MyError(String msg){
	this.message=msg;	
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
