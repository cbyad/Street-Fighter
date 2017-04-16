package com.cps.exception;

@SuppressWarnings("serial")
public class PreConditionError extends Exception{

	
	public PreConditionError(String msg) {
		super(msg);
	}
	
	
	@Override
	public String getMessage(){ return super.getMessage();}

}
