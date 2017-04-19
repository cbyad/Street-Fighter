package com.cps.exception;

@SuppressWarnings("serial")
public class PostConditionError extends ContractError{

	
	public PostConditionError(String msg) {
		super("PostCondition failed: " + msg);
	}
	

}
