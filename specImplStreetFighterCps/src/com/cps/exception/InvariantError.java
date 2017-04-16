package com.cps.exception;

@SuppressWarnings("serial")
public class InvariantError extends Exception{

	
	
	public InvariantError(String msg) {
		super(msg);
		
	}
	
	@Override
	public String getMessage(){ return super.getMessage();}
	

}
