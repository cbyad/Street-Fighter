package com.cps.exception;

@SuppressWarnings("serial")
public class PostConditionError extends Exception{

	
	public PostConditionError(String msg) {
		super(msg);
	}
	

	@Override
	public String getMessage(){ return super.getMessage();}
	


}
